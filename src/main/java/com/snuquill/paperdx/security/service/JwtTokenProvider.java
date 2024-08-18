package com.snuquill.paperdx.security.service;

import java.io.Serializable;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.snuquill.paperdx.security.domain.vo.AuthTokenType;
import com.snuquill.paperdx.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {

	private final Key key;
	private final long accessTokenExpTime;
	private final long refreshTokenExpTime;

	public JwtTokenProvider(
		@Value("${jwt.secret}") String secretKey,
		@Value("${jwt.access-token.expiration-time}") long accessTokenExpTime,
		@Value("${jwt.refresh-token.expiration-time}") long refreshTokenExpTime
	) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.accessTokenExpTime = accessTokenExpTime;
		this.refreshTokenExpTime = refreshTokenExpTime;
	}

	public String createAccessToken(Long id, String name) {
		Map<String, ? extends Serializable> claims = Map.of(
			"type", AuthTokenType.ACCESS,
			"id", id,
			"name", name
		);
		return createToken(claims, accessTokenExpTime);
	}

	public String createRefreshToken(Long id) {
		Map<String, ? extends Serializable> claims = Map.of(
			"type", AuthTokenType.REFRESH,
			"id", id
		);
		return createToken(claims, refreshTokenExpTime);
	}

	public Claims parseClaims(String accessToken) {
		JwtUtils.validateToken(key, accessToken);

		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
	}

	private String createToken(Map<String, ?> claims, long expireTime) {
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime tokenValidity = now.plusSeconds(expireTime);
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(Date.from(now.toInstant()))
			.setExpiration(Date.from(tokenValidity.toInstant()))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}
}
