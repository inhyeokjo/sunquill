package com.snuquill.paperdx.admin.auth.domain;

public interface JwtTokenGenerator {
	String createRefreshToken(Long id);

	String createAccessToken(Long id, String name);
}
