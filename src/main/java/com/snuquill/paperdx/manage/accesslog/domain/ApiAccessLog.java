package com.snuquill.paperdx.manage.accesslog.domain;

import static com.snuquill.paperdx.utils.HttpServletUtils.*;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.MDC;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiAccessLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "access_log_id")
	private Long id;

	private String requestId;

	private LocalDateTime requestTime;

	private LocalDateTime responseTime;

	private Long executionTime;

	private String method;

	private String uri;

	private String responseCode;

	private String clientIp;

	@Column(columnDefinition = "text")
	private String userAgent;

	@Column(columnDefinition = "text")
	private String stackTrace;

	@Column(columnDefinition = "text")
	private String requestHeader;

	@Column(columnDefinition = "text")
	private String requestBody;

	@Column(columnDefinition = "text")
	private String responseHeader;

	@Column(columnDefinition = "text")
	private String responseBody;

	public static ApiAccessLog makeDetailApiAccessLog(HttpServletRequest request, HttpServletResponse response, LocalDateTime requestTime, boolean isRest) {
		return ApiAccessLog.builder()
			.requestId(MDC.get("requestId"))
			.requestTime(requestTime)
			.responseTime(LocalDateTime.now())
			.executionTime(Duration.between(requestTime, LocalDateTime.now()).toMillis())
			.method(request.getMethod())
			.uri(request.getRequestURI())
			.responseCode(String.valueOf(response.getStatus()))
			.clientIp(request.getHeader("x-forwarded-for"))
			.userAgent(request.getHeader("User-Agent"))
			.stackTrace(LogContext.getStackTrace())
			.requestHeader(extractHeader(request))
			.requestBody(extractBody(request))
			.responseHeader(extractHeader(response))
			.responseBody(isRest ? extractBody(response) : "")
			.build();
	}

	public static ApiAccessLog makeShortApiAccessLog(HttpServletRequest request, HttpServletResponse response, LocalDateTime requestTime) {
		return ApiAccessLog.builder()
			.requestId(MDC.get("requestId"))
			.requestTime(requestTime)
			.responseTime(LocalDateTime.now())
			.executionTime(Duration.between(requestTime, LocalDateTime.now()).toMillis())
			.method(request.getMethod())
			.uri(request.getRequestURI())
			.responseCode(String.valueOf(response.getStatus()))
			.clientIp(request.getHeader("x-forwarded-for"))
			.userAgent(request.getHeader("User-Agent"))
			.build();
	}

	public static ApiAccessLog emptyApiAccessLog() {
		return new ApiAccessLog();
	}
}
