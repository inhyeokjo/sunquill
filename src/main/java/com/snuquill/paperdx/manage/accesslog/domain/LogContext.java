package com.snuquill.paperdx.manage.accesslog.domain;

import org.apache.commons.lang3.exception.ExceptionUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogContext {
	private static final ThreadLocal<Throwable> exception = new ThreadLocal<>();

	public static void setException(Throwable throwable) {
		exception.set(throwable);
	}

	public static void clear() {
		exception.remove();
	}

	public static String getStackTrace() {
		if (exception.get() == null) {
			return "";
		}
		return ExceptionUtils.getStackTrace(exception.get());
	}
}
