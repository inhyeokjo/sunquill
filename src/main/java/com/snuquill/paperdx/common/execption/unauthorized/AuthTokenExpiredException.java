package com.snuquill.paperdx.common.execption.unauthorized;

import java.io.Serial;

public class AuthTokenExpiredException extends UnauthorizedException {
	@Serial
	private static final long serialVersionUID = -6957784906148280831L;

	public AuthTokenExpiredException(String message) {
		super(message);
	}

	public AuthTokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getErrorCode() {
		return "-1001";
	}
}
