package com.snuquill.paperdx.article.application;

import java.util.List;

import com.snuquill.paperdx.article.domain.Author;
import com.snuquill.paperdx.article.domain.Category;

public record ArticleDto(String pictureUrl, Category category, List<String> title, List<String> contents, Author author) {

}
