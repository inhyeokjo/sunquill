package com.snuquill.paperdx.biz.auth.domain;

import com.snuquill.paperdx.biz.auth.domain.vo.AuthTokenPair;

public interface JwtTokenGenerator {
	String createRefreshToken(AdminUser adminUser);

	String createAccessToken(AdminUser adminUser);

	AuthTokenPair createAuthTokenPair(AdminUser adminUser);

	Long validateRefreshToken(String refreshToken);
}
