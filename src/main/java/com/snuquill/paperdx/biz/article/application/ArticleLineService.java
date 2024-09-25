package com.snuquill.paperdx.biz.article.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.article.domain.Article;
import com.snuquill.paperdx.biz.article.domain.ArticleRepository;
import com.snuquill.paperdx.biz.article.domain.Author;
import com.snuquill.paperdx.biz.article.domain.AuthorRepository;
import com.snuquill.paperdx.biz.article.domain.Category;
import com.snuquill.paperdx.common.execption.badrequest.PaginationException;
import com.snuquill.paperdx.common.execption.badrequest.PathVariableException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleLineService {

	private final ArticleRepository articleRepository;
	private final AuthorRepository authorRepository;

	@Transactional(readOnly = true)
	public List<ArticleLineDto> getRecentArticleList(int count) {
		PageRequest countRequest = PageRequest.of(0, count, Sort.by("publishDate").descending());
		List<Article> articleList = articleRepository.findArticlesByInvisible(false, countRequest).toList();
		Map<Long, Author> authorMap = getAuthorMap(articleList);

		return articleList.stream()
			.map(article -> ArticleLineDto.of(article, authorMap))
			.toList();
	}

	@Transactional(readOnly = true)
	public Page<ArticleLineDto> getCategoryArticlePage(String categoryName, int page, int pageSize) {
		if (page <= 0) {
			log.warn("user tried to access non-existing page: /article/" + categoryName + "/" + page);
			log.warn("article line page must be greater than 0");
			throw new PaginationException("페이지는 1페이지 이상이어야 합니다.");
		}

		if (!Category.isCategory(categoryName)) {
			log.warn("user tried to access non-existing page: /article/" + categoryName + "/" + page);
			throw new PathVariableException("존재하지 않는 카테고리 페이지 입니다. category: " + categoryName);
		}

		Category category = Category.valueOf(categoryName.toUpperCase());

		PageRequest countRequest = PageRequest.of(page - 1, pageSize, Sort.by("publishDate").descending());
		List<Article> articleList = articleRepository.findByCategoryVisible(category, countRequest);
		long categoryArticleCount = articleRepository.countAllByCategoryAndInvisible(category, false);
		Map<Long, Author> authorMap = getAuthorMap(articleList);

		List<ArticleLineDto> articleLineDtoList = articleList.stream()
			.map(article -> ArticleLineDto.of(article, authorMap))
			.toList();
		return new PageImpl<>(articleLineDtoList, countRequest, categoryArticleCount);
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

	public List<ArticleLineDto> getMostReadArticles(int count) {
		PageRequest countRequest = PageRequest.of(0, count, Sort.by("viewCount").descending());
		List<Article> articleList = articleRepository.findAll(countRequest).toList();
		Map<Long, Author> authorMap = getAuthorMap(articleList);

		return articleList.stream()
			.map(article -> ArticleLineDto.of(article, authorMap))
			.toList();
	}
}
