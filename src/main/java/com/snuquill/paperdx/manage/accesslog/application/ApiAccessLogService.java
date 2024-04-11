package com.snuquill.paperdx.manage.accesslog.application;

import java.time.LocalDateTime;

import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLog;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogLevel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ApiAccessLogService {

	boolean isAdapted(ApiAccessLogLevel apiAccessLogLevel);

	ApiAccessLog saveApiAccessLog(HttpServletRequest request, HttpServletResponse response, LocalDateTime requestTime, boolean isRest);
}
