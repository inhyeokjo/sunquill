package com.snuquill.paperdx.biz.toparticle.application;

import com.snuquill.paperdx.biz.toparticle.domain.TopArticle;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopArticleDto {

	private String fileApi;
	private String title;
	private String summary;

	public static TopArticleDto of(TopArticle topArticle) {
		return new TopArticleDto(topArticle.getFileApi(), topArticle.getTitle(), topArticle.getSummary());
	}
}
