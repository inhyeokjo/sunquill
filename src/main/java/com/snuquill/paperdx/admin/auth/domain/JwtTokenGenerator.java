package com.snuquill.paperdx.admin.auth.domain;

import com.snuquill.paperdx.admin.auth.domain.vo.AuthTokenPair;

public interface JwtTokenGenerator {
	String createRefreshToken(AdminUser adminUser);

	String createAccessToken(AdminUser adminUser);

	AuthTokenPair createAuthTokenPair(AdminUser adminUser);

	Long validateRefreshToken(String refreshToken);
}
