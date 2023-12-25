package com.snuquill.paperdx.biz.editorpick.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class CategoryTest {

	@Test
	void assertCategorySame() {
		Set<String> articleCategoryNameList =
			Arrays.stream(Category.values())
				.map(Enum::name)
				.collect(Collectors.toSet());
		Set<String> categoryNameList =
			Arrays.stream(com.snuquill.paperdx.biz.article.domain.Category.values())
				.map(Enum::name)
				.collect(Collectors.toSet());

		assertThat(articleCategoryNameList).isEqualTo(categoryNameList);
	}

}
