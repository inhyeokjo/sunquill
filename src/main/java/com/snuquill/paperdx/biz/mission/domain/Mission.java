package com.snuquill.paperdx.biz.mission.domain;

import com.snuquill.paperdx.biz.article.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mission_id", nullable = false)
	private Long id;
	@Column(columnDefinition = "text")
	private String missionText;

	public void setMission(String mission) {
		this.missionText = mission;
	}
}
