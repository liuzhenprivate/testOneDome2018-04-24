<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.sinontech.modle.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Integer signflag = (Integer)request.getAttribute("signflag");//0未开启签到功能 1 已签到2未签到
UserInfo user = (UserInfo)request.getAttribute("user");
%>

<!DOCTYPE html>
<html>
  <head>
  	<meta charset="utf-8">
    <base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
     <title>书架</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="bookshelfT clearfix">
		<span>我的书架</span>
		<a href="<%=basePath%>bookshelf/goEdit/<%=user.getWebchatId()%>.do?userid=<%=user.getId()%>" title="">管理书架</a>
	</div>
	<div class="bookshelfDe clearfix">
		<ul>
			<c:if test="${varList != null}">
			<c:forEach items="${varList}" var="var" varStatus="vs">
				<li>
				<a href="<%=basePath%>article/findArticleId/<%=user.getWebchatId()%>/${var.article.id}/<%=user.getId()%>.do">
					<div>
						<img class="bookshelfDe1" src="${var.article.httpUrl}${var.article.bookCover}" alt="" />
						<c:if test="${var.article.serialState == 0 }">
							<span>更新：第${var.article.countChapters }章</span>
						</c:if>
						<c:if test="${var.article.serialState == 1 }"><span>已完结</span></c:if>
					</div>
					<h2>${var.article.articleName}</h2>
					<p>${var.articleChapter.chapterName }</p>
				</a>
				</li>
			</c:forEach>
			</c:if>
		</ul>
	</div>
	<div style="height: 1.2rem;"></div>
	<div class="RankingBot clearfix">
		<a href="<%=basePath%>read/bookstore/<%=user.getWebchatId()%>/1">书城</a>
		<a href="<%=basePath%>board/listBoard/<%=user.getWebchatId() %>.do">排行</a>
		<a class="RankingBotAct" href="<%=basePath%>bookshelf/userlistBookShelf/<%=user.getWebchatId()%>">书架</a>
		<a href="<%=basePath%>my/index/<%=user.getWebchatId()%>">我的</a>
	</div>
	<script>
		$('.RankingBot span').click(function(){
			$(this).addClass('RankingBotAct').siblings().removeClass('RankingBotAct');
		});
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>