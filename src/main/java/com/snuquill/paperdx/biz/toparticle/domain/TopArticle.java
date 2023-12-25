package com.snuquill.paperdx.biz.toparticle.domain;

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
	private String fileApi;
	private String title;
	private String summary;
	private Long articleId;

	public static TopArticle dummy() {
		return new TopArticle(null, "https://picsum.photos/740/420", "Dummy Title", "Dummy Summary", null);
	}
}
