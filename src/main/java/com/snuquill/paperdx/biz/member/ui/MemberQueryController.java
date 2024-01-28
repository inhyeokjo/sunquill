package com.snuquill.paperdx.biz.member.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.snuquill.paperdx.biz.member.application.MemberDto;
import com.snuquill.paperdx.biz.member.application.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberQueryController {
	private final MemberService memberService;

	@GetMapping("/members")
	public String getMemberList(Model model) {
		MultiValueMap<String, MemberDto> memberMap = memberService.getOrganizationChart();
		model.addAttribute("memberMap", memberMap);
		return "meet-the-staff";
	}
}
