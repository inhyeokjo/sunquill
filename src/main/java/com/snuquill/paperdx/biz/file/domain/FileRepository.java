package com.snuquill.paperdx.biz.file.domain;

import java.io.InputStream;

public interface FileRepository {
	String uploadFile(String name, FileType fileType, InputStream inputFileStream);
}
