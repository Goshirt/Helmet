package com.helmet.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.helmet.dao.BloggerDao;
import com.helmet.entity.Blogger;
import com.helmet.service.BloggerService;

/**
 * 博主Service实现类
 * 
 * @author Helmet
 * 2018年5月2日
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
	private BloggerDao bloggerDao;
	
	public Blogger getBloggerByName(String bloggerName) {
		return bloggerDao.getBloggerByName(bloggerName);
	}

	public Blogger getBlog() {
		return bloggerDao.getBlogger();
	}

}
