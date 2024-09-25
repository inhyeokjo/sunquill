package com.snuquill.paperdx.biz.introduction.ui.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostIntroductionRequestDto {
	@NotNull
	private String introduction;
}
