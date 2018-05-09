package com.helmet.dao;

import java.util.List;

import com.helmet.entity.BlogType;

public interface BlogTypeDao {
	/**
	 * 获取博客类别列表，并统计各类别的博客数量
	 * @return
	 */
	public List<BlogType> getTypeList();
	
	/*
	 * 通过id获取博客类别信息
	 */
	public BlogType findById(int typeId);
}
