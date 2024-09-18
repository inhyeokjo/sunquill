package com.snuquill.paperdx.manage.accesslog.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.common.execption.biz.BizException;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogLevel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiAccessLogServiceFactory {
	private final List<ApiAccessLogService> apiAccessLogServices;

	public ApiAccessLogService of(ApiAccessLogLevel apiAccessLogLevel) {
		return apiAccessLogServices.stream()
			.filter(apiAccessLogService -> apiAccessLogService.isAdapted(apiAccessLogLevel))
			.findAny()
			.orElseThrow(() -> new BizException(apiAccessLogLevel.name() + " : has no matching Service") {
			});
	}

}
