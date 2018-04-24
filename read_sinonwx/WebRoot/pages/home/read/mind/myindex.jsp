<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
	Integer signflag = (Integer)request.getAttribute("signflag");//0未开启签到功能 1 已签到2未签到
	UserInfo user = (UserInfo)request.getAttribute("user");
	//System.out.println("======"+user.toString());
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>我的</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="mindTop clearfix">
		<div class="mindTopL">
			<img src="<%=user.getHeadimgurl() %>" alt="" />
			
			<%=user.getIsvip()==0?"":"<span>V</span>" %>
			
		</div>
		<div class="mindTopC">
			<h2><%=user.getNickname() %></h2>
			<p>ID:<%=user.getId() %></p>
		</div>
		<% if(signflag==2){ %>
		<img src="pages/home/read/images/myPic86.png" alt="" onclick="sign()"/>
		<%}else{ %>
		<img src="pages/home/read/images/myPic88.png" alt="" onclick="sign()"/>
		<%} %>
	</div>
	<div class="mindC clearfix">
		<div>
			<h2><%=user.getCumulativeCurrency()-user.getBookCurrency() %></h2>
			<p>阅读币</p>
		</div>
		<i></i>
		<div onclick="signlist()">
			<h2 id="times"><%=user.getSigntimes() %></h2>
			<p>签到天数</p>
		</div>
	</div>
	<div class="mindCount clearfix">
		<span>我的账户</span>
		<a href="javascript:void(0)" title="">充值</a>
	</div>
	<%-- <div class="mindVips clearfix">
		<span>我的VIP</span>
		<a href="<%=basePath%>/recharge/viplist/<%=user.getWebchatId() %>" title="">
		<%if(user.getIsvip()==1){ %> 
		已开通连续包月
		<%}else if(user.getIsvip()==2){ %>
		已开通包月
		<%}else{ %>
		未开通
		<%} %>
		</a>
	</div> --%>
	<div class="mindRead clearfix">
		<span>阅读历史</span>
		<a href="<%=basePath%>article/historicalRecords/<%=user.getWebchatId()%>/<%=user.getId()%>.do" title=""></a>
	</div>
	 
	<div class="mindAuto clearfix">
		<span>自动购买付费章节</span>
		<%if(user.getAutoOrder()==1){ %>
		<img src="pages/home/read/images/myPic67.png" alt="" />
		<%}else{ %>
		<img src="pages/home/read/images/myPic66.png" alt="" />
		<%} %>
	</div>
	<div style="height: 1.2rem;"></div>
	<div class="RankingBot clearfix">
		<a href="<%=basePath%>read/bookstore/<%=user.getWebchatId()%>/1">书城</a>
		<a href="<%=basePath%>board/listBoard/<%=user.getWebchatId() %>.do">排行</a>
		<a href="<%=basePath%>bookshelf/userlistBookShelf/<%=user.getWebchatId()%>">书架</a>
		<a class="RankingBotAct" href="<%=basePath%>my/index/<%=user.getWebchatId()%>">我的</a>
	</div>
	<script>
		$('.mindCount a').click(function(e){
			e.stopPropagation();
			window.location.href="<%=basePath%>/recharge/list/<%=user.getWebchatId() %>";
		});
		
		$('.mindCount').click(function(){
			window.location.href="<%=basePath%>/my/account/<%=user.getWebchatId() %>";
		});
		
		
		$('.RankingBot span').click(function(){
			$(this).addClass('RankingBotAct').siblings().removeClass('RankingBotAct');
		});
		
		$('.mindTop>img').click(function(){
			$(this).attr('src','pages/home/read/images/myPic88.png');
		});
		
		$('.mindAuto img').click(function(){
			if($(this).attr('src')=='pages/home/read/images/myPic66.png'){
				$(this).attr('src','pages/home/read/images/myPic67.png');
				auto(1);
			}else{
				$(this).attr('src','pages/home/read/images/myPic66.png');
				auto(0);
			}
		});
		
		function sign(){
			var url = "<%=basePath%>sign/sign/<%=user.getWebchatId() %>";
			//alert(url);
			
			$.get(url,function(data){
				//alert(data);
				if(data>0){
					$("#times").text(data);
					location.reload();
				}
			});
		}
		//开启关闭自动购买付费章节功能
		function auto(autoOrder){
			var url = "<%=basePath%>recharge/autoOrder/<%=user.getWebchatId() %>/"+autoOrder;
			//alert(url);
			$.get(url,function(data){
				//alert(data);
			});
		}
		function signlist(){
			window.location.href="<%=basePath%>sign/list/<%=user.getWebchatId() %>";
		}
	</script>


	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>


