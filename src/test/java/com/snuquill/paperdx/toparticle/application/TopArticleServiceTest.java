package com.snuquill.paperdx.toparticle.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.snuquill.paperdx.toparticle.domain.TopArticle;
import com.snuquill.paperdx.toparticle.domain.TopArticleRepository;

@ExtendWith(MockitoExtension.class)
class TopArticleServiceTest {

	@InjectMocks
	private TopArticleService topArticleService;

	@Mock
	private TopArticleRepository articleRepository;

	TopArticleServiceTest() {
	}

	@Nested
	@DisplayName("TopArticle조회")
	class getTopArticle {
		@Test
		@DisplayName("정상적으로 조회 된 경우")
		void 정상_케이스() {
			TopArticle topArticle = new TopArticle(1L, "fileApi", "title", "summary", 1L);
			TopArticleDto topArticleDto = new TopArticleDto("fileApi", "title", "summary");
			when(articleRepository.findTop1ByOrderByModifiedDateDesc()).thenReturn(Optional.of(topArticle));

			TopArticleDto findTopArticleDto = topArticleService.getTopArticle();

			assertThat(findTopArticleDto).isEqualTo(topArticleDto);
		}

		@Test
		@DisplayName("TopArticle이 존재하지 않는 경우")
		void 조희_결과가_0인_케이스() {
			TopArticle dummy = TopArticle.dummy();
			TopArticleDto dummyDto = new TopArticleDto(dummy.getFileApi(), dummy.getTitle(), dummy.getSummary());
			when(articleRepository.findTop1ByOrderByModifiedDateDesc()).thenReturn(Optional.empty());

			TopArticleDto findTopArticleDto = topArticleService.getTopArticle();

			assertThat(findTopArticleDto).isEqualTo(dummyDto);
		}
	}
}
