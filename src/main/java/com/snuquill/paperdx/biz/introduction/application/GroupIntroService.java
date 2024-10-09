package com.snuquill.paperdx.biz.introduction.application;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.introduction.domain.GroupIntro;
import com.snuquill.paperdx.biz.introduction.domain.GroupIntroRepository;
import com.snuquill.paperdx.common.execption.biz.DataNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupIntroService {
	private final GroupIntroRepository groupIntroRepository;

	@Transactional(readOnly = true)
	public GroupIntroDto getIntroduction() {
		return GroupIntroDto.of(getGroupIntroDomain());
	}

	@Secured("ROLE_SUPER_ADMIN")
	@Transactional
	public void setIntroduction(String introduction) {
		GroupIntro groupIntroDomain = getGroupIntroDomain();
		groupIntroDomain.setIntro(introduction);
		groupIntroRepository.save(groupIntroDomain);
	}

	private GroupIntro getGroupIntroDomain() {
		List<GroupIntro> introList = groupIntroRepository.findAll();
		if (introList.isEmpty()) {
			log.error("Introduction 데이터가 존재하지 않습니다. GroupIntro Table에는 정확히 1개의 데이터만 존재해야 합니다.");
			throw new DataNotFoundException("Introduction Data Not Found. Please Check DB");
		} else if (introList.size() > 1) {
			log.warn("총 {}개의 Introduction 데이터가 존재합니다. GroupIntro Table에는 정확히 1개의 데이터만 존재해야 합니다.", introList.size());
		}
		return introList.get(0);
	}
}
