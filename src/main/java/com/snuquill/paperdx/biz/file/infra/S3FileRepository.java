package com.snuquill.paperdx.biz.file.infra;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.snuquill.paperdx.biz.file.domain.FileRepository;
import com.snuquill.paperdx.biz.file.domain.FileType;
import com.snuquill.paperdx.common.execption.biz.S3FileUploadException;

import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3FileRepository implements FileRepository {
	private final S3Template s3Template;
	private final S3FilePathMapper s3FilePathMapper;

	@Value("${aws.s3.bucket}")
	private String s3BucketName;

	@Override
	public String uploadFile(String name, FileType fileType, InputStream inputFileStream) {
		String filePath = s3FilePathMapper.getFilePath(name, fileType);
		S3Resource uploadedResource = s3Template.upload(s3BucketName, filePath, inputFileStream);
		try {
			URL url = uploadedResource.getURL();
			return url.toString();
		} catch (IOException e) {
			throw new S3FileUploadException("S3에 파일을 업로드하지 못했습니다.", e);
		}
	}
}
