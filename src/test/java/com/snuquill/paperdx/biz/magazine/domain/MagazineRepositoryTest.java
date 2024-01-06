package com.snuquill.paperdx.biz.magazine.domain;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.snuquill.paperdx.testconfig.BaseDataJpaTest;

class MagazineRepositoryTest extends BaseDataJpaTest {

	@Autowired
	private MagazineRepository magazineRepository;

	@Test
	void findTopNByPublishDateDesc() {
		Magazine magazine1 = Magazine.builder()
			.publishDate(LocalDate.now())
			.fileLink("fileLink")
			.volumeNumber(1)
			.volumeCoverLink("volumeCoverLink")
			.build();
		Magazine magazine2 = Magazine.builder()
			.publishDate(LocalDate.now().plusDays(1))
			.fileLink("fileLink")
			.volumeNumber(2)
			.volumeCoverLink("volumeCoverLink")
			.build();
		Magazine magazine3 = Magazine.builder()
			.publishDate(LocalDate.now().plusDays(2))
			.fileLink("fileLink")
			.volumeNumber(3)
			.volumeCoverLink("volumeCoverLink")
			.build();
		Magazine magazine4 = Magazine.builder()
			.publishDate(LocalDate.now().plusDays(3))
			.fileLink("fileLink")
			.volumeNumber(4)
			.volumeCoverLink("volumeCoverLink")
			.build();
		Magazine magazine5 = Magazine.builder()
			.publishDate(LocalDate.now().plusDays(4))
			.fileLink("fileLink")
			.volumeNumber(5)
			.volumeCoverLink("volumeCoverLink")
			.build();
		magazineRepository.save(magazine1);
		magazineRepository.save(magazine2);
		magazineRepository.save(magazine3);
		magazineRepository.save(magazine4);
		magazineRepository.save(magazine5);

		PageRequest countRequest = PageRequest.of(0, 3, Sort.by("publishDate").descending());
		Page<Magazine> all = magazineRepository.findAll(countRequest);
		List<Magazine> magazineList = all.stream().toList();

		Assertions.assertThat(magazineList).hasSize(3);
		Assertions.assertThat(magazineList.get(0).getVolumeNumber()).isEqualTo(magazine5.getVolumeNumber());
		Assertions.assertThat(magazineList.get(1).getVolumeNumber()).isEqualTo(magazine4.getVolumeNumber());
		Assertions.assertThat(magazineList.get(2).getVolumeNumber()).isEqualTo(magazine3.getVolumeNumber());
	}

	@Test
	void findLatestVolumeNumber() {
		Magazine magazine1 = Magazine.builder()
			.volumeNumber(2)
			.build();
		Magazine magazine2 = Magazine.builder()
			.volumeNumber(2)
			.build();
		Magazine magazine3 = Magazine.builder()
			.volumeNumber(2)
			.build();
		Magazine magazine4 = Magazine.builder()
			.volumeNumber(5)
			.build();
		Magazine magazine5 = Magazine.builder()
			.volumeNumber(9999999)
			.build();
		magazineRepository.save(magazine1);
		magazineRepository.save(magazine2);
		magazineRepository.save(magazine3);
		magazineRepository.save(magazine4);
		magazineRepository.save(magazine5);

		Integer latestVolumeNumber = magazineRepository.findLatestVolumeNumber().get();
		Assertions.assertThat(latestVolumeNumber).isEqualTo(9999999);
	}
}
