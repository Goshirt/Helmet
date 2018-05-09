<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pageTitle}</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/blog.css">
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
	<style type="text/css">
		body{
			padding-top: 20px;
			padding-bottom: 40px;
		}
	</style>
	<script type="text/javascript">
		
	</script>
</head>
<body>
	<div class="container">
		<jsp:include page="/foreground/commen/head.jsp"></jsp:include>
		<jsp:include page="/foreground/commen/navbar.jsp"></jsp:include>
		<div class="row">
			<div class="col-md-9">
				<jsp:include page="${mainPage }"></jsp:include>
			</div>
			<div class="col-md-3">
				<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/user_icon.png"/>
					博主信息
				</div>
				<div class="user_image">
					<img src="${pageContext.request.contextPath}/static/userImages/${blogger.imageName}"/>
				</div>
				<div class="nickName">${blogger.bloggerName}</div>
				<div class="userSign">(${blogger.sign })</div>
			</div>
	  	
	  		<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/>
					按博客类别
				</div>
				<div class="datas">
					<ul>
							<c:forEach items="${blogTypeList }" var="blogType">
								<li><span><a href="/index.html?typeId=${blogType.typeId }">${blogType.typeName }(<font color="red">${blogType.typeCount }</font>)</a></span></li>
							</c:forEach>
					</ul>
				</div>
			</div>
			<div class="data_list">
					<div class="data_list_title">
						<img src="${pageContext.request.contextPath}/static/images/byDate_icon.png"/>
						按发布时间
					</div>
					<div class="datas">
						<ul>
							<c:forEach items="${blogList }" var="blog">
								<li><span><a href="/index.html?releaseDateStr=${blog.releaseDateStr }">${blog.releaseDateStr }(<font color="red">${blog.blogCount }</font>)</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			
				<div class="data_list">
					<div class="data_list_title">
						<img src="${pageContext.request.contextPath}/static/images/link_icon.png"/>
						个人项目
					</div>
					<div class="datas">
						<ul>
								<c:forEach items="${linkList}" var="link">
									<li><span><a href="${link.linkUrl }" target="_blank">${link.linkName }</a></span></li>
								</c:forEach>
							
							
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div>
			<nav aria-label="Page navigation">
			  <ul class="pagination">
			   		${pageCode }
			  </ul>
			</nav>
		</div>
		<jsp:include page="/foreground/commen/footer.jsp"></jsp:include>
	</div>
</body>
</html>