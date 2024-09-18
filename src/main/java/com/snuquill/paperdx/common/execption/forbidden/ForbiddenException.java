package com.snuquill.paperdx.common.execption.forbidden;

import com.snuquill.paperdx.common.execption.CustomException;

public abstract class ForbiddenException extends CustomException {
	public ForbiddenException() {
	}

	public ForbiddenException(String message) {
		super(message);
	}

	public ForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForbiddenException(Throwable cause) {
		super(cause);
	}
}
