package com.snuquill.paperdx.biz.auth.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditAuthTokenRepository extends JpaRepository<AuditAuthToken, Long> {

	Optional<AuditAuthToken> findByAdminUserId(Long userId);
}
