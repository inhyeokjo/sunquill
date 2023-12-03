package com.snuquill.paperdx.editorpick.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.editorpick.domain.EditorPick;
import com.snuquill.paperdx.editorpick.domain.EditorPickRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditorPickService {

	private final EditorPickRepository editorPickRepository;

	@Transactional(readOnly = true)
	public EditorPickDto getEditorPickList() {
		List<EditorPick> editorPickList = editorPickRepository.findAll();
		return EditorPickDto.of(editorPickList);
	}
}
