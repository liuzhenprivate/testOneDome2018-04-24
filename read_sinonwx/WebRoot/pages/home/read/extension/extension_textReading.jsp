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
     <title>正文阅读颜色</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/text.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body>
	<input type="hidden" name = "user_id" id = "user_id" value = "${user_id }"/>
	<div class="fileLDe">
		<h1>${extensionContent.title }</h1>
		<div class="fileLDeImg">
			<img src="${httpUrl }${extensionContent.cover}" alt="" />
		</div>
		<c:choose>
		<c:when test="${not empty chapterList}">
		<c:forEach items="${chapterList}" var="var" varStatus="vs">
			<div class="fileLDeAll">
				<h2>${var.articleChapter.chapterName }</h2>
				<p>${var.chapterTxt }</p>
			</div>
		</c:forEach>
		</c:when>
		</c:choose>
		<a href="<%=basePath%>articlechapter/articlechapterGetById/${webchat.id }/${articleChapterid}/0/${extensionContent.forceChapter}.do">
			<div class="fileLDeBtn">
				${extensionContent.clickTitle }
			</div>
		</a>
	</div>
	<script>
		
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>