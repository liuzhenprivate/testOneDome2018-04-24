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
    <title>消费详情</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #fdfdfd;">
	<div class="reChargeAllT">
		<span></span>
		消费章节
		<i></i>
	</div>
	<div class="reChargeAllDe">
		<ul>
		<c:forEach items="${consums}" var="var" varStatus="vs">
			<li class="clearfix">
				<div class="reChargeAllDeL">
					<h2>${var.chapterName }</h2>
					<p>${var.createTime }</p>
				</div>
				<i>-${var.consumes }</i>
			</li>
		</c:forEach>
			 
		</ul>
	</div>
	<script>
		
	</script>



	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>

