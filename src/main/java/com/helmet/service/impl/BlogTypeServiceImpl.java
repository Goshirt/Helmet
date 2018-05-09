package com.helmet.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.helmet.dao.BlogTypeDao;
import com.helmet.entity.BlogType;
import com.helmet.service.BlogTypeService;

@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

	@Resource
	private BlogTypeDao blogTypeDao;
	
	public List<BlogType> getTypeList() {
		return blogTypeDao.getTypeList();
	}

}
