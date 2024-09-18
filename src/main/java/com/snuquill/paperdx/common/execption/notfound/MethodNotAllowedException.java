package com.snuquill.paperdx.common.execption.notfound;

import java.io.Serial;

public class MethodNotAllowedException extends PageNotFoundException {
	@Serial
	private static final long serialVersionUID = -6276857053387894098L;

	public MethodNotAllowedException(String message) {
		super(message);
	}

	public MethodNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodNotAllowedException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getErrorCode() {
		return "-3101";
	}
}
