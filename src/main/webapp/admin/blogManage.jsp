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
<!-- 引入ueditor -->
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js">
	
</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	function searchBlog(){
		var s_title=$("#s_title").val();
		if(s_title==null || s_title==""){
			$.messager.alert("系统提示", "请输入搜索的博客标题");
			return ;
		}
		$("#dg").datagrid("load",{"s_title":s_title});
	}
	
	function deleteBlog(){
		var selects=$("#dg").datagrid("getSelections");
		if (selects.length==0) {
			$.messager.alert("系统提示", "请选择需要删除的博客");
			return;
		}
		var ids=[];
		for(var i=0;i<selects.length;i++){
			ids.push(selects[i].blogId);
		}
		var deleteIds=ids.join(",");
		$.messager.confirm("系统提示", "你确定删除选中的数据吗？", function(r) {
				if(r){
					$.post("${pageContext.request.contextPath}/admin/blog/delete.do",{deleteIds:deleteIds},function(result){
						if (result.success) {
							$.messager.alert("系统提示", "博客删除成功");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示", "删除失败");
						}
					},"json");
				}
			}
		)
	}
	
	function updateBlog(){
		var select=$("#dg").datagrid("getSelections");
		if (select.length!=1) {
			$.messager.alert("系统提示", "请选择一条需要修改的博客");
			return;
		}
		var row=select[0];
		window.parent.openTab('更新博客','updateBlog.jsp?blogId='+row.blogId,'icon-menuManage');
	}
	function formatterBlogType(val,row){
		return val.typeName;
	}
	function formatterTitle(val,row){
		return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+row.blogId+".html'>"+val+"</a>";
	}
	function resetValue(){
	}
</script>
</head>
<body style="margin:2px;">
    <table id="dg" title="修改博客信息" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="/admin/blog/list.do" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="blogId" width="50" align="center">编号</th>
    		<th field="title" width="100" align="center" formatter="formatterTitle">博客标题</th>
    		<th field="releaseDate" width="100" align="center" >创建时间</th>
    		<th field="blogType" width="100" align="center" formatter="formatterBlogType">博客类型</th>
    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:updateBlog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteBlog()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
		&nbsp;博客标题：&nbsp;<input type="text" name="s_title"  id="s_title"  size="20" onkeydown="if(event.keyCode==13) searchBlog()"/>
		<a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>

</body>
</html>