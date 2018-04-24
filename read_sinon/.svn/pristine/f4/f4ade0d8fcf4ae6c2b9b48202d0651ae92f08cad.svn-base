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
	<title>账户信息（收款信息）编辑</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/accountInformation.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
	
	
	//保存
	function save(){
		if($("#ACCOUNT_NAME").val()==""){
			$("#ACCOUNT_NAME").tips({
				side:3,
	            msg:'请输入银行卡名字',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ACCOUNT_NAME").focus();
			return false;
		}
		if($("#CARD_NUM").val()==""){
			$("#CARD_NUM").tips({
				side:3,
	            msg:'请输入卡号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CARD_NUM").focus();
			return false;
		}
		if($("#OPEN_CARD").val()==""){
			$("#OPEN_CARD").tips({
				side:3,
	            msg:'请输入开户行',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#OPEN_CARD").focus();
			return false;
		}
		 
		$("#Form").submit();
	}
	
</script>
</head>

<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">账户信息</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">收款信息</a>
	</div>
	<form action="channelaccount/saveaccount.do" name="Form" id="Form" method="post">
	<div class="presentRecordDe">
		<div class="presentRecordDeH1">账户信息</div>
		<div class="receivablesInformationInp clearfix">
			<span>姓名</span>
			<div class="clearfix">
				<input id="ACCOUNT_NAME" name="ACCOUNT_NAME"/>
				<p>请输入持卡人姓名</p>
			</div>
			<i>请输入与银行卡匹配的真实姓名，必填，一旦保存，不可修改</i>
		</div>
		<div class="receivablesInformationInp clearfix">
			<span>银行账号</span>
			<div class="clearfix">
				<input id="CARD_NUM" name="CARD_NUM"/>
				<p>请输入银行卡账号</p>
			</div>
			<i>请输入正确的银行卡号，必填，一旦保存，不可修改</i>
		</div>
		<div class="receivablesInformationInp clearfix">
			<span>开户银行</span>
			<div class="clearfix">
				<input id="OPEN_CARD" name="OPEN_CARD"/>
				<p>请输入开户银行名称</p>
			</div>
			<i>请输入开户银行全称，必填，一旦保存，不可修改</i>
		</div>
		<div class="receivablesInformationInp clearfix">
			<span>常用手机</span>
			<div class="clearfix">
				<input id="PHONE" name="PHONE"/>
				<p>请输入常用手机号码</p>
			</div>
			<i>选填，可修改</i>
		</div>
		<div class="receivablesInformationInp clearfix">
			<span>联系QQ</span>
			<div class="clearfix">
				<input id="QQ" name="QQ"/>
				<p>请输入联系QQ</p>
			</div>
			<i>选填，可修改</i>
		</div>
		<div class="receivablesInformationInp clearfix">
			<span>微信号</span>
			<div class="clearfix">
				<input id="WEBCHAT" name="WEBCHAT"/>
				<p>请输入联系微信号</p>
			</div>
			<i>选填，可修改</i>
		</div>
		<div class="receivablesInformationInp clearfix">
			<span>居住地</span>
			<div class="clearfix">
				<input id="ADDRESS" name="ADDRESS"/>
				<p>请输入常住地址</p>
			</div>
			<i>选填，可修改</i>
		</div>
		<div class="clearfix receivablesInformationEditBtn">
			<a href="javascript:save();" title="">保&nbsp;&nbsp;存</a>
			<span>取&nbsp;&nbsp;消</span>
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
		</div>
		</form>
	<script>
		$('.receivablesInformationInp div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.receivablesInformationInp div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.receivablesInformationInp div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
	</script>
</body>
</html>

