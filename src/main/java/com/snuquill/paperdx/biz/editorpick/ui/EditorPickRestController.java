package com.snuquill.paperdx.biz.editorpick.ui;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.editorpick.application.EditorPickService;
import com.snuquill.paperdx.biz.editorpick.application.dto.EditorPickDto;
import com.snuquill.paperdx.biz.editorpick.domain.Category;
import com.snuquill.paperdx.biz.editorpick.ui.dto.EditorPickRequestDto;
import com.snuquill.paperdx.common.execption.badrequest.PathVariableException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/admin/editorPick")
@RequiredArgsConstructor
@Slf4j
public class EditorPickRestController {

	private final EditorPickService editorPickService;

	@PreAuthorize("hasRole('ROLE_EDITOR')")
	@PostMapping("/{category}")
	public void setEditorPick(
		@PathVariable("category") String categoryString,
		@Validated @RequestBody EditorPickRequestDto.SettingRequest editorPickSettingRequest
	) {
		Category category = Category.of(categoryString).orElseThrow(
			() -> new PathVariableException("Unknown category: " + categoryString)
		);
		editorPickService.setEditorPick(category, editorPickSettingRequest.id());
	}

	@GetMapping
	public EditorPickDto getAllEditorPick() {
		return editorPickService.getEditorPickList();
	}
}
