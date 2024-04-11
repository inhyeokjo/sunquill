package com.snuquill.paperdx.manage.accesslog.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.snuquill.paperdx.manage.accesslog.domain.LogContext;

@Aspect
@Component
public class ExceptionStoreAspect {

	@Before("execution(* com.snuquill.paperdx.common.execption.handler.ApiExceptionHandler.*(..))")
	public void storeException(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (arg instanceof Throwable throwable) {
				LogContext.setException(throwable);
			}
		}
	}
}
