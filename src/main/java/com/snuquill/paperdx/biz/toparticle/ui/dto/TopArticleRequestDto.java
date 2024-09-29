package com.snuquill.paperdx.biz.toparticle.ui.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopArticleRequestDto {
	public record SettingRequest(@NotNull Long id, @NotBlank String summary) {
	}
}
