package com.snuquill.paperdx.security.infra;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.snuquill.paperdx.admin.auth.service.AdminUserService;
import com.snuquill.paperdx.admin.auth.service.dto.AdminUserDto;
import com.snuquill.paperdx.security.domain.User;
import com.snuquill.paperdx.security.domain.UserRepository;
import com.snuquill.paperdx.security.domain.vo.RoleKind;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DependencyUserRepository implements UserRepository {

	private final AdminUserService adminUserService;

	@Override
	public User getUser(Long userId) {
		AdminUserDto adminUserDto = adminUserService.getAdminUser(userId);

		Set<RoleKind> roleKindSet = adminUserDto.getRoles()
			.stream()
			.map(roleDto -> RoleKind.valueOf(roleDto.getRoleCd()))
			.collect(Collectors.toSet());

		return User.builder()
			.id(adminUserDto.getId())
			.mail(adminUserDto.getMail())
			.password(adminUserDto.getPassword())
			.name(adminUserDto.getName())
			.retired(adminUserDto.isRetired())
			.roleKindSet(roleKindSet)
			.build();
	}
}
