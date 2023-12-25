package com.snuquill.paperdx.biz.article.infra;

import org.springframework.stereotype.Component;

import com.snuquill.paperdx.biz.article.domain.Author;
import com.snuquill.paperdx.biz.member.domain.Member;

@Component
public class AuthorConverter {

	public Author createAuthor(Member member) {
		return new Author(
			member.getId(),
			member.getName(),
			member.getEmail(),
			member.getRole(),
			member.getProfilePicture().getUrl());
	}
}
