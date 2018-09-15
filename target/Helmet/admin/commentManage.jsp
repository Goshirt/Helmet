<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>写 Blog</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function formatterState(val,row){
		if (val==0) {
			return "<font color='red'>待审核</font>";
		}else if(val==1){
			return "<font color='red'>审核通过</font>";
		}else if(val==2){
			return "<font color='red'>审核不通过</font>";
		}
	}
	function formatterBlog(val,row){
		if(val==null){
			return "该博客已经删除";
		}else{
			return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+row.blog.blogId+".html'>"+row.blog.title+"</a>";
		}
	}
	function deleteComment(){
		var selects=$("#dg").datagrid("getSelections");
		if (selects.length<1) {
			$.messager.alert("系统提示","请选择需要删除的评论");
			return;
		}
		var ids=[];
		for (var i = 0; i < selects.length; i++) {
			ids.push(selects[i].commentId);
		}
		var deleteIds=ids.join(",");
		$.messager.confirm("系统提示", "你确定删除选中的评论吗？", function(r) {
			if(r){
				$.post("${pageContext.request.contextPath}/admin/comment/delete.do",{deleteIds:deleteIds},function(result){
					if (result.success) {
						$.messager.alert("系统提示", "删除成功");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示", "删除失败");
					}
				},"json");
			}
		});
	}
</script>
</head>
<body style="margin: 2px;">
	 <table id="dg" title="评论管理" class="easyui-datagrid" fitColumns="true" 
		    pagination="true" rownumbers="true" url="/admin/comment/list.do" fit="true" toolbar="#tb">
		    <thead>
		    	<tr>
		    		<th field="cb" checkbox="true" align="center"></th>
		    		<th field="commentId" width="50" align="center">编号</th>
		    		<th field="userIP" width="100" align="center" >评论人IP</th>
		    		<th field="content" width="200" align="center" >评论内容</th>
		    		<th field="commentDate" width="100" align="center">评论时间</th>
		    		<th field="state" width="100" align="center" formatter="formatterState">状态</th>
		    		<th field="blog" width="150" align="center" formatter="formatterBlog">评论的博客标题</th>
		    	</tr>
		    </thead>
	</table>
	<div id="tb">
		<div>
			<a href="javascript:deleteComment()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
</body>
</html>