package com.snuquill.paperdx.article.ui;

import com.snuquill.paperdx.article.application.ArticleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/temp")
@RequiredArgsConstructor
@Validated
public class TempController {

	private final ArticleDetailService articleDetailService;

	@GetMapping("/home")
	public String getHomepage() {
		return "home";
	}
	@GetMapping("/404")
	public String get404() {
		return "404";
	}
}
