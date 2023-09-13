package com.snuquill.paperdx.article.infra;

import org.springframework.stereotype.Component;

import com.snuquill.paperdx.article.domain.Author;
import com.snuquill.paperdx.member.domain.Member;

@Component
public class AuthorConverter {

	public Author createAuthor(Member member) {
		return new Author(
			member.getName(),
			member.getEmail(),
			member.getRole(),
			member.getProfilePicture().getUrl());
	}
}
