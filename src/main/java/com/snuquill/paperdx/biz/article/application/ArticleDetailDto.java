package com.snuquill.paperdx.biz.article.application;

import java.util.List;

import com.snuquill.paperdx.biz.article.domain.Author;
import com.snuquill.paperdx.biz.article.domain.Category;

public record ArticleDetailDto(String pictureUrl, Category category, List<String> title, List<String> contents, Author author) {

	public String getTitleString() {
		return String.join("\n", title);
	}
}
