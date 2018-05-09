package com.helmet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.helmet.entity.Blog;
import com.helmet.entity.PageBean;
import com.helmet.service.BlogService;
import com.helmet.util.PageUtil;
import com.helmet.util.StringUtil;

/**
 * 处理博客首页的各种请求
 * 
 * @author Helmet
 * 2018年5月4日
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	@Resource
	private BlogService blogService;
	
	@RequestMapping("index")
	public ModelAndView index(@RequestParam(value="page",required=false)String page,@RequestParam(value="typeId",required=false)String typeId,@RequestParam(value="releaseDateStr",required=false)String releaseDateStr,HttpServletRequest request) throws Exception{
		ModelAndView view=new ModelAndView();
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("typeId", typeId);
		map.put("releaseDateStr", releaseDateStr);
		//查询博客列表的参数,用于分页代码的url
		StringBuffer param=new StringBuffer();
		if (StringUtil.isNotEmpty(typeId)) {
			param.append("typeId="+typeId);
		}
		if (StringUtil.isNotEmpty(releaseDateStr)) {
			param.append("&releaseDateStr="+releaseDateStr);
		}
		String pageCode=PageUtil.genPageCode(request.getContextPath()+"/index.html", blogService.count(map), Integer.parseInt(page), 8, param.toString());
		List<Blog> blogs=blogService.blogList(map);
		for (Blog blog : blogs) {
			List<String> imageList=blog.getImageList();
			//获取博客内容
			String blogInfo=blog.getContent();
			//解析博客内容
			Document document=Jsoup.parse(blogInfo);
			//选取该博客内容中img标签并且后缀为 .jpg的值,img[src$=.jpg]
			Elements elements=document.select("img");
			for (int i = 0; i < elements.size(); i++) {
				Element jpg=elements.get(i);
				imageList.add(jpg.toString());
				//控制显示的略缩图最多三张
				if (i==2) {
					break;
				}
			}
		}
		//springmvc的ModelAndView,前台通过key获取到value
		view.addObject("blogs", blogs);
		view.addObject("mainPage", "foreground/blog/blogList.jsp");
		view.addObject("pageTitle", "Helmet 博客主页");
		view.addObject("pageCode", pageCode);
		//setViewName只需要写需要转发到的页面的名字，不要到加后缀，配置文件已经自动拼接.jsp,转发到指定的页面
		view.setViewName("mainTemp");
		return view;
	}
	
	@RequestMapping("aboutMe")
	public ModelAndView aboutMe()throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "foreground/helmet/myInfo.jsp");
		mav.addObject("pageTitle", "Helmet 博主简介");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	@RequestMapping("download")
	public ModelAndView download()throws Exception {
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "foreground/download/download.jsp");
		mav.addObject("pageTitle", "资源下载 ");
		mav.setViewName("mainTemp");
		return mav;
	}
}
