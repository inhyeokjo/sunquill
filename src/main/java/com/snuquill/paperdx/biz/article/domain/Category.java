package com.snuquill.paperdx.biz.article.domain;

import org.thymeleaf.util.StringUtils;

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

	public String getCategoryName() {
		return StringUtils.capitalize(name()).replace("_", " ");
	}

	public String getCategoryDescription() {
		// 카테코리값별로 다른 멘트 추가

		return switch (name()) {
			case "FEATURES" -> "Deals with general affairs, from domestic to international news.";
			case "SNU_SOCIETY" -> "Deals with news on campus.";
			case "ARTS_CULTURE" -> "Deals with the current creative trends.";
			case "SHORT_ARTICLES" -> "For a concise yet deep insight into today's issues. ";
			case "OPINION" -> "A closer look into the thoughts of today and tomorrow's leaders.";
			default -> "None";
		};
	}
}
