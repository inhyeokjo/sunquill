package com.snuquill.paperdx.common.execption;

public abstract class CustomException extends RuntimeException {
	protected CustomException() {
	}

	protected CustomException(String message) {
		super(message);
	}

	protected CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	protected CustomException(Throwable cause) {
		super(cause);
	}

	protected abstract String getErrorCode();
}
