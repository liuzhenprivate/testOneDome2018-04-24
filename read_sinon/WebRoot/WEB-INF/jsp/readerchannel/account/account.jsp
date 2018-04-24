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
	<title>账户信息（收款信息）</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/accountInformation.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">账户信息</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">收款信息</a>
	</div>
	<div class="presentRecordDe">
		<div class="presentRecordDeH1">账户信息</div>
		<div class="receivablesInformationInf">
			<ul>
				<li class="clearfix">
					<span>姓名</span>
					<p>${account.ACCOUNT_NAME }</p>
				</li>
				<li class="clearfix">
					<span>银行账号</span>
					<p>${account.CARD_NUM }</p>
				</li>
				<li class="clearfix">
					<span>开户银行</span>
					<p>${account.OPEN_CARD }</p>
				</li>
				<li class="clearfix">
					<span>常用手机</span>
					<p>${account.PHONE }</p>
				</li>
				<li class="clearfix">
					<span>联系QQ</span>
					<p>${account.QQ }</p>
				</li>
				<li class="clearfix">
					<span>微信号</span>
					<p>${account.WEBCHAT }</p>
				</li>
				<li class="clearfix">
					<span>居住地</span>
					<p>${account.ADDRESS }</p>
				</li>
			</ul>
		</div>
		<div class="receivablesInformationBtn clearfix">
			<a href="channelaccount/goeditaccount.do" title="">修&nbsp;&nbsp;改</a>
		</div>
		<div style="margin-top: 30px;" class="presentRecordDeH1">收款说明</div>
		<div class="receivablesInformationExplain">
			<ul>
				<li class="clearfix">
					<span>结算方式</span>
					<p>每日结算（周六、周日的款项在周一一起结算），一般会在每个工作日的18点左右到账，请注意查看，如遇特殊情况，到账时间可</br>能会有最多不超过2天的延迟。</p>
				</li>
				<li>
					<span>温馨提示</span>
					<p>收款信息一经填写无法修改，为了您的账户安全，如需修改请联系管理员</br>管理员微信：5463464&nbsp;&nbsp;&nbsp;&nbsp;QQ：51456463&nbsp;&nbsp;&nbsp;&nbsp;电话：13336223325</p>
				</li>
			</ul>
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

