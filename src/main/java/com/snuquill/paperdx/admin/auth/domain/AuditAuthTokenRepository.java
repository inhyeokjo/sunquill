package com.snuquill.paperdx.admin.auth.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditAuthTokenRepository extends JpaRepository<AuditAuthToken, Long> {

	Optional<AuditAuthToken> findByAdminUserId(Long userId);
}
