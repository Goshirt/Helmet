package com.helmet.entity;

/**
 * 
 * 
 * @author Helmet
 * 2018年5月4日
 */
public class PageBean {

	private int page; // 当前页
	private int pageSize; //每页大小
	private int start;  // 每页起始数据
	
	
	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return (page-1)*pageSize;
	}
	
	
}
