package com.snuquill.paperdx.common.execption.unauthorized;

import java.io.Serial;

public class RefreshTokenNotFoundException extends UnauthorizedException {
	@Serial
	private static final long serialVersionUID = -6832069765865198778L;

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
