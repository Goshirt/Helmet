package com.helmet.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helmet.entity.Blog;
import com.helmet.entity.Comment;
import com.helmet.entity.PageBean;
import com.helmet.service.CommentService;
import com.helmet.util.DateJosnValueProcessor;
import com.helmet.util.ObjectJsonValueProcessor;
import com.helmet.util.ResponeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/admin/comment")
public class CommentAdminCotroller {
	
	
	@Resource
	private CommentService commentService;
	
	@RequestMapping("list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String size,@RequestParam(value="state",required=false)String state,HttpServletResponse response)throws Exception {
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(size));
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("state", state);
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Comment> comments=commentService.getComments(map);
		long count=commentService.countComment(map);
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class, new DateJosnValueProcessor("yyyy-MM-dd HH:mm:ss"));
		config.registerJsonValueProcessor(Blog.class, new ObjectJsonValueProcessor(new String[]{"blogId","title"}, Blog.class));
		JSONArray rows=JSONArray.fromObject(comments, config);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", count);
		ResponeUtil.write(result, response);
		return null;
	}
	
	/**
	 * 删除评论
	 * @param deleteIds
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="deleteIds",required=true)String deleteIds,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String[] ids=deleteIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			commentService.deleteComment(ids[i]);
		}
		result.put("success", true);
		ResponeUtil.write(result, response);
		return null;
	}
	
	/**
	 * 评论审核通过
	 * @param comment
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String updateComment(@RequestParam(value="commentIds")String commentIds,@RequestParam(value="state",required=false)Integer state,HttpServletResponse response)throws Exception{
		String[] ids=commentIds.split(",");
		JSONObject result=new JSONObject();
		for (int i = 0; i < ids.length; i++) {
			Comment comment=new Comment();
			comment.setCommentId(Integer.parseInt(ids[i]));
			comment.setState(state);
			commentService.updateComment(comment);
		}
		result.put("success", true);
		ResponeUtil.write(result, response);
		return null;
	}
}
