package com.snuquill.paperdx.common.execption;

import java.io.Serial;

public abstract class CustomException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = -3521370284408668179L;

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
