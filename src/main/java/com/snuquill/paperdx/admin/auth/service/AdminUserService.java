package com.snuquill.paperdx.admin.auth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.admin.auth.domain.AdminUser;
import com.snuquill.paperdx.admin.auth.domain.AdminUserRepository;
import com.snuquill.paperdx.admin.auth.service.dto.AdminUserDto;
import com.snuquill.paperdx.common.execption.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserService {

	private final AdminUserRepository adminUserRepository;

	@Transactional(readOnly = true)
	public AdminUserDto getAdminUser(Long id) {
		Optional<AdminUser> adminUserOptional = adminUserRepository.findByIdWithRoles(id);
		AdminUser adminUser = adminUserOptional.orElseThrow(() -> new DataNotFoundException("ID가 " + id + "인 테이터를 찾을 수 없습니다."));

		if (!adminUser.hasRole()) {
			throw new DataNotFoundException("Role이 없는 사용자 입니다. userId=" + adminUser.getId());
		}

		return AdminUserDto.from(adminUser);
	}
}
