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
    <title>搜索结果</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
    <script>
    	function search(){
    		$("#FORM").submit();
    	}
    </script>
</head>
<body style="background: #f2f2f2;">
<form id="FORM" name="FORM" action="<%=basePath%>read/search/${webchatId}" method="post">
	<div class="searchTop clearfix">
		<div class="clearfix">
			<img src="pages/home/read/images/myPic31.png" alt="" />
			<input placeholder="输入书籍、作者名称或关键词搜索" id="key" name="key" value="${key }"/>
		</div>
		<span onclick="search()">搜索</span>
	</div>
	</form>
	<div style="background: #ffffff; margin-top: 0;" class="detailsListDe">
		<ul>
		<c:forEach items="${list}" var="var" varStatus="vs">
			<li class="clearfix">
			<a href="article/findArticleId/${webchatId }/${var.id }/${user.id}.do">
				<div class="detailsListDeL"><img src="${var.httpUrl}${var.bookCover}" alt="" /></div>
				<div class="detailsListDeR">
				
					<div class="detailsListDeRT clearfix">
						<!-- img src="pages/home/read/images/myPic2.png" alt="" /-->
						<p>${var.articleName }</p>
					</div>
					<div class="detailsListDeRC">
						 ${var.summary }
					</div>
					<div class="detailsListDeRB clearfix">
						<p>${var.author }</p>
						<div class="clearfix">
							<span>${var.articleCategory.gategory }</span>
							<span>${var.readers }人在看</span>
						</div>
					</div>
				
				</div>
				</a>
			</li>
		</c:forEach>
			 
		</ul>
	</div>
	<div class="vipBottom clearfix">
		<span style="margin-left: 0.3rem;"></span>
		<p>我是有底线的</p>
		<span></span>
	</div>

	<script>
		
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>


