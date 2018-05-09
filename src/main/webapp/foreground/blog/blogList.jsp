<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div class="data_list">
		<div class="data_list_title">
			<img src="${pageContext.request.contextPath}/static/images/list_icon.png" />最新博客
		</div>
		<div class="datas">
			<ul>
				<c:forEach var="blog" items="${blogs }">
				
					<li style="margin-bottom: 30px"><span class="date"><a
							href="${pageContext.request.contextPath}/blog/articles/${blog.blogId}.html"><fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy年-MM月-dd日"/></a></span>
						<span class="title"><a
							href="${pageContext.request.contextPath}/blog/articles/${blog.blogId}.html">${blog.title }</a></span>
							
						<span class="summary">摘要:${blog.summary }</span> 
						<span class="img">
							<c:forEach var="image" items="${blog.imageList }">
								<a href="/blog/articles/${blog.blogId}.html">${image }</a> &nbsp;&nbsp;
							</c:forEach>
						</span> 
						<span class="info">发表于<fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/> 
							阅读(<font color="red">${blog.clickNum }</font>) 评论(<font color="red">${blog.replyNum }</font>) </span></li>
					<hr style="height: 5px; border: none; border-top: 1px dashed gray; padding-bottom: 10px;" />
				</c:forEach>

				
			</ul>
		</div>
	</div>
