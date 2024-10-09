package com.snuquill.paperdx.common.execption.biz;

import java.io.Serial;

public class FileReadFailedException extends BizException {
	@Serial
	private static final long serialVersionUID = -2597638223748735635L;

	public FileReadFailedException(String message) {
		super(message);
	}

	public FileReadFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileReadFailedException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getErrorCode() {
		return "-2002";
	}
}
