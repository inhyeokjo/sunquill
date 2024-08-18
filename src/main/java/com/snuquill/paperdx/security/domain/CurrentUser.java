package com.snuquill.paperdx.security.domain;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.snuquill.paperdx.security.domain.vo.RoleKind;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder(access = AccessLevel.PRIVATE)
public class CurrentUser implements UserDetails {
	@Serial
	private static final long serialVersionUID = -6698528204934471671L;

	private final Long id;
	private final String name;
	private final String password;
	private final boolean retired;
	private final Set<RoleKind> roleKindSet;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authoritySet = new HashSet<>();

		if (this.roleKindSet != null) {
			authoritySet.addAll(this.roleKindSet);
		}
		return authoritySet;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !retired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return roleKindSet != null;
	}

	public static CurrentUser from(User user) {
		return CurrentUser.builder()
			.id(user.getId())
			.name(user.getName())
			.password(user.getPassword())
			.retired(user.isRetired())
			.roleKindSet(user.getRoleKindSet())
			.build();
	}
}
