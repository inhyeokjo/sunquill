package com.snuquill.paperdx.video.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

	public List<VideoInfoDto> getRecentUploadVideoInfo(int retrieveCount) {
		PageRequest countRequest = PageRequest.of(0, retrieveCount, Sort.by("upload_date").descending());
		Page<VideoInfo> findVideoInfo = videoInfoRepository.findAll(countRequest);
		return findVideoInfo.stream().map(VideoInfoDto::of).toList();
	}

}
