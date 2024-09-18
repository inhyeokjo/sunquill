package com.snuquill.paperdx.common.execption.unauthorized;

import java.io.Serial;

public class EmailNotFoundException extends UnauthorizedException {
	@Serial
	private static final long serialVersionUID = 4811336711022639461L;

	public EmailNotFoundException(String message) {
		super(message);
	}

	public EmailNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getErrorCode() {
		return "-2000";
	}
}
