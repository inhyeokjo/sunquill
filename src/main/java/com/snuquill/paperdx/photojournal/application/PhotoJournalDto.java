package com.snuquill.paperdx.photojournal.application;

import com.snuquill.paperdx.photojournal.domain.PhotoJournal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhotoJournalDto {

	private String description;
	private Integer volumeNumber;
	private Long photographerId;
	private String photoLink;

	public static PhotoJournalDto of(PhotoJournal photoJournal) {
		return new PhotoJournalDto(
			photoJournal.getDescription(),
			photoJournal.getVolumeNumber(),
			photoJournal.getPhotographerId(),
			photoJournal.getPhotoLink());
	}
}
