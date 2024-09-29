package com.snuquill.paperdx.biz.toparticle.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snuquill.paperdx.biz.article.application.ArticleDetailDto;
import com.snuquill.paperdx.biz.article.application.ArticleDetailService;
import com.snuquill.paperdx.biz.toparticle.domain.TopArticle;
import com.snuquill.paperdx.biz.toparticle.domain.TopArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopArticleService {

	private final TopArticleRepository topArticleRepository;
	private final ArticleDetailService articleDetailService;


	@Transactional(readOnly = true)
	public TopArticleDto getTopArticle() {
		TopArticle topArticle = topArticleRepository.findTop1ByOrderByModifiedDateDesc()
			.orElse(TopArticle.dummy());
		return TopArticleDto.of(topArticle);

	}

	@Transactional
	public void setTopArticle(Long articleId, String summary) {
		Optional<TopArticle> optionalTopArticle = topArticleRepository.findTop1ByOrderByModifiedDateDesc();
		ArticleDetailDto articleDetailDto = articleDetailService.getArticleDetail(articleId);
		if (optionalTopArticle.isEmpty()) {
			TopArticle newTopArticle = TopArticle.of(articleId, summary, articleDetailDto);
			topArticleRepository.save(newTopArticle);
		} else {
			TopArticle topArticle = optionalTopArticle.get();
			topArticle.apply(articleId, summary, articleDetailDto);
			topArticleRepository.save(topArticle);
		}
	}
}
