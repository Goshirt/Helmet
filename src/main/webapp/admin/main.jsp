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
				<a href="javascript:openTab('写博客','writeBlog.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">写博客</a>
				<a href="javascript:openTab('评论审核','writeBlog.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">写博客</a>
			</div>
			<div title="博客管理"  data-options="iconCls:'icon-product'" style="padding:10px;">
				<a href="javascript:openTab('商品管理','productManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品</a>
				<a href="javascript:openTab('商品大类管理','productBigTypeManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品大类</a>
				<a href="javascript:openTab('商品小类管理','productSmallTypeManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理商品小类</a>
			</div>
			<div title="评论管理"  data-options="iconCls:'icon-order'" style="padding:10px">
				<a href="javascript:openTab('评论管理','orderManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理订单</a>
			</div>
			<div title="博客类别管理" data-options="iconCls:'icon-comment'" style="padding:10px">
				<a href="javascript:openTab('博客类别管理','commentManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理留言</a>
			</div>
			<div title="博主信息管理"  data-options="iconCls:'icon-notice'" style="padding:10px">
				<a href="javascript:openTab('博主信息管理','noticeManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理公告</a>
			</div>
			<div title="下载资源管理"  data-options="iconCls:'icon-news'" style="padding:10px">
				<a href="javascript:openTab('下载资源管理','newsManage.jsp','icon-manage')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-manage'" style="width: 150px;">管理新闻</a>
			</div>
		</div>
	</div>
	<div region="south" style="height: 50px;padding: 10px;" align="center">
			Copyright &copy; 2018 Helmet All Rights Reserved.
	</div>
 	
</div>

<div id="dlg-buttons">
	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closeDlg()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</body>
</html>