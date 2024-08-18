package com.snuquill.paperdx.admin.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.admin.auth.domain.AuditAuthToken;
import com.snuquill.paperdx.admin.auth.domain.AuditAuthTokenRepository;
import com.snuquill.paperdx.admin.auth.domain.vo.AuthTokenPair;
import com.snuquill.paperdx.common.execption.DataNotFoundException;
import com.snuquill.paperdx.utils.MD5Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditAuthTokenService {

	private final AuditAuthTokenRepository auditAuthTokenRepository;

	public AuthTokenPair saveAuditAuthToken(Long userId, String accessToken, String refreshToken) {

		Optional<AuditAuthToken> optionalAuditAuthToken = auditAuthTokenRepository.findByAdminUserId(userId);
		AuditAuthToken newAuditAuthToken = AuditAuthToken.of(userId, MD5Utils.hash(accessToken), MD5Utils.hash(refreshToken));

		if (optionalAuditAuthToken.isEmpty()) {
			auditAuthTokenRepository.save(newAuditAuthToken);
			return AuthTokenPair.of(accessToken, refreshToken);
		}

		AuditAuthToken auditAuthToken = optionalAuditAuthToken.get();
		auditAuthToken.apply(newAuditAuthToken);
		auditAuthTokenRepository.save(auditAuthToken);
		return AuthTokenPair.of(accessToken, refreshToken);
	}

	public AuditAuthToken getAuditAuthTokenByUserId(Long userId) {
		Optional<AuditAuthToken> optionalAuditAuthToken = auditAuthTokenRepository.findByAdminUserId(userId);
		return optionalAuditAuthToken.orElseThrow(() -> new DataNotFoundException("Audit Auth Token 값을 찾을 수 없습니다. UserId=" + userId));
	}
}
