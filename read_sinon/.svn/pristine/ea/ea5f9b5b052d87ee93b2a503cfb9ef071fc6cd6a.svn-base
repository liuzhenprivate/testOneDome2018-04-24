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
		if($("#CHANNEL_ID").val()==""){
			$("#CHANNEL_ID").tips({
				side:3,
	            msg:'请输入渠道ID(外键SYS_USER表主键)',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHANNEL_ID").focus();
			return false;
		}
		if($("#ACCOUNT_NAME").val()==""){
			$("#ACCOUNT_NAME").tips({
				side:3,
	            msg:'请输入收款银行卡名字',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ACCOUNT_NAME").focus();
			return false;
		}
		if($("#CARD_NUM").val()==""){
			$("#CARD_NUM").tips({
				side:3,
	            msg:'请输入收款卡号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CARD_NUM").focus();
			return false;
		}
		if($("#OPEN_CARD").val()==""){
			$("#OPEN_CARD").tips({
				side:3,
	            msg:'请输入收款开户行',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#OPEN_CARD").focus();
			return false;
		}
		if($("#MONEY").val()==""){
			$("#MONEY").tips({
				side:3,
	            msg:'请输入打款数额',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MONEY").focus();
			return false;
		}
		if($("#CHANNEL_ACCOUNT_NAME").val()==""){
			$("#CHANNEL_ACCOUNT_NAME").tips({
				side:3,
	            msg:'请输入打款银行卡名字',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHANNEL_ACCOUNT_NAME").focus();
			return false;
		}
		if($("#CHANNEL_CARD_NUM").val()==""){
			$("#CHANNEL_CARD_NUM").tips({
				side:3,
	            msg:'请输入打款卡号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHANNEL_CARD_NUM").focus();
			return false;
		}
		if($("#STATE").val()==""){
			$("#STATE").tips({
				side:3,
	            msg:'请输入收款状态(0等待收款1确认收款2未收款)',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STATE").focus();
			return false;
		}
		if($("#MEMO").val()==""){
			$("#MEMO").tips({
				side:3,
	            msg:'请输入确认收款备注',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMO").focus();
			return false;
		}
		if($("#CHECK_TIME").val()==""){
			$("#CHECK_TIME").tips({
				side:3,
	            msg:'请输入确认收款时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHECK_TIME").focus();
			return false;
		}
		if($("#CREATE_TIME").val()==""){
			$("#CREATE_TIME").tips({
				side:3,
	            msg:'请输入创建时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CREATE_TIME").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="remittancelog/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="REMITTANCE_LOG_ID" id="REMITTANCE_LOG_ID" value="${pd.REMITTANCE_LOG_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">渠道ID(外键SYS_USER表主键):</td>
				<td><input type="number" name="CHANNEL_ID" id="CHANNEL_ID" value="${pd.CHANNEL_ID}" maxlength="32" placeholder="这里输入渠道ID(外键SYS_USER表主键)" title="渠道ID(外键SYS_USER表主键)"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">收款银行卡名字:</td>
				<td><input type="text" name="ACCOUNT_NAME" id="ACCOUNT_NAME" value="${pd.ACCOUNT_NAME}" maxlength="32" placeholder="这里输入收款银行卡名字" title="收款银行卡名字"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">收款卡号:</td>
				<td><input type="text" name="CARD_NUM" id="CARD_NUM" value="${pd.CARD_NUM}" maxlength="32" placeholder="这里输入收款卡号" title="收款卡号"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">收款开户行:</td>
				<td><input type="text" name="OPEN_CARD" id="OPEN_CARD" value="${pd.OPEN_CARD}" maxlength="32" placeholder="这里输入收款开户行" title="收款开户行"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">打款数额:</td>
				<td><input type="text" name="MONEY" id="MONEY" value="${pd.MONEY}" maxlength="32" placeholder="这里输入打款数额" title="打款数额"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">打款银行卡名字:</td>
				<td><input type="text" name="CHANNEL_ACCOUNT_NAME" id="CHANNEL_ACCOUNT_NAME" value="${pd.CHANNEL_ACCOUNT_NAME}" maxlength="32" placeholder="这里输入打款银行卡名字" title="打款银行卡名字"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">打款卡号:</td>
				<td><input type="text" name="CHANNEL_CARD_NUM" id="CHANNEL_CARD_NUM" value="${pd.CHANNEL_CARD_NUM}" maxlength="32" placeholder="这里输入打款卡号" title="打款卡号"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">收款状态(0等待收款1确认收款2未收款):</td>
				<td><input type="number" name="STATE" id="STATE" value="${pd.STATE}" maxlength="32" placeholder="这里输入收款状态(0等待收款1确认收款2未收款)" title="收款状态(0等待收款1确认收款2未收款)"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">确认收款备注:</td>
				<td><input type="text" name="MEMO" id="MEMO" value="${pd.MEMO}" maxlength="32" placeholder="这里输入确认收款备注" title="确认收款备注"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">确认收款时间:</td>
				<td><input type="text" name="CHECK_TIME" id="CHECK_TIME" value="${pd.CHECK_TIME}" maxlength="32" placeholder="这里输入确认收款时间" title="确认收款时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">创建时间:</td>
				<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" maxlength="32" placeholder="这里输入创建时间" title="创建时间"/></td>
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