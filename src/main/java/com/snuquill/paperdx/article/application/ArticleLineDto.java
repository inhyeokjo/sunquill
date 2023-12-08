package com.snuquill.paperdx.article.application;

import com.snuquill.paperdx.article.domain.Article;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleLineDto {
	private String articleTitle;
	private String articleSummary;

	public static ArticleLineDto of(Article article) {
		return new ArticleLineDto(article.getTitle(), article.getContents());
	}
}
