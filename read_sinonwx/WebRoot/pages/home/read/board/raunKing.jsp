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
    <title>榜单详情</title>
     <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="RankingT clearfix">
		<div><span>
			<c:if test="${varList != null}">
			<c:forEach items="${varList}" var="var" varStatus="vs">
				<c:if test="${vs.index == 0}">
					<a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${var.id }/0.do?one='one'">
						${var.boardNmae }
					</a>
				</c:if>
			</c:forEach>
			</c:if>
		</span></div>
		<div><span>
			<c:if test="${varList != null}">
			<c:forEach items="${varList}" var="var" varStatus="vs">
				<c:if test="${vs.index == 1}">
					<a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${var.id }/0.do?one='one'">
						${var.boardNmae }
					</a>
				</c:if>
			</c:forEach>
			</c:if>
		</span></div>
		<div><span>
			<c:if test="${varList != null}">
			<c:forEach items="${varList}" var="var" varStatus="vs">
				<c:if test="${vs.index == 2}">
					<a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${var.id }/0.do?one='one'">
						${var.boardNmae }
					</a>
				</c:if>
			</c:forEach>
			</c:if>
		</span></div>
	</div>
	<a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${listDowd.id }/0.do?one='one'">
	<div class="RankingRecommend">
		<h2>${listDowd.boardNmae }</h2>
		<div class="RankingRecommendA clearfix">
			<div class="RankingRecommendAL">
				<img src="http://kuwx.xinxinwx.cn/read/${editorimg }" alt="" />
			</div>
			<div class="RankingRecommendAR">
				<c:if test="${articleList != null}">
				<c:forEach items="${articleList}" var="var" varStatus="vs">
					<div class="clearfix">
						<img src="pages/home/read/images/myPic10${vs.index+1}.png" alt="" />
						<p>${var.articleName }</p>
					</div>
				</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
	</a>
	<div class="RankingLink">
		<ul>
			<c:if test="${boardInfolist != null}">
			<c:forEach items="${boardInfolist}" var="var" varStatus="vs">
					<li class="clearfix">
						<img src="${var.board.boardImg }" alt="" />
						<p>${var.board.boardNmae }</p><a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${var.board.id }/0.do?one='one'">${var.article.articleName }</a>
					</li>
			</c:forEach>
			</c:if>
		</ul>
	</div>
	<div style="height: 1.2rem;"></div>
	<div class="RankingBot clearfix">
		<a href="<%=basePath%>read/bookstore/<%=user.getWebchatId()%>/1">书城</a>
		<a class="RankingBotAct" href="<%=basePath%>board/listBoard/<%=user.getWebchatId()%>.do">排行</a>
		<a href="<%=basePath%>bookshelf/userlistBookShelf/<%=user.getWebchatId()%>">书架</a>
		<a href="<%=basePath%>my/index/<%=user.getWebchatId()%>">我的</a>
	</div>
	<script>
		$('.RankingBot a').click(function(){
			$(this).addClass('RankingBotAct').siblings().removeClass('RankingBotAct');
		});
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>