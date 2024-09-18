package com.snuquill.paperdx.biz.editorpick.domain;

import java.util.Optional;

public enum Category {
	FEATURES, SNU_SOCIETY, ARTS_CULTURE, SHORT_ARTICLES, OPINION;

	public static Optional<Category> of(String category) {
		return switch (category) {
			case "features" -> Optional.of(FEATURES);
			case "snuSociety" -> Optional.of(SNU_SOCIETY);
			case "artsCulture" -> Optional.of(ARTS_CULTURE);
			case "shortArticles" -> Optional.of(SHORT_ARTICLES);
			case "opinion" -> Optional.of(OPINION);
			default -> Optional.empty();
		};
	}
}
