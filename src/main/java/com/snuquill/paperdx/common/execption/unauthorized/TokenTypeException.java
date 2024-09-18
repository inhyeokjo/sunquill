package com.snuquill.paperdx.common.execption.unauthorized;

public class TokenTypeException extends UnauthorizedException{
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
