package com.snuquill.paperdx.biz.member.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.snuquill.paperdx.biz.member.domain.Member;
import com.snuquill.paperdx.biz.member.domain.MemberRepository;
import com.snuquill.paperdx.common.execption.biz.DataNotFoundException;

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

	public MemberDto getMemberDtoById(Long id) {
		return memberRepository.findById(id)
			.map(MemberDto::of)
			.orElseThrow(() -> new DataNotFoundException("id: " + id + " 인 Member Entity가 존재하지 않습니다."));
	}

	public List<MemberDto> getAllMemberDtoByIdList(List<Long> idList) {
		return memberRepository.findAllById(idList).stream().map(MemberDto::of).toList();
	}
}
