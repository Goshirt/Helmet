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
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="gbk" src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	function checkForm(){
		var nickName=$("#nickName").val();
		//数据库保存blog的内容需要带有html标记
		var intro=UE.getEditor('editor').getContent();
		var sign=$("#sign").val();
		if (nickName==null || nickName=="") {
			$("#error").html("请输入博客标题");
		}else if (intro==null || intro=="") {
			$("#error").html("请填写博主的简介");
		}else if (sign==null || sign=="") {
			$("#error").html("请输入个性签名");
		}else{
			$("#error").html("");
			$("#fm").submit();
		}
	}
	function resetValue(){
		$("#title").val("");
		$("#blogTypeId").combobox("setValue","");
		UE.getEditor('editor').setContent('');
		$("#keyWord").val("");
	}
</script>
</head>
<body style="margin: 15px;">
<div id="panel" class="easyui-panel" title="博主信息管理" style="padding: 10px">
	<form  id="fm" action="${pageContext.request.contextPath}/admin/blogger/update.do" method="post" 
		enctype="multipart/form-data" onsubmit="reutrn checkForm()">
		<table cellspacing="20px">
			<tr>
				<td width="80px">博主名：</td>
				<td>
					<input type="text" id="bloggerName" name="bloggerName" style="width: 200px" value="${currentUser.bloggerName}" readonly="readonly"/>
					<input type="hidden" name="bloggerId" value="${currentUser.bloggerId} ">
				</td>
			</tr>
			<tr>
				<td>昵称：</td>
				<td>
					<input type="text" id="nickName" name="nickName" style="width: 200px"/>
				</td>
			</tr>
			<tr>
				<td>头像：</td>
				<td>
					<input type="file" id="imageFile" name="imageFile" style="width: 200px"/>
				</td>
			<tr>
				<td valign="top">博主简介：</td>
				<td>
					<script id="editor" name="intro" type="text/plain" style="width:100%;height:500px;"></script>
				</td>
			</tr>
			<tr>
				<td>个人签名：</td>
				<td>
					<input type="text" id="sign" name="sign" style="width: 200px"/>
				</td>
			</tr>
			<tr>
				<td>
					<a href="javascript:checkForm()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">提交</a>
				</td>
			</tr>
		</table>
	</form>
</div>
	<!-- 加载编辑器的容器 -->
	<script id="editor" name="content" type="text/plain"
		style="width:100%;height:500px;"></script>
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var ue = UE.getEditor('editor');
		
		//当需要吧数据加载进ueditor时必须使用监听器，访问后台获取数据，并塞值
		ue.addListener("ready",function(){
			//UE的ajax交互
			UE.ajax.request("${pageContext.request.contextPath}/admin/blogger/getInfo.do", 
					{
						method:"post",
						async:false,
						data:{},
						onsuccess:function(result){
							var result=eval('('+result.responseText+')');
							//把后台获取到的值塞进输入框
							$("#nickName").val(result.nickName);
							$("#sign").val(result.sign);
							UE.getEditor("editor").setContent(result.intro);
						}
					})
		})
	</script>

</body>
</html>