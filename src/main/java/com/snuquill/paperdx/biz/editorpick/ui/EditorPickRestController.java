package com.snuquill.paperdx.biz.editorpick.ui;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.editorpick.application.EditorPickService;
import com.snuquill.paperdx.biz.editorpick.domain.Category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
@Slf4j
public class EditorPickRestController {

	private final EditorPickService editorPickService;

	@PreAuthorize("hasRole('ROLE_EDITOR')")
	@PostMapping("editorPick/{category}")
	public void setEditorPick(
		@PathVariable("category") String categoryString,
		@Valid @RequestBody EditorPickRequestDto.SettingRequest editorPickSettingRequest
	) {
		Category category = Category.of(categoryString);
		editorPickService.setEditorPick(category, editorPickSettingRequest.id());
	}
}
