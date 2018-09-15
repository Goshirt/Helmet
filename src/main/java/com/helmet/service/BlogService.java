package com.helmet.service;

import java.util.List;
import java.util.Map;

import com.helmet.entity.Blog;

public interface BlogService {
	
	public List<Blog> getList();
	

	/*
	 * 按各种条件获取博客信息列表
	 */
	public List<Blog> blogList(Map<String, Object> map);
	
	/*
	 * 获取博客总数
	 */
	public Long count(Map<String, Object> map);
	
	/**
	 * 通过id获取博客信息
	 * @param blogId
	 * @return
	 */
	public Blog getBlogById(Integer blogId);
	
	/**
	 * 更新博客内容
	 * @param blog
	 */
	public Integer updateBlog(Blog blog);
	
	/**
	 * 获取上一篇博客
	 * @param blogId
	 * @return
	 */
	public Blog getPreviousBlog(Integer blogId);
	
	
	/**
	 * 获取下一篇博客
	 * @param blogId
	 * @return
	 */
	public Blog getNextBlog(Integer blogId);
	
	/**
	 * 添加博客
	 * @return
	 */
	public Integer addBlog(Blog blog);
	
	/**
	 * 删除博客
	 * @param blogId
	 * @return
	 */
	public Integer deleteBlog(Integer blogId);
	
	/**
	 * 判断指定类型下的博客数
	 * @param typeId
	 * @return
	 */
	public Integer haveBlog(Integer typeId);
}
