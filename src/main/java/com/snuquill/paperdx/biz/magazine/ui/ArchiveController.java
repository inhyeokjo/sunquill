package com.snuquill.paperdx.biz.magazine.ui;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import com.snuquill.paperdx.biz.common.dataobject.PageMetaData;
import com.snuquill.paperdx.biz.magazine.application.MagazineDto;
import com.snuquill.paperdx.biz.magazine.application.MagazineService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArchiveController {

	private final MagazineService magazineService;

	@GetMapping("/archives/{page}")
	public String getArchiveList(Model model, @PathVariable("page") int page) {
		Page<MagazineDto> magazinePage = magazineService.getMagazinePage(page);
		model.addAttribute("magazinePage", magazinePage);
		model.addAttribute("pageMetaData", PageMetaData.of(magazinePage));
		return "archives";
	}

	@GetMapping("/archives")
	public RedirectView getArchiveList() {
		return new RedirectView("/archives/1");
	}
}
