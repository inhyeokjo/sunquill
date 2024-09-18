package com.snuquill.paperdx.common.execption;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponseBody {
	private String errorCode;
	private LocalDateTime timestamp;
	private String title;
	private String detail;
	private String instance;

	public static ErrorResponseBody of(CustomException customException, String requestUri) {
		String fullName = customException.getClass().getName();
		String[] parts = fullName.split("\\.");
		String className = parts[parts.length - 1];

		return new ErrorResponseBody(
			customException.getErrorCode(),
			LocalDateTime.now(),
			className,
			customException.getMessage(),
			requestUri
		);
	}

	public static ErrorResponseBody of(Throwable throwable) {
		return null;
	}
}
