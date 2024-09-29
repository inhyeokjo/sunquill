package com.snuquill.paperdx.biz.editorpick.ui.dto;

import jakarta.validation.constraints.NotNull;

public class EditorPickRequestDto {
	public record SettingRequest(@NotNull Long id) {
	}
}
