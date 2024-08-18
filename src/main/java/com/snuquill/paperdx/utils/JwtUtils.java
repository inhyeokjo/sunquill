package com.snuquill.paperdx.utils;

import java.security.Key;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class JwtUtils {

	public static void validateToken(Key key, String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("Invalid JWT Token", e);
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT Token", e);
		} catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT Token", e);
		} catch (IllegalArgumentException e) {
			log.info("JWT claims string is empty.", e);
		}
	}

	public static String extractJwtTokenFromRequest(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		return JwtUtils.extractTokenFromBearerString(authorizationHeader);
	}

	private static String extractTokenFromBearerString(String bearerStrings) {
		if (StringUtils.isBlank(bearerStrings)) {
			return "";
		}
		return bearerStrings.substring("Bearer ".length());
	}
}