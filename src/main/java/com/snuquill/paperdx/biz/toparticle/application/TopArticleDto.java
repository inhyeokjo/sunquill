package com.snuquill.paperdx.biz.toparticle.application;

import com.snuquill.paperdx.biz.toparticle.domain.TopArticle;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopArticleDto {

	private Long articleId;
	private String pictureUrl;
	private String title;
	private String summary;

	public static TopArticleDto of(TopArticle topArticle) {
		return new TopArticleDto(topArticle.getArticleId(), topArticle.getPictureUrl(), topArticle.getTitle(), topArticle.getSummary());
	}
}
