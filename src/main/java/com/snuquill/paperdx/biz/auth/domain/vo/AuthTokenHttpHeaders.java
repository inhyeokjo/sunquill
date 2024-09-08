package com.snuquill.paperdx.biz.auth.domain.vo;

import org.springframework.http.HttpHeaders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthTokenHttpHeaders {
	public static final String AUTHORIZATION = HttpHeaders.AUTHORIZATION;
	public static final String REFRESH_TOKEN = "Refresh-Token";
}
