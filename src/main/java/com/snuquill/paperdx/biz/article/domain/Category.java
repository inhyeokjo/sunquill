package com.snuquill.paperdx.biz.article.domain;

public enum Category {
	FEATURES, SNU_SOCIETY, ARTS_CULTURE, SHORT_ARTICLES, OPINION;

	public static boolean isCategory(String categoryName) {
		for (Category category : Category.values()) {
			if (category.name().equals(categoryName.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
}
