package com.snuquill.paperdx.biz.article.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.snuquill.paperdx.testconfig.BaseDataJpaTest;

class ArticleRepositoryTest extends BaseDataJpaTest {

	@Autowired
	ArticleRepository articleRepository;

	@Nested
	@DisplayName("카테고리별 기사 목록 조회 테스트")
	class findByCategoryTest {

		@Test
		@DisplayName("성공 케이스")
		void findByCategory() {
			return;
		}
	}
}
