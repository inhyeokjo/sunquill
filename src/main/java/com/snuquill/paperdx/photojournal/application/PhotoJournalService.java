package com.snuquill.paperdx.photojournal.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.photojournal.domain.PhotoJournal;
import com.snuquill.paperdx.photojournal.domain.PhotoJournalRepository;
import com.snuquill.paperdx.photojournal.domain.VolumeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoJournalService {
	private final PhotoJournalRepository photoJournalRepository;
	private final VolumeService volumeService;

	public List<PhotoJournalDto> getLatestVolumePhotoJournal() {
		Integer volumeNumber = volumeService.getLatestMagazineVolumeNumber();
		List<PhotoJournal> photoJournalList = photoJournalRepository.findAllByVolumeNumber(volumeNumber);
		return photoJournalList.stream().map(PhotoJournalDto::of).toList();
	}
}
