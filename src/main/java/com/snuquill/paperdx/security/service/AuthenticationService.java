package com.snuquill.paperdx.security.service;

import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.snuquill.paperdx.security.domain.CurrentUser;
import com.snuquill.paperdx.security.domain.User;
import com.snuquill.paperdx.security.domain.UserRepository;
import com.snuquill.paperdx.security.domain.vo.AuthTokenType;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;

	public void setAuthenticationToSecurityContextHolder(String token) {
		SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
	}

	private Authentication getAuthentication(String jwt) {
		Claims claims = jwtTokenProvider.parseClaims(jwt);

		AuthTokenType type = claims.get("type", AuthTokenType.class);
		if (type != AuthTokenType.ACCESS) {
			throw new AuthorizationServiceException("Token Type is not AccessToken");
		}

		Long userId = claims.get("id", Long.class);

		User user = userRepository.getUser(userId);
		CurrentUser currentUser = CurrentUser.from(user);
		UsernamePasswordAuthenticationToken token
			= new UsernamePasswordAuthenticationToken(currentUser, "", currentUser.getAuthorities());

		token.setDetails(currentUser);
		return token;
	}

}
