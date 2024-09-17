package com.snuquill.paperdx.common.execption.biz;

public class BizException extends RuntimeException{
	public BizException(String message) {
		super(message);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}
}
