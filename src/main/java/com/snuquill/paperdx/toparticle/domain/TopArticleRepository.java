package com.snuquill.paperdx.toparticle.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopArticleRepository extends JpaRepository<TopArticle, Long> {

	Optional<TopArticle> findTop1ByOrderByModifiedDateDesc();
}
