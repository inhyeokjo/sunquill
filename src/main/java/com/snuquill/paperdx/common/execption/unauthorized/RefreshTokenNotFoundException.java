package com.snuquill.paperdx.common.execption.unauthorized;

public class RefreshTokenNotFoundException extends UnauthorizedException{
	public RefreshTokenNotFoundException(String message) {
		super(message);
	}

	public RefreshTokenNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getErrorCode() {
		return "-1002";
	}
}
