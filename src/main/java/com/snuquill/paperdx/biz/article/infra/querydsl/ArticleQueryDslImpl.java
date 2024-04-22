package com.snuquill.paperdx.biz.article.infra.querydsl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
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
		JPAQuery<Article> query = queryFactory.selectFrom(article)
			.where(article.category.eq(category)
				.and(article.invisible.isFalse()))
			.limit(pageable.getPageSize())
			.offset(pageable.getOffset());

		for (Sort.Order order : pageable.getSort()) {
			PathBuilder<?> entityPath = new PathBuilder<>(Article.class, article.getMetadata().getName());
			query.orderBy(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, entityPath.get(order.getProperty())));
		}

		return query.fetch();
	}

	@Override
	public Page<Article> searchArticle(String keyword, Pageable pageable) {
		List<Article> content = queryFactory
			.selectFrom(article)
			.where(
				article.invisible.eq(Boolean.TRUE)
					.or(article.title.containsIgnoreCase(keyword))
					.or(article.contents.containsIgnoreCase(keyword))
					.or(article.authorName.containsIgnoreCase(keyword))
			)
			.orderBy(
				getSortList(pageable).toArray(OrderSpecifier[]::new)
			)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long count = queryFactory
			.select(article.count())
			.from(article)
			.where(
				article.invisible.eq(Boolean.TRUE)
					.or(article.title.containsIgnoreCase(keyword))
					.or(article.contents.containsIgnoreCase(keyword))
					.or(article.authorName.containsIgnoreCase(keyword))
			)
			.fetchOne();

		return new PageImpl<>(content, pageable, count);
	}

	private List<OrderSpecifier> getSortList(Pageable page) {
		List<OrderSpecifier> orders = new ArrayList<>();
		if (!page.getSort().isEmpty()) {
			for (Sort.Order order : page.getSort()) {
				PathBuilder<?> entityPath = new PathBuilder<>(Article.class, article.getMetadata().getName());
				orders.add(new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, entityPath.get(order.getProperty())));
			}
		}
		return orders;
	}
}
