package com.snuquill.paperdx.biz.photojournal.ui;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.photojournal.application.PhotoJournalDto;
import com.snuquill.paperdx.biz.photojournal.application.PhotoJournalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/photoJournal")
public class PhotoJournalRestController {
	private final PhotoJournalService photoJournalService;

	@GetMapping("/lastVolume")
	public List<PhotoJournalDto> getLatestVolumePhotoJournalList() {
		return photoJournalService.getLatestVolumePhotoJournal();
	}
}
