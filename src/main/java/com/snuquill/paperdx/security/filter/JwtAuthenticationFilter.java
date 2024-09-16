package com.snuquill.paperdx.security.filter;

import java.nio.file.AccessDeniedException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.snuquill.paperdx.security.service.AuthenticationService;
import com.snuquill.paperdx.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final AuthenticationService authenticationService;

	private final AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher("/api/admin/auth/**");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
		try {
			if (requestMatcher.matches(request)) {
				filterChain.doFilter(request, response);
				return;
			}

			String jwt = JwtUtils.extractJwtTokenFromRequest(request);
			if (!StringUtils.isBlank(jwt)) {
				authenticationService.setAuthenticationToSecurityContextHolder(jwt);
			}
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			log.warn("Authentication token setting error.", e);
			if (e instanceof AccessDeniedException) {
				response.setStatus(HttpStatus.FORBIDDEN.value());
			} else {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			}
		}
	}
}
