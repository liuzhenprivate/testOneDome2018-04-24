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
    <title>全部书评</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="bookReviewAllT clearfix">
		<span>书评</span>
		<i>${discusssum }人评论</i>
		<a href="discuss/todiscuss/${articleId }"><p>撰写评论</p></a>
	</div>
	<div class="bookReviewAllDe">
		<ul>
		<c:forEach items="${discusslist}" var="var" varStatus="vs">
			<li>
				<div class="bookReviewAllDeT clearfix">
					<img src="${var.user.headimgurl }" alt="" />
					<p>${var.user.nickname }</p>
					<c:if test="${var.user.isvip==1 }">
					<span>VIP</span>
					</c:if>
					<div class="clearfix">
					<c:forEach var="s"  begin="1" end="${var.levels}">
					 <img src="pages/home/read/images/myPic42.png" alt="" />
					</c:forEach>
					 
					<c:forEach var="s"  begin="1" end="${5-var.levels}">
					 <img src="pages/home/read/images/myPic41.png" alt="" />
					</c:forEach>
					</div>
				</div>
				<div class="bookReviewAllDeC">
				<p>${var.content }</p>
				</div>
				<div class="bookReviewAllDeB clearfix">
					<p>${var.createTime }</p>
					<span id="zans${var.id}" onclick="zan(${var.id})">${var.zans }</span>
				</div>
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
	function zan(id){
		var url = "<%=basePath%>article/zan/${articleId }/"+id;
		//alert(url);
		$("#zans"+id).addClass("bookReviewAllDeBAct");
		$.get(url,function(data){
			//alert(data);
			if(data>0){
			$("#zans"+id).html(data);
			}
		});
	}
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>

