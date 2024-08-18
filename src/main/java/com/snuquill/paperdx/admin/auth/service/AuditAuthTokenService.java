package com.snuquill.paperdx.admin.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.admin.auth.domain.AuditAuthToken;
import com.snuquill.paperdx.admin.auth.domain.AuditAuthTokenRepository;
import com.snuquill.paperdx.utils.MD5Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditAuthTokenService {

	private final AuditAuthTokenRepository auditAuthTokenRepository;

	public void saveAuditAuthToken(Long userId, String accessToken, String refreshToken) {

		Optional<AuditAuthToken> optionalAuditAuthToken = auditAuthTokenRepository.findByAdminUserId(userId);
		AuditAuthToken newAuditAuthToken = AuditAuthToken.of(userId, MD5Utils.hash(accessToken), MD5Utils.hash(refreshToken));

		if (optionalAuditAuthToken.isEmpty()) {
			auditAuthTokenRepository.save(newAuditAuthToken);
			return;
		}

		AuditAuthToken auditAuthToken = optionalAuditAuthToken.get();
		auditAuthToken.apply(newAuditAuthToken);
		auditAuthTokenRepository.save(auditAuthToken);
	}
}
