package com.snuquill.paperdx.biz.article.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Long id;
	private String title;
	private String contents;
	@Enumerated(EnumType.STRING)
	private Category category;
	@Embedded
	@AttributeOverride(name = "url", column = @Column(name = "picture_url"))
	private Picture mainPicture;
	private boolean invisible;
	private Long authorId;
	private String authorName;
	private LocalDateTime publishDate;
	private Long viewCount;

	public Article(String title, String contents, Category category, Picture mainPicture, Long authorId, String authorName) {
		this.title = title;
		this.contents = contents;
		this.category = category;
		this.mainPicture = mainPicture;
		this.authorId = authorId;
		this.publishDate = LocalDateTime.now();
		this.authorName = authorName;
	}

	public static Article testDummy(int number, Category category) {
		return new Article("title" + number, "contents" + number, category, new Picture("url"), 1L, "testMember");
	}

	public void changeTitle(String title) {
		this.title = title;
	}

	public void changeContents(String contents) {
		this.contents = contents;
	}

	public void changeImage(String pictureUrl) {
		this.mainPicture = new Picture(pictureUrl);
	}

	public void hide() {
		this.invisible = false;
	}

	public void show() {
		this.invisible = true;
	}

	public List<String> getTitleList() {
		return this.makeList(this.title);
	}

	public List<String> getContentsList() {
		return this.makeList(this.contents);
	}

	public List<String> makeList(String text) {
		return Arrays.asList(text.replace("\r\n", "\n").split("\n"));
	}

	public Long upViewCount() {
		viewCount += 1;
		return viewCount;
	}
}
