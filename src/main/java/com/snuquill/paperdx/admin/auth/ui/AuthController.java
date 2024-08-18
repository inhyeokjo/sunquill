package com.snuquill.paperdx.admin.auth.ui;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.admin.auth.domain.vo.AuthTokenHttpHeaders;
import com.snuquill.paperdx.admin.auth.domain.vo.AuthTokenPair;
import com.snuquill.paperdx.admin.auth.service.AuthService;
import com.snuquill.paperdx.admin.auth.service.PasswordService;
import com.snuquill.paperdx.admin.auth.ui.dto.LoginRequestDto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/auth")
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

	@GetMapping("/encode-password")
	public String getEncodedPassword(@RequestParam("pw") String password) {
		return passwordService.generatePassword(password);
	}
}
