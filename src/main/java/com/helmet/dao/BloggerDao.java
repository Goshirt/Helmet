package com.helmet.dao;

import com.helmet.entity.Blogger;

/**
 * 操作博主实体的接口
 * 
 * @author Helmet
 * 2018年5月2日
 */

public interface BloggerDao {
	public Blogger getBloggerByName(String bloggerName);
	
	public Blogger getBlogger();
}
