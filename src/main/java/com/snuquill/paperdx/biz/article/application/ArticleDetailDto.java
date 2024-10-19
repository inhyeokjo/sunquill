package com.snuquill.paperdx.biz.article.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snuquill.paperdx.biz.article.domain.Author;
import com.snuquill.paperdx.biz.article.domain.Category;

public record ArticleDetailDto(String pictureUrl, Category category, String title, String contents, Author author) {

	@JsonIgnore
	public String getTitleString() {
		return String.join("\n", title);
	}
}
