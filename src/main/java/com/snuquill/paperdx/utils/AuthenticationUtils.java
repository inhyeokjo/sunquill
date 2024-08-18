package com.snuquill.paperdx.utils;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.snuquill.paperdx.security.domain.CurrentUser;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthenticationUtils {

	public static Optional<String> getName() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null && context.getAuthentication() != null) {
			return Optional.of(context.getAuthentication().getName());
		}

		return Optional.empty();
	}

	public static CurrentUser getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (CurrentUser)authentication.getPrincipal();
	}
}
