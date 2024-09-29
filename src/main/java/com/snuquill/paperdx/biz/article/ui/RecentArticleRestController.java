package com.snuquill.paperdx.biz.article.ui;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.article.application.ArticleLineDto;
import com.snuquill.paperdx.biz.article.application.ArticleLineService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/admin/article/recent")
@RequiredArgsConstructor
@Slf4j
public class RecentArticleRestController {

	private final ArticleLineService recentArticleService;

	@GetMapping
	public List<ArticleLineDto> getRecentArticleList(
		@RequestParam(defaultValue = "3") @NotNull int count
	) {
		return recentArticleService.getRecentArticleList(count);
	}

}
