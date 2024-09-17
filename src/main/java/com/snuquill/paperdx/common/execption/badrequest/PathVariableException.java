package com.snuquill.paperdx.common.execption.badrequest;

public class PathVariableException extends BadRequestException{
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
	String getErrorCode() {
		return "-3002";
	}
}
