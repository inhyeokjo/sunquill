package com.snuquill.paperdx.article.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snuquill.paperdx.article.application.ArticleDetailService;
import com.snuquill.paperdx.article.application.ArticleDto;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
@Validated
public class ArticleController {

	private final ArticleDetailService articleDetailService;

	@GetMapping("/{articleId}")
	public String getArticleDetail(@NotNull @PathVariable Long articleId, Model model) {
		ArticleDto articleDetail = articleDetailService.getArticleDetail(articleId);
		model.addAttribute(articleDetail);
		return "article";
	}
}
