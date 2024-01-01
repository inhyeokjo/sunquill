package com.snuquill.paperdx.biz.article.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.snuquill.paperdx.biz.article.domain.Article;
import com.snuquill.paperdx.biz.article.domain.ArticleRepository;
import com.snuquill.paperdx.biz.article.domain.Author;
import com.snuquill.paperdx.biz.article.domain.AuthorRepository;
import com.snuquill.paperdx.biz.article.domain.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleLineService {

	private final ArticleRepository articleRepository;
	private final AuthorRepository authorRepository;

	public List<ArticleLineDto> getRecentArticleList(int count) {
		PageRequest countRequest = PageRequest.of(0, count, Sort.by("publishDate").descending());
		List<Article> articleList = articleRepository.findAll(countRequest).toList();
		Map<Long, Author> authorMap = getAuthorMap(articleList);

		return articleList.stream()
			.map(article -> ArticleLineDto.of(article, authorMap))
			.toList();
	}

	public List<ArticleLineDto> getCategoryArticlePage(Category category, int page) {
		int pageSize = 10;
		PageRequest countRequest = PageRequest.of(page, pageSize, Sort.by("publishDate").descending());
		List<Article> articleList = articleRepository.findByCategoryVisible(category, countRequest);
		Map<Long, Author> authorMap = getAuthorMap(articleList);

		return articleList.stream()
			.map(article -> ArticleLineDto.of(article, authorMap))
			.toList();
	}

	private Map<Long, Author> getAuthorMap(List<Article> articleList) {
		List<Long> authorIdList = articleList.stream()
			.map(Article::getAuthorId)
			.toList();
		List<Author> authorList = authorRepository.getAllAuthor(authorIdList);
		return makeAuthorMap(authorList);
	}

	private Map<Long, Author> makeAuthorMap(List<Author> authorList) {
		return authorList.stream().collect(Collectors.toMap(Author::getId, author -> author));
	}
}
