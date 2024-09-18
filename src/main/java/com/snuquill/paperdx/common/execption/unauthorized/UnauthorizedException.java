package com.snuquill.paperdx.common.execption.unauthorized;

import com.snuquill.paperdx.common.execption.CustomException;

public abstract class UnauthorizedException extends CustomException {
	protected UnauthorizedException() {
	}

	protected UnauthorizedException(String message) {
		super(message);
	}

	protected UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	protected UnauthorizedException(Throwable cause) {
		super(cause);
	}
}
