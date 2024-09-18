package com.snuquill.paperdx.common.execption.unauthorized;

import java.io.Serial;

public class JwtTokenParsingException extends UnauthorizedException {
	@Serial
	private static final long serialVersionUID = 2309150733419976256L;

	public JwtTokenParsingException(String message) {
		super(message);
	}

	public JwtTokenParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getErrorCode() {
		return "-3005";
	}
}
