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
	<title>基础设置</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/basicSetting.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">基础设置</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">必要设置</a>
	</div>
	<div class="necessarySettingDe">
		<div class="necessarySettingDeTop clearfix">
			<p>公众号后台配置</p>
			<a href="javascript:void(0)" title=""><span>查看教程</span></a>
		</div>
		<div class="channelDetailsDeAll clearfix">
			<ul>
				<li class="clearfix">
					<span>公众号编号</span>
					<p>${webchat.WEBCHAT_ID }</p>
				</li>
				<li class="clearfix">
					<span>公众号名称</span>
					<p>${webchat.NAME }</p>
				</li>
				<li class="clearfix">
					<span>公众号类型</span>
					<p>
					<c:if test="${webchat.TYPE=='1' }">
					公众号
					</c:if>
					<c:if test="${webchat.TYPE=='2' }">
					订阅号
					</c:if>
					</p>
				</li>
				<li class="clearfix">
					<span>微信号</span>
					<p>${webchat.WEBCHAT_CODE }</p>
				</li>
				<li class="clearfix">
					<span>App ID</span>
					<p>${webchat.APPID }</p>
				</li>
				<li class="clearfix">
					<span>App Secret</span>
					<p>${webchat.APPSECRET }</p>
				</li>
				<li class="clearfix">
					<span>令牌</span>
					<p>${webchat.TOKEN } </p>
				</li>
				<li class="clearfix">
					<span>公众号二维码</span>
					<div>
						<img class="channelDetailsDeAll1" src="${webchat.PIC_URL }" alt="" />
						<img class="channelDetailsDeAll2" src="${webchat.PIC_URL }" alt="" />
					</div>
				</li>
				<li class="clearfix">
					<span>白名单</span>
					<p>139.224.19.31</p>
				</li>
				<li class="clearfix">
					<span>业务域名</span>
					<p>kuwx.xinxinwx.cn/read/</p>
				</li>
				<li class="clearfix">
					<span>安全域名</span>
					<p>kuwx.xinxinwx.cn/read/</p>
				</li>
				<li class="clearfix">
					<span>网页授权域名</span>
					<p>kuwx.xinxinwx.cn/read/</p>
				</li>
			</ul>
		</div>
		<div class="necessarySettingDeBtn clearfix">
			<a href="channelwebchat/goeditwebchat.do" title="">修&nbsp;&nbsp;改</a>
		</div>
	</div>
	<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("Thunder"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		$('.channelDetailsDeAll ul li div').mouseenter(function(){
			$('.channelDetailsDeAll2').show();
		})
		$('.channelDetailsDeAll ul li div').mouseleave(function(){
			$('.channelDetailsDeAll2').hide();
		})
	</script>
</body>
</html>