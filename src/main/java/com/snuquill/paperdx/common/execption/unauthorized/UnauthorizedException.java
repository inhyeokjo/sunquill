package com.snuquill.paperdx.common.execption.unauthorized;

import org.springframework.security.core.AuthenticationException;

public abstract class UnauthorizedException extends AuthenticationException {
	protected UnauthorizedException(String message) {
		super(message);
	}

	protected UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	abstract String getErrorCode();
}
