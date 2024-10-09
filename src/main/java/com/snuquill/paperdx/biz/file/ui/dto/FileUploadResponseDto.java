package com.snuquill.paperdx.biz.file.ui.dto;

public record FileUploadResponseDto(String endPoint) {
	public static FileUploadResponseDto with(String endPoint) {
		return new FileUploadResponseDto(endPoint);
	}
}
