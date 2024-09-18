package com.snuquill.paperdx.common.execption.biz;

import java.io.Serial;

public class
DataNotFoundException extends BizException {
	@Serial
	private static final long serialVersionUID = -5326324482464971535L;

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getErrorCode() {
		return "2001";
	}
}
