package com.snuquill.paperdx.common.execption.unauthorized;

public class RefreshTokenExpiredException extends UnauthorizedException{
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
