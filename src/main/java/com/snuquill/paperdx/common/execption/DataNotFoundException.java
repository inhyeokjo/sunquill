package com.snuquill.paperdx.common.execption;

public class DataNotFoundException extends BizException{
	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}
}
