package com.snuquill.paperdx.common.execption.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	@ExceptionHandler(Throwable.class)
	public String baseControllerAdvice() {
		return "404";
	}
}
