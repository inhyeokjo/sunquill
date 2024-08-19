package com.snuquill.paperdx.admin.auth.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
	Optional<AdminUser> findAdminUserByMail(String mail);

	@Query("select au FROM AdminUser au LEFT JOIN FETCH au.userRoles WHERE au.id=:id")
	Optional<AdminUser> findByIdWithRoles(Long id);
}
