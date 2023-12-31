package com.snuquill.paperdx.biz.article.application;

import java.util.Map;

import com.snuquill.paperdx.biz.article.domain.Article;
import com.snuquill.paperdx.biz.article.domain.Author;

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

	public static ArticleLineDto of(Article article, Map<Long, Author> authorMap) {
		String authorName = authorMap.get(article.getAuthorId()).getName();
		return new ArticleLineDto(article.getTitle(), article.getContents(), article.getMainPicture().getUrl(), authorName);
	}
}
