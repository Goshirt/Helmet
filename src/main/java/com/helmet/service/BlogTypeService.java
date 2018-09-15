package com.helmet.service;

import java.util.List;
import java.util.Map;

import com.helmet.entity.BlogType;

public interface BlogTypeService {
	/**
	 * 获取博客类别列表，并统计各类别的博客数量
	 * @return
	 */
	public List<BlogType> getTypeList();
	
	/*
	 * 通过id获取博客类别信息
	 */
	public BlogType findById(int typeId);
	
	/**
	 * 获取博客类别列表，分页
	 * @param map
	 * @return
	 */
	public List<BlogType> list(Map<String, Object> map);
	
	/**
	 * 统计类别总数
	 * @return
	 */
	public Long countType();
	
	/**
	 * 添加
	 * @param blogType
	 * @return
	 */
	public Integer add(BlogType blogType);
	
	
	/**
	 * 修改
	 * @param blogType
	 * @return
	 */
	public Integer update(BlogType blogType);
	
	/**
	 * 删除
	 * @param typeId
	 * @return
	 */
	public Integer delete(int typeId);
}
