package com.snuquill.paperdx.biz.toparticle.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.toparticle.domain.TopArticle;
import com.snuquill.paperdx.biz.toparticle.domain.TopArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopArticleService {

	private final TopArticleRepository topArticleRepository;

	@Transactional(readOnly = true)
	public TopArticleDto getTopArticle() {
		TopArticle topArticle = topArticleRepository.findTop1ByOrderByModifiedDateDesc()
			.orElse(TopArticle.dummy());
		return TopArticleDto.of(topArticle);

	}
}
