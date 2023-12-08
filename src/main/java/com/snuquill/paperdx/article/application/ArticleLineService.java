package com.snuquill.paperdx.article.application;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.snuquill.paperdx.article.domain.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleLineService {

	private final ArticleRepository articleRepository;

	public List<ArticleLineDto> getRecentArticleList(int count) {
		PageRequest countRequest = PageRequest.of(0, count, Sort.by("publishDate").descending());
		return articleRepository.findAll(countRequest)
			.stream()
			.map(ArticleLineDto::of)
			.toList();
	}
}
