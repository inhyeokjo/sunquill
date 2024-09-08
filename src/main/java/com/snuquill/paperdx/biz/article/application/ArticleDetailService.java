package com.snuquill.paperdx.biz.article.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.article.domain.Article;
import com.snuquill.paperdx.biz.article.domain.ArticleRepository;
import com.snuquill.paperdx.biz.article.domain.Author;
import com.snuquill.paperdx.biz.article.domain.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleDetailService {

	private final ArticleRepository articleRepository;
	private final AuthorRepository authorRepository;

	//TODO Read Only = True 설정
	@Transactional
	public ArticleDetailDto getArticleDetail(Long articleId) {
		Article article = articleRepository.findById(articleId).orElseThrow();
		article.upViewCount();
		Long authorId = article.getAuthorId();
		Author author = authorRepository.getAuthor(authorId);
		articleRepository.save(article);
		return new ArticleDetailDto(
			article.getMainPicture().getUrl(),
			article.getCategory(),
			article.getTitleList(),
			article.getContentsList(),
			author);
	}

	public String getArticleTitle(Long articleId) {
		Article article = articleRepository.findById(articleId).orElseThrow();

		return article.getTitle();

	}
}
