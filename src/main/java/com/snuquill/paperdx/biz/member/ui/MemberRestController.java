package com.snuquill.paperdx.biz.member.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.member.application.MemberDto;
import com.snuquill.paperdx.biz.member.application.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/admin/member")
public class MemberRestController {
	private final MemberService memberService;

	@GetMapping
	public Map<String, List<MemberDto>> getMemberMap() {
		return new HashMap<>(memberService.getOrganizationChart());
	}
}
