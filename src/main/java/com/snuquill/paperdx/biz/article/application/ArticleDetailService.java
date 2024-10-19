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
		return new ArticleDetailDto(
			article.getMainPicture().getUrl(),
			article.getCategory(),
			article.getTitle(),
			article.getContents(),
			author);
	}

	public String getArticleTitle(Long articleId) {
		Article article = findArticleById(articleId);

		return article.getTitle();

	}

	@Transactional
	public void modifyArticle(Long articleId, ArticleRequestDto.ModifyRequest modifyRequest) {
		Article article = findArticleById(articleId);

		Article newArticle = modifyRequest.mapToDomain(article.getAuthorId(), article.getAuthorName());

		article.apply(newArticle);
		articleRepository.save(article);
	}

	@Transactional
	public void uploadArticle(ArticleRequestDto.UploadRequest uploadRequest) {
		Author author = authorRepository.getAuthor(uploadRequest.authorId());
		Article article = uploadRequest.mapToDomain(author.getName());

		articleRepository.save(article);
	}

	private Article findArticleById(Long articleId) {
		return articleRepository.findById(articleId)
			.orElseThrow(() -> new DataNotFoundException("Article이 존재하지 않습니다. ID: " + articleId));
	}
}
