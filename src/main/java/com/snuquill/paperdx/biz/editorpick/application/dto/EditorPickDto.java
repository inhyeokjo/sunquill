package com.snuquill.paperdx.biz.editorpick.application.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.snuquill.paperdx.biz.editorpick.domain.Category;
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
		Map<Category, List<EditorPick>> editorPickMap = editorPickList.stream().collect(Collectors.groupingBy(EditorPick::getCategory));
		return EditorPickDto.builder()
			.opinionEditorPickList(convertToArticleTitleLinkDto(editorPickMap.get(Category.OPINION)))
			.featuresEditorPickList(convertToArticleTitleLinkDto(editorPickMap.get(Category.FEATURES)))
			.snuSocietyEditorPickList(convertToArticleTitleLinkDto(editorPickMap.get(Category.SNU_SOCIETY)))
			.artsAndCultureEditorPickList(convertToArticleTitleLinkDto(editorPickMap.get(Category.ARTS_CULTURE)))
			.shortArticleEditorPickList(convertToArticleTitleLinkDto(editorPickMap.get(Category.SHORT_ARTICLES)))
			.build();
	}

	private static List<ArticleTitleLinkDto> convertToArticleTitleLinkDto(List<EditorPick> editorPickList) {
		return editorPickList.stream().map(ArticleTitleLinkDto::of).toList();
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
