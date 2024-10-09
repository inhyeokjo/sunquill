package com.snuquill.paperdx.biz.file.infra;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.biz.file.domain.FileType;

@Service
public class S3FilePathMapper {
	private final Map<FileType, String> s3PathMap;

	public S3FilePathMapper() {
		s3PathMap = new EnumMap<>(FileType.class);
		s3PathMap.put(FileType.MAGAZINE, "magazine/");
		s3PathMap.put(FileType.STAFF_PROFILE, "photo/staff-profile");
		s3PathMap.put(FileType.MAGAZINE_COVER, "photo/magazine");
		s3PathMap.put(FileType.ARTICLE, "photo/article");

	}

	public String getFilePath(String name, FileType fileType) {
		return s3PathMap.get(fileType) + "/" + name;
	}

}
