package com.snuquill.paperdx.biz.article.ui;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snuquill.paperdx.biz.article.application.ArticleLineDto;
import com.snuquill.paperdx.biz.article.application.ArticleLineService;
import com.snuquill.paperdx.biz.article.domain.Category;
import com.snuquill.paperdx.common.execption.PageNotFoundException;

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

		if (Arrays.stream(Category.values()).map(Enum::name).toList().contains(categoryName.toUpperCase())) {
			log.warn("user tried to access non-existing page: /article/"+ categoryName +"/"+ page);
			throw new PageNotFoundException("존재하지 않는 페이지입니다.");
		}
		Category category = Category.valueOf(categoryName.toUpperCase());

		List<ArticleLineDto> categoryArticlePage = articleLineService.getCategoryArticlePage(category, page);
		model.addAttribute("articleLineList", categoryArticlePage);
		return "features";
	}
}
