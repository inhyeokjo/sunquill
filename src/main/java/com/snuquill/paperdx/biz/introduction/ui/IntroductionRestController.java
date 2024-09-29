package com.snuquill.paperdx.biz.introduction.ui;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.introduction.application.GroupIntroDto;
import com.snuquill.paperdx.biz.introduction.application.GroupIntroService;
import com.snuquill.paperdx.biz.introduction.ui.dto.IntroductionRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/admin/introduction")
public class IntroductionRestController {
	private final GroupIntroService groupIntroService;

	@GetMapping
	public GroupIntroDto getIntroduction() {
		return groupIntroService.getIntroduction();
	}

	@PostMapping
	public void postIntroduction(
		@RequestBody IntroductionRequestDto.SettingRequest introductionSettingRequestDto
	) {
		String introduction = introductionSettingRequestDto.introduction();
		groupIntroService.setIntroduction(introduction);
	}
}
