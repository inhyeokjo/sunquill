package com.snuquill.paperdx.biz.article.domain;

import java.util.List;

public interface AuthorRepository {
	Author getAuthor(Long id);

	List<Author> getAllAuthor(List<Long> idList);
}
