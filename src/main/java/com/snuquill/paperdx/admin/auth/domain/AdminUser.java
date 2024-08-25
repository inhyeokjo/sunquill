package com.snuquill.paperdx.admin.auth.domain;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "admin_user", schema = "snuquill")
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String mail;

	private String password;

	private String name;
	private boolean retired;

	@OneToMany(mappedBy = "adminUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserRole> userRoles;

	public boolean hasRole() {
		return userRoles != null && !userRoles.isEmpty();
	}

	public Set<Role> getRoleSet() {
		return userRoles.stream()
			.map(UserRole::getRole)
			.collect(Collectors.toSet());
	}

	public Set<String> getRoleCdSet() {
		return getRoleSet().stream()
			.map(Role::getRoleCd)
			.collect(Collectors.toSet());
	}
}
