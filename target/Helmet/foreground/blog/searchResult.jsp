<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="data_list">
	<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/static/images/search_icon.png" />查询结果:&nbsp;
		查询关键字"<font color="red">${searchField}</font>" (共查询到&nbsp;"<font color="red">${resultSum}</font>"&nbsp;条记录)
	</div>
	<div class="datas search">
		<ul>
			<c:choose>
				<c:when test="${blogs.size()==0 }">
					<div align="center" style="padding-top: 20px">未查询到结果，换个关键字试试看！</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="blog" items="${blogs }">
						<li style="margin-bottom: 20px">
							<span class="title"><a href="${pageContext.request.contextPath}/blog/articles/${blog.blogId}.html" target="_blank">${blog.title }</a></span>
							<span class="summary">摘要:${blog.content }...</span> 
							<span class="link" style="margin-top: 10px;"><a href="${pageContext.request.contextPath}/blog/articles/${blog.blogId}.html">http://www.helmet.com/blog/articles/${blog.blogId}.html</a>&nbsp;&nbsp;&nbsp;&nbsp;发布日期：${blog.releaseDateStr}</span>
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<div>
		${pageCode}
	</div>
</div>