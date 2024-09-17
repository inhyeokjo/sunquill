package com.snuquill.paperdx.common.execption.unauthorized;

public class JwtTokenParsingException extends UnauthorizedException{
	public JwtTokenParsingException(String message) {
		super(message);
	}

	public JwtTokenParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	String getErrorCode() {
		return "-3005";
	}
}
