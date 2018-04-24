<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
		if($("#USER_ID").val()==""){
			$("#USER_ID").tips({
				side:3,
	            msg:'请输入用户ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USER_ID").focus();
			return false;
		}
		if($("#CHANNEL_ID").val()==""){
			$("#CHANNEL_ID").tips({
				side:3,
	            msg:'请输入渠道  默认微信',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHANNEL_ID").focus();
			return false;
		}
		if($("#WEHCHAT_ID").val()==""){
			$("#WEHCHAT_ID").tips({
				side:3,
	            msg:'请输入微信公众号ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#WEHCHAT_ID").focus();
			return false;
		}
		if($("#MONEY").val()==""){
			$("#MONEY").tips({
				side:3,
	            msg:'请输入充值金额',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MONEY").focus();
			return false;
		}
		if($("#BOOK_CURRENCY").val()==""){
			$("#BOOK_CURRENCY").tips({
				side:3,
	            msg:'请输入对应书币',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BOOK_CURRENCY").focus();
			return false;
		}
		if($("#BOOK_CURRENCY_GIFT").val()==""){
			$("#BOOK_CURRENCY_GIFT").tips({
				side:3,
	            msg:'请输入赠送的书币',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BOOK_CURRENCY_GIFT").focus();
			return false;
		}
		if($("#BOOK_CURRENCY_ALL").val()==""){
			$("#BOOK_CURRENCY_ALL").tips({
				side:3,
	            msg:'请输入总书币',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BOOK_CURRENCY_ALL").focus();
			return false;
		}
		if($("#CONSUME").val()==""){
			$("#CONSUME").tips({
				side:3,
	            msg:'请输入消费金额',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONSUME").focus();
			return false;
		}
		if($("#CREATE_TIME").val()==""){
			$("#CREATE_TIME").tips({
				side:3,
	            msg:'请输入充值时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CREATE_TIME").focus();
			return false;
		}
		if($("#STATE").val()==""){
			$("#STATE").tips({
				side:3,
	            msg:'请输入充值状态',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STATE").focus();
			return false;
		}
		if($("#RESULT_PAY").val()==""){
			$("#RESULT_PAY").tips({
				side:3,
	            msg:'请输入支付结果',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#RESULT_PAY").focus();
			return false;
		}
		if($("#TIME_PAY").val()==""){
			$("#TIME_PAY").tips({
				side:3,
	            msg:'请输入支付完成时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TIME_PAY").focus();
			return false;
		}
		if($("#RESULT_RECHARGE").val()==""){
			$("#RESULT_RECHARGE").tips({
				side:3,
	            msg:'请输入充值结果',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#RESULT_RECHARGE").focus();
			return false;
		}
		if($("#TIME_RECHARGE").val()==""){
			$("#TIME_RECHARGE").tips({
				side:3,
	            msg:'请输入充值完成时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TIME_RECHARGE").focus();
			return false;
		}
		if($("#CHANNEL").val()==""){
			$("#CHANNEL").tips({
				side:3,
	            msg:'请输入渠道',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHANNEL").focus();
			return false;
		}
		if($("#CHANNEL_INCOME").val()==""){
			$("#CHANNEL_INCOME").tips({
				side:3,
	            msg:'请输入渠道分成',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHANNEL_INCOME").focus();
			return false;
		}
		if($("#INCOME").val()==""){
			$("#INCOME").tips({
				side:3,
	            msg:'请输入平台收益',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#INCOME").focus();
			return false;
		}
		if($("#UPSTREAM_INCOME").val()==""){
			$("#UPSTREAM_INCOME").tips({
				side:3,
	            msg:'请输入上游收益',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#UPSTREAM_INCOME").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="rechargelog/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="RECHARGE_ID" id="RECHARGE_ID" value="${pd.RECHARGE_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户ID:</td>
				<td><input type="number" name="USER_ID" id="USER_ID" value="${pd.USER_ID}" maxlength="32" placeholder="这里输入用户ID" title="用户ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">渠道  默认微信:</td>
				<td><input type="text" name="CHANNEL_ID" id="CHANNEL_ID" value="${pd.CHANNEL_ID}" maxlength="32" placeholder="这里输入渠道  默认微信" title="渠道  默认微信"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">微信公众号ID:</td>
				<td><input type="text" name="WEHCHAT_ID" id="WEHCHAT_ID" value="${pd.WEHCHAT_ID}" maxlength="32" placeholder="这里输入微信公众号ID" title="微信公众号ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">充值金额:</td>
				<td><input type="number" name="MONEY" id="MONEY" value="${pd.MONEY}" maxlength="32" placeholder="这里输入充值金额" title="充值金额"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">对应书币:</td>
				<td><input type="number" name="BOOK_CURRENCY" id="BOOK_CURRENCY" value="${pd.BOOK_CURRENCY}" maxlength="32" placeholder="这里输入对应书币" title="对应书币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">赠送的书币:</td>
				<td><input type="number" name="BOOK_CURRENCY_GIFT" id="BOOK_CURRENCY_GIFT" value="${pd.BOOK_CURRENCY_GIFT}" maxlength="32" placeholder="这里输入赠送的书币" title="赠送的书币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">总书币:</td>
				<td><input type="number" name="BOOK_CURRENCY_ALL" id="BOOK_CURRENCY_ALL" value="${pd.BOOK_CURRENCY_ALL}" maxlength="32" placeholder="这里输入总书币" title="总书币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">消费金额:</td>
				<td><input type="number" name="CONSUME" id="CONSUME" value="${pd.CONSUME}" maxlength="32" placeholder="这里输入消费金额" title="消费金额"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">充值时间:</td>
				<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" maxlength="32" placeholder="这里输入充值时间" title="充值时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">充值状态:</td>
				<td><input type="number" name="STATE" id="STATE" value="${pd.STATE}" maxlength="32" placeholder="这里输入充值状态" title="充值状态"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">支付结果:</td>
				<td><input type="text" name="RESULT_PAY" id="RESULT_PAY" value="${pd.RESULT_PAY}" maxlength="32" placeholder="这里输入支付结果" title="支付结果"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">支付完成时间:</td>
				<td><input type="text" name="TIME_PAY" id="TIME_PAY" value="${pd.TIME_PAY}" maxlength="32" placeholder="这里输入支付完成时间" title="支付完成时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">充值结果:</td>
				<td><input type="text" name="RESULT_RECHARGE" id="RESULT_RECHARGE" value="${pd.RESULT_RECHARGE}" maxlength="32" placeholder="这里输入充值结果" title="充值结果"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">充值完成时间:</td>
				<td><input type="text" name="TIME_RECHARGE" id="TIME_RECHARGE" value="${pd.TIME_RECHARGE}" maxlength="32" placeholder="这里输入充值完成时间" title="充值完成时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">渠道:</td>
				<td><input type="text" name="CHANNEL" id="CHANNEL" value="${pd.CHANNEL}" maxlength="32" placeholder="这里输入渠道" title="渠道"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">渠道分成:</td>
				<td><input type="text" name="CHANNEL_INCOME" id="CHANNEL_INCOME" value="${pd.CHANNEL_INCOME}" maxlength="32" placeholder="这里输入渠道分成" title="渠道分成"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">平台收益:</td>
				<td><input type="number" name="INCOME" id="INCOME" value="${pd.INCOME}" maxlength="32" placeholder="这里输入平台收益" title="平台收益"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">上游收益:</td>
				<td><input type="number" name="UPSTREAM_INCOME" id="UPSTREAM_INCOME" value="${pd.UPSTREAM_INCOME}" maxlength="32" placeholder="这里输入上游收益" title="上游收益"/></td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>