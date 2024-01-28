package com.snuquill.paperdx.temp.ui;

import com.snuquill.paperdx.biz.article.application.ArticleDetailDto;
import com.snuquill.paperdx.biz.article.application.ArticleDetailService;
import com.snuquill.paperdx.biz.photojournal.application.PhotoJournalDto;
import com.snuquill.paperdx.biz.photojournal.application.PhotoJournalService;
import com.snuquill.paperdx.biz.photojournal.application.PhotoJournalWithMemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequestMapping("/temp")
@RequiredArgsConstructor
@Validated
public class TempController {
	private final ArticleDetailService articleDetailService;
	private final PhotoJournalService photoJournalService;

	@GetMapping("/home")
	public String getHomepage() {
		return "home";
	}

	@GetMapping("/404")
	public String get404() {
		return "404";
	}

	@GetMapping("/features")
	public String getArticles() { return "features";}

	@GetMapping("/shortArticle")
	public String getShortArticle(Model model) {

		ArticleDetailDto shortArticleDetail = articleDetailService.getArticleDetail(18L);
		model.addAttribute(shortArticleDetail);
		return "shortArticle";
	}

	@GetMapping("/photojournal")
	public String getPhotojournal(Model model){
		List<PhotoJournalWithMemberDto> photoJournalWithMemberDtoList = photoJournalService.getLatestVolumePhotoJournalWithMember();
		model.addAttribute(photoJournalWithMemberDtoList);
		return "photojournal";
	}

}
