package com.helmet.service;

import java.util.List;
import java.util.Map;

import com.helmet.entity.Comment;

public interface CommentService {
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
	 public Integer saveComment(Comment comment);
	 
	 /**
	  * 获取指定博客评论的数量
	  * @param map
	  * @return
	  */
	 public Long countComment(Map<String, Object> map);
	 
	 /**
	  * 删除评论
	  * @param deleteIds
	  * @return
	  */
	 public Integer deleteComment(String deleteId);
	 
	 /**
	  * 更新评论信息
	  * @param comment
	  * @return
	  */
	 public Integer updateComment(Comment comment);

}
