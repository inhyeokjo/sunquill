package com.snuquill.paperdx.magazine.application;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.snuquill.paperdx.magazine.domain.MagazineRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MagazineService {
	private final MagazineRepository magazineRepository;

	public MagazineDto getMagazine(int volumeNumber) {
		return new MagazineDto(magazineRepository.findByVolumeNumber(volumeNumber));
	}

	public List<MagazineDto> getRecentMagazine(int count) {
		PageRequest countRequest = PageRequest.of(0, count, Sort.by("publishDate").descending());
		return magazineRepository.findAll(countRequest).stream()
			.map(MagazineDto::new)
			.toList();
	}

	public Integer getLatestVolumeNumber() {
		Integer latestVolumeNumber = magazineRepository.findLatestVolumeNumber().orElse(-1);
		if (latestVolumeNumber == -1) {
			log.warn("등록된 Volume이 없습니다.");
		}
		return latestVolumeNumber;
	}
}
