package com.helmet.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helmet.entity.Blog;
import com.helmet.lucene.BlogIndex;
import com.helmet.service.BlogService;
import com.helmet.util.ResponeUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
	
	@Resource
	private BlogService blogService;
	
	private BlogIndex blogIndex=new BlogIndex();
	
	/**
	 * 添加或者修改博客
	 * @param blog
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Blog blog,HttpServletResponse response)throws Exception {
		//添加或者修改成功与否标记
		int flag=0;
		//Id为空就是添加，不为空是修改
		if (blog.getBlogId()==null) {
			flag=blogService.addBlog(blog);
			blogIndex.blogToIndex(blog);
		}else {
			
		}
		JSONObject result=new JSONObject();
		if (flag>0) {
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponeUtil.write(result, response);
		return null;
	}
}
