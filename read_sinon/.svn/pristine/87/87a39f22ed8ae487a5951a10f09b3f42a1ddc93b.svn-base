<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<title>基本信息</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="essentialInformationAll">
		<div class="channelDetailsDeInfor clearfix">
			<h2>基本信息</h2>
		</div>
		<div class="channelDetailsDeAll clearfix">
			<ul>
				<li class="clearfix">
					<span>渠道ID</span>
					<p>${wxpd.USER_ID }</p>
				</li>
				<li class="clearfix">
					<span>用户昵称</span>
					<p>${wxpd.NICKNAME }</p>
				</li>
				<li class="clearfix">
					<span>微信号</span>
					<p>暂无</p>
				</li>
				<li class="clearfix">
					<span>绑定手机</span>
					<p>
						<c:if test="${wxpd.PHONE  == null }">
							暂无
						</c:if>
						<c:if test="${wxpd.PHONE  != null }">
							${var.PHONE }
						</c:if>
					</p>
				</li>
				<li class="clearfix">
					<span>注册时间</span>
					<p>${wxpd.CREATE_TIME }</p>
				</li>
				<li class="clearfix">
					<span>是否关注</span>
					<p>
						<c:if test="${wxpd.FOLLOWSTATE  == 0 }">
							未关注
						</c:if>
						<c:if test="${wxpd.FOLLOWSTATE  == 1 }">
							已关注
						</c:if>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${wxpd.CTIME}
					</p>
				</li>
			</ul>
		</div>
	</div>
	<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("faker"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
	</script>
</body>
</html>
