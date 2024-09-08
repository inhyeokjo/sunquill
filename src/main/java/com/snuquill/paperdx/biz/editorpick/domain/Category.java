package com.snuquill.paperdx.biz.editorpick.domain;

public enum Category {
	FEATURES, SNU_SOCIETY, ARTS_CULTURE, SHORT_ARTICLES, OPINION;

	public static Category of(String category) {
		return switch (category) {
			case "features" -> FEATURES;
			case "snuSociety" -> SNU_SOCIETY;
			case "artsCulture" -> ARTS_CULTURE;
			case "shortArticles" -> SHORT_ARTICLES;
			case "opinion" -> OPINION;
			default -> throw new IllegalArgumentException("Unknown category: " + category);
		};
	}
}
