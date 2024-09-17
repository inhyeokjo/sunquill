package com.snuquill.paperdx.common.execption;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponseBody {
	private String errorCode;
	private LocalDateTime timestamp;
	private String title;
	private String status;
	private String detail;
	private String instance;
}
