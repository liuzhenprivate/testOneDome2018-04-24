<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
	Long cumulativeCurrency = (Long)request.getAttribute("cumulativeCurrency");
	//List<Rechargeset> rechargelist = (List<Rechargeset>)request.getAttribute("rechargelist");
//	UserInfo user = (UserInfo)request.getAttribute("user");
//	System.out.println(user.toString());
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>消费记录</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #fdfdfd;">
	<div class="reChargeAllT">
		<span></span>
		消费书籍
		<i></i>
	</div>
	<div class="recordConsumpte">
		<ul>
		<c:forEach items="${articleLoglist}" var="var" varStatus="vs">
			<li class="clearfix">
			<a href="<%=basePath%>consum/listchapter/${webchatid}/${var.article.id }">
				<div class="recordConsumpteL">
					<img src="${var.article.httpUrl}${var.article.bookCover}" alt="" />
				</div>
				<div class="recordConsumpteC">
					<h2>${var.article.articleName }</h2>
					<p>${var.fee }阅读币</p>
					<p>${var.createTime }</p>
				</div>
				<img src="pages/home/read/images/myPic23.png" alt="" />
			</a>
			</li>
		</c:forEach>
			 
		</ul>
	</div>
	<script>
		
	</script>


	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>


