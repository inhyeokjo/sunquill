package com.snuquill.paperdx.admin.auth.service.dto;

import com.snuquill.paperdx.admin.auth.domain.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDto {

	private String roleCd;
	private String roleName;
	private String description;

	public static RoleDto from(Role role) {
		return new RoleDto(role.getRoleCd(), role.getRoleName(), role.getDescription());
	}
}
