package com.snuquill.paperdx.biz.homepage.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.biz.article.application.ArticleLineDto;
import com.snuquill.paperdx.biz.article.application.ArticleLineService;
import com.snuquill.paperdx.biz.editorpick.application.dto.EditorPickDto;
import com.snuquill.paperdx.biz.editorpick.application.EditorPickService;
import com.snuquill.paperdx.biz.homepage.application.dto.HomePageDto;
import com.snuquill.paperdx.biz.magazine.application.MagazineDto;
import com.snuquill.paperdx.biz.magazine.application.MagazineService;
import com.snuquill.paperdx.biz.photojournal.application.PhotoJournalDto;
import com.snuquill.paperdx.biz.photojournal.application.PhotoJournalService;
import com.snuquill.paperdx.biz.toparticle.application.TopArticleDto;
import com.snuquill.paperdx.biz.toparticle.application.TopArticleService;
import com.snuquill.paperdx.biz.video.application.VideoInfoDto;
import com.snuquill.paperdx.biz.video.application.VideoInfoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomePageService {
	private final ArticleLineService articleLineService;
	private final TopArticleService topArticleService;
	private final EditorPickService editorPickService;
	private final MagazineService magazineService;
	private final VideoInfoService videoInfoService;
	private final PhotoJournalService photoJournalService;

	public HomePageDto getHomePage() {
		List<ArticleLineDto> recentArticleList = articleLineService.getRecentArticleList(3);
		TopArticleDto topArticle = topArticleService.getTopArticle();
		EditorPickDto editorPickList = editorPickService.getEditorPickList();
		List<MagazineDto> recentMagazine = magazineService.getRecentMagazine(3);
		List<VideoInfoDto> recentUploadVideoInfo = videoInfoService.getRecentUploadVideoInfo(3);
		List<PhotoJournalDto> latestVolumePhotoJournal = photoJournalService.getLatestVolumePhotoJournal();
		return HomePageDto.builder()
			.topArticleDto(topArticle)
			.editorPickDto(editorPickList)
			.articleLineDtoList(recentArticleList)
			.magazineDtoList(recentMagazine)
			.videoInfoDtoList(recentUploadVideoInfo)
			.photoJournalDtoList(latestVolumePhotoJournal)
			.build();
	}
}
