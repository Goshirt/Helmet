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
import com.helmet.entity.PageBean;
import com.helmet.lucene.BlogIndex;
import com.helmet.service.BlogService;
import com.helmet.util.DateJosnValueProcessor;
import com.helmet.util.ResponeUtil;
import com.helmet.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

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
			flag=blogService.updateBlog(blog);
			blogIndex.updateIndex(blog);
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
	
	/**
	 * 获取list
	 * @param page
	 * @param size
	 * @param s_title
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String size,@RequestParam(value="s_title",required=false)String s_title,HttpServletResponse response)throws Exception{
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(size));
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("title", StringUtil.formatLike(s_title));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Blog> blogs=blogService.blogList(map);
		long count=blogService.count(map);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJosnValueProcessor("yyyy-MM-dd"));
		JSONArray blogArrays=JSONArray.fromObject(blogs, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", blogArrays);
		result.put("total", count);
		ResponeUtil.write(result, response);
		return null;
		
		
	}
	
	/**
	 * 删除blog
	 * @param deleteIds
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="deleteIds")String deleteIds,HttpServletResponse response)throws Exception{
		String[] ids=null;
		if (StringUtil.isNotEmpty(deleteIds)) {
			ids=deleteIds.split(",");
		}
		if (ids!=null) {
			for (int i = 0; i < ids.length; i++) {
				blogService.deleteBlog(Integer.parseInt(ids[i]));
				blogIndex.deleteIndex(ids[i]);
			}
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponeUtil.write(result, response);
		return null;
	}
	
	/**
	 * 通过id获取blog
	 * @param blogId
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getBlog")
	public String getBlogById(@RequestParam(value="blogId")String blogId,HttpServletResponse response)throws Exception {
		Blog blog=blogService.getBlogById(Integer.parseInt(blogId));
		JSONObject result=JSONObject.fromObject(blog);
		ResponeUtil.write(result, response);
		return null;
		
		
	}
}
