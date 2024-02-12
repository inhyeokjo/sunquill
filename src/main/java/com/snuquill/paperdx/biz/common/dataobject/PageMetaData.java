package com.snuquill.paperdx.biz.common.dataobject;

import org.springframework.data.domain.Page;

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

	public static PageMetaData of(Page<?> page) {
		int targetPage = page.getNumber() + 1;
		int startPage = page.getNumber() / 10 * 10 + 1;
		boolean isEndPageList = startPage <= page.getTotalPages() / 10 + 1;
		int endPage = !isEndPageList ? startPage + 10 : page.getTotalPages();
		return new PageMetaData(startPage, endPage, targetPage, startPage == 1, isEndPageList);
	}
}
