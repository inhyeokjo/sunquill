package com.snuquill.paperdx.photojournal.infra;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.magazine.application.MagazineService;
import com.snuquill.paperdx.photojournal.domain.VolumeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VolumeServiceImpl implements VolumeService {

	private final MagazineService magazineService;

	public Integer getLatestMagazineVolumeNumber() {
		return magazineService.getLatestVolumeNumber();
	}

}
