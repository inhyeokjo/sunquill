package com.snuquill.paperdx.common.execption.badrequest;

import java.io.Serial;

public class QueryParameterException extends BadRequestException {
	@Serial
	private static final long serialVersionUID = -8126968889534689643L;

	public QueryParameterException() {
	}

	public QueryParameterException(String message) {
		super(message);
	}

	public QueryParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryParameterException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getErrorCode() {
		return "-3001";
	}
}
