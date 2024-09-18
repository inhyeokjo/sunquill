package com.snuquill.paperdx.common.execption.unauthorized;

import java.io.Serial;

public class WrongPasswordException extends UnauthorizedException {
	@Serial
	private static final long serialVersionUID = -8820660023315179020L;

	public WrongPasswordException(String message) {
		super(message);
	}

	public WrongPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getErrorCode() {
		return "-3003";
	}
}
