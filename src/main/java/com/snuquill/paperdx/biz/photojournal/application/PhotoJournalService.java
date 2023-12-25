package com.snuquill.paperdx.biz.photojournal.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.biz.photojournal.domain.PhotoJournalRepository;
import com.snuquill.paperdx.biz.photojournal.domain.VolumeService;
import com.snuquill.paperdx.biz.photojournal.domain.PhotoJournal;

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
