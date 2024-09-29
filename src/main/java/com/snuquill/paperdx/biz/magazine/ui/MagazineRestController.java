package com.snuquill.paperdx.biz.magazine.ui;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.magazine.application.MagazineDto;
import com.snuquill.paperdx.biz.magazine.application.MagazineService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/admin/magazine")
public class MagazineRestController {
	private final MagazineService magazineService;

	@GetMapping
	public List<MagazineDto> getMagazineList(
		@RequestParam(defaultValue = "3") @NotNull int count
	) {
		return magazineService.getRecentMagazine(count);
	}

	//TODO POST Mapping 기능 구현 필요
}
