package com.snuquill.paperdx.common.execption.unauthorized;

public class WrongPasswordException extends UnauthorizedException{
	public WrongPasswordException(String message) {
		super(message);
	}

	public WrongPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	String getErrorCode() {
		return "-3003";
	}
}
