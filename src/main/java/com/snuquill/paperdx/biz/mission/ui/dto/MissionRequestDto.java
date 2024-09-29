package com.snuquill.paperdx.biz.mission.ui.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MissionRequestDto {
	public record SettingRequest(@NotNull String mission) {
	}
}
