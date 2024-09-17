package com.snuquill.paperdx.common.execption.notfound;

public class PageNotFoundException extends RuntimeException {
	public PageNotFoundException(String message) {
		super(message);
	}

	public PageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PageNotFoundException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return "-3100";
	}
}
