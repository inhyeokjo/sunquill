package com.snuquill.paperdx.biz.mission.application;

import com.snuquill.paperdx.biz.mission.domain.Mission;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MissionDto {
	private String missionText;

	public static MissionDto of(Mission mission) {
		return new MissionDto(mission.getMissionText());
	}

}
