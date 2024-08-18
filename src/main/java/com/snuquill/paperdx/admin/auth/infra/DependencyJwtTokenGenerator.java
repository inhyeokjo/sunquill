package com.snuquill.paperdx.admin.auth.infra;

import org.springframework.stereotype.Component;

import com.snuquill.paperdx.admin.auth.domain.JwtTokenGenerator;
import com.snuquill.paperdx.security.domain.vo.AuthTokenType;
import com.snuquill.paperdx.security.service.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DependencyJwtTokenGenerator implements JwtTokenGenerator {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public String createRefreshToken(Long id) {
		return jwtTokenProvider.createRefreshToken(id);
	}

	@Override
	public String createAccessToken(Long id, String name) {
		return jwtTokenProvider.createAccessToken(id, name);
	}

	@Override
	public Long validateRefreshToken(String refreshToken) {
		return jwtTokenProvider.validateToken(refreshToken, AuthTokenType.REFRESH);
	}
}
