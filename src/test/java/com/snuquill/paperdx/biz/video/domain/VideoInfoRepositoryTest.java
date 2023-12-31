package com.snuquill.paperdx.biz.video.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.snuquill.paperdx.testconfig.BaseDataJpaTest;

class VideoInfoRepositoryTest extends BaseDataJpaTest {

	@Autowired
	private VideoInfoRepository videoInfoRepository;

	@Test
	void pageRequestTest() {
		videoInfoRepository.save(VideoInfo.builder()
			.title("1")
			.uploadDate(LocalDateTime.now())
			.build());
		videoInfoRepository.save(VideoInfo.builder()
			.title("2")
			.uploadDate(LocalDateTime.now().minusDays(2))
			.build());
		videoInfoRepository.save(VideoInfo.builder()
			.title("3")
			.uploadDate(LocalDateTime.now().minusDays(3))
			.build());

		PageRequest countRequest = PageRequest.of(0, 2, Sort.by("uploadDate").descending());
		Page<VideoInfo> findVideoInfo = videoInfoRepository.findAll(countRequest);
		List<VideoInfo> findVideoInfoList = findVideoInfo.toList();
		assertThat(findVideoInfoList).hasSize(2);
		assertThat(findVideoInfoList.get(0).getTitle()).isEqualTo("1");
		assertThat(findVideoInfoList.get(1).getTitle()).isEqualTo("2");
	}
}
