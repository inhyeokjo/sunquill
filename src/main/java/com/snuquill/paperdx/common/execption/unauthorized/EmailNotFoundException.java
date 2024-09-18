package com.snuquill.paperdx.common.execption.unauthorized;

public class EmailNotFoundException extends  UnauthorizedException {
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
