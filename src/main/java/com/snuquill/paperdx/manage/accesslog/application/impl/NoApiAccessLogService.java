package com.snuquill.paperdx.manage.accesslog.application.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.manage.accesslog.application.ApiAccessLogService;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLog;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogLevel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class NoApiAccessLogService implements ApiAccessLogService {
	@Override
	public boolean isAdapted(ApiAccessLogLevel apiAccessLogLevel) {
		return ApiAccessLogLevel.NONE == apiAccessLogLevel;
	}

	@Override
	public ApiAccessLog saveApiAccessLog(HttpServletRequest request, HttpServletResponse response, LocalDateTime requestTime, boolean isRest) {
		return ApiAccessLog.emptyApiAccessLog();
	}

}
