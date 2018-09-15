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
	var url;
	function deleteBlogType(){
		var selects=$("#dg").datagrid("getSelections");
		if (selects.length==0) {
			$.messager.alert("系统提示", "请选择需要删除的博客类型");
			return;
		}
		var ids=[];
		for(var i=0;i<selects.length;i++){
			ids.push(selects[i].typeId);
		}
		var deleteIds=ids.join(",");
		$.messager.confirm("系统提示", "你确定删除选中的数据吗？", function(r) {
				if(r){
					$.post("${pageContext.request.contextPath}/admin/blogType/delete.do",{deleteIds:deleteIds},function(result){
						if (result.success) {
							$.messager.alert("系统提示", "博客类型删除成功");
							$("#dg").datagrid("reload");
						}else{
							$.messager.alert("系统提示", result.errorInfo);
						}
					},"json");
				}
			}
		)
	}
	
	function openUpdateDialog(){
		var selects=$("#dg").datagrid("getSelections");
		if (selects.length!=1) {
			$.messager.alert("系统提示", "请选择一条需要修改的博客类型");
			return;
		}
		var row=selects[0];
		$("#dlg").dialog("open").dialog("setTitle","修改博客类型");
		//加载指定的form表单，并把row的数据塞进同名的输入框中
		$("#fm").form("load",row);
		url="${pageContext.request.contextPath}/admin/blogType/save.do?typeId="+row.typeId;
	}
	function openAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加博客类型");
		url="${pageContext.request.contextPath}/admin/blogType/save.do";
	}
	function save(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if (result.success) {
					$.messager.alert("系统提示", "操作成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		})
	}
	function closeDialog(){
		resetValue();
		$("#dlg").dialog("close");
	}
	function resetValue(){
		$("#typeName").val("");
		$("#orderNo").val("");
	}
</script>
</head>
<body style="margin:2px;">
    <table id="dg" title="博客类别管理" class="easyui-datagrid" fitColumns="true" 
    pagination="true" rownumbers="true" url="${pageContext.request.contextPath}/admin/blogType/list.do" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="typeId" width="50" align="center">编号</th>
    		<th field="typeName" width="100" align="center">类型名</th>
    		<th field="orderNo" width="100" align="center" >权重(权重越高，前台显示优先)</th>
    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openUpdateDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteBlogType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
</div>
<div class="easyui-dialog" id="dlg" style="width:400px;height:200px;padding: 10px 20px;" closed="true" buttons="#bt">
	<form method="post" id="fm">
		<table>
			<tr>
				<td>博客类型名：</td>
				<td><input type="text" id="typeName" name="typeName" class="easyui-validatebox" required="true"/></td>
			</tr>
			<tr>
				<td>权重：</td>
				<td><input type="text" id="orderNo" name="orderNo" class="easyui-validatebox" required="true"/></td>
			</tr>
		</table>
	</form>
</div>
<div id="bt">
	<a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">确定</a>
	<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">关闭</a>
</div>
</body>
</html>