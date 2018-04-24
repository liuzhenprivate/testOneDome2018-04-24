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
	<title>账户信息（收款信息）编辑修改</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/accountInformation.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
	
	
	//保存
	function save(){
		$("#Form").submit();
	}
	function goindex(){
		window.location.href="<%=basePath%>channelaccount/index";
	}
</script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">账户信息</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">收款信息</a>
	</div>
	<form action="channelaccount/editaccount.do" name="Form" id="Form" method="post">
	<input type="hidden" name="ACCOUNT_ID" id="ACCOUNT_ID" value="${account.ACCOUNT_ID}"/>
	<div class="presentRecordDe">
		<div class="presentRecordDeH1">账户信息</div>
		<div class="receivablesInformationEdited clearfix">
			<span>姓名</span>
			<p>${account.ACCOUNT_NAME }</p>
		</div>
		<div class="receivablesInformationEdited clearfix">
			<span>银行账号</span>
			<p>${account.CARD_NUM }</p>
		</div>
		<div class="receivablesInformationEdited clearfix">
			<span>开户银行</span>
			<p>${account.OPEN_CARD }</p>
		</div>
		<div class="receivablesInformationInp clearfix">
			<span>常用手机</span>
			<div class="clearfix">
				<input id="PHONE" name="PHONE" value="${account.PHONE }"/>
			</div>
			<i>选填，可修改</i>
		</div>
		<div class="receivablesInformationInp clearfix">
			<span>联系QQ</span>
			<div class="clearfix">
				<input id="QQ" name="QQ" value="${account.QQ }"/>
			</div>
			<i>选填，可修改</i>
		</div>
		<div class="receivablesInformationInp clearfix">
			<span>微信号</span>
			<div class="clearfix">
				<input id="WEBCHAT" name="WEBCHAT" value="${account.WEBCHAT }"/>
			</div>
			<i>选填，可修改</i>
		</div>
		<div class="receivablesInformationInp clearfix">
			<span>居住地</span>
			<div class="clearfix">
				<input id="ADDRESS" name="ADDRESS" value="${account.ADDRESS }"/>
			</div>
			<i>选填，可修改</i>
		</div>
		<div class="clearfix receivablesInformationEditBtn">
			<a href="javascript:save()" title="">保&nbsp;&nbsp;存</a>
			<span onclick="javascript:goindex();" >取&nbsp;&nbsp;消</span>
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


