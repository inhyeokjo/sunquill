package com.snuquill.paperdx.common.execption.biz;

import java.io.Serial;

import com.snuquill.paperdx.common.execption.CustomException;

public class BizException extends CustomException {
	@Serial
	private static final long serialVersionUID = -9136973487537752673L;

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
