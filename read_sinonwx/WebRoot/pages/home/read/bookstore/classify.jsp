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
    <title>分类</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body>
	 <div class="classifyT clearfix">
		<span class="classifyTAct" onclick="show(1)">男生</span>
		<span onclick="show(0)">女生</span>
	</div>
	 
	<div class="classifyDe" id="boy">
		<ul class="clearfix">
		<c:forEach items="${boylist}" var="var" varStatus="vs">
			<li class="clearfix">
			<a href="read/listCategoryArticle/${webchatId }/${var.id}">
				<div>
					<img src="<%=basePath%>${var.imgUrl }" alt="" />
					<span>热门</span>
				</div>
				<p>${var.gategory }</p>
			</a>
			</li>
		</c:forEach>
			 
		</ul>
	</div>
	<div class="classifyDe" id="girl" style="display:none; ">
		<ul class="clearfix">
		<c:forEach items="${girllist}" var="var" varStatus="vs">
			<li class="clearfix">
			<a href="read/listCategoryArticle/${webchatId }/${var.id}">
				<div>
					<img src="<%=basePath%>${var.imgUrl }" alt="" />
					<span>热门</span>
				</div>
				<p>${var.gategory }</p>
			</a>
			</li>
		</c:forEach>
			 
		</ul>
	</div>
	<script>
		$('.classifyT span').click(function(){
			$(this).addClass('classifyTAct').siblings().removeClass('classifyTAct');
		});
		function show(id){
			if(id==1){
				$("#boy").show();
				$("#girl").hide();
			}else{
				$("#girl").show();
				$("#boy").hide();
			}
		}
	</script>


	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>

