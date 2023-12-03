package com.snuquill.paperdx.toparticle.application;

import com.snuquill.paperdx.toparticle.domain.TopArticle;

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
