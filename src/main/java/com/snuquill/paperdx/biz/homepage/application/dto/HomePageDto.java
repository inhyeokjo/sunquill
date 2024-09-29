package com.snuquill.paperdx.biz.homepage.application.dto;

import java.util.List;

import com.snuquill.paperdx.biz.article.application.ArticleLineDto;
import com.snuquill.paperdx.biz.editorpick.application.dto.EditorPickDto;
import com.snuquill.paperdx.biz.magazine.application.MagazineDto;
import com.snuquill.paperdx.biz.photojournal.application.PhotoJournalDto;
import com.snuquill.paperdx.biz.toparticle.application.TopArticleDto;
import com.snuquill.paperdx.biz.video.application.VideoInfoDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HomePageDto {
	private TopArticleDto topArticleDto;
	private List<ArticleLineDto> articleLineDtoList;
	private EditorPickDto editorPickDto;
	private List<MagazineDto> magazineDtoList;
	private List<VideoInfoDto> videoInfoDtoList;
	private List<PhotoJournalDto> photoJournalDtoList;
}
