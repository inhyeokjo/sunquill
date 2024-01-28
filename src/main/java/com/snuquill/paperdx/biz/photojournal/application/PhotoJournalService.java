package com.snuquill.paperdx.biz.photojournal.application;

import java.util.List;

import com.snuquill.paperdx.biz.member.domain.Member;
import com.snuquill.paperdx.biz.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.photojournal.domain.PhotoJournalRepository;
import com.snuquill.paperdx.biz.photojournal.domain.VolumeService;
import com.snuquill.paperdx.biz.photojournal.domain.PhotoJournal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoJournalService {
    private final PhotoJournalRepository photoJournalRepository;
    private final VolumeService volumeService;

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<PhotoJournalDto> getLatestVolumePhotoJournal() {
        Integer volumeNumber = volumeService.getLatestMagazineVolumeNumber();
        List<PhotoJournal> photoJournalList = photoJournalRepository.findAllByVolumeNumber(volumeNumber);
        return photoJournalList.stream().map(PhotoJournalDto::of).toList();
    }

    @Transactional(readOnly = true)
    public List<PhotoJournalWithMemberDto> getLatestVolumePhotoJournalWithMember() {
        Integer volumeNumber = volumeService.getLatestMagazineVolumeNumber();
        List<PhotoJournal> photoJournalList = photoJournalRepository.findAllByVolumeNumber(volumeNumber);
        return photoJournalList.stream().map(photoJournal -> {
            Member member = memberRepository.findMemberById(photoJournal.getPhotographerId());
            return new PhotoJournalWithMemberDto(photoJournal, member);
        }).toList();
    }
}
