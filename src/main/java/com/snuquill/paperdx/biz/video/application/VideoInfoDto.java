package com.snuquill.paperdx.biz.video.application;

import java.time.LocalDateTime;

import com.snuquill.paperdx.biz.video.domain.VideoInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoInfoDto {

	private String iframeSrc;
	private String title;
	private LocalDateTime uploadDate;

	public static VideoInfoDto of(VideoInfo videoInfo) {
		return new VideoInfoDto(videoInfo.getIframeSrc(), videoInfo.getTitle(), videoInfo.getUploadDate());
	}
}
