package com.snuquill.paperdx.video.application;

import com.snuquill.paperdx.video.domain.VideoInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoInfoDto {

	private String iframeSrc;
	private String title;

	public static VideoInfoDto of(VideoInfo videoInfo) {
		return new VideoInfoDto(videoInfo.getIframeSrc(), videoInfo.getTitle());
	}
}
