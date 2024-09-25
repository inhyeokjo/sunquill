package com.snuquill.paperdx.biz.article.ui;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.article.application.ArticleLineDto;
import com.snuquill.paperdx.biz.article.application.ArticleLineService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/article")
@RequiredArgsConstructor
@Validated
public class ArticleLineRestController {
	private final ArticleLineService articleLineService;

	@GetMapping("/{feature}/{page}")
	public Page<ArticleLineDto> getArticleLinePage(
		@PathVariable("feature") String categoryName,
		@PathVariable("page") int page,
		@RequestParam(value = "pageSize", defaultValue = "10") int pageSize
	) {
		return articleLineService.getCategoryArticlePage(categoryName, page, pageSize);
	}
}
