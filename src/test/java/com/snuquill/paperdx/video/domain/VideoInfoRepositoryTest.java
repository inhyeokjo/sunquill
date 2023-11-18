package com.snuquill.paperdx.video.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VideoInfoRepositoryTest {

	@Autowired
	private VideoInfoRepository videoInfoRepository;

	@Nested
	@DisplayName("findAllByPicked")
	class findAllByPicked {
		@Test
		void noPick() {
			videoInfoRepository.save(VideoInfo.builder()
				.picked(false)
				.title("1")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(false)
				.title("2")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(false)
				.title("3")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(false)
				.title("4")
				.build());

			List<VideoInfo> pickedVideoInfoList = videoInfoRepository.findAllByPicked(true);
			assertThat(pickedVideoInfoList).isEmpty();
		}

		@Test
		void pick1() {
			videoInfoRepository.save(VideoInfo.builder()
				.picked(false)
				.title("1")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(false)
				.title("2")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(true)
				.title("3")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(false)
				.title("4")
				.build());

			List<VideoInfo> pickedVideoInfoList = videoInfoRepository.findAllByPicked(true);
			assertThat(pickedVideoInfoList).hasSize(1);
			assertThat(pickedVideoInfoList.get(0).getTitle()).isEqualTo("3");
		}

		@Test
		void pickAll() {
			videoInfoRepository.save(VideoInfo.builder()
				.picked(true)
				.title("1")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(true)
				.title("2")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(true)
				.title("3")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(true)
				.title("4")
				.build());

			List<VideoInfo> pickedVideoInfoList = videoInfoRepository.findAllByPicked(true);
			assertThat(pickedVideoInfoList).hasSize(4);
		}

		@Test
		void pick3() {
			videoInfoRepository.save(VideoInfo.builder()
				.picked(true)
				.title("1")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(false)
				.title("2")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(true)
				.title("3")
				.build());
			videoInfoRepository.save(VideoInfo.builder()
				.picked(true)
				.title("4")
				.build());

			List<VideoInfo> pickedVideoInfoList = videoInfoRepository.findAllByPicked(true);
			assertThat(pickedVideoInfoList).hasSize(3);
		}
	}
}
