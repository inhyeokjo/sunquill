package com.snuquill.paperdx.article.application;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.article.domain.Article;
import com.snuquill.paperdx.article.domain.ArticleRepository;
import com.snuquill.paperdx.article.domain.Author;
import com.snuquill.paperdx.article.domain.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleDetailService {

	private final ArticleRepository articleRepository;
	private final AuthorRepository authorRepository;

	public ArticleDetailDto getArticleDetail(Long articleId) {
		Article article = articleRepository.findById(articleId).orElseThrow();
		Long authorId = article.getAuthorId();
		Author author = authorRepository.getAuthor(authorId);
		return new ArticleDetailDto(
			article.getMainPicture().getUrl(),
			article.getCategory(),
			article.getTitleList(),
			article.getContentsList(),
			author);
	}
}
