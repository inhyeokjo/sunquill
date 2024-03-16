package com.snuquill.paperdx.biz.mission.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.snuquill.paperdx.biz.mission.application.MissionDto;
import com.snuquill.paperdx.biz.mission.application.MissionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MissionController {
	private final MissionService missionService;

	@GetMapping("/mission")
	public String getIntroductionPage(Model model) {
		MissionDto mission = missionService.getMission();
		model.addAttribute("mission", mission);
		return "mission";
	}
}

