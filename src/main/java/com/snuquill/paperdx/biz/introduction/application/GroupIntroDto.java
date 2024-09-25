package com.snuquill.paperdx.biz.introduction.application;

import com.snuquill.paperdx.biz.introduction.domain.GroupIntro;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupIntroDto {
	private String introduction;

	public static GroupIntroDto of(GroupIntro groupIntro) {
		return new GroupIntroDto(groupIntro.getIntroduction());
	}
}
