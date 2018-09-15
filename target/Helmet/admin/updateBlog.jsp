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
	function submitBlog(){
		var title=$("#title").val();
		var blogTypeId=$("#blogTypeId").combobox("getValue");
		//数据库保存blog的内容需要带有html标记
		var content=UE.getEditor('editor').getContent();
		//获取纯文本内容，Lucene索引需要
		var contentNoTag=UE.getEditor('editor').getContentTxt();
		var keyWord=$("#keyWord").val();
		if (title==null || title=="") {
			$("#error").html("请输入博客标题");
		}else if (blogTypeId==null || blogTypeId=="") {
			$("#error").html("请选择博客类别");
		}else if (content==null || content=="") {
			$("#error").html("请输入博客内容");
		}else if (keyWord==null || keyWord=="") {
			$("#error").html("请输入关键字");
		}else{
			$("#error").html("");
			$.post("${pageContext.request.contextPath}/admin/blog/save.do",
					{
					 "blogId":"${param.blogId}",
					 "title":title,
					 "blogType.typeId":blogTypeId,
					 "contentNoTag":contentNoTag,
					 "content":content,
					 "summary":UE.getEditor('editor').getContentTxt().substr(0,155),
					 "keyWord":keyWord
					 },
					function(result){
						if (result.success) {
							$.messager.alert("系统提示","博客更新成功");
							resetValue();
						}else{
							$.messager.alert("系统提示","博客更新失败");
						}
			},"json");
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
<body style="margin: 5px;">
<div id="panel" class="easyui-panel" title="修改博客" style="padding: 10px">
	<table cellspacing="20px">
		<tr>
			<td width="80px">博客标题：</td>
			<td>
				<input type="text" id="title" name="title" style="width: 400px"/>
			</td>
		</tr>
		<tr>
			<td>所属类别：</td>
			<td>
				<select class="easyui-combobox" style="width: 154px" id="blogTypeId" name="blogType.typeId" editable="false" panelHeight="auto">
					<option value="">请选择博客类别...</option>
					<c:forEach var="blogType" items="${blogTypeList }">
						<option value="${blogType.typeId }">${blogType.typeName }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td valign="top">博客内容：</td>
			<td>
				<script id="editor" name="content" type="text/plain" style="width:100%;height:500px;"></script>
			</td>
		</tr>
		<tr>
			<td>关键字：</td>
			<td>
				<input type="text" id="keyWord" name="keyWord" style="width: 400px"/>&nbsp;(多个关键字中间用空格隔开)
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="javascript:submitBlog()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">发布博客</a>&nbsp;&nbsp;&nbsp;
				<span><font color="red" id="error"></font></span>
			</td>
		</tr>
	</table>
</div>
	<!-- 加载编辑器的容器 -->
	<script id="editor" name="content" type="text/plain"
		style="width:100%;height:500px;"></script>
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var ue = UE.getEditor('editor');
		//
		UE.ajax.request("${pageContext.request.contextPath}/admin/blog/getBlog.do",{
			method:"post",
			async:false,
			data:{"blogId":"${param.blogId}"},
			onsuccess:function(result){
				result=eval('('+result.responseText+')');
				$("#title").val(result.title);
				$("#keyWord").val(result.keyWord);
				$("#blogTypeId").combobox("setValue",result.blogType.typeId);
				UE.getEditor('editor').setContent(result.content);
			}
		})
	</script>

</body>
</html>