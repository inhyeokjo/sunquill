package com.snuquill.paperdx.biz.introduction.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.snuquill.paperdx.biz.introduction.application.GroupIntroDto;
import com.snuquill.paperdx.biz.introduction.application.GroupIntroService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IntroductionController {
	private final GroupIntroService groupIntroService;
	@GetMapping("/introduction")
	public String getIntroductionPage(Model model) {
		GroupIntroDto introduction = groupIntroService.getIntroduction();
		model.addAttribute("introduction", introduction);
		return "introduction";
	}
}

