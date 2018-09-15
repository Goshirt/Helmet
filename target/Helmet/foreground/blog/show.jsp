<script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
    SyntaxHighlighter.all();
    
    //显示所有的评论信息，默认是显示10条
    function showOtherComment(){
    	$(".otherComment").show();
    }
    //更换验证码
    function loadimage(){
		document.getElementById("randImage").src="${pageContext.request.contextPath}/image.jsp?"+Math.random();
	}
	function checkPublishForm(){
		var content=$("#content").val();
		var imageCode=$("#imageCode").val();
		if (content==null||content=="") {
			$("#error").html("请输入评论内容")
			return ;
		}
		if (imageCode==null||imageCode=="") {
			$("#error").html("请输入评论验证码")
			return ;
		}
		$.post("${pageContext.request.contextPath}/comment/save.do",
				{"content":content,"imageCode":imageCode,"blog.blogId":'${blog.blogId}'},
				function(result){
					if (result.success) {
						alert("评论成功，待博主审核后通过后显示");
						 resetValue();
						$("#error").html("");
					}else {
						$("#error").html(result.error);
					}
		},"json");
	}
	function resetValue(){
		$("#imageCode").val("");
		$("#content").val("");
	}
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div class="data_list">
		<div class="data_list_title">
			<img
				src="${pageContext.request.contextPath}/static/images/blog_show_icon.png" />最新博客
		</div>
		<div>
		   <div class="blog_title" style="margin-top: 20px;text-align: center;"><h3><strong>${blog.title}</strong></h3></div>
		   <div style="padding-left: 330px;padding-bottom: 20px;padding-top: 10px">
			<div class="bshare-custom"><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到人人网" class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a title="分享到网易微博" class="bshare-neteasemb"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a><span class="BSHARE_COUNT bshare-share-count">0</span></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=1&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
		   </div>
			<div class="blog_info" style="text-align: center;">
				发布时间：『 <fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd"/>』&nbsp;&nbsp;博客类别：${blog.blogType.typeName}&nbsp;&nbsp;阅读(<font color="red">${blog.clickNum }</font>) 
				<img src="${pageContext.request.contextPath}/static/images/dollar.png" />打赏
			</div>
			<div class="blog_content" style="margin-top: 20px;padding-bottom: 30px;">
				${blog.content }
			</div>
			<div class="blog_keyWord">
				<font><strong>关键字：</strong></font>
					<c:choose>
						<c:when test="${wordList==null }">
							&nbsp;&nbsp;无
						</c:when>
						<c:otherwise>
							<c:forEach items="${wordList}" var="word">
								&nbsp;&nbsp;<a href="#" target="_blank">${word }</a>&nbsp;&nbsp;
							</c:forEach>
						</c:otherwise>
					</c:choose>
			</div>
			<div class="blog_lastAndNextPage">
				${upCodeAndDownCode}
			</div>
			<div class="data_list">
				<div class="data_list_title">
					<img src="/static/images/comment_icon.png"/>
					评论信息
					<c:if test="${commentList.size()>10 }">
						<a href="javascript:showOtherComment()" style="float: right;padding-right: 40px;">显示所有评论</a>
					</c:if>
				</div>
				<div class="commentDatas">
					<c:choose>
						<c:when test="${comments.size()==0 }">
							暂无评论
						</c:when>
						<c:otherwise>
							<c:forEach var="comment" items="${comments}" varStatus="status">
								<c:choose>
									<c:when test="${status.index<10 }">
										<div class="comment">
											<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIP }：</font>${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</span>			
										</div>
									</c:when>
									<c:otherwise>
										<div class="otherComment">
											<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIP }：</font>${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</span>			
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="data_list" >
			<div class="data_list_title">
				<img src="/static/images/publish_comment_icon.png"/>
				发表评论
			</div>
			<div class="publish_comment">
				<form class="form1" method="post">
					<div>
						<textarea style="width: 100%" rows="3" id="content" name="content" placeholder="来说两句吧..."></textarea>
					</div>
					<div class="verCode">
						验证码：<input type="text" value="" name="imageCode"  id="imageCode" size="10" onkeydown="if(event.keyCode==13)checkPublishForm()"/>&nbsp;<img onclick="javascript:loadimage();"  title="换一张试试" name="randImage" id="randImage" src="/image.jsp" width="60" height="20" border="1" align="absmiddle"> 
						<span><font color="red" id="error">${error }</font></span>
					</div>
					<div class="publishButton">
						<button class="btn btn-primary" type="button" onclick="return checkPublishForm()">发表评论</button>
					</div>
				</form>
			</div>
		</div>
	</div>
