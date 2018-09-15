package com.helmet.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.helmet.entity.Blog;
import com.helmet.entity.BlogType;
import com.helmet.entity.Blogger;
import com.helmet.entity.Link;
import com.helmet.service.BlogService;
import com.helmet.service.BlogTypeService;
import com.helmet.service.BloggerService;
import com.helmet.service.LinkService;

@Component
public class InitData implements ServletContextListener,ApplicationContextAware{
	
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application=sce.getServletContext();
		BloggerService bloggerService=(BloggerService) applicationContext.getBean(BloggerService.class);
		Blogger blogger=bloggerService.getBlogger();
		//密码比较敏感，清空
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		LinkService linkService=applicationContext.getBean(LinkService.class);
		List<Link> linkList=linkService.getLinkList();
		application.setAttribute("linkList", linkList);
		
		BlogTypeService blogTypeService=(BlogTypeService) applicationContext.getBean(BlogTypeService.class);
		List<BlogType> blogTypeList=blogTypeService.getTypeList();
		application.setAttribute("blogTypeList", blogTypeList);
		
		BlogService blogService=(BlogService)applicationContext.getBean(BlogService.class);
		List<Blog> blogList=blogService.getList();
		application.setAttribute("blogList", blogList);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
