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
    <title>我的账户</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="myCountT">
		<div class="myCountTDe">
			<h2>${bookCurrency }</h2>
			<p>阅读币</p>
			<div> <a href="<%=basePath%>/recharge/list/${user.webchatId}">充&nbsp;&nbsp;&nbsp;值</a> </div>
		</div>
	</div>
	<div class="myCountDe">
		<ul>
			<li class="clearfix">
				<span>充值记录</span>
				<a href="<%=basePath%>recharge/rechargeLog/${user.webchatId}" title=""></a>
			</li>
			<li class="clearfix">
				<span>消费记录</span>
				<a href="<%=basePath%>/consum/list/${user.webchatId}" title="">近身保镖</a>
			</li>
			<%-- <li class="clearfix">
				<span>VIP会员</span>
				<a href="<%=basePath%>/recharge/viplist/${user.webchatId}" title="">
				<c:if test="${user.isvip==0 }">
				未开通
				</c:if>
				<c:if test="${user.isvip==1 }">
				已开通连续包月
				</c:if>
				<c:if test="${user.isvip==2 }">
				已开通包月
				</c:if>
				</a>
			</li> --%>
		</ul>
	</div>
	<script>
		
	</script>

	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>

