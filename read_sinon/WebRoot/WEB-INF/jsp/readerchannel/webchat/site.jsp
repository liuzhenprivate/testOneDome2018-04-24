<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<title>站点设置</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/basicSetting.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">基础设置</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">站点设置</a>
	</div>
	<div class="necessarySettingDe">
		<div class="necessarySettingDeTop clearfix">
			<p>站点信息</p>
			<a href="javascript:void(0)" title=""><span>查看教程</span></a>
		</div>
		<div class="siteSettingDeAll clearfix">
			<ul>
				<li class="clearfix">
					<span>站点名称</span>
					<p>${webchat.SETNAME }</p>
				</li>
				<li class="clearfix">
					<span>关键词</span>
					<p>${webchat.KEYWORDS }</p>
				</li>
				<li class="clearfix">
					<span>客服电话</span>
					<p>${webchat.CUSTOM_PHONE }</p>
				</li>
				<li class="clearfix">
					<span>客服QQ</span>
					<p>${webchat.CUSTOM_QQ }</p>
				</li>
				<li class="clearfix">
					<span>客服邮箱</span>
					<p>${webchat.CUSTOM_EMAIL }</p>
				</li>
				<li class="clearfix">
					<span>客服微信</span>
					<p>${webchat.CUSTOM_WEBCHAT }</p>
				</li>
				<li class="clearfix">
					<span>公众号名称</span>
					<p>${webchat.NAME }</p>
				</li>
				<li class="clearfix">
					<span>描述</span>
					<p>${webchat.MEMO }</p>
				</li>
			</ul>
		</div>
		<div class="necessarySettingDeBtn clearfix">
			<a href="channelwebchat/goeditsiteset.do" title="">修&nbsp;&nbsp;改</a>
		</div>
	</div>
	<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("iframe"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
	</script>
</body>
</html>

