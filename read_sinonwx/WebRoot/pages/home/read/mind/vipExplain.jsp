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
    <title>VIP说明</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="vipExplain">
		<h2>连续包月</h2>
		<p>1.连续包月套餐享购书超低折扣。</p>
		<p>2.扣费以1个月为期，到期前一天，自动扣除费用。</p>
		<p>3.连续包月套餐可随时退订，具体退订以及绑定说明待完善</p>
		<h2>一次性开通</h2>
		<p>1.连续包月套餐享购书超低折扣。</p>
		<p>2.扣费以1个月为期，到期前一天，自动扣除费用。</p>
		<p>3.连续包月套餐可随时退订，具体退订以及绑定说明待完善</p>
	</div>



	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>

