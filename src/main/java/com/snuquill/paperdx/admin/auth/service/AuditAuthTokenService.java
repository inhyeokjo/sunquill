package com.snuquill.paperdx.admin.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.admin.auth.domain.AuditAuthToken;
import com.snuquill.paperdx.admin.auth.domain.AuditAuthTokenRepository;
import com.snuquill.paperdx.admin.auth.domain.vo.AuthTokenPair;
import com.snuquill.paperdx.common.execption.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditAuthTokenService {

	private final AuditAuthTokenRepository auditAuthTokenRepository;

	public void saveAuditAuthToken(Long userId, AuthTokenPair authTokenPair) {
		String accessTokenHash = authTokenPair.getAccessTokenHash();
		String refreshTokenHash = authTokenPair.getRefreshTokenHash();

		Optional<AuditAuthToken> optionalAuditAuthToken = auditAuthTokenRepository.findByAdminUserId(userId);
		AuditAuthToken newAuditAuthToken = AuditAuthToken.of(userId, accessTokenHash, refreshTokenHash);

		if (optionalAuditAuthToken.isEmpty()) {
			auditAuthTokenRepository.save(newAuditAuthToken);
			return;
		}

		AuditAuthToken auditAuthToken = optionalAuditAuthToken.get();
		auditAuthToken.apply(newAuditAuthToken);
		auditAuthTokenRepository.save(auditAuthToken);
	}

	public AuditAuthToken getAuditAuthTokenByUserId(Long userId) {
		Optional<AuditAuthToken> optionalAuditAuthToken = auditAuthTokenRepository.findByAdminUserId(userId);
		return optionalAuditAuthToken.orElseThrow(() -> new DataNotFoundException("Audit Auth Token 값을 찾을 수 없습니다. UserId=" + userId));
	}
}
