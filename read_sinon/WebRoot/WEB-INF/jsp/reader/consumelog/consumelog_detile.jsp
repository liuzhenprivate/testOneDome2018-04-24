<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<title>消费管理</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
	<style>
		.CURRENCY{
			width:480px;
			padding: 10px 0;
			background:#fff
		}
		.CURRENCY div{
			margin-left: 30px;
			margin-top: 30px;
			color:#333;
		}
		.CURRENCY div span{
			display: inline-block;
			font-size:14px;
			height: 30px;
			line-height: 30px;
			float: left;
		}
		.CURRENCY div p{
			font-size:14px;
			height: 30px;
			line-height: 30px;
			float: left;
			text-align: left;
			margin-left: 50px;
		}
	</style>
<body>
	<div id="zhongxin">
	<div class="CURRENCY" >
		<div class="clearfix">
			<span>剩余阅读币：</span>
			<p>${pd.BOOKCURRENCYS }</p>
		</div>
		
		<div class="clearfix">
			<span>使用阅读币：</span>
			<p>${pd.BOOK_CURRENCY }</p>
		</div>
		
		<div class="clearfix">
			<span>签到阅读币：</span>
			<p>${pd.QBOOK_CURRENCY }</p>
		</div>	
	</div>
	</div>
</body>
</html>


