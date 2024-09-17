package com.snuquill.paperdx.common.execption.notfound;

public class MethodNotAllowedException extends PageNotFoundException {
	public MethodNotAllowedException(String message) {
		super(message);
	}

	public MethodNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodNotAllowedException(Throwable cause) {
		super(cause);
	}
	public String getErrorCode() {
		return "-3101";
	}
}
