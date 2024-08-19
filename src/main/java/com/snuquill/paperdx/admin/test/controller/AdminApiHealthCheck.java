package com.snuquill.paperdx.admin.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogLevel;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogging;

@RestController
@RequestMapping("/api/admin")
public class AdminApiHealthCheck {

	@ApiAccessLogging(successLogLevel = ApiAccessLogLevel.NONE, failLogLevel = ApiAccessLogLevel.DETAIL)
	@GetMapping("/health")
	public String adminApiHealthCheck() {
		return "ok";
	}
}
