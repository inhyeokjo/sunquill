package com.snuquill.paperdx.manage.accesslog.application.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.manage.accesslog.application.ApiAccessLogService;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLog;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogLevel;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetailApiAccessLogService implements ApiAccessLogService {

	private final ApiAccessLogRepository apiAccessLogRepository;

	@Override
	public boolean isAdapted(ApiAccessLogLevel apiAccessLogLevel) {
		return ApiAccessLogLevel.DETAIL == apiAccessLogLevel;
	}

	@Override
	@Transactional
	public ApiAccessLog saveApiAccessLog(HttpServletRequest request, HttpServletResponse response, LocalDateTime requestTime, boolean isRest) {
		ApiAccessLog apiAccessLog = ApiAccessLog.makeDetailApiAccessLog(request, response, requestTime, isRest);
		return apiAccessLogRepository.save(apiAccessLog);
	}
}
