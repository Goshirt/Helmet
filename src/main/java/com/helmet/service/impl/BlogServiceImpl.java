package com.helmet.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.helmet.dao.BlogDao;
import com.helmet.entity.Blog;
import com.helmet.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService{

	
	@Resource
	private BlogDao blogDao;
	
	public List<Blog> getList() {
		return blogDao.getList();
	}

	public List<Blog> blogList(Map<String, Object> map) {
		return blogDao.blogList(map);
	}

	public Long count(Map<String, Object> map) {
		return blogDao.count(map);
	}

	public Blog getBlogById(Integer blogId) {
		// TODO Auto-generated method stub
		return blogDao.getBlogById(blogId);
	}

	public Integer updateBlog(Blog blog) {
		return blogDao.updateBlog(blog);
	}

	public Blog getPreviousBlog(Integer blogId) {
		return blogDao.getPreviousBlog(blogId);
	}

	public Blog getNextBlog(Integer blogId) {
		return blogDao.getNextBlog(blogId);
	}

	public Integer addBlog(Blog blog) {
		return blogDao.addBlog(blog);
	}

	public Integer deleteBlog(Integer blogId) {
		return blogDao.deleteBlog(blogId);
	}

	public Integer haveBlog(Integer typeId) {
		return blogDao.haveBlog(typeId);
	}

}
