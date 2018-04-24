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
	<title>账户信息（财务管理）提现</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/public.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script>
		function save(){
			var SURPLUS = parseInt($("#SURPLUS").val());
			var money = $("#TXMONEY").val();
			if(''==money){
				$("#TXMONEY").tips({
					side:3,
		            msg:'请输入提现金额',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TXMONEY").focus();
				return false;
			}
			 var re = /^\d+(?=\.{0,1}\d+$|$)/;
			 if (!re.test(money)) { 
				 $("#TXMONEY").tips({
						side:3,
			            msg:'请输入正确的金额',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#TXMONEY").focus();
					return false;
			 }
			var rsmoney = parseFloat(money)*100;
			//alert(rsmoney+"=="+SURPLUS);
			if(rsmoney>SURPLUS){
				$("#TXMONEY").tips({
					side:3,
		            msg:'提现金额大于可提现额度',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TXMONEY").focus();
				return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
		}
	</script>
</head>
<body>
<div id="zhongxin">
<form action="channelaccount/savepresentRecord.do" method="post" name="Form" id="Form">
	<div class="applicatePresentateAlert">
		<div class="applicatePresentateDe">
		<input type="hidden" id="SURPLUS" name="SURPLUS" value="${user.SURPLUS }"/>
			<div class="applicatePresentateDeInp clearfix">
				<span>提现金额：</span>
				<div class="clearfix">
					<input id="TXMONEY" name="TXMONEY" value=""/>
					<p>请输入提现金额 </p>
					<i>最多可提现${user.SURPLUS*0.01 }元</i>
				</div>
			</div>
			<div class="applicatePresentateDeCard clearfix">
				<span>提现至：</span>
				<div class="clearfix">
					<i>【${account.CARD_NUM }】</i>
					<p>${account.OPEN_CARD }</p>
				</div>
			</div>
			<div class="applicatePresentateDeRemind clearfix">
				<span>温馨提醒：</span>
				<p>每次提现金额不能低于100元，正常情况下申请提现后将在1~3天内到账</p>
			</div>
		</div>
		<div class="automaticReplyBtn" onclick="save()">
			完&nbsp;&nbsp;成
		</div>
	</div>
	</form>
	</div>
	<script>
		$('.applicatePresentateDeInp div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.applicatePresentateDeInp div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.applicatePresentateDeInp div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
	</script>
</body>
</html>


