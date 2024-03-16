package com.snuquill.paperdx.biz.member.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.snuquill.paperdx.biz.member.domain.Member;
import com.snuquill.paperdx.biz.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public MultiValueMap<String, MemberDto> getOrganizationChart() {
		List<Member> memberList = memberRepository.findMembersByRetired(false);
		MultiValueMap<String, MemberDto> organizationChart = new LinkedMultiValueMap<>();
		for (Member member : memberList) {
			MemberDto memberDto = MemberDto.of(member);
			organizationChart.add(member.getTeam(), memberDto);
		}
		return organizationChart;
	}
}
