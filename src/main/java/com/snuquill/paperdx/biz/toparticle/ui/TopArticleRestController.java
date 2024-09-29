package com.snuquill.paperdx.biz.toparticle.ui;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.toparticle.application.TopArticleDto;
import com.snuquill.paperdx.biz.toparticle.application.TopArticleService;
import com.snuquill.paperdx.biz.toparticle.ui.dto.TopArticleRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/admin/topArticle")
@RequiredArgsConstructor
@Slf4j
public class TopArticleRestController {

	private final TopArticleService topArticleService;

	@PostMapping
	public void setTopArticleService(
		@Validated @RequestBody TopArticleRequestDto.SettingRequest topArticleSettingRequestDto
	) {
		topArticleService.setTopArticle(topArticleSettingRequestDto.id(), topArticleSettingRequestDto.summary());
	}

	@GetMapping
	public TopArticleDto getTopArticle() {
		return topArticleService.getTopArticle();
	}
}
