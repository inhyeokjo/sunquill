package com.snuquill.paperdx.biz.toparticle.domain;

import com.snuquill.paperdx.biz.article.application.ArticleDetailDto;
import com.snuquill.paperdx.biz.article.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class TopArticle extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "top_article_id")
	private Long id;
	private String pictureUrl;
	private String title;
	@Column(columnDefinition = "text")
	private String summary;
	private Long articleId;

	public static TopArticle dummy() {
		return new TopArticle(null, "https://picsum.photos/740/420", "Dummy Title", "Dummy Summary", null);
	}

	public static TopArticle of(Long articleId, String summary, ArticleDetailDto articleDetailDto) {
		return new TopArticle(
			null,
			articleDetailDto.pictureUrl(),
			articleDetailDto.getTitleString(),
			summary,
			articleId
		);
	}

	public void apply(Long articleId, String summary, ArticleDetailDto articleDetailDto) {
		this.pictureUrl = articleDetailDto.pictureUrl();
		this.title = articleDetailDto.getTitleString();
		this.summary = summary;
		this.articleId = articleId;
	}
}
