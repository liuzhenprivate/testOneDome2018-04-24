<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	 
	Integer signflag = (Integer)request.getAttribute("signflag");//0未开启签到功能 1 已签到2未签到
	UserInfo user = (UserInfo)request.getAttribute("user");
//	System.out.println(user.toString());
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
     <title>强制关注</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="compulsoryAttentionT">
		<h2>关注作者授权公众号继续阅读</h2>
		<p>因版权问题，请关注和继续阅读</p>
	</div>
	<div class="compulsoryAttentionPic">
		<img src="${imghosturl }${webchat.picurl }" alt="" />
	</div>
	<div class="compulsoryAttentionB">
		<h2>长按识别二维码关注</h2>
		<p>或搜索公众号关注“${webchat.name }”</p>
	</div>
	<script>
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>