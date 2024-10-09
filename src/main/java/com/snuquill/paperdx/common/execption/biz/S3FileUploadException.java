package com.snuquill.paperdx.common.execption.biz;

import java.io.Serial;

public class S3FileUploadException extends BizException {
	@Serial
	private static final long serialVersionUID = 2094715527812644505L;

	public S3FileUploadException(String message) {
		super(message);
	}

	public S3FileUploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public S3FileUploadException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getErrorCode() {
		return "-2003";
	}
}
