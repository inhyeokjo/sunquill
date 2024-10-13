package com.snuquill.paperdx.biz.article.ui.dto;

import com.snuquill.paperdx.biz.article.domain.Article;
import com.snuquill.paperdx.biz.article.domain.Category;

public class ArticleRequestDto {
	public record UploadRequest(String title, String pictureUrl, Category category, String contents, Long authorId, boolean invisible) {
		public Article mapToDomain(String authorName) {
			return new Article(title, contents, category, pictureUrl, authorId, authorName, invisible);
		}
	}
	public record ModifyRequest(String title, String pictureUrl, Category category, String contents, boolean invisible) {
		public Article mapToDomain(Long authorId, String authorName) {
			return new Article(title, contents, category, pictureUrl, authorId, authorName, invisible);
		}
	}
}
