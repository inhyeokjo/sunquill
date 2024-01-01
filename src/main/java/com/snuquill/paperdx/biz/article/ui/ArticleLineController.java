package com.snuquill.paperdx.biz.article.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import com.snuquill.paperdx.biz.article.application.ArticleLineDto;
import com.snuquill.paperdx.biz.article.application.ArticleLineService;

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
		List<ArticleLineDto> categoryArticlePage = articleLineService.getCategoryArticlePage(categoryName, page);
		model.addAttribute("articleLineList", categoryArticlePage);
		model.addAttribute("categoryName", StringUtils.capitalize(categoryName).replace("_", " "));
		return "features";
	}
}
