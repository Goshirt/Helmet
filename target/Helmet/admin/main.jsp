<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<title>Helmet 博客系统后台管理</title>
<script type="text/javascript">
function openTab(text,url,iconCls){
	if ($("#tabs").tabs("exists",text)) {
		$("#tabs").tabs("select",text);
	}else{
		var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${pageContext.request.contextPath}/admin/"+url+"'></iframe>";
		$("#tabs").tabs("add",{
			title:text,
			iconCls:iconCls,
			closable:true,
			content:content
		})
	}
}

var url;
function openPasswordModifyDialog(){
	$("#dlg").dialog("open").dialog("setTitle","修改密码");
	url="${pageContext.request.contextPath}/admin/blogger/modifyPassword.do";
}
function save(){
	$("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			var newPassword=$("#newPassword").val();
			var newPassword2=$("#newPassword2").val();
			alert(newPassword.length);
			if (!$(this).form("validate")) {
				return false;
			}
			/* if (newPassswrod.length<6||newPassswrod.length>14) {
				$("#errorInfo").html("<font color='red'>密码长度不正确</font>");
				return falsee;
			} */
			if (newPassword!=newPassword2) {
				$("#errorInfo").html("<font color='red'>新密码跟确认密码不一致</font>");
				return falsee;
			}
			$("#errorInfo").html("");
			return true;
		},
		success:function(result){
			var result=eval('('+result+')');
			if (result.success) {
				$.messager.alert("系统提示", "密码修改成功，下次登录生效");
				resetValue();
				$("#dlg").dialog("close");
			}else{
				$.messager.alert("系统提示", "密码修改失败");
				return;
			}
		}
	});
}

function closeDialog(){
	resetValue();
	$("#dlg").dialog("close");
}

function resetValue(){
	$("#newPassword").val("");
	$("#newPassword2").val("");
}

function reflashCache(){
	$.post("${pageContext.request.contextPath }/admin/systerm//reflashCache.do",{},function(result){
		if (result.success) {
			$.messager.alert("系统提示","系统缓存刷新成功");
		}else{
			$.messager.alert("系统提示","系统缓存刷新失败");
		}
	},"json")
}

function logout(){
	$.messager.confirm("系统提示", "你确定推出登录吗", function(r){
		if (r) {
			window.location.href="${pageContext.request.contextPath}/admin/blogger/logout.do";
		}
	})
}
</script>
</head>
<body>
	<body class="easyui-layout">
	<div region="north" style="height: 78px;">
		<table style="padding: 5px;" width="100%">
			<tr>
				<td width="50%">
					<img src="${pageContext.request.contextPath }/static/images/mylogo.png">
				</td>
				<td valign="bottom" align="right" width="50%" style="margin-right: 40px;">
					<font size="3"><strong>welcome:&nbsp;&nbsp;</strong><font color="red">${blogger.bloggerName}</font></font>
				</td>
			</tr>
		</table>
	</div>
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" data-options="iconCls:'icon-home'">
				<div align="center" style="padding-top: 100px;"><font color="red" size="10">欢迎使用Helmet博客后台管理</font> </div>
			</div>
		</div>
	</div>
	<div region="west" style="width: 200px;" title="导航菜单" split="true">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="常用操作" data-options="selected:true,iconCls:'icon-user'" style="padding: 10px">
				<a href="javascript:openTab('写博客','writeBlog.jsp','icon-menuManage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-menuManage'" style="width: 150px;">写博客</a>
				<a href="javascript:openTab('评论审核','replyManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">评论审核</a>
			</div>
			<div title="博客管理"  data-options="iconCls:'icon-product'" style="padding:10px;">
				<a href="javascript:openTab('写博客','writeBlog.jsp','icon-menuManage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-menuManage'" style="width: 150px;">写博客</a>
				<a href="javascript:openTab('博客管理','blogManage.jsp','icon-modifyPassword')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改博客</a>
			</div>
			<div title="评论管理"  data-options="iconCls:'icon-order'" style="padding:10px">
				<a href="javascript:openTab('评论审核','replyManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">评论审核</a>
				<a href="javascript:openTab('评论管理','commentManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">评论管理</a>
			</div>
			<div title="博客类别管理" data-options="iconCls:'icon-comment'" style="padding:10px">
				<a href="javascript:openTab('博客类别管理','blogTypeManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">博客类别管理</a>
			</div>
			<div title="博主信息管理"  data-options="iconCls:'icon-notice'" style="padding:10px">
				<a href="javascript:openTab('博主信息管理','bloggerInfoManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">博主信息管理</a>
			</div>
			<div title="系统管理"  data-options="iconCls:'icon-list'" style="padding:10px">
			    <a href="javascript:openTab('友情链接管理','linkManage.jsp','icon-link')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-link'" style="width: 150px">友情链接管理</a>
				<a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
				<a href="javascript:reflashCache()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-refresh'" style="width: 150px;">刷新系统缓存</a>
				<a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
			</div>
		</div>
	</div>
	<div region="south" style="height: 50px;padding: 10px;" align="center">
			Copyright &copy; 2018 Helmet All Rights Reserved.
	</div>
 	
</div>

<div id="dlg-buttons">
	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeDlg()" cla ss="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

<div class="easyui-dialog" id="dlg" style="width:400px;height:250px;padding: 30px 20px;" closed="true" buttons="#bt">
	<form method="post" id="fm">
		<table>
			<tr>
				<td>博主：</td>
				<td><input type="text" id="bloggerName" name="bloggerName" value="${currentUser.bloggerName }" readonly="readonly"/></td>
				<td><input type="hidden" value="${currentUser.bloggerId} }"></td>
			</tr>
			<tr>
				<td>新密码(长度为6-14)：</td>
				<td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox" required="true"/></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox" required="true"/></td>
			</tr>
			<tr>
				<td id="errorInfo"></td>
			</tr>
		</table>
	</form>
</div>
<div id="bt">
	<a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">确定</a>
	<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">关闭</a>
</div>
</body>
</body>
</html>