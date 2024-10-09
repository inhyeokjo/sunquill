package com.snuquill.paperdx.biz.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.snuquill.paperdx.biz.file.domain.File;
import com.snuquill.paperdx.biz.file.domain.FileMetadataRepository;
import com.snuquill.paperdx.biz.file.domain.FileRepository;
import com.snuquill.paperdx.biz.file.domain.FileType;
import com.snuquill.paperdx.common.execption.biz.FileReadFailedException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileRepository fileRepository;
	private final FileMetadataRepository fileMetadataRepository;

	@Transactional
	public String uploadFile(FileType fileType, MultipartFile file, long userId) {
		String originalFilename = file.getOriginalFilename();
		String fileExtension = "";
		if (originalFilename != null) {
			fileExtension = getFileExtension(originalFilename);
		}
		String storedName = UUID.randomUUID() + fileExtension;
		InputStream inputStream;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {
			throw new FileReadFailedException("파일을 읽는데 실패했습니다.", e);
		}
		String endPoint = fileRepository.uploadFile(storedName, fileType, inputStream);

		File uploadedFile = File.with(originalFilename, storedName, endPoint, userId);
		fileMetadataRepository.save(uploadedFile);
		return endPoint;
	}

	private String getFileExtension(String fileName) {
		int lastIndex = fileName.lastIndexOf('.');
		if (lastIndex == -1 || lastIndex == fileName.length() - 1) {
			return ""; // 확장자가 없거나 점(.) 뒤에 아무것도 없음
		}
		return fileName.substring(lastIndex);
	}
}
