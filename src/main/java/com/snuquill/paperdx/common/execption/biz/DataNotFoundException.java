package com.snuquill.paperdx.common.execption.biz;

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

	@Override
	public String getErrorCode() {
		return "2001";
	}
}
