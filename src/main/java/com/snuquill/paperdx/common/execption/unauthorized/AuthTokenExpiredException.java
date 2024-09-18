package com.snuquill.paperdx.common.execption.unauthorized;

public class AuthTokenExpiredException extends UnauthorizedException{
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
