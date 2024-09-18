package com.snuquill.paperdx.common.execption.badrequest;

import java.io.Serial;

public class PaginationException extends BadRequestException {
	@Serial
	private static final long serialVersionUID = 6742416589928630935L;

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
