package com.snuquill.paperdx.biz.article.ui;

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
		model.addAttribute("pageMetaData", getPageMetaData(categoryArticlePage));
		model.addAttribute("category", Category.valueOf(categoryName.toUpperCase()));
		return "features";
	}

	private PageMetaData getPageMetaData(Page<ArticleLineDto> categoryArticlePage) {
		int targetPage = categoryArticlePage.getNumber() + 1;
		int startPage = categoryArticlePage.getNumber() / 10 * 10 + 1;
		boolean isEndPageList = startPage <= categoryArticlePage.getTotalPages() / 10 + 1;
		int endPage = !isEndPageList ? startPage + 10 : categoryArticlePage.getTotalPages();
		return new PageMetaData(startPage, endPage, targetPage, startPage == 1, isEndPageList);
	}
}
