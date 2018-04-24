<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
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
    <title>特权详情</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="privilegesDetailT">
		<img src="pages/home/read/images/myPic81.png" alt="" />
		<p>特权详情</p>
	</div>
	<div class="privilegesDetailDe">
		<ul>
			<li class="clearfix">
				<img src="pages/home/read/images/myPic82.png" alt="" />
				<div>
					<h2>VIP专区免费</h2>
					<p><span>1万</span>册VIP专享书籍免费畅读</p>
				</div>
			</li>
			<li class="clearfix">
				<img src="pages/home/read/images/myPic83.png" alt="" />
				<div>
					<h2>VIP专区免费</h2>
					<p><span>1万</span>册VIP专享书籍免费畅读</p>
				</div>
			</li>
			<li class="clearfix">
				<img src="pages/home/read/images/myPic84.png" alt="" />
				<div>
					<h2>VIP专区免费</h2>
					<p><span>1万</span>册VIP专享书籍免费畅读</p>
				</div>
			</li>
			<li class="clearfix">
				<img src="pages/home/read/images/myPic85.png" alt="" />
				<div>
					<h2>VIP专区免费</h2>
					<p><span>1万</span>册VIP专享书籍免费畅读</p>
				</div>
			</li>
		</ul>
	</div>


	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>


