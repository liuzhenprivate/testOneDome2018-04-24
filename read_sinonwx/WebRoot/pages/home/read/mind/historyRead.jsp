<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
	Integer signflag = (Integer)request.getAttribute("signflag");//0未开启签到功能 1 已签到2未签到
	UserInfo user = (UserInfo)request.getAttribute("user");
	//System.out.println("======"+user.toString());
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>阅读历史</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
    <script src="pages/home/read/js/jquery.mobile-1.3.2.min.js"></script>
</head>
<body style="background: #fdfdfd;">
	<div class="historyReadTop clearfix">
		<span>我的记录</span>
		<i onclick="delAll();">清空</i>
	</div>
	<div class="historyReadDe">
		<ul>
			<c:if test="${varList != null}">
			<c:forEach items="${varList}" var="var" varStatus="vs">
					<li>
						<a href="<%=basePath%>articlechapter/articlechapterGetById/<%=user.getWebchatId()%>/${var.article.id}/<%=user.getId()%>.do">
							<div class="historyReadDeL">
								<h2>${var.articleName }:${var.chapterName}</h2>
								<p>${var.createTime }</p>
							</div>
						</a>
						<div class="historyReadDeR" onclick="del('${var.id}')">删除</div>
					</li>
				
			</c:forEach>
			</c:if>
		</ul>
	</div>
	<script type="text/javascript">
		function del(id){
			$.get("<%=basePath%>article/delArticleChapterLog/<%=user.getWebchatId()%>/"+id+".do",function(data,status){
			});
			//location.reload();
		}
		function delAll(){
			$.get("<%=basePath%>article/delAllArticleChapterLog/<%=user.getWebchatId()%>/"+<%=user.getId()%>+".do",function(data,status){
				location.reload();
			});
			
		}
	</script>
	<script>
		$('.historyReadDe ul li').on('swipeleft',function(){
			$(this).find('.historyReadDeL').animate({
				left:'-1.3rem'
			});
			$(this).find('.historyReadDeR').animate({
				right:'0'
			});
		});
		$('.historyReadDe ul li').on('swiperight',function(){
			$(this).find('.historyReadDeL').animate({
				left:'0'
			});
			$(this).find('.historyReadDeR').animate({
				right:'-1.3rem'
			});
		});
		
		$(document).on('click','.historyReadDeR',function(){
			$(this).parent('li').remove();
		});
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>