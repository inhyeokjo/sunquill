package com.snuquill.paperdx.toparticle.application;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.toparticle.domain.TopArticle;
import com.snuquill.paperdx.toparticle.domain.TopArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopArticleService {

	private final TopArticleRepository topArticleRepository;

	public TopArticleDto getTopArticle() {
		TopArticle topArticle = topArticleRepository.findTop1ByOrderByModifiedDateDesc()
			.orElse(TopArticle.dummy());
		return TopArticleDto.of(topArticle);

	}
}
