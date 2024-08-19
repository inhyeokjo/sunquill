package com.snuquill.paperdx.utils;

import org.springframework.util.DigestUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MD5Utils {

	public static String hash(String text) {
		return DigestUtils.md5DigestAsHex(text.getBytes());
	}
}
