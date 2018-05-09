package com.helmet.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.helmet.entity.Blogger;
import com.helmet.service.BloggerService;
import com.helmet.util.CryptographyUtil;

/**
 * 博主Controller层
 * 
 * @author Helmet
 * 2018年5月2日
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {
	
	@Resource
	private BloggerService bloggerService;
	
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request) {
		Subject subject=SecurityUtils.getSubject();
		//把前台传递过来的用户名和使用md5加密的密码封装成token
		UsernamePasswordToken token=new UsernamePasswordToken(blogger.getBloggerName(), CryptographyUtil.md5(blogger.getPassword(), "helmet"));
		try {
			//进行验证，下一步进入myReaml中的doGetAuthenticationInfo
			subject.login(token);
			return "redirect:/admin/main.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "用户名或者密码错误");
			return "login";
		}
	}
}
