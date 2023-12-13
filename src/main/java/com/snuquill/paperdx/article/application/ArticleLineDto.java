package com.snuquill.paperdx.article.application;

import com.snuquill.paperdx.article.domain.Article;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleLineDto {
	private String articleTitle;
	private String articleSummary;
	private String articlePictureUrl;
	private String authorName;

	public static ArticleLineDto of(Article article, String authorName) {
		return new ArticleLineDto(article.getTitle(), article.getContents(), article.getMainPicture().getUrl(), authorName);
	}
}
