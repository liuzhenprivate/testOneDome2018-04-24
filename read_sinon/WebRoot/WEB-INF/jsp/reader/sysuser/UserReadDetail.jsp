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
	<title>渠道用户详情</title>
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
		<a class="announcementManagementTop1" href="<%=basePath%>user/goSysUserDetail.do?USER_ID=${pd.USER_ID}" title="">渠道详情</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">渠道用户详情</a>
	</div>
	<div class="channelDetailsDe">
		<div class="channelDetailsDeTop clearfix">
			<span id="channelUserDetailsOne" onclick="srcTo('channelUserDetailsOne')" name="<%=basePath%>wxuser/sysUserReadNewsAll.do?USERID=${pd.USERID}" class="channelDetailsDeTopAct">基本信息</span>
			<span id="channelUserDetailsTwo" onclick="srcTo('channelUserDetailsTwo')" name="<%=basePath%>rechargelog/sysUserReadReechargeLog.do?USERID=${pd.USERID}">充值记录</span>
			<span id="channelUserDetailsThree" onclick="srcTo('channelUserDetailsThree')" name="<%=basePath%>consumelog/sysUserReadConsumelog.do?USERID=${pd.USERID}">消费记录</span>
			<span id="channelUserDetailsFour" onclick="srcTo('channelUserDetailsFour')" name="<%=basePath%>singlog/sysUserReadSignLog.do?USERID=${pd.USERID}">签到记录</span>
		</div>
		<div class="channelDetailsDeTab">
			<iframe id="channelUserDetails" src="<%=basePath%>wxuser/sysUserReadNewsAll.do?USERID=${pd.USERID}" width="100%" height="100%" id="faker" frameborder="no" scrolling="no" allowtransparency="yes"></iframe>
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
		     var iframe = document.getElementById("channelUserDetails");           
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
