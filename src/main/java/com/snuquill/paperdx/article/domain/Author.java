package com.snuquill.paperdx.article.domain;

import com.snuquill.paperdx.article.common.Constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Slf4j
public class Author {
	private Long id;
	private String name;
	private String email;
	private String role;
	private String pictureUrl;

	public static Author getAnonimousAuthor() {
		return new Author(1L, "anonymous", "anonymous@domain.com", "None", Constants.ANONYMOUS_MEMBER_PICTURE_URL);
	}
}
