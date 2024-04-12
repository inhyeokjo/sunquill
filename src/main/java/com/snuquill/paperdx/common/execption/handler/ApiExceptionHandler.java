package com.snuquill.paperdx.common.execption.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String baseControllerAdvice() {
		return "404";
	}
}
