package com.snuquill.paperdx.biz.article.ui;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.snuquill.paperdx.biz.article.application.ArticleLineDto;
import com.snuquill.paperdx.biz.article.application.ArticleLineService;
import com.snuquill.paperdx.biz.article.application.ArticleSearchService;
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
	private final ArticleSearchService articleSearchService;

	@GetMapping("/{feature}/{page}")
	public String getFeatureArticleList(@PathVariable("feature") String categoryName, @PathVariable("page") int page, Model model) {
		int pageSize = 10;

		model.addAttribute("isSearch", false);

		Page<ArticleLineDto> categoryArticlePage = articleLineService.getCategoryArticlePage(categoryName, page, pageSize, false);
		model.addAttribute("articleLinePage", categoryArticlePage);
		model.addAttribute("pageMetaData", PageMetaData.of(categoryArticlePage));
		model.addAttribute("category", Category.valueOf(categoryName.toUpperCase()));

		List<ArticleLineDto> recentArticleList = articleLineService.getRecentArticleList(5);
		model.addAttribute("recentArticleList", recentArticleList);

		List<ArticleLineDto> mostReadArticleList = articleLineService.getMostReadArticles(5);
		model.addAttribute("mostReadArticleList", mostReadArticleList);
		return "features";
	}

	@GetMapping("/search")
	public String searchArticle(@RequestParam("search-text") String searchText, @RequestParam(defaultValue = "1", value = "page") int page, Model model) {
		model.addAttribute("isSearch", true);
		model.addAttribute("searchText", searchText);

		Page<ArticleLineDto> searchPage = articleSearchService.search(searchText, page);
		model.addAttribute("articleLinePage", searchPage);
		model.addAttribute("pageMetaData", PageMetaData.of(searchPage));

		// TODO recentArticle, mostReadArticle API로 빼고, Rest로 불러서 사용.
		List<ArticleLineDto> recentArticleList = articleLineService.getRecentArticleList(5);
		model.addAttribute("recentArticleList", recentArticleList);

		List<ArticleLineDto> mostReadArticleList = articleLineService.getMostReadArticles(5);
		model.addAttribute("mostReadArticleList", mostReadArticleList);
		return "features";
	}
}
