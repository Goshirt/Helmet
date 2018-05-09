package com.helmet.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.helmet.dao.CommentDao;
import com.helmet.entity.Comment;
import com.helmet.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	
	
	@Resource
	private CommentDao commentDao;
	
	public List<Comment> getComments(Map<String, Object> map) {
		return commentDao.getComments(map);
	}

	public int saveComment(Comment comment) {
		return commentDao.saveComment(comment);
	}

}
