package com.snuquill.paperdx.temp.ui;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/temp")
@RequiredArgsConstructor
@Validated
public class TempController {

	@GetMapping("/home")
	public String getHomepage() {
		return "home";
	}

	@GetMapping("/404")
	public String get404() {
		return "404";
	}

	@GetMapping("/features")
	public String getArticles() { return "features";}
}
