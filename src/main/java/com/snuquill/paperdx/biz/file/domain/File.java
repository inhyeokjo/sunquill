package com.snuquill.paperdx.biz.file.domain;

import com.snuquill.paperdx.biz.article.domain.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String originalFileName;
	private String storedFileName;
	private String fileEndpoint;
	private Long uploadUser;

	public static File with(String originalFileName, String storedFileName, String fileEndpoint, Long uploadUser) {
		return new File(null, originalFileName, storedFileName, fileEndpoint, uploadUser);
	}

}
