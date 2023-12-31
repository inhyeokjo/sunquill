package com.snuquill.paperdx.biz.video.application;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.video.domain.VideoInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoInfoService {

	private final VideoInfoRepository videoInfoRepository;

	@Transactional(readOnly = true)
	public List<VideoInfoDto> getRecentUploadVideoInfo(int retrieveCount) {
		PageRequest countRequest = PageRequest.of(0, retrieveCount, Sort.by("uploadDate").descending());
		return videoInfoRepository.findAll(countRequest).stream()
			.map(VideoInfoDto::of)
			.toList();
	}

}
