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
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/readerManagement.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="<%=basePath%>channelsysuserread/list.do?USER_ID=${pd.USER_ID}" title="">读者管理</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">基本信息</a>
	</div>
	<div class="readerDetailsDe">
		<div class="readerDetailsDeTop clearfix">
			<span id="essentialInformationOne" onclick="srcTo('essentialInformationOne')" name="<%=basePath%>wxuser/channelReadNewsAll.do?USERID=${pd.USERID}" class="readerDetailsDeTop1 readerDetailsDeTopAct">基本信息</span>
			<span id="essentialInformationTwo" onclick="srcTo('essentialInformationTwo')" name="<%=basePath%>rechargelog/channelReadReechargeLog.do?USERID=${pd.USERID}" class="readerDetailsDeTop2">充值记录</span>
			<span id="essentialInformationThree" onclick="srcTo('essentialInformationThree')" name="<%=basePath%>consumelog/channelReadConsumelog.do?USERID=${pd.USERID}" class="readerDetailsDeTop3">消费记录</span>
			<span id="essentialInformationFour" onclick="srcTo('essentialInformationFour')" name="<%=basePath%>singlog/channelReadSignLog.do?USERID=${pd.USERID}" class="readerDetailsDeTop4">签到记录</span>
		<div class="readerDetailsDeTab">
			<iframe id="readerDetails" src="<%=basePath%>wxuser/channelReadNewsAll.do?USERID=${pd.USERID}" width="100%" height="100%" id="faker" frameborder="no" scrolling="no" allowtransparency="yes"></iframe>
		</div>
		</div>
	</div>
	<script>
		$('.readerDetailsDeTop span').click(function(){
			$(this).addClass('readerDetailsDeTopAct').siblings().removeClass('readerDetailsDeTopAct');
		})
		
		$('.readerManagementDeSelL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.readerManagementDeSelL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.readerManagementDeSelL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
		$('.rechargeRecordSel').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.rechargeRecordSel ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$('.rechargeRecordSel p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.rechargeRecordSel').find('ul').hide();
		})
		
		function srcTo(id){
			var src=$('#'+id).attr('name');
			$('.readerDetailsDeTab iframe').attr('src',src);
		}
		function srcIframe() {           
		     var iframe = document.getElementById("readerDetails");           
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