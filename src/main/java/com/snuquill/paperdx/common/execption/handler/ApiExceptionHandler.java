package com.snuquill.paperdx.common.execption.handler;

import static com.snuquill.paperdx.common.constraint.NamedTemplate.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.snuquill.paperdx.common.execption.ErrorResponseBody;
import com.snuquill.paperdx.common.execption.badrequest.BadRequestException;
import com.snuquill.paperdx.common.execption.biz.BizException;
import com.snuquill.paperdx.common.execption.forbidden.ForbiddenException;
import com.snuquill.paperdx.common.execption.notfound.PageNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	@ExceptionHandler(PageNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Object pageNotFoundExceptionControllerAdvice(PageNotFoundException pageNotFoundException, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if (!requestUri.startsWith("/api")) {
			return ERROR_PAGE;
		}
		return new ResponseEntity<>(ErrorResponseBody.of(pageNotFoundException, requestUri), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Object pageNotFoundExceptionControllerAdvice(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if (!requestUri.startsWith("/api")) {
			return ERROR_PAGE;
		}
		return new ResponseEntity<>(ErrorResponseBody.of(methodArgumentTypeMismatchException), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Object handleNoHandlerFoundException(NoHandlerFoundException noHandlerFoundException, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if (!requestUri.startsWith("/api")) {
			return ERROR_PAGE;
		}
		return new ResponseEntity<>(ErrorResponseBody.of(noHandlerFoundException), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object pageNotFoundExceptionControllerAdvice(BadRequestException badRequestException, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if (!requestUri.startsWith("/api")) {
			return ERROR_PAGE;
		}
		return new ResponseEntity<>(ErrorResponseBody.of(badRequestException, requestUri), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ForbiddenException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Object forbiddenExceptionControllerAdvice(ForbiddenException forbiddenException, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if (!requestUri.startsWith("/api")) {
			return ERROR_PAGE;
		}
		return new ResponseEntity<>(ErrorResponseBody.of(forbiddenException, requestUri), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Object authenticationExceptionControllerAdvice(AuthenticationException authenticationException, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if (!requestUri.startsWith("/api")) {
			return ERROR_PAGE;
		}
		return new ResponseEntity<>(ErrorResponseBody.of(authenticationException), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(BizException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Object bizExceptionControllerAdvice(BizException bizException, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if (!requestUri.startsWith("/api")) {
			return ERROR_PAGE;
		}
		return new ResponseEntity<>(ErrorResponseBody.of(bizException, requestUri), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Object baseControllerAdvice(Throwable throwable, HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		if (!requestUri.startsWith("/api")) {
			return ERROR_PAGE;
		}
		return new ResponseEntity<>(ErrorResponseBody.of(throwable), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
