package com.helmet.controller.admin;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.helmet.entity.Blogger;
import com.helmet.service.BloggerService;
import com.helmet.util.CryptographyUtil;
import com.helmet.util.DateUtil;
import com.helmet.util.ResponeUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {
	
	@Resource
	private BloggerService bloggerService;
	
	@RequestMapping("/getInfo")
	public String getInfo(HttpServletResponse response)throws Exception {
		Blogger blogger= bloggerService.getBlogger();
		JSONObject result=JSONObject.fromObject(blogger);
		ResponeUtil.write(result, response);
		return null;
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam("imageFile")MultipartFile imageFile,Blogger blogger,HttpServletResponse response,HttpServletRequest request)throws Exception {
		if (!imageFile.isEmpty()) {
			//获取项目的根路径
			String filePath=request.getServletContext().getRealPath("/");
			//用当前时间作为上传文件的保存名，并获取上传文件的后缀，跟保存名拼接一起
			String fileName=DateUtil.getNowTimeString("yyyyMMddhhmmss")+"."+imageFile.getOriginalFilename().split("\\.")[1];
			//把上传的文件流写到指定的项目路径
			imageFile.transferTo(new File(filePath+"static/userImages/"+fileName));
			blogger.setImageName(fileName);
		}
		int num=bloggerService.updateBlogger(blogger);
		StringBuffer sBuffer=new StringBuffer();
		if (num>0) {
			sBuffer.append("<script language='javascript'>alert('修改成功！');</script>");
		}else{
			sBuffer.append("<script language='javascript'>alert('修改失败！');</script>");
		}
		ResponeUtil.write(sBuffer, response);
		return null;
	}
	
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword,HttpServletResponse response)throws Exception {
		Blogger blogger=new Blogger();
		blogger.setPassword(CryptographyUtil.md5(newPassword, "helmet"));
		int num=bloggerService.updateBlogger(blogger);
		JSONObject result=new JSONObject();
		if (num>0) {
			result.put("success", true);
		}else {
			result.put("success", false);
		}
		ResponeUtil.write(result, response);
		return null;
	}
	/**
	 * 注销 退出登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout()throws Exception {
		//Shiro 获取当前登录用户并退出
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp";
	}
}
