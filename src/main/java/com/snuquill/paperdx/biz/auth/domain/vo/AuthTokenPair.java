package com.snuquill.paperdx.biz.auth.domain.vo;

import com.snuquill.paperdx.utils.MD5Utils;

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

	public String getAccessTokenHash() {
		return MD5Utils.hash(accessToken);
	}

	public String getRefreshTokenHash() {
		return MD5Utils.hash(refreshToken);
	}
}
