package com.snuquill.paperdx.homepage.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	@GetMapping("/home")
	public String getHomePage() {
		//todo 데이터 추출
		return "home";
	}
}
