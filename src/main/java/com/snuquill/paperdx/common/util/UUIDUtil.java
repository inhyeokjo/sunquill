package com.snuquill.paperdx.common.util;

import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UUIDUtil {
	public static String generateShortUUID() {
		return UUID.randomUUID().toString().substring(0, 6);
	}
}
