package com.snuquill.paperdx.common.execption.badrequest;

public class  PaginationException extends BadRequestException {
	public PaginationException() {
	}

	public PaginationException(String message) {
		super(message);
	}

	public PaginationException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaginationException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getErrorCode() {
		return "-3000";
	}
}
