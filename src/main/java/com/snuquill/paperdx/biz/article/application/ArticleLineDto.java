package com.snuquill.paperdx.biz.article.application;

import java.util.Map;

import com.snuquill.paperdx.biz.article.domain.Article;
import com.snuquill.paperdx.biz.article.domain.Author;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleLineDto {
	private Long id;
	private String articleTitle;
	private String articleSummary;
	private String articlePictureUrl;
	private String authorName;
	private boolean invisible;

	public static ArticleLineDto withHighlight(Article article,String searchText) {
		String highlightedTitle = highlightSearchText(article.getTitle(), searchText);
		String highlightedStringContents = highlightSearchText(article.getStringContents(), searchText);
		return new ArticleLineDto(article.getId(), highlightedTitle, highlightedStringContents, article.getMainPicture().getUrl(), article.getAuthorName(), article.isInvisible());
	}

	public static ArticleLineDto of(Article article, String authorName) {
		return new ArticleLineDto(article.getId(), article.getTitle(), article.getStringContents(), article.getMainPicture().getUrl(), authorName, article.isInvisible());
	}

	public static ArticleLineDto of(Article article, Map<Long, Author> authorMap) {
		String authorName = authorMap.get(article.getAuthorId()).getName();
		return new ArticleLineDto(article.getId(), article.getTitle(), article.getStringContents(), article.getMainPicture().getUrl(), authorName, article.isInvisible());
	}

	public static ArticleLineDto of(Article article) {
		return new ArticleLineDto(article.getId(), article.getTitle(), article.getStringContents(), article.getMainPicture().getUrl(), article.getAuthorName(), article.isInvisible());
	}



	private static String highlightSearchText(String text, String searchText) {
		if (searchText == null || searchText.isEmpty() || text == null) {
			return text;
		}
		return text.replaceAll(
			"(?i)(" + searchText + ")",
			"<span class=\"highlight\">$1</span>");
	}
}
