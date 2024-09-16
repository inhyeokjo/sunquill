package com.snuquill.paperdx.biz.article.infra;

import org.springframework.stereotype.Component;

import com.snuquill.paperdx.biz.article.domain.Author;
import com.snuquill.paperdx.biz.member.application.MemberDto;

@Component
public class AuthorConverter {

	public Author createAuthor(MemberDto memberDto) {
		return new Author(
			memberDto.getId(),
			memberDto.getName(),
			memberDto.getEmail(),
			memberDto.getRole(),
			memberDto.getMemberPictureUrl()
		);
	}
}
