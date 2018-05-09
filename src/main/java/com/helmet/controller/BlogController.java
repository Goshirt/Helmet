package com.helmet.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.helmet.entity.Blog;
import com.helmet.entity.Comment;
import com.helmet.lucene.BlogIndex;
import com.helmet.service.BlogService;
import com.helmet.service.CommentService;
import com.helmet.util.PageUtil;
import com.helmet.util.StringUtil;


/**
 * 博客Controller层
 * 
 * @author Helmet
 * 2018年5月2日
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Resource
	private BlogService blogService;
	@Resource
	private CommentService commentService;
	
	private BlogIndex blogIndex=new BlogIndex();
	
	/**
	 * 访问链接中的65.html的65被当做是参数blogId
	 * @param blogId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/articles/{blogId}")
	public ModelAndView showBlog(@PathVariable("blogId")Integer blogId,HttpServletRequest request)throws Exception {
		ModelAndView view=new ModelAndView();
		Blog blog=blogService.getBlogById(blogId);
		//博客阅读数加1
		blog.setClickNum(blog.getClickNum()+1);
		blogService.updateBlog(blog);
		String keyWord=blog.getKeyWord();
		//处理博客的关键字，去掉空格，并转为list
		if (StringUtil.isNotEmpty(keyWord)) {
			String[] words=keyWord.split(" ");
			List<String> wordList=StringUtil.filterWhite(Arrays.asList(words));
			view.addObject("wordList", wordList);
		}else{
			view.addObject("wordList", null);
		}
		//获取上一篇和下一篇代码   request.getServletContext().getContextPath()获取项目根路径
		String upCodeAndDownCode=PageUtil.genUpCodeAndDownCode(blogService.getPreviousBlog(blogId), blogService.getNextBlog(blogId), request.getServletContext().getContextPath());
		//封装获取评论内容需要的参数map
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("blogId", blogId);
		map.put("state", 1);
		//获取评论内容
		List<Comment> comments=commentService.getComments(map);
		view.addObject("blog", blog);
		view.addObject("mainPage", "foreground/blog/show.jsp");
		view.addObject("pageTitle", blog.getTitle()+"--Helmet");
		view.addObject("upCodeAndDownCode", upCodeAndDownCode);
		view.addObject("comments",comments);
		view.setViewName("mainTemp");
		return view;
	}
	
	/**
	 * 使用lucene通过关键字查询
	 * @param searchField
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam(value="searchField",required=false)String searchField,@RequestParam(value="page",required=false)String page,HttpServletRequest request)throws Exception {
		String pageSize="2";
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		ModelAndView view=new ModelAndView();
		List<Blog> blogs=blogIndex.serachBlog(searchField);
		System.out.println(blogs.size());
		String pageCode=PageUtil.genUpPageAndDownPage(Integer.parseInt(page), searchField, blogs.size(), Integer.parseInt(pageSize), request.getServletContext().getContextPath());
		//搜索查询时，每一页的起始数据
		int fromIndex=Integer.parseInt(pageSize)*(Integer.parseInt(page)-1);
		int toIndex=blogs.size()>=Integer.parseInt(page)*Integer.parseInt(pageSize)?Integer.parseInt(page)*Integer.parseInt(pageSize):blogs.size();
		view.addObject("mainPage", "foreground/blog/searchResult.jsp");
		view.addObject("pageCode", pageCode);
		view.addObject("pageTitle", "查询关键字'"+searchField+"'--Helmet");
		view.addObject("blogs", blogs.subList(fromIndex, toIndex));
		view.addObject("searchField", searchField);
		view.addObject("resultSum", blogs.size());
		view.setViewName("mainTemp");
		return view;
	}
	
}
