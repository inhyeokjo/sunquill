package com.snuquill.paperdx.biz.introduction.ui.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IntroductionRequestDto {
	public record SettingRequest(@NotNull String introduction) {
	}
}
