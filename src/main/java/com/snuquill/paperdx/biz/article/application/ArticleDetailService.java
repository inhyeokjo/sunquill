package com.snuquill.paperdx.biz.article.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.article.domain.Article;
import com.snuquill.paperdx.biz.article.domain.ArticleRepository;
import com.snuquill.paperdx.biz.article.domain.Author;
import com.snuquill.paperdx.biz.article.domain.AuthorRepository;
import com.snuquill.paperdx.biz.article.ui.dto.ArticleRequestDto;
import com.snuquill.paperdx.common.execption.biz.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleDetailService {

	private final ArticleRepository articleRepository;
	private final AuthorRepository authorRepository;

	@Transactional(readOnly = true)
	public ArticleDetailDto getArticleDetail(Long articleId) {
		Article article = findArticleById(articleId);
		article.upViewCount();
		Long authorId = article.getAuthorId();
		Author author = authorRepository.getAuthor(authorId);
		articleRepository.save(article);
		return ArticleDetailDto.from(article, author);
	}

	public String getArticleTitle(Long articleId) {
		Article article = findArticleById(articleId);

		return article.getTitle();

	}

	@Transactional
	public void modifyArticle(Long articleId, ArticleRequestDto.ModifyRequest modifyRequest) {
		Author author = authorRepository.getAuthor(modifyRequest.authorId());
		Article newArticle = modifyRequest.mapToDomain(author.getName());

		Article article = findArticleById(articleId);
		article.apply(newArticle);

		articleRepository.save(article);
	}

	@Transactional
	public void uploadArticle(ArticleRequestDto.UploadRequest uploadRequest) {
		Author author = authorRepository.getAuthor(uploadRequest.authorId());
		Article article = uploadRequest.mapToDomain(author.getName());

		articleRepository.save(article);
	}

	public void changeVisibility(Long articleId, boolean invisible) {
		Article article = findArticleById(articleId);
		if (invisible) {
			article.hide();
		} else {
			article.show();
		}
		articleRepository.save(article);
	}

	private Article findArticleById(Long articleId) {
		return articleRepository.findById(articleId)
			.orElseThrow(() -> new DataNotFoundException("Article이 존재하지 않습니다. ID: " + articleId));
	}
}
