package com.snuquill.paperdx.admin.auth.infra;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.snuquill.paperdx.admin.auth.domain.AdminUser;
import com.snuquill.paperdx.admin.auth.domain.JwtTokenGenerator;
import com.snuquill.paperdx.admin.auth.domain.vo.AuthTokenPair;
import com.snuquill.paperdx.security.domain.vo.AuthTokenType;
import com.snuquill.paperdx.security.service.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DependencyJwtTokenGenerator implements JwtTokenGenerator {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public String createRefreshToken(AdminUser adminUser) {
		Long id = adminUser.getId();
		return jwtTokenProvider.createRefreshToken(id);
	}

	@Override
	public String createAccessToken(AdminUser adminUser) {
		Long id = adminUser.getId();
		String name = adminUser.getName();
		Set<String> roleCdSet = adminUser.getRoleCdSet();

		return jwtTokenProvider.createAccessToken(id, name, roleCdSet);
	}

	@Override
	public AuthTokenPair createAuthTokenPair(AdminUser adminUser) {
		String accessToken = createAccessToken(adminUser);
		String refreshToken = createRefreshToken(adminUser);

		return AuthTokenPair.of(accessToken, refreshToken);
	}

	@Override
	public Long validateRefreshToken(String refreshToken) {
		return jwtTokenProvider.validateToken(refreshToken, AuthTokenType.REFRESH);
	}
}
