package com.snuquill.paperdx.video.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoInfoRepository extends JpaRepository<VideoInfo, Long> {
	List<VideoInfo> findAllByPicked(boolean picked);
}
