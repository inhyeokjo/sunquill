package com.snuquill.paperdx.biz.article.ui;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageMetaData {
	private long pageStart;
	private long pageEnd;
	private long targetPage;
	private boolean isFirstPageList;
	private boolean isEndPageList;
}
