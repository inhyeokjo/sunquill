package com.snuquill.paperdx.biz.photojournal.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoJournalRepository extends JpaRepository<PhotoJournal, Long> {
	List<PhotoJournal> findAllByVolumeNumber(Integer volumeNumber);
}
