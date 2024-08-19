package com.snuquill.paperdx.admin.auth.ui.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
	@NotBlank
	private final String email;
	@NotBlank
	private final String password;
}
