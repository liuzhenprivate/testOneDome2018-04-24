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
    <title>正文阅读</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
     
</head>
<body style="background: #f7f7f7;">
	<div class="textReadingT">
		<h2><sapn>第1章</sapn>我是江户川柯南</h2>
		<p>当方泽确定了自家猫会说话的那一刻，心里第一时间想到的是，要是把这只又土又肥的大黄猫给卖了，会不会让他能在海平市最好的会所奢侈一整天。</p>
		<p>当方泽确定了自家猫会说话的那一刻，心里第一时间想到的是，要是把这只又土又肥的大黄猫给卖了，会不会让他能在海平市最好的会所奢侈一整天。</p>
		<p>当方泽确定了自家猫会说话的那一刻，心里第一时间想到的是，要是把这只又土又肥的大黄猫给卖了，会不会让他能在海平市最好的会所奢侈一整天。</p>
		<p>当方泽确定了自家猫会说话的那一刻，心里第一时间想到的是，要是把这只又土又肥的大黄猫给卖了，会不会让他能在海平市最好的会所奢侈一整天。</p>
	</div>
	<div class="textReadingBtn">
		<div class="textReadingBtnT clearfix">
			<span>上一章</span>
			<div>
				<span></span>
				<i></i>
			</div>
			<i>下一章</i>
		</div>
		<div class="textReadingBtnB clearfix">
			<span>目录</span>
			<span>设置</span>
		</div>
	</div>
	<div class="textReadPrompt clearfix">
		<div class="textReadPromptL">
			<img src="pages/home/read/images/myPic69.png" alt="" />
			<p>上一页</p>
		</div>
		<div class="textReadPromptLine"></div>
		<div class="textReadPromptDe">
			<img class="textReadPromptDe1" src="pages/home/read/images/myPic71.png" alt="" />
			<p>菜单区</p>
			<img class="textReadPromptDe2" src="pages/home/read/images/myPic72.png" alt="" />
		</div>
		<div class="textReadPromptR">
			<img src="pages/home/read/images/myPic70.png" alt="" />
			<p>下一页</p>
		</div>
	</div>
	<script>
		
	</script>


	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>


