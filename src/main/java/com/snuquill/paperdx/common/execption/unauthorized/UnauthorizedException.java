package com.snuquill.paperdx.common.execption.unauthorized;

import org.springframework.security.core.AuthenticationException;

import com.snuquill.paperdx.common.execption.CustomException;

public abstract class UnauthorizedException extends CustomException {
	public UnauthorizedException() {
	}

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}
}
