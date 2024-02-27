package com.snuquill.paperdx.biz.editorpick.application;

import java.util.ArrayList;
import java.util.List;

import com.snuquill.paperdx.biz.editorpick.domain.EditorPick;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EditorPickDto {
	private List<ArticleTitleLinkDto> featuresEditorPickList;
	private List<ArticleTitleLinkDto> snuSocietyEditorPickList;
	private List<ArticleTitleLinkDto> artsAndCultureEditorPickList;
	private List<ArticleTitleLinkDto> shortArticleEditorPickList;
	private List<ArticleTitleLinkDto> opinionEditorPickList;

	public static EditorPickDto of(List<EditorPick> editorPickList) {
		List<ArticleTitleLinkDto> featuresEditorPickList = new ArrayList<>();
		List<ArticleTitleLinkDto> snuSocietyEditorPickList = new ArrayList<>();
		List<ArticleTitleLinkDto> artsAndCultureEditorPickList = new ArrayList<>();
		List<ArticleTitleLinkDto> shortArticleEditorPickList = new ArrayList<>();
		List<ArticleTitleLinkDto> opinionEditorPickList = new ArrayList<>();

		for (EditorPick editorPick : editorPickList) {
			switch (editorPick.getCategory()) {
				case ARTS_CULTURE -> artsAndCultureEditorPickList.add(ArticleTitleLinkDto.of(editorPick));
				case OPINION -> opinionEditorPickList.add(ArticleTitleLinkDto.of(editorPick));
				case FEATURES -> featuresEditorPickList.add(ArticleTitleLinkDto.of(editorPick));
				case SNU_SOCIETY -> snuSocietyEditorPickList.add(ArticleTitleLinkDto.of(editorPick));
				case SHORT_ARTICLES -> shortArticleEditorPickList.add(ArticleTitleLinkDto.of(editorPick));
			}
		}
		return EditorPickDto.builder()
			.opinionEditorPickList(opinionEditorPickList)
			.featuresEditorPickList(featuresEditorPickList)
			.snuSocietyEditorPickList(snuSocietyEditorPickList)
			.artsAndCultureEditorPickList(artsAndCultureEditorPickList)
			.shortArticleEditorPickList(shortArticleEditorPickList)
			.build();
	}

	@AllArgsConstructor
	@Data
	public static class ArticleTitleLinkDto {
		private Long id;
		private String title;
		private Long articleId;

		public static ArticleTitleLinkDto of(EditorPick editorPick) {
			return new ArticleTitleLinkDto(editorPick.getId(), editorPick.getTitle(), editorPick.getArticleId());
		}
	}
}
