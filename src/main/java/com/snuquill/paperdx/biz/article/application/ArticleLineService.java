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
import com.snuquill.paperdx.common.execption.PageNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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

	public List<ArticleLineDto> getCategoryArticlePage(String categoryName, int page) {
		int pageSize = 10;
		if (!Category.isCategory(categoryName)) {
			log.warn("user tried to access non-existing page: /article/" + categoryName + "/" + page);
			throw new PageNotFoundException("존재하지 않는 페이지입니다.");
		}
		Category category = Category.valueOf(categoryName.toUpperCase());

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
