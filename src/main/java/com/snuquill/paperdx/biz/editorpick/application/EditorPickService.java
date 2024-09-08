package com.snuquill.paperdx.biz.editorpick.application;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.editorpick.domain.ArticleTitleRepository;
import com.snuquill.paperdx.biz.editorpick.domain.Category;
import com.snuquill.paperdx.biz.editorpick.domain.EditorPick;
import com.snuquill.paperdx.biz.editorpick.domain.EditorPickRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditorPickService {

	private final EditorPickRepository editorPickRepository;
	private final ArticleTitleRepository articleTitleRepository;

	@Transactional(readOnly = true)
	public EditorPickDto getEditorPickList() {
		List<EditorPick> editorPickList = editorPickRepository.findAll();
		return EditorPickDto.of(editorPickList);
	}

	@PreAuthorize("hasRole('ROLE_EDITOR')")
	@Transactional
	public void setEditorPick(Category category, Long articleId) {
		EditorPick editorPick = editorPickRepository.findByCategory(category);

		String title = articleTitleRepository.findTitleById(articleId);

		editorPick.changeArticle(articleId, title);
		editorPickRepository.save(editorPick);
	}
}
