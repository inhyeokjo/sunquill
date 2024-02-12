package com.snuquill.paperdx.biz.magazine.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.magazine.domain.MagazineRepository;
import com.snuquill.paperdx.common.execption.PageNotFoundException;

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

	@Transactional(readOnly = true)
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

	public Page<MagazineDto> getMagazinePage(int page) {
		int pageSize = 12;
		if (page <= 0) {
			log.warn("user tried to access non-existing page: /archive/" + "/" + page);
			log.warn("archives page must be greater than 0");
			throw new PageNotFoundException("존재하지 않는 페이지입니다.");
		}
		PageRequest countRequest = PageRequest.of(page - 1, pageSize, Sort.by("publishDate").descending());
		List<MagazineDto> magazineDtoList = magazineRepository.findAll(countRequest).stream().map(MagazineDto::new).toList();
		long magazineCount = magazineRepository.count();
		return new PageImpl<>(magazineDtoList, countRequest, magazineCount);
	}
}
