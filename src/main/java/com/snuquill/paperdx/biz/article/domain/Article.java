package com.snuquill.paperdx.biz.article.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.snuquill.paperdx.utils.StringUtils;

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
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
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

	public Article(String title, String contents, Category category, String mainPictureUrl, Long authorId, String authorName, boolean invisible) {
		this.title = title;
		this.contents = contents;
		this.category = category;
		this.mainPicture = Picture.of(mainPictureUrl);
		this.authorId = authorId;
		this.publishDate = LocalDateTime.now();
		this.authorName = authorName;
		this.viewCount = 0L;
		this.invisible = invisible;
	}

	public static Article testDummy(int number, Category category) {
		return new Article("title" + number, "contents" + number, category, "url", 1L, "testMember", false);
	}

	public void changeTitle(String title) {
		this.title = title;
	}

	public void changeContents(String contents) {
		this.contents = contents;
	}

	public void changeImage(String pictureUrl) {
		this.mainPicture = Picture.of(pictureUrl);
	}

	public void hide() {
		this.invisible = true;
	}

	public void show() {
		this.invisible = false;
	}

	public List<String> makeList(String text) {
		return Arrays.asList(text.replace("\r\n", "\n").split("\n"));
	}

	public Long upViewCount() {
		viewCount += 1;
		return viewCount;
	}

	public Article apply(Article newArticle) {
		this.setTitle(newArticle.getTitle());
		this.setContents(newArticle.getHtmlContents());
		this.setCategory(newArticle.getCategory());
		this.setMainPicture(newArticle.getMainPicture());
		this.setInvisible(newArticle.isInvisible());
		this.setAuthorId(newArticle.getAuthorId());
		this.setAuthorName(newArticle.getAuthorName());
		this.setPublishDate(newArticle.getPublishDate());
		return this;
	}

	public String getStringContents() {
		return StringUtils.extractStringFromHtml(contents);
	}

	public String getHtmlContents() {
		return contents;
	}

	private void getContents() { /* do not use getter of content */ }
}
