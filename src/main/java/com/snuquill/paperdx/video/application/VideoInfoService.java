package com.snuquill.paperdx.video.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.video.domain.VideoInfo;
import com.snuquill.paperdx.video.domain.VideoInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoInfoService {

	private final VideoInfoRepository videoInfoRepository;

	public List<VideoInfoDto> getPickedVideo() {
		List<VideoInfo> pickedVideoInfo = videoInfoRepository.findAllByPicked(true);
		if (pickedVideoInfo.size() != 3) {
			log.warn("선택된 비디오가 3개가 아닙니다. 관리자는 비디오를 3개로 유지해주세요");
		}
		return pickedVideoInfo.stream().map(VideoInfoDto::of).toList();
	}
}
