package com.snuquill.paperdx.biz.article.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.snuquill.paperdx.biz.article.infra.querydsl.ArticleQueryDsl;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleQueryDsl, QuerydslPredicateExecutor<Article> {
	long countAllByCategoryAndInvisible(Category category, boolean invisible);
}
