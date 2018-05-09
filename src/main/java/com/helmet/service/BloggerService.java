package com.helmet.service;

import com.helmet.entity.Blogger;

/**
 * 博主Service
 * 
 * @author Helmet
 * 2018年5月2日
 */
public interface BloggerService {
	
	public Blogger getBloggerByName(String bloggerName);
	
	public Blogger getBlog();
}
