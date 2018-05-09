package com.helmet.util;

import com.helmet.entity.Blog;

/**
 * 分页代码的实现
 * 
 * @author Helmet
 * 2018年5月4日
 */
public class PageUtil {
	/**
	 * 生成分页代码
	 * @param targUrl  每页对应的链接地址
	 * @param totalNum 总记录数
	 * @param currentPage  当前页
	 * @param pageSize  每页大小
	 * @param param  链接携带的参数
	 * @return
	 */
	public static String genPageCode(String targUrl, long totalNum,int currentPage,int pageSize,String param) {
		//总页数
		long totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		if (totalPage==0) {
			return "未查询到数据";
		}else {
			StringBuffer pageCode=new StringBuffer();
			pageCode.append(" <li><a href='"+targUrl+"?page=1&"+param+"'>首页</a></li>");
			if(currentPage>1){
				pageCode.append("<li><a href='"+targUrl+"?page="+(currentPage-1)+"&"+param+"'>上一页</a></li>");			
			}else{
				pageCode.append("<li class='disabled'><a href='"+targUrl+"?page="+(currentPage-1)+"&"+param+"'>上一页</a></li>");		
			}
			for(int i=currentPage-2;i<=currentPage+2;i++){
				if(i<1||i>totalPage){
					continue;
				}
				if(i==currentPage){
					pageCode.append("<li class='active'><a href='"+targUrl+"?page="+i+"&"+param+"'>"+i+"</a></li>");	
				}else{
					pageCode.append("<li><a href='"+targUrl+"?page="+i+"&"+param+"'>"+i+"</a></li>");	
				}
			}
			if(currentPage<totalPage){
				pageCode.append("<li><a href='"+targUrl+"?page="+(currentPage+1)+"&"+param+"'>下一页</a></li>");		
			}else{
				pageCode.append("<li class='disabled'><a href='"+targUrl+"?page="+(currentPage+1)+"&"+param+"'>下一页</a></li>");	
			}
			pageCode.append(" <li><a href='"+targUrl+"?page="+totalPage+"&"+param+"'>尾页</a></li>");
			return pageCode.toString();
		}
	}
	
	/**
	 * 生成上一篇和下一篇的页面代码
	 * @param previousBlog  上一篇
	 * @param nextBlog  下一篇
	 * @param projectPath 项目工程根路径
	 * @return
	 */
	public static String genUpCodeAndDownCode(Blog previousBlog,Blog nextBlog,String projectPath){
		StringBuffer upCodeAndDownCode=new StringBuffer();
		if (previousBlog==null || previousBlog.getBlogId()==null) {
			upCodeAndDownCode.append("<p>上一篇：没有了</p>");
		}else {
			//upCodeAndDownCode.append("<p>上一篇：<a href='"+projectPath+"blog/articles/"+previousBlog.getBlogId()+".html'>"+previousBlog.getTitle()+"</a></p>");
			upCodeAndDownCode.append("<p>上一篇：<a href='"+projectPath+previousBlog.getBlogId()+".html'>"+previousBlog.getTitle()+"</a></p>");
		}
		if (nextBlog==null || nextBlog.getBlogId()==null) {
			upCodeAndDownCode.append("<p>下一篇：没有了</p>");
		}else {
			//upCodeAndDownCode.append("<p>下一篇：<a href='"+projectPath+"blog/articles/"+nextBlog.getBlogId()+".html'>"+nextBlog.getTitle()+"</a></p>");
			upCodeAndDownCode.append("<p>下一篇：<a href='"+projectPath+nextBlog.getBlogId()+".html'>"+nextBlog.getTitle()+"</a></p>");
		}
		return upCodeAndDownCode.toString();
	}
	
	public static String genUpPageAndDownPage(Integer page,String searchField,Integer total,Integer pageSize,String projectPath) {
		int totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
		StringBuffer pageCode=new StringBuffer();
		if (totalPage==0) {
			return "";
		}else {
			pageCode.append("<nav >");
			pageCode.append("<ul class='pager'>");
			if (page>1) {
				pageCode.append("<li><a href='"+projectPath+"/blog/search.html?page="+(page-1)+"&searchField="+searchField+"'>上一页</a></li>");
			}else {
				pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
			}
			if (page<totalPage) {
				pageCode.append("<li><a href='"+projectPath+"/blog/search.html?page="+(page+1)+"&searchField="+searchField+"'>下一页</a></li>");
			}else {
				pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
			}
			pageCode.append("</ul>");
			pageCode.append("</nav>");
		}
		return pageCode.toString();
	}
}
