package com.snuquill.paperdx.common.execption.unauthorized;

import java.io.Serial;

public class RefreshTokenExpiredException extends UnauthorizedException {
	@Serial
	private static final long serialVersionUID = -407944075665647934L;

	public RefreshTokenExpiredException(String message) {
		super(message);
	}

	public RefreshTokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getErrorCode() {
		return "-1003";
	}
}
