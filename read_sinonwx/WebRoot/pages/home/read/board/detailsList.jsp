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
<body>
	<div class="detailsListT clearfix">
		<c:if test="${boardType ==0 }">
			<span class="detailsListTAct" id="allboard"><a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${boardId}/0.do">总榜</a></span>
			<span id="manboard"><a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${boardId}/1.do">男生</a></span>
			<span id="girlboard"><a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${boardId}/2.do">女生</a></span>
		</c:if>
		<c:if test="${boardType ==1 }">
			<span id="allboard"><a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${boardId}/0.do">总榜</a></span>
			<span class="detailsListTAct" id="manboard"><a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${boardId}/1.do">男生</a></span>
			<span id="girlboard"><a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${boardId}/2.do">女生</a></span>
		</c:if>
		<c:if test="${boardType ==2 }">
			<span id="allboard"><a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${boardId}/0.do">总榜</a></span>
			<span id="manboard"><a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${boardId}/1.do">男生</a></span>
			<span class="detailsListTAct" id="girlboard"><a href="<%=basePath%>boarddetil/listBoardDetail/<%=user.getWebchatId() %>/${boardId}/2.do">女生</a></span>
		</c:if>
	</div>
	<div class="detailsListDe">
		<ul>
			<c:if test="${varList != null}">
			<c:forEach items="${varList}" var="var" varStatus="vs">
				<a href="<%=basePath%>article/findArticleId/<%=user.getWebchatId() %>/${var.id}/<%=user.getId()%>.do">
					<li class="clearfix">
						<div class="detailsListDeL"><img src="${var.httpUrl}${var.bookCover}" alt="" /></div>
						<div class="detailsListDeR">
							<div class="detailsListDeRT clearfix">
								<c:if test="${vs.index+1 < 4}">
									<img src="pages/home/read/images/myPic10${vs.index+1}.png" alt="" />
								</c:if>
								<c:if test="${vs.index+1 > 3}">
									<span>${vs.index+1}</span>
								</c:if>
								<p>${var.articleName }</p>
							</div>
							<div class="detailsListDeRC">
								${var.summary }
							</div>
							<div class="detailsListDeRB clearfix">
								<p>${var.author }</p>
								<div class="clearfix">
									<span>${var.articleCategory.gategory}</span>
									<span>${var.displayReaders }人在看</span>
								</div>
							</div>
						</div>
					</li>
				</a>
			</c:forEach>
			</c:if>
		</ul>
	</div>
	<script>
		$('.detailsListT span').click(function(){
			$(this).addClass('detailsListTAct').siblings().removeClass('detailsListTAct');
		});
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>