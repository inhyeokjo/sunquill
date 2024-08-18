package com.snuquill.paperdx.admin.auth.service;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.admin.auth.domain.AdminUser;
import com.snuquill.paperdx.admin.auth.domain.AuditAuthToken;
import com.snuquill.paperdx.admin.auth.domain.JwtTokenGenerator;
import com.snuquill.paperdx.admin.auth.domain.vo.AuthTokenPair;
import com.snuquill.paperdx.admin.auth.ui.dto.LoginRequestDto;
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
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}

		String accessToken = jwtTokenGenerator.createAccessToken(adminUser.getId(), adminUser.getName());
		String refreshToken = jwtTokenGenerator.createRefreshToken(adminUser.getId());

		return auditAuthTokenService.saveAuditAuthToken(adminUser.getId(), accessToken, refreshToken);
	}

	public AuthTokenPair renewToken(String refreshToken) {
		// 유효한 토큰인지 확인(토큰 타입, 토큰 오류 여부 확인)
		// 토큰 만료 여부 확인
		Long userId = jwtTokenGenerator.validateRefreshToken(refreshToken);

		// Audit Auth Token 테이블 확인
		AuditAuthToken auditAuthToken = auditAuthTokenService.getAuditAuthTokenByUserId(userId);
		String refreshTokenHash = auditAuthToken.getRefreshTokenHash();
		if (!MD5Utils.hash(refreshToken).equals(refreshTokenHash)) {
			throw new BadCredentialsException("최신 Refresh Token이 아닙니다. 가장 최근에 발급받은 Refresh Token을 사용해주세요. userId=" + userId + ", refreshToken=" + refreshToken);
		}

		AdminUser adminUser = adminUserService.getAdminUser(userId);

		String newAccessToken = jwtTokenGenerator.createAccessToken(adminUser.getId(), adminUser.getName());
		String newRefreshToken = jwtTokenGenerator.createRefreshToken(adminUser.getId());

		return auditAuthTokenService.saveAuditAuthToken(adminUser.getId(), newAccessToken, newRefreshToken);
	}
}
