package com.snuquill.paperdx.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.snuquill.paperdx.security.domain.vo.RoleKind;
import com.snuquill.paperdx.security.filter.JwtAuthenticationFilter;
import com.snuquill.paperdx.security.config.handler.JwtAccessDeniedHandler;
import com.snuquill.paperdx.security.config.handler.JwtAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/api/admin/**")

			.csrf(AbstractHttpConfigurer::disable)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			// exception handling 할 때 우리가 만든 클래스를 추가
			.exceptionHandling(exceptionHandling ->
				exceptionHandling
					.authenticationEntryPoint(jwtAuthenticationEntryPoint)
					.accessDeniedHandler(jwtAccessDeniedHandler)
			)

			// 보안 헤더를 설정함
			// Default :
			// Cache-Control: no-cache, no-store, max-age=0, must-revalidate
			// Pragma: no-cache
			// Expires: 0
			// X-Content-Type-Options: nosniff
			// Strict-Transport-Security: max-age=31536000 ; includeSubDomains
			// X-Frame-Options: DENY
			// X-XSS-Protection: 0
			.headers(Customizer.withDefaults())

			.sessionManagement(session ->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)

			.authorizeHttpRequests(auth ->
				auth.requestMatchers("/api/admin/auth/**").permitAll()
					.anyRequest().authenticated()
			)
			.build();
	}

	@Bean
	public SecurityFilterChain serviceFilterChain(HttpSecurity http) throws Exception {
		return http
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().permitAll() // 모든 요청을 허용
			)
			.build();
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy(
			RoleKind.SUPER_ADMIN.getAuthority() + ">" + RoleKind.DESIGNER.getAuthority() + "\n" +
				RoleKind.SUPER_ADMIN.getAuthority() + ">" + RoleKind.REPORTER.getAuthority() + "\n" +
				RoleKind.SUPER_ADMIN.getAuthority() + ">" + RoleKind.PHOTOGRAPHER.getAuthority() + "\n" +
				RoleKind.SUPER_ADMIN.getAuthority() + ">" + RoleKind.MEMBER.getAuthority() + "\n" +
				RoleKind.SUPER_ADMIN.getAuthority() + ">" + RoleKind.EDITOR.getAuthority() + "\n" +
				RoleKind.SUPER_ADMIN.getAuthority() + ">" + RoleKind.SUBEDITOR.getAuthority()
		);
		return roleHierarchy;
	}
}
