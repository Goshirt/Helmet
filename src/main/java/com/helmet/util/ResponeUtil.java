package com.helmet.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * ajax交互，向页面写数据
 * 
 * @author Helmet
 * 2018年5月6日
 */
public class ResponeUtil {
	public static void write(Object object,HttpServletResponse response)throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pWriter=response.getWriter();
		pWriter.println(object.toString());
		pWriter.flush();
		pWriter.close();
	}
}
