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
    <title>充值</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="reChargeT clearfix">
		<p>阅读币余额</p>
		<span>${cumulativeCurrency}</span>
	</div>
	<div class="reChargeDe">
		<h1>选择充值金额</h1>
		<ul class="clearfix">
			<c:forEach items="${rechargelist}" var="var" varStatus="vs">
			<li>
			<a href="<%=basePath%>recharge/getrecharge.do?webchatid=${user.webchatId }&rechargeid=${var.id}&rurl=${rurl}">
				<div class="clearfix">
					<span>${var.money/100 }元</span>
				</div>
				<p>${var.bookCurrency }阅读币
				<c:if test="${var.bookCurrencyGift>0 }">
				<i>+${var.bookCurrencyGift}阅读币</i>
				</c:if>
				</p>
				<c:if test="${var.bookCurrencyGift>0 }">
				<h2 class="clearfix"><span>送${var.bookCurrencyGift}</span></h2>
				</c:if>
			</a>
			</li>
			</c:forEach>
			
		</ul>
	</div>
	<div class="reChargeB">
		<div class="clearfix">
			<span>VIP会员</span>
			<a href="javascript:void(0)" title="">查看详情</a>
		</div>
		<p>通VIP会员即可享受<i>付费书籍8折、VIP专区全免、双倍签到奖励</i>等超值优惠</p>
	</div>
	<script>
		
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>

