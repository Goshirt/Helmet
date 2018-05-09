package com.helmet.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.helmet.entity.Blog;
import com.helmet.entity.Comment;
import com.helmet.service.BlogService;
import com.helmet.service.CommentService;
import com.helmet.util.ResponeUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	
	@Resource
	private CommentService commentService;
	
	@Resource 
	private BlogService blogService;
	
	
	@RequestMapping("/save")
	public String saveComment(Comment comment,@RequestParam(value="imageCode")String imageCode,HttpServletRequest request,HttpServletResponse response,HttpSession session)throws Exception {
		//获取session中的验证码
		String sRand=(String) session.getAttribute("sRand");
		JSONObject result=new JSONObject();
		//评论是否成功标志，0：不成功    >0:成功
		int flag=0;
		if (!sRand.equals(imageCode)) {
			result.put("success", false);
			result.put("error", "验证码错误");
		}else {
			//获取用户的IP地址
			String userIP=request.getRemoteAddr();
			comment.setUserIP(userIP);
			if (comment.getCommentId()==null) {
				flag=commentService.saveComment(comment);
				Blog blog=blogService.getBlogById(comment.getBlog().getBlogId());
				blog.setReplyNum(blog.getReplyNum()+1);
				blogService.updateBlog(blog);
			}else {
				
			}
		}
		if (flag>0) {
			result.put("success", true);
		}else {
			result.put("success", false);
		}
		ResponeUtil.write(result, response);
		return null;
	}
}
