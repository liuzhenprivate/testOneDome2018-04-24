<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
//	Integer signflag = (Integer)request.getAttribute("signflag");//0未开启签到功能 1 已签到2未签到
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
    <title>正文阅读购买</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
     
</head>
<body style="background: #f7f7f7;">
	<div class="textReadingT">
	<h2><sapn>${articleChapter.chapterName }</sapn></h2>
		<p>${chapterTxt }</p></div>
	<div class="textReadingBuy">
		<div class="textReadingBuy1 clearfix">
			<p>下一章：第234章 我的客人我的客人不好惹</p>
			<span>20阅读币</span>
		</div>
		<div class="textReadingBuy1 clearfix">
			<p>我的余额：</p>
			<span>87451阅读币</span>
		</div>
		<div class="textReadingBuy2 clearfix">
			<p>我的余额：</p>
			<img src="pages/home/read/images/myPic66.png" alt="" />
		</div>
		<div class="textReadingBuy3 clearfix">
			<p>需支付：<i>20阅读币</i></p>
			<span>购&nbsp;&nbsp;&nbsp;买</span>
		</div>
	</div>
	<script>
		$('.textReadingBuy2 img').click(function(){
			if($(this).attr('src')=='pages/home/read/images/myPic66.png'){
				$(this).attr('src','pages/home/read/images/myPic67.png');
			}else{
				$(this).attr('src','pages/home/read/images/myPic66.png');
			}
		})
	</script>

	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>


