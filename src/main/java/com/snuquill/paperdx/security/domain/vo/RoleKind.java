package com.snuquill.paperdx.security.domain.vo;

import org.springframework.security.core.GrantedAuthority;

public enum RoleKind implements GrantedAuthority {
	SUPER_ADMIN,
	DESIGNER,
	REPORTER,
	PHOTOGRAPHER,
	MEMBER,
	EDITOR,
	SUBEDITOR;

	@Override
	public String getAuthority() {
		return "ROLE_" + name();
	}
}
