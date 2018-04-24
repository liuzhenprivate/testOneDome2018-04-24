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
	<div class="myVipTop clearfix">
		<div class="myVipTopL">
			<img src="${user.headimgurl }" alt="" />
			<c:if test="${user.isvip!=0 }">
			<span>V</span>
			</c:if>
		</div>
		<div class="myVipTopR">
			<h2>${user.nickname }</h2>
			<span>
			<c:if test="${user.isvip==1 }">
			包月
			</c:if>
			<c:if test="${user.isvip==2 }">
			连续包月
			</c:if>
			<c:if test="${user.isvip==0 }">
			未包月
			</c:if>
			</span>
		</div>
	</div>
	<div class="myVipExplain">
		<p>VIP套餐</p>
		<a href="<%=basePath%>recharge/vipExplain/${user.webchatId }" title="">VIP说明</a>
	</div>
	<div class="myVipSty">
		<ul>
		<c:forEach items="${rechargelist}" var="var" varStatus="vs">
			<li class="clearfix">
			<a href="<%=basePath%>recharge/getrecharge.do?webchatid=${user.webchatId }&rechargeid=${var.id}">
				<div class="myVipStyL">
					<div class="clearfix">
						<h2>${var.rechargeName }</h2>
						<img src="pages/home/read/images/myPic95.png" alt="" />
					</div>
					<p>${var.memo }</p>
				</div>
				<span>￥<i>${var.money/100 }</i></span>
			</a>
			</li>
		</c:forEach>
			 
		</ul>
	</div>
	<div class="myVipExplain">
		<p>VIP尊享特权</p>
		<a href="<%=basePath%>recharge/privilegesDetail/${user.webchatId }" title="">特权详情</a>
	</div>
	<div class="myVipPrivilege clearfix">
		<div>
			<img src="pages/home/read/images/myPic82.png" alt="" />
			<p>VIP专区</p>
		</div>
		<span style="left: 25%;"></span>
		<div>
			<img src="pages/home/read/images/myPic83.png" alt="" />
			<p>专享折扣</p>
		</div>
		<span style="left: 50%;"></span>
		<div>
			<img src="pages/home/read/images/myPic84.png" alt="" />
			<p>赠阅读币</p>
		</div>
		<span style="left: 75%;"></span>
		<div>
			<img src="pages/home/read/images/myPic85.png" alt="" />
			<p>签到双倍</p>
		</div>
	</div>
	<div class="vipRecommed">
		<h2>VIP精选</h2>
		<ul class="clearfix">
			<li>
				<div class="vipRecommedDe">
					<img src="pages/home/read/images/myPic26.png" alt="" />
					<span>VIP</span>
				</div>
				<h3>我家客人你惹不起我家客人你惹不起</h3>
				<p>烽火成林</p>
			</li>
			<li>
				<div class="vipRecommedDe">
					<img src="pages/home/read/images/myPic26.png" alt="" />
					<span>VIP</span>
				</div>
				<h3>我家客人你惹不起我家客人你惹不起</h3>
				<p>烽火成林</p>
			</li>
			<li>
				<div class="vipRecommedDe">
					<img src="pages/home/read/images/myPic26.png" alt="" />
					<span>VIP</span>
				</div>
				<h3>我家客人你惹不起我家客人你惹不起</h3>
				<p>烽火成林</p>
			</li>
		</ul>
	</div>
	<div class="vipRecommedMore">
		查看更多
	</div>
	
	<!--我的VIP弹窗-->
	<div class="myVipAlertO">
		<div class="myVipAlertODe">
			<h2>下次扣费时间</h2>
			<p>2017年12月23日</p>
			<div>
				<span>我知道了</span>
			</div>
		</div>
	</div>
	
	<!--我的VIP弹窗示例-->
	<div class="myVipAlertT">
		<div class="myVipAlertTDe">
			<h2>下次扣费时间</h2>
			<p>2017年12月23日</p>
			<div class="clearfix">
				<span>取消</span>
				<span>确定</span>
			</div>
		</div>
	</div>
	<script>
		$(document).on('click','.vipRecommedMore',function(){
			var html='<li><div class="vipRecommedDe"><img src="pages/home/read/images/myPic26.png" alt="" /><span>VIP</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li>'
			$('.vipRecommed ul').append(html);
		})
		
		$('.myVipAlertODe div').click(function(){
			$('.myVipAlertO').hide();
		})
		
		$('.myVipAlertTDe span').click(function(){
			$('.myVipAlertT').hide();
		})
	</script>

	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>


