package com.snuquill.paperdx.article.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.snuquill.paperdx.article.domain.Article;
import com.snuquill.paperdx.article.domain.ArticleRepository;
import com.snuquill.paperdx.article.domain.Author;
import com.snuquill.paperdx.article.domain.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleLineService {

	private final ArticleRepository articleRepository;
	private final AuthorRepository authorRepository;

	public List<ArticleLineDto> getRecentArticleList(int count) {
		PageRequest countRequest = PageRequest.of(0, count, Sort.by("publishDate").descending());
		List<Article> articleList = articleRepository.findAll(countRequest).toList();
		List<Long> authorIdList = articleList.stream()
			.map(Article::getAuthorId)
			.toList();
		List<Author> authorList = authorRepository.getAllAuthor(authorIdList);
		return articleList.stream()
			.map(article -> ArticleLineDto.of(article, makeAuthorMap(authorList).get(article.getAuthorId()).getName()))
			.toList();
	}

	private Map<Long, Author> makeAuthorMap(List<Author> authorList) {
		return authorList.stream().collect(Collectors.toMap(Author::getId, author -> author));
	}
}
