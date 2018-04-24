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
	<title>渠道详情</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">渠道管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop1" href="<%=basePath%>user/list.do" title="">渠道列表</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">渠道详情</a>
	</div>
	<div class="channelDetailsDe">
		<div class="channelDetailsDeTop clearfix">
			<span id="channelDetailsOne" onclick="srcTo('channelDetailsOne')" name="<%=basePath%>user/sysuserNewsDetails.do?USER_ID=${pd.USER_ID}" class="channelDetailsDeTopAct">渠道信息总览</span>
			<span id="channelDetailsFive" onclick="srcTo('channelDetailsFive')" name="<%=basePath%>user/sysuserReadDetails.do?USER_ID=${pd.USER_ID}">渠道读者列表</span>
			<span id="channelDetailsSeven" onclick="srcTo('channelDetailsSeven')" name="<%=basePath%>extensioncontent/sysuserExtensionDetails.do?USER_ID=${pd.USER_ID}">推广统计</span>
			<span id="channelDetailsThree" onclick="srcTo('channelDetailsThree')" name="<%=basePath%>user/sysuserRechargeDetails.do?USER_ID=${pd.USER_ID}">充值记录</span>
			<span id="channelDetailsSix" onclick="srcTo('channelDetailsSix')" name="<%=basePath%>user/sysuserConsumeDetails.do?USER_ID=${pd.USER_ID}">消费记录</span>
			<span id="channelDetailsFour" onclick="srcTo('channelDetailsFour')" name="<%=basePath%>user/sysuserSingDetails.do?USER_ID=${pd.USER_ID}">签到记录</span>
			<span id="channelDetailsTwo" onclick="srcTo('channelDetailsTwo')" name="<%=basePath%>user/sysuserAccountDetails.do?USER_ID=${pd.USER_ID}">账户信息</span>
		</div>
		<div class="channelDetailsDeTab">
			<iframe id="channelDetails" src="<%=basePath%>user/sysuserNewsDetails.do?USER_ID=${pd.USER_ID}" width="100%" height="100%" id="faker" frameborder="no" scrolling="no" allowtransparency="yes"></iframe>
		</div>
	</div>
	
	<script>
		$('.channelDetailsDeTop span').click(function(){
			$(this).addClass('channelDetailsDeTopAct').siblings().removeClass('channelDetailsDeTopAct');
		})
		
		function srcTo(id){
			var src=$('#'+id).attr('name');
			$('.channelDetailsDeTab iframe').attr('src',src);
		}
		function srcIframe() {           
		     var iframe = document.getElementById("channelDetails");           
		     try {               
		            var bHeight =iframe.contentWindow.document.body.scrollHeight;               
		            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;              
		            var height = Math.max(bHeight, dHeight);            
		            iframe.height = height;
		        } catch (ex) { }       
		}
		window.setInterval("srcIframe()", 100);
		
		
	</script>
</body>
</html>

