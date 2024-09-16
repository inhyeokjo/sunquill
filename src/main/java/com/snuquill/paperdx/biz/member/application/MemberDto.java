package com.snuquill.paperdx.biz.member.application;

import com.snuquill.paperdx.biz.member.domain.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Data
public class MemberDto {
	private Long id;
	private String memberPictureUrl;
	private String name;
	private String role;
	private String email;

	public static MemberDto of(Member member) {
		return MemberDto.builder()
			.id(member.getId())
			.memberPictureUrl(member.getProfilePicture().getUrl())
			.name(member.getName())
			.role(member.getRole())
			.email(member.getEmail())
			.build();
	}
}
