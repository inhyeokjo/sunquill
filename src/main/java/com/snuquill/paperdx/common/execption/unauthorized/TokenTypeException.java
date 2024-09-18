package com.snuquill.paperdx.common.execption.unauthorized;

import java.io.Serial;

public class TokenTypeException extends UnauthorizedException {
	@Serial
	private static final long serialVersionUID = -5628482385361120761L;

	public TokenTypeException(String message) {
		super(message);
	}

	public TokenTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getErrorCode() {
		return "-3004";
	}
}
