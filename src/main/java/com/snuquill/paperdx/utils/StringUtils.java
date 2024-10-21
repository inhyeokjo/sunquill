package com.snuquill.paperdx.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {
	public static String extractStringFromHtml(String html) {
		return html
			.replaceAll("<[^>]*>", "")
			.replaceAll("\\.(\\S)", ". $1")
			.trim();
	}
}
