package com.snuquill.paperdx.admin.auth.domain;

import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin_audit_auth_token", schema = "snuquill")
public class AuditAuthToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long adminUserId;
	private String authTokenHash;
	private LocalDateTime authTokenIssueDate;
	private String refreshTokenHash;
	private LocalDateTime refreshTokenIssueDate;

	public static AuditAuthToken of(Long userId, String authTokenHash, String refreshTokenHash) {
		return new AuditAuthToken(null, userId, authTokenHash, LocalDateTime.now(), refreshTokenHash, LocalDateTime.now());
	}

	public void apply(AuditAuthToken newAuditAuthToken) {
		this.adminUserId = newAuditAuthToken.getAdminUserId();
		this.authTokenHash = newAuditAuthToken.getAuthTokenHash();
		this.authTokenIssueDate = newAuditAuthToken.getAuthTokenIssueDate();
		this.refreshTokenHash = newAuditAuthToken.getRefreshTokenHash();
		this.refreshTokenIssueDate = newAuditAuthToken.getRefreshTokenIssueDate();
	}
}
