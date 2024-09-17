package com.snuquill.paperdx.common.execption.notfound;

public abstract class PageNotFoundException extends RuntimeException {
	protected PageNotFoundException(String message) {
		super(message);
	}

	protected PageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	protected PageNotFoundException(Throwable cause) {
		super(cause);
	}

	abstract String getErrorCode();
}
