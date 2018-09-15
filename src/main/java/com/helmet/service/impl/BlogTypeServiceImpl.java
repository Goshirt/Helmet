package com.helmet.service.impl;

import java.util.List;
import java.util.Map;

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

	public BlogType findById(int typeId) {
		return blogTypeDao.findById(typeId);
	}

	public List<BlogType> list(Map<String, Object> map) {
		return blogTypeDao.list(map);
	}

	public Long countType() {
		return blogTypeDao.countType();
	}

	public Integer add(BlogType blogType) {
		return blogTypeDao.add(blogType);
	}

	public Integer update(BlogType blogType) {
		return blogTypeDao.update(blogType);
	}

	public Integer delete(int typeId) {
		return blogTypeDao.delete(typeId);
	}

}
