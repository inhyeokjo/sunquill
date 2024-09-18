package com.snuquill.paperdx.common.execption.badrequest;

import com.snuquill.paperdx.common.execption.CustomException;

public abstract class BadRequestException extends CustomException {
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
}
