package com.snuquill.paperdx.biz.auth.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.auth.domain.AdminUser;
import com.snuquill.paperdx.biz.auth.domain.AdminUserRepository;
import com.snuquill.paperdx.biz.auth.service.dto.AdminUserDto;
import com.snuquill.paperdx.common.execption.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserService {

	private final AdminUserRepository adminUserRepository;

	@Transactional(readOnly = true)
	public AdminUserDto getAdminUserDto(Long id) {
		AdminUser adminUser = getAdminUser(id);
		return AdminUserDto.from(adminUser);
	}

	@Transactional(readOnly = true)
	public AdminUser getAdminUser(Long id) {
		Optional<AdminUser> adminUserOptional = adminUserRepository.findByIdWithRoles(id);
		AdminUser adminUser = adminUserOptional.orElseThrow(() -> new DataNotFoundException("ID가 " + id + "인 User를 찾을 수 없습니다."));
		if (!adminUser.hasRole()) {
			throw new DataNotFoundException("Role이 없는 사용자 입니다. userId=" + adminUser.getId());
		}
		return adminUser;
	}

	public AdminUser getAdminUserByMail(String email) {
		Optional<AdminUser> userOptional = adminUserRepository.findAdminUserByMail(email);
		if (userOptional.isEmpty()) {
			throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
		}
		return userOptional.get();
	}
}
