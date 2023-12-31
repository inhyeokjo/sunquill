package com.snuquill.paperdx.biz.editorpick.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.snuquill.paperdx.biz.editorpick.domain.Category;
import com.snuquill.paperdx.biz.editorpick.domain.EditorPick;
import com.snuquill.paperdx.biz.editorpick.domain.EditorPickRepository;
import com.snuquill.paperdx.testconfig.BaseMockitoTest;

class EditorPickServiceTest extends BaseMockitoTest {

	@InjectMocks
	EditorPickService editorPickService;

	@Mock
	EditorPickRepository editorPickRepository;

	@Nested
	@DisplayName("Editor's Picks 조회")
	class getEditorPickList {

		@Test
		@DisplayName("정상적으로 조회된 경우")
		void 정상_케이스() {
			List<EditorPick> editorPickList = generateMockData();
			when(editorPickRepository.findAll()).thenReturn(editorPickList);

			EditorPickDto editorPickDtoList = editorPickService.getEditorPickList();

			assertThat(editorPickDtoList).isEqualTo(EditorPickDto.of(editorPickList));
		}

		@Test
		@DisplayName("조회된 결과가 없는 경우")
		void 에디터_픽이_0개인_경우() {
			List<EditorPick> editorPickList = new ArrayList<>();
			when(editorPickRepository.findAll()).thenReturn(editorPickList);

			EditorPickDto editorPickDtoList = editorPickService.getEditorPickList();

			assertThat(editorPickDtoList.getFeaturesEditorPickList()).isEmpty();
			assertThat(editorPickDtoList.getOpinionEditorPickList()).isEmpty();
			assertThat(editorPickDtoList.getSnuSocietyEditorPickList()).isEmpty();
			assertThat(editorPickDtoList.getShortArticleEditorPickList()).isEmpty();
			assertThat(editorPickDtoList.getArtsAndCultureEditorPickList()).isEmpty();
		}

		private List<EditorPick> generateMockData() {
			List<EditorPick> editorPickList = new ArrayList<>();

			EditorPick featureEditorPick1 = new EditorPick(1L, "title", 1L, Category.FEATURES);
			EditorPick featureEditorPick2 = new EditorPick(2L, "title", 2L, Category.FEATURES);
			EditorPick featureEditorPick3 = new EditorPick(3L, "title", 3L, Category.FEATURES);
			editorPickList.add(featureEditorPick1);
			editorPickList.add(featureEditorPick2);
			editorPickList.add(featureEditorPick3);

			EditorPick opinionEditorPick1 = new EditorPick(4L, "title", 4L, Category.OPINION);
			EditorPick opinionEditorPick2 = new EditorPick(5L, "title", 5L, Category.OPINION);
			EditorPick opinionEditorPick3 = new EditorPick(6L, "title", 6L, Category.OPINION);
			editorPickList.add(opinionEditorPick1);
			editorPickList.add(opinionEditorPick2);
			editorPickList.add(opinionEditorPick3);

			EditorPick snuSocietyEditorPick1 = new EditorPick(7L, "title", 7L, Category.SNU_SOCIETY);
			EditorPick snuSocietyEditorPick2 = new EditorPick(8L, "title", 8L, Category.SNU_SOCIETY);
			EditorPick snuSocietyEditorPick3 = new EditorPick(9L, "title", 9L, Category.SNU_SOCIETY);
			editorPickList.add(snuSocietyEditorPick1);
			editorPickList.add(snuSocietyEditorPick2);
			editorPickList.add(snuSocietyEditorPick3);

			EditorPick artsAndCultureEditorPick1 = new EditorPick(10L, "title", 10L, Category.ARTS_CULTURE);
			EditorPick artsAndCultureEditorPick2 = new EditorPick(11L, "title", 11L, Category.ARTS_CULTURE);
			EditorPick artsAndCultureEditorPick3 = new EditorPick(12L, "title", 12L, Category.ARTS_CULTURE);
			editorPickList.add(artsAndCultureEditorPick1);
			editorPickList.add(artsAndCultureEditorPick2);
			editorPickList.add(artsAndCultureEditorPick3);

			EditorPick shortArticleEditorPick1 = new EditorPick(10L, "title", 10L, Category.SHORT_ARTICLES);
			EditorPick shortArticleEditorPick2 = new EditorPick(11L, "title", 11L, Category.SHORT_ARTICLES);
			EditorPick shortArticleEditorPick3 = new EditorPick(12L, "title", 12L, Category.SHORT_ARTICLES);
			editorPickList.add(shortArticleEditorPick1);
			editorPickList.add(shortArticleEditorPick2);
			editorPickList.add(shortArticleEditorPick3);

			return editorPickList;
		}
	}
}
