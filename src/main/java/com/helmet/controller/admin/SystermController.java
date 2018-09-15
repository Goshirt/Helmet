package com.helmet.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.helmet.entity.Blog;
import com.helmet.entity.BlogType;
import com.helmet.entity.Blogger;
import com.helmet.entity.Link;
import com.helmet.service.BlogService;
import com.helmet.service.BlogTypeService;
import com.helmet.service.BloggerService;
import com.helmet.service.LinkService;
import com.helmet.util.ResponeUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/systerm/")
public class SystermController {
	@Resource
	private BloggerService bloggerService;
	
	@Resource
	private LinkService linkService;
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@Resource
	private BlogService blogService;
	
	@RequestMapping("/reflashCache")
	public String reflashCache(HttpServletResponse response,HttpServletRequest request)throws Exception{
		
		//通过request获取application
		ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
		
		Blogger blogger=bloggerService.getBlogger();
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		List<Link> linkList=linkService.getLinkList();
		application.setAttribute("linkList", linkList);
		
		List<BlogType> blogTypeList=blogTypeService.getTypeList();
		application.setAttribute("blogTypeList", blogTypeList);
		
		List<Blog> blogList=blogService.getList();
		application.setAttribute("blogList", blogList);
		
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponeUtil.write(result, response);
		return null;
	}
}
