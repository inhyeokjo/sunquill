package com.snuquill.paperdx.magazine.application;

import java.time.LocalDate;

import com.snuquill.paperdx.magazine.domain.Magazine;

import lombok.Data;

@Data
public class MagazineDto {

	private int volumeNumber;
	private LocalDate publishDate;
	private String volumeCoverLink;
	private String fileLink;
	public MagazineDto(Magazine magazine) {
		volumeNumber = magazine.getVolumeNumber();
		publishDate = magazine.getPublishDate();
		volumeCoverLink = magazine.getVolumeCoverLink();
		fileLink = magazine.getFileLink();
	}
}
