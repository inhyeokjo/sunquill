package com.snuquill.paperdx.biz.editorpick.infra;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.biz.article.application.ArticleDetailService;
import com.snuquill.paperdx.biz.editorpick.domain.ArticleTitleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DependencyArticleTitleRepository implements ArticleTitleRepository {

	private final ArticleDetailService articleDetailService;

	@Override
	public String findTitleById(Long articleId) {
		return articleDetailService.getArticleTitle(articleId);
	}
}
