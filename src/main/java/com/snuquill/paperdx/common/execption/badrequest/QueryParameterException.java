package com.snuquill.paperdx.common.execption.badrequest;

public class QueryParameterException extends BadRequestException{
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
