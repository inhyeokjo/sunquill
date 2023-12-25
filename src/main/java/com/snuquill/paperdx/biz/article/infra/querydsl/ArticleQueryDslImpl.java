package com.snuquill.paperdx.biz.article.infra.querydsl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.snuquill.paperdx.biz.article.domain.Article;
import com.snuquill.paperdx.biz.article.domain.Category;
import com.snuquill.paperdx.biz.article.domain.QArticle;

@Repository
public class ArticleQueryDslImpl extends QuerydslRepositorySupport implements ArticleQueryDsl {

	private final JPAQueryFactory queryFactory;
	private QArticle article = QArticle.article;

	public ArticleQueryDslImpl(JPAQueryFactory queryFactory) {
		super(Article.class);
		this.queryFactory = queryFactory;
	}

	@Override
	public List<Article> findByCategoryVisible(Category category, Pageable pageable) {
		return queryFactory.selectFrom(article)
			.where(article.category.eq(category)
				.and(article.invisible.isFalse()))
			.limit(pageable.getPageSize())
			.offset(pageable.getOffset())
			.fetch();
	}
}
