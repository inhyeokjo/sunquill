package com.snuquill.paperdx.biz.introduction.domain;

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
public class GroupIntro extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_intro_id", nullable = false)
	private Long id;
	@Column(columnDefinition = "text")
	private String introduction;
	private String groupImgLink;
}
