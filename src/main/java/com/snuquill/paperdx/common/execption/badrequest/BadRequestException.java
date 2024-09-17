package com.snuquill.paperdx.common.execption.badrequest;

public abstract class BadRequestException extends RuntimeException{
	protected BadRequestException() {
	}

	protected BadRequestException(String message) {
		super(message);
	}

	protected BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	protected BadRequestException(Throwable cause) {
		super(cause);
	}

	abstract String getErrorCode();
}
