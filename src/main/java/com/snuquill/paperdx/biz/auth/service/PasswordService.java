package com.snuquill.paperdx.biz.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordService {

	private final PasswordEncoder passwordEncoder;

	public String generatePassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
}
