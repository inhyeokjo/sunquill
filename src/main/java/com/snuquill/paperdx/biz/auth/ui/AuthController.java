package com.snuquill.paperdx.biz.auth.ui;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.auth.domain.vo.AuthTokenHttpHeaders;
import com.snuquill.paperdx.biz.auth.domain.vo.AuthTokenPair;
import com.snuquill.paperdx.biz.auth.service.AuthService;
import com.snuquill.paperdx.biz.auth.service.PasswordService;
import com.snuquill.paperdx.biz.auth.ui.dto.LoginRequestDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/auth")
@Slf4j
public class AuthController {

	private final AuthService authService;
	private final PasswordService passwordService;

	@PostMapping("/login")
	public void login(
		@Validated @RequestBody LoginRequestDto loginRequestDto,
		HttpServletResponse response
	) {
		AuthTokenPair tokenPair = authService.login(loginRequestDto);
		setAuthTokenToHttpHeader(response, tokenPair);
	}

	private static void setAuthTokenToHttpHeader(HttpServletResponse response, AuthTokenPair tokenPair) {
		response.setHeader(AuthTokenHttpHeaders.AUTHORIZATION, "Bearer " + tokenPair.getAccessToken());
		response.setHeader(AuthTokenHttpHeaders.REFRESH_TOKEN, tokenPair.getRefreshToken());
	}

	@PostMapping("/renew")
	public void renew(
		HttpServletRequest request,
		HttpServletResponse response
	) {
		String refreshToken = request.getHeader(AuthTokenHttpHeaders.REFRESH_TOKEN);

		if (StringUtils.isBlank(refreshToken)) {
			log.warn("Refresh Token is blank.");
			throw new IllegalArgumentException();
		}

		AuthTokenPair authTokenPair = authService.renewToken(refreshToken);
		setAuthTokenToHttpHeader(response, authTokenPair);
	}

	@GetMapping("/encode-password")
	public String getEncodedPassword(@RequestParam("pw") String password) {
		return passwordService.generatePassword(password);
	}
}
