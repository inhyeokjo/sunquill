package com.snuquill.paperdx.biz.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.auth.domain.AdminUser;
import com.snuquill.paperdx.biz.auth.domain.AuditAuthToken;
import com.snuquill.paperdx.biz.auth.domain.JwtTokenGenerator;
import com.snuquill.paperdx.biz.auth.domain.vo.AuthTokenPair;
import com.snuquill.paperdx.biz.auth.ui.dto.LoginRequestDto;
import com.snuquill.paperdx.common.execption.unauthorized.RefreshTokenExpiredException;
import com.snuquill.paperdx.common.execption.unauthorized.WrongPasswordException;
import com.snuquill.paperdx.utils.MD5Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AdminUserService adminUserService;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenGenerator jwtTokenGenerator;
	private final AuditAuthTokenService auditAuthTokenService;

	@Transactional
	public AuthTokenPair login(LoginRequestDto loginRequestDto) {
		String email = loginRequestDto.getEmail();
		String password = loginRequestDto.getPassword();
		AdminUser adminUser = adminUserService.getAdminUserByMail(email);

		if (!passwordEncoder.matches(password, adminUser.getPassword())) {
			throw new WrongPasswordException("비밀번호가 일치하지 않습니다.");
		}

		AuthTokenPair authTokenPair = jwtTokenGenerator.createAuthTokenPair(adminUser);
		auditAuthTokenService.saveAuditAuthToken(adminUser.getId(), authTokenPair);
		return authTokenPair;
	}

	@Transactional
	public AuthTokenPair renewToken(String refreshToken) {
		// 유효한 토큰인지 확인(토큰 타입, 토큰 오류 여부 확인)
		// 토큰 만료 여부 확인
		Long userId = jwtTokenGenerator.validateRefreshToken(refreshToken);

		// Audit Auth Token 테이블 확인
		AuditAuthToken auditAuthToken = auditAuthTokenService.getAuditAuthTokenByUserId(userId);
		String refreshTokenHash = auditAuthToken.getRefreshTokenHash();
		if (!MD5Utils.hash(refreshToken).equals(refreshTokenHash)) {
			throw new RefreshTokenExpiredException("최신 Refresh Token이 아닙니다. 가장 최근에 발급받은 Refresh Token을 사용해주세요. userId=" + userId + ", refreshToken=" + refreshToken);
		}

		AdminUser adminUser = adminUserService.getAdminUser(userId);
		AuthTokenPair authTokenPair = jwtTokenGenerator.createAuthTokenPair(adminUser);
		auditAuthTokenService.saveAuditAuthToken(adminUser.getId(), authTokenPair);
		return authTokenPair;
	}
}
