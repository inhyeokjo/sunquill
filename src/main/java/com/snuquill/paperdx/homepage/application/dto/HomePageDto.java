package com.snuquill.paperdx.homepage.application.dto;

import java.util.List;

import com.snuquill.paperdx.article.application.ArticleLineDto;
import com.snuquill.paperdx.editorpick.application.EditorPickDto;
import com.snuquill.paperdx.magazine.application.MagazineDto;
import com.snuquill.paperdx.photojournal.application.PhotoJournalDto;
import com.snuquill.paperdx.toparticle.application.TopArticleDto;
import com.snuquill.paperdx.video.application.VideoInfoDto;

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
