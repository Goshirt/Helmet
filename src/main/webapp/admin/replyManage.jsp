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
		return "<font color='red'>待审核</font>";
	}
	function formatterBlog(val,row){
		if(val==null){
			return "该博客已经删除";
		}else{
			return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+row.blog.blogId+".html'>"+row.blog.title+"</a>";
		}
	}
	function passComment(){
		var selects=$("#dg").datagrid("getSelections");
		if (selects.length<1) {
			$.messager.alert("系统提示","请选择需要审核通过的评论");
			return;
		}
		var ids=[];
		for (var i = 0; i < selects.length; i++) {
			ids.push(selects[i].commentId);
		}
		var commentIds=ids.join(",");
		$.messager.confirm("系统提示", "你确定审核通过选中的评论吗？", function(r) {
			if(r){
				$.post("${pageContext.request.contextPath}/admin/comment/update.do",{commentIds:commentIds,state:1},function(result){
					if (result.success) {
						$.messager.alert("系统提示", "审核通过成功");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示", "审核通过失败");
					}
				},"json");
			}
		});
	}
	function nopassComment(){
		var selects=$("#dg").datagrid("getSelections");
		if (selects.length<1) {
			$.messager.alert("系统提示","请选择不通过审核的评论");
			return;
		}
		var ids=[];
		for (var i = 0; i < selects.length; i++) {
			ids.push(selects[i].commentId);
		}
		var commentIds=ids.join(",");
		$.messager.confirm("系统提示", "你确定不通过选中的评论吗？", function(r) {
			if(r){
				$.post("${pageContext.request.contextPath}/admin/comment/update.do",{commentIds:commentIds,state:2},function(result){
					if (result.success) {
						$.messager.alert("系统提示", "操作成功");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示", "操作失败");
					}
				},"json");
			}
		});
	} 
</script>
</head>
<body style="margin: 2px;">
	 <table id="dg" title="评论审核" class="easyui-datagrid" fitColumns="true" 
		    pagination="true" rownumbers="true" url="/admin/comment/list.do?state=0" fit="true" toolbar="#tb">
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
			<a href="javascript:passComment()" class="easyui-linkbutton" iconCls="icon-fahuo" plain="true">审核通过</a>
			<a href="javascript:nopassComment()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">审核不通过</a>
		</div>
	</div>
</body>
</html>