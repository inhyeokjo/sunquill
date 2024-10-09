package com.snuquill.paperdx.biz.file.ui;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.snuquill.paperdx.biz.file.domain.FileType;
import com.snuquill.paperdx.biz.file.service.FileService;
import com.snuquill.paperdx.biz.file.ui.dto.FileUploadResponseDto;
import com.snuquill.paperdx.security.domain.CurrentUser;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/file")
@RequiredArgsConstructor
@Validated
public class FileController {

	private final FileService fileService;

	@PostMapping("/upload")
	public FileUploadResponseDto uploadFile(
		@RequestParam("file") MultipartFile file,
		@RequestParam("fileType") FileType fileType,
		@AuthenticationPrincipal CurrentUser currentUser) {

		String endPoint = fileService.uploadFile(fileType, file, currentUser.getUserId());
		return FileUploadResponseDto.with(endPoint);
	}
}
