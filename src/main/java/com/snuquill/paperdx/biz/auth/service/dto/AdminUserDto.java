package com.snuquill.paperdx.biz.auth.service.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.snuquill.paperdx.biz.auth.domain.AdminUser;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class AdminUserDto {
	private Long id;
	private String mail;
	private String password;
	private String name;
	private boolean retired;
	private Set<RoleDto> roles;

	public static AdminUserDto from(AdminUser adminUser) {
		Set<RoleDto> roleDtoSet = adminUser.getRoleSet().stream()
			.map(RoleDto::from)
			.collect(Collectors.toSet());

		return AdminUserDto.builder()
			.id(adminUser.getId())
			.mail(adminUser.getMail())
			.password(adminUser.getPassword())
			.name(adminUser.getName())
			.retired(adminUser.isRetired())
			.roles(roleDtoSet)
			.build();
	}
}
