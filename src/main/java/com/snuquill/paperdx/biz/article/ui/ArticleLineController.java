package com.snuquill.paperdx.biz.article.ui;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snuquill.paperdx.biz.article.application.ArticleLineDto;
import com.snuquill.paperdx.biz.article.application.ArticleLineService;
import com.snuquill.paperdx.biz.article.domain.Category;
import com.snuquill.paperdx.biz.common.dataobject.PageMetaData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ArticleLineController {

	private final ArticleLineService articleLineService;

	@GetMapping("/{feature}/{page}")
	public String getFeatureArticleList(@PathVariable("feature") String categoryName, @PathVariable("page") int page, Model model) {
		Page<ArticleLineDto> categoryArticlePage = articleLineService.getCategoryArticlePage(categoryName, page);
		model.addAttribute("articleLinePage", categoryArticlePage);
		model.addAttribute("pageMetaData", PageMetaData.of(categoryArticlePage));
		model.addAttribute("category", Category.valueOf(categoryName.toUpperCase()));

		List<ArticleLineDto> recentArticleList = articleLineService.getRecentArticleList(5);
		model.addAttribute("recentArticleList", recentArticleList);

		List<ArticleLineDto> mostReadArticleList = articleLineService.getMostReadArticles(5);
		model.addAttribute("mostReadArticleList", mostReadArticleList);
		return "features";
	}
}
