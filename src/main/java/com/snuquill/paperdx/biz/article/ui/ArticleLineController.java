package com.snuquill.paperdx.biz.article.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snuquill.paperdx.biz.article.application.ArticleLineDto;
import com.snuquill.paperdx.biz.article.application.ArticleLineService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
@Validated
public class ArticleLineController {

	private final ArticleLineService articleLineService;

	@GetMapping("/{feature}/{page}")
	public String getFeatureArticleList(@PathVariable("feature") String feature, @PathVariable("page") int page, Model model) {
		List<ArticleLineDto> categoryArticlePage = articleLineService.getCategoryArticlePage(feature, page);
		model.addAttribute("articleLineList", categoryArticlePage);
		return "features";
	}
}
