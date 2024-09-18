package com.snuquill.paperdx.common.execption.biz;

import com.snuquill.paperdx.common.execption.CustomException;

public class BizException extends CustomException {
	public BizException(String message) {
		super(message);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return "-9999";
	}
}
