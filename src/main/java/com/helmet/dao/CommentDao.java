package com.helmet.dao;

import java.util.List;
import java.util.Map;

import com.helmet.entity.Comment;

public interface CommentDao {
	 
	/**
	 * 根据参数map获得评论集
	 * @param map
	 * @return
	 */
	 public List<Comment> getComments(Map<String, Object> map);
	 
	 /**
	  * 保存评论
	  * @param comment
	  * @return
	  */
	 public int saveComment(Comment comment);
}
