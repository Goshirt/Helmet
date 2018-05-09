<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	function checkForm(){
		var searchField=$("#searchField").val().trim();
		if (searchField==null || searchField=="") {
			return false;
		}else{
			return true;
		}
	}	
</script>
<div class="row">
	<div class="col-md-12" style="padding-top: 15px;">
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/index.html">首页</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/aboutMe.html"><font color="blank"><strong>关于Helmet</strong></font></a></li>
					<li><a href="${pageContext.request.contextPath}/download.html"><font color="blank"><strong>资源下载</strong></font></a></li>
				</ul>
				<form  action="${pageContext.request.contextPath}/blog/search.html" class="navbar-form navbar-right" method="post" onsubmit="return checkForm()">
					<div class="form-group">
						<input type="text" id="searchField" name="searchField" class="form-control" placeholder="输入关键字">
					</div>
					<button type="submit" class="btn btn-default">Search</button>
				</form>
			</div>
		</div>
		</nav>
	</div>
</div>