package com.snuquill.paperdx.common.execption.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.snuquill.paperdx.common.execption.biz.BizException;
import com.snuquill.paperdx.common.execption.forbidden.ForbiddenException;
import com.snuquill.paperdx.common.execption.notfound.PageNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	@ExceptionHandler(PageNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String pageNotFoundExceptionControllerAdvice() {
		return "404";
	}

	@ExceptionHandler(ForbiddenException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public String forbiddenExceptionControllerAdvice() {
		return "403";
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String authenticationExceptionControllerAdvice() {
		return "401";
	}

	@ExceptionHandler(BizException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String bizExceptionControllerAdvice() {
		return "500";
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String baseControllerAdvice() {
		return "500";
	}
}
