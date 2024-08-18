package com.snuquill.paperdx.admin.auth.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthTokenPair {
	private String accessToken;
	private String refreshToken;

	public static AuthTokenPair of(String accessToken, String refreshToken) {
		return new AuthTokenPair(accessToken, refreshToken);
	}
}
