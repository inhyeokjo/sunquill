package com.snuquill.paperdx.security.domain;

import java.util.Set;

import com.snuquill.paperdx.security.domain.vo.RoleKind;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
	private Long id;
	private String mail;
	private String password;
	private String name;
	private boolean retired;
	private Set<RoleKind> roleKindSet;
}
