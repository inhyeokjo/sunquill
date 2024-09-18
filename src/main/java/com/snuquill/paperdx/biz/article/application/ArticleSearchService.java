package com.snuquill.paperdx.biz.article.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.snuquill.paperdx.biz.article.domain.Article;
import com.snuquill.paperdx.biz.article.domain.ArticleRepository;
import com.snuquill.paperdx.common.execption.badrequest.PaginationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleSearchService {

	private final ArticleRepository articleRepository;

	public Page<ArticleLineDto> search(String searchText, int page) {
		int pageSize = 10;
		if (page <= 0) {
			log.warn("user tried to access non-existing searchText={}, page={}", searchText, page);
			log.warn("article line page must be greater than 0");
			throw new PaginationException("페이지는 1페이지 이상이어야 합니다.");
		}

		PageRequest pageable = PageRequest.of(page - 1, pageSize, Sort.by("publishDate").descending());
		Page<Article> articles = articleRepository.searchArticle(searchText, pageable);
		return articles.map(ArticleLineDto::of);
	}
}
