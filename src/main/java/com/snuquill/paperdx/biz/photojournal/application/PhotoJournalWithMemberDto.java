package com.snuquill.paperdx.biz.photojournal.application;

import com.snuquill.paperdx.biz.member.domain.Member;
import com.snuquill.paperdx.biz.photojournal.domain.PhotoJournal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhotoJournalWithMemberDto {

    private String description;
    private Integer volumeNumber;
    private Long photographerId;
    private String photoLink;

    private String photographerName;

    public PhotoJournalWithMemberDto(PhotoJournal photoJournal, Member member) {
        this.description = photoJournal.getDescription();
        this.volumeNumber = photoJournal.getVolumeNumber();
        this.photographerId = photoJournal.getPhotographerId();
        this.photoLink = photoJournal.getPhotoLink();
        this.photographerName = member.getName();
    }

    public static PhotoJournalWithMemberDto of(PhotoJournal photoJournal, Member member) {
        return new PhotoJournalWithMemberDto(
                photoJournal.getDescription(),
                photoJournal.getVolumeNumber(),
                photoJournal.getPhotographerId(),
                photoJournal.getPhotoLink(),
                member.getName());
    }
}
