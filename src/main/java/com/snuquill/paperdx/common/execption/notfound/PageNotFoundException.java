package com.snuquill.paperdx.common.execption.notfound;

import com.snuquill.paperdx.common.execption.CustomException;

public class PageNotFoundException extends CustomException {
	public PageNotFoundException(String message) {
		super(message);
	}

	public PageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PageNotFoundException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return "-3100";
	}
}
