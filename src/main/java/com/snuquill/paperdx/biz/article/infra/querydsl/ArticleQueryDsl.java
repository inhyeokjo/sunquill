package com.snuquill.paperdx.biz.article.infra.querydsl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.snuquill.paperdx.biz.article.domain.Article;
import com.snuquill.paperdx.biz.article.domain.Category;

public interface ArticleQueryDsl {

	List<Article> findByCategoryVisible(Category category, Pageable pageable, boolean containInvisible);

	Page<Article> searchArticle(String keyword, Pageable pageable);
}
