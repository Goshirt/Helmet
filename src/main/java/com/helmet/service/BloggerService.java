package com.helmet.service;

import com.helmet.entity.Blogger;

/**
 * 博主Service
 * 
 * @author Helmet
 * 2018年5月2日
 */
public interface BloggerService {
	
	/**
	 * 通过博主名获取博主信息
	 * @param bloggerName
	 * @return
	 */
	public Blogger getBloggerByName(String bloggerName);
	
	/**
	 * 获取博主信息
	 * @return
	 */
	public Blogger getBlogger();
	
	/**
	 * 更新博主信息
	 * @param blogger
	 * @return
	 */
	public Integer updateBlogger(Blogger blogger);
}
