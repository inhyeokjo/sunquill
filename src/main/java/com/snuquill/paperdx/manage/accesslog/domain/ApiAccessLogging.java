package com.snuquill.paperdx.manage.accesslog.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiAccessLogging {
	ApiAccessLogLevel successLogLevel() default ApiAccessLogLevel.SHORT;

	ApiAccessLogLevel failLogLevel() default ApiAccessLogLevel.DETAIL;
}
