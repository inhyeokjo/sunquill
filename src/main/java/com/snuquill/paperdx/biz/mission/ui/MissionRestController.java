package com.snuquill.paperdx.biz.mission.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snuquill.paperdx.biz.mission.application.MissionDto;
import com.snuquill.paperdx.biz.mission.application.MissionService;
import com.snuquill.paperdx.biz.mission.ui.dto.MissionRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/mission")
public class MissionRestController {
	private final MissionService missionService;

	@GetMapping
	public MissionDto getMission() {
		return missionService.getMission();
	}

	@PostMapping
	public void postMission(
		@RequestBody MissionRequestDto.SettingRequest missionRequestSettingDto
	) {
		String mission = missionRequestSettingDto.mission();
		missionService.setMission(mission);
	}
}
