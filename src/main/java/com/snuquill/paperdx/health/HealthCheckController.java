package com.snuquill.paperdx.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogLevel;
import com.snuquill.paperdx.manage.accesslog.domain.ApiAccessLogging;

@RestController
public class HealthCheckController {

	@ApiAccessLogging(successLogLevel = ApiAccessLogLevel.NONE, failLogLevel = ApiAccessLogLevel.DETAIL)
	@GetMapping("/health")
	public String healthCheck() {
		return "Hello!";
	}
}
