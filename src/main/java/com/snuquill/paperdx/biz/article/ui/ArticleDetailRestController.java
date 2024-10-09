package com.snuquill.paperdx.biz.article.ui;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.article.application.ArticleDetailDto;
import com.snuquill.paperdx.biz.article.application.ArticleDetailService;
import com.snuquill.paperdx.biz.article.ui.dto.ArticleRequestDto;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/article")
@RequiredArgsConstructor
@Validated
public class ArticleDetailRestController {
	private final ArticleDetailService articleDetailService;

	@GetMapping("/{articleId}")
	public ArticleDetailDto getArticleDetail(@NotNull @PathVariable Long articleId) {
		return articleDetailService.getArticleDetail(articleId);
	}

	@PostMapping("/{articleId}")
	public void modifyArticleDetail(@NotNull @PathVariable Long articleId,
		@RequestBody ArticleRequestDto.UploadRequest uploadRequest
	) {
		articleDetailService.modifyArticle(articleId, uploadRequest);
	}

	@PostMapping
	public void uploadArticleDetail(
		@RequestBody ArticleRequestDto.UploadRequest uploadRequest
	) {
		articleDetailService.uploadArticle(uploadRequest);
	}
}
