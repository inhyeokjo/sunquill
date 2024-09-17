package com.snuquill.paperdx.common.execption.unauthorized;

public class AuthTokenNotFoundException extends UnauthorizedException{
	public AuthTokenNotFoundException(String message) {
		super(message);
	}

	public AuthTokenNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	String getErrorCode() {
		return "";
	}
}
