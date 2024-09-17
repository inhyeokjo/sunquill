package com.snuquill.paperdx.common.execption.unauthorized;

public class AuthTokenExpiredException extends UnauthorizedException{
	public AuthTokenExpiredException(String message) {
		super(message);
	}

	public AuthTokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	String getErrorCode() {
		return "-1001";
	}
}
