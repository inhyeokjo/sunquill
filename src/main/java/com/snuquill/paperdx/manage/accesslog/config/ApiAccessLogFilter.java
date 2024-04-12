package com.snuquill.paperdx.manage.accesslog.config;

import static com.snuquill.paperdx.utils.HttpServletUtils.*;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.snuquill.paperdx.manage.accesslog.application.ApiAccessLogService;
import com.snuquill.paperdx.manage.accesslog.application.ApiAccessLogServiceFactory;
import com.snuquill.paperdx.manage.accesslog.domain.LogContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(0)
public class ApiAccessLogFilter extends OncePerRequestFilter {

	private final RequestMappingHandlerMapping requestMappingHandlerMapping;
	private final ApiAccessLogServiceFactory apiAccessLogServiceFactory;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		// 로깅을 진행하지 않을 URI에 대해서는 필터 넘어가도록 처리
		String uri = request.getRequestURI();
		if (uri.startsWith("/css") || uri.startsWith("/images") || uri.equals("/favicon.ico")) {
			filterChain.doFilter(request, response);
			return;
		}

		// ContentCachingResponseWrapper으로 감싸야 하는 경우 감싸는 로직
		Object handler = findHandlerMethod(request);
		ApiAccessLogStrategy apiAccessLogStrategy = ApiAccessLogStrategy.getNoneStrategy();
		if (handler != null) {
			apiAccessLogStrategy = ApiAccessLogStrategy.of(handler);
			if (apiAccessLogStrategy.needWrap()) {
				request = wrapRequest(request);
				response = wrapResponse(response);
			}
		}

		LocalDateTime requestTime = LocalDateTime.now();

		filterChain.doFilter(request, response);

		ApiAccessLogService apiAccessLogService = apiAccessLogServiceFactory.of(apiAccessLogStrategy.getFinalLogLevel(response));
		apiAccessLogService.saveApiAccessLog(request, response, requestTime, apiAccessLogStrategy.isRest);

		unwrap(response);
		LogContext.clear();
	}

	private Object findHandlerMethod(HttpServletRequest request) {
		Object handler;
		try {
			handler = requestMappingHandlerMapping.getHandler(request).getHandler();
		} catch (Exception e) {
			log.warn("Cannot Find Handler Method of request. request method : {}, request uri : {}", request.getMethod(), request.getRequestURI(), e);
			return null;
		}
		return handler;
	}
}
