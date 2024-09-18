package com.snuquill.paperdx.common.execption.badrequest;

import java.io.Serial;

public class PathVariableException extends BadRequestException {
	@Serial
	private static final long serialVersionUID = 2803871861899442676L;

	public PathVariableException() {
	}

	public PathVariableException(String message) {
		super(message);
	}

	public PathVariableException(String message, Throwable cause) {
		super(message, cause);
	}

	public PathVariableException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getErrorCode() {
		return "-3002";
	}
}
