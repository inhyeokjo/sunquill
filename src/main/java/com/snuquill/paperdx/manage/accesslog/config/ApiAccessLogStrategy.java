package com.snuquill.paperdx.manage.accesslog.config;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogLevel;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogging;
import com.snuquill.paperdx.utils.HttpServletUtils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ApiAccessLogStrategy {
	ApiAccessLogLevel successLogLevel;
	ApiAccessLogLevel failLogLevel;
	boolean isRest;

	public static ApiAccessLogStrategy of(Object handler) {
		boolean isRest = true; //isRest 값 false인 경우 ModelAndView를 저장하기 때문에 기본적으로 true로 취급한다.

		if (handler instanceof HandlerMethod handlerMethod) {

			isRest = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), ResponseBody.class) != null
				|| AnnotationUtils.findAnnotation(handlerMethod.getMethod(), ResponseBody.class) != null;

			if (handlerMethod.hasMethodAnnotation(ApiAccessLogging.class)) {
				ApiAccessLogging apiAccessLogging = handlerMethod.getMethodAnnotation(ApiAccessLogging.class);
				return new ApiAccessLogStrategy(apiAccessLogging.successLogLevel(), apiAccessLogging.failLogLevel(), isRest);
			}

		}

		return ApiAccessLogStrategy.getDefaultStrategy(isRest);
	}

	public static ApiAccessLogStrategy getDefaultStrategy(boolean isRest) {
		return new ApiAccessLogStrategy(ApiAccessLogLevel.SHORT, ApiAccessLogLevel.DETAIL, isRest);
	}

	public static ApiAccessLogStrategy getNoneStrategy() {
		return new ApiAccessLogStrategy(ApiAccessLogLevel.NONE, ApiAccessLogLevel.NONE, true);
	}

	public boolean needWrap() {
		return successLogLevel == ApiAccessLogLevel.DETAIL || failLogLevel == ApiAccessLogLevel.DETAIL;
	}

	public ApiAccessLogLevel getFinalLogLevel(HttpServletResponse response) {
		return HttpServletUtils.isSuccess(response) ? successLogLevel : failLogLevel;
	}
}
