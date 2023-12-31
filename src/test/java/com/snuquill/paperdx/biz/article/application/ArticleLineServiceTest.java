package com.snuquill.paperdx.biz.article.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.snuquill.paperdx.biz.article.domain.ArticleRepository;
import com.snuquill.paperdx.biz.article.domain.AuthorRepository;
import com.snuquill.paperdx.testconfig.BaseMockitoTest;

class ArticleLineServiceTest extends BaseMockitoTest {

	@InjectMocks
	ArticleLineService articleLineService;

	@Mock
	ArticleRepository articleRepository;
	@Mock
	AuthorRepository authorRepository;

	@Nested
	@DisplayName("카테코리별 기사 목록 조회 테스트")
	class getFeatureArticleListTest {

		@Test
		@DisplayName("성공 케이스")
		void getFeatureArticleList() {
			// articleLineService.getCategoryArticlePage("")
		}
	}

}
