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
	<title>读者详情</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void()" title="">读者管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">读者详情</a>
	</div>
	<div class="readerDetailsDe">
		<div class="readerDetailsDeTop clearfix">
			<span id="readerDetailsOne" onclick="srcTo('readerDetailsOne')" name="<%=basePath%>wxuser/readNewsAll.do?USERID=${pd.USERID}&OPENID=${pd.OPENID }" class="readerDetailsDeTop1 readerDetailsDeTopAct">读者信息总览</span>
			<span id="readerDetailsTwo" onclick="srcTo('readerDetailsTwo')" name="<%=basePath%>rechargelog/readReechargeLog.do?USERID=${pd.USERID}&OPENID=${pd.OPENID }" class="readerDetailsDeTop2">充值记录</span>
			<span id="readerDetailsThree" onclick="srcTo('readerDetailsThree')" name="<%=basePath%>consumelog/readerConsumeLog.do?USERID=${pd.USERID}&OPENID=${pd.OPENID }" class="readerDetailsDeTop3">消费记录</span>
			<span id="readerDetailsFour" onclick="srcTo('readerDetailsFour')" name="<%=basePath%>singlog/readerSignLog.do?USERID=${pd.USERID}&OPENID=${pd.OPENID }" class="readerDetailsDeTop4">签到记录</span>
		</div>
		<div class="readerDetailsDeTab">
			<iframe id="readerDetails" src="<%=basePath%>wxuser/readNewsAll.do?USERID=${pd.USERID}&OPENID=${pd.OPENID }" width="100%" height="100%" id="faker" frameborder="no" scrolling="no" allowtransparency="yes"></iframe>
		</div>
		
	</div>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
		$('.readerDetailsDeTop span').click(function(){
			$(this).addClass('readerDetailsDeTopAct').siblings().removeClass('readerDetailsDeTopAct');
		})
		laydate.render({
		  elem: '#test6'
		  ,range: true
		});
		
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