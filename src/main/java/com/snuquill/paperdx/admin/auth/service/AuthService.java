package com.snuquill.paperdx.admin.auth.service;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.admin.auth.domain.AdminUser;
import com.snuquill.paperdx.admin.auth.domain.AdminUserRepository;
import com.snuquill.paperdx.admin.auth.domain.JwtTokenGenerator;
import com.snuquill.paperdx.admin.auth.domain.vo.AuthTokenPair;
import com.snuquill.paperdx.admin.auth.ui.dto.LoginRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AdminUserRepository adminUserRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenGenerator jwtTokenGenerator;
	private final AuditAuthTokenService auditAuthTokenService;

	@Transactional
	public AuthTokenPair login(LoginRequestDto loginRequestDto) {
		String email = loginRequestDto.getEmail();
		String password = loginRequestDto.getPassword();
		Optional<AdminUser> userOptional = adminUserRepository.findAdminUserByMail(email);

		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
		}

		AdminUser adminUser = userOptional.get();
		if (!passwordEncoder.matches(password, adminUser.getPassword())) {
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}

		String accessToken = jwtTokenGenerator.createAccessToken(adminUser.getId(), adminUser.getName());
		String refreshToken = jwtTokenGenerator.createRefreshToken(adminUser.getId());

		auditAuthTokenService.saveAuditAuthToken(adminUser.getId(), accessToken, refreshToken);

		return AuthTokenPair.of(accessToken, refreshToken);
	}
}
