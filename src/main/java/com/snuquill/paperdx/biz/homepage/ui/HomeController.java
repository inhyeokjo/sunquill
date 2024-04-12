package com.snuquill.paperdx.biz.homepage.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.snuquill.paperdx.biz.homepage.application.HomePageService;
import com.snuquill.paperdx.biz.homepage.application.dto.HomePageDto;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogLevel;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogging;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final HomePageService homePageService;

	@ApiAccessLogging(successLogLevel = ApiAccessLogLevel.NONE, failLogLevel = ApiAccessLogLevel.NONE)
	@GetMapping("/")
	public RedirectView redirectRootPage() {
		return new RedirectView("/home");
	}

	@GetMapping("/home")
	public String getHomePage(Model model) {
		HomePageDto homePageDto = homePageService.getHomePage();
		model.addAttribute("topArticle", homePageDto.getTopArticleDto());
		model.addAttribute("editorPick", homePageDto.getEditorPickDto());
		model.addAttribute("recentArticleList", homePageDto.getArticleLineDtoList());
		model.addAttribute("recentMagazineList", homePageDto.getMagazineDtoList());
		model.addAttribute("recentVideoList", homePageDto.getVideoInfoDtoList());
		model.addAttribute("photoJournalList", homePageDto.getPhotoJournalDtoList());
		return "home";
	}
}
