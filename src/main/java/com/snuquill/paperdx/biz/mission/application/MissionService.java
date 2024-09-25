package com.snuquill.paperdx.biz.mission.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.mission.domain.Mission;
import com.snuquill.paperdx.biz.mission.domain.MissionRepository;
import com.snuquill.paperdx.common.execption.biz.DataNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MissionService {
	private final MissionRepository missionRepository;

	@Transactional(readOnly = true)
	public MissionDto getMission() {
		return MissionDto.of(getMissionDomain());
	}

	@Transactional
	public void setMission(String mission) {
		Mission missionDomain = getMissionDomain();
		missionDomain.setMission(mission);
		missionRepository.save(missionDomain);
	}

	private Mission getMissionDomain() {
		List<Mission> missionList = missionRepository.findAll();
		if (missionList.isEmpty()) {
			log.error("Mission 데이터가 존재하지 않습니다. Mission Table에는 정확히 1개의 데이터만 존재해야 합니다.");
			throw new DataNotFoundException("Mission Data Not Found. Please Check DB");
		} else if (missionList.size() > 1) {
			log.warn("총 {}개의 Mission 데이터가 존재합니다. Mission Table에는 정확히 1개의 데이터만 존재해야 합니다.", missionList.size());
		}
		return missionList.get(0);
	}
}
