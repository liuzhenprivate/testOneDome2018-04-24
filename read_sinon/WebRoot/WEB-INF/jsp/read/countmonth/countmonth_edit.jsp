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
	            msg:'请输入渠道ID',
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
		if($("#USERS").val()==""){
			$("#USERS").tips({
				side:3,
	            msg:'请输入会员总数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USERS").focus();
			return false;
		}
		if($("#RECHARGES").val()==""){
			$("#RECHARGES").tips({
				side:3,
	            msg:'请输入充值总数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#RECHARGES").focus();
			return false;
		}
		if($("#RECHARGETIMES").val()==""){
			$("#RECHARGETIMES").tips({
				side:3,
	            msg:'请输入充值笔数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#RECHARGETIMES").focus();
			return false;
		}
		if($("#RECHARGEUSERS").val()==""){
			$("#RECHARGEUSERS").tips({
				side:3,
	            msg:'请输入充值人数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#RECHARGEUSERS").focus();
			return false;
		}
		if($("#CONSUMES").val()==""){
			$("#CONSUMES").tips({
				side:3,
	            msg:'请输入消费阅读币',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONSUMES").focus();
			return false;
		}
		if($("#MONEY").val()==""){
			$("#MONEY").tips({
				side:3,
	            msg:'请输入消费人民币',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MONEY").focus();
			return false;
		}
		if($("#CONSUMENUMBER").val()==""){
			$("#CONSUMENUMBER").tips({
				side:3,
	            msg:'请输入消费笔数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONSUMENUMBER").focus();
			return false;
		}
		if($("#COUNT_MONTH").val()==""){
			$("#COUNT_MONTH").tips({
				side:3,
	            msg:'请输入统计月份',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#COUNT_MONTH").focus();
			return false;
		}
		if($("#CREATE_TIME").val()==""){
			$("#CREATE_TIME").tips({
				side:3,
	            msg:'请输入创建日期',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CREATE_TIME").focus();
			return false;
		}
		if($("#UPDATE_TIME").val()==""){
			$("#UPDATE_TIME").tips({
				side:3,
	            msg:'请输入更新时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#UPDATE_TIME").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="countmonth/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="COUNNT_MONTH_ID" id="COUNNT_MONTH_ID" value="${pd.COUNNT_MONTH_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">渠道ID:</td>
				<td><input type="number" name="CHANNEL_ID" id="CHANNEL_ID" value="${pd.CHANNEL_ID}" maxlength="32" placeholder="这里输入渠道ID" title="渠道ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">微信公众号ID:</td>
				<td><input type="number" name="WEHCHAT_ID" id="WEHCHAT_ID" value="${pd.WEHCHAT_ID}" maxlength="32" placeholder="这里输入微信公众号ID" title="微信公众号ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">会员总数:</td>
				<td><input type="number" name="USERS" id="USERS" value="${pd.USERS}" maxlength="32" placeholder="这里输入会员总数" title="会员总数"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">充值总数:</td>
				<td><input type="number" name="RECHARGES" id="RECHARGES" value="${pd.RECHARGES}" maxlength="32" placeholder="这里输入充值总数" title="充值总数"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">充值笔数:</td>
				<td><input type="number" name="RECHARGETIMES" id="RECHARGETIMES" value="${pd.RECHARGETIMES}" maxlength="32" placeholder="这里输入充值笔数" title="充值笔数"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">充值人数:</td>
				<td><input type="number" name="RECHARGEUSERS" id="RECHARGEUSERS" value="${pd.RECHARGEUSERS}" maxlength="32" placeholder="这里输入充值人数" title="充值人数"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">消费阅读币:</td>
				<td><input type="number" name="CONSUMES" id="CONSUMES" value="${pd.CONSUMES}" maxlength="32" placeholder="这里输入消费阅读币" title="消费阅读币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">消费人民币:</td>
				<td><input type="number" name="MONEY" id="MONEY" value="${pd.MONEY}" maxlength="32" placeholder="这里输入消费人民币" title="消费人民币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">消费笔数:</td>
				<td><input type="number" name="CONSUMENUMBER" id="CONSUMENUMBER" value="${pd.CONSUMENUMBER}" maxlength="32" placeholder="这里输入消费笔数" title="消费笔数"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">统计月份:</td>
				<td><input type="text" name="COUNT_MONTH" id="COUNT_MONTH" value="${pd.COUNT_MONTH}" maxlength="32" placeholder="这里输入统计月份" title="统计月份"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">创建日期:</td>
				<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" maxlength="32" placeholder="这里输入创建日期" title="创建日期"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">更新时间:</td>
				<td><input type="text" name="UPDATE_TIME" id="UPDATE_TIME" value="${pd.UPDATE_TIME}" maxlength="32" placeholder="这里输入更新时间" title="更新时间"/></td>
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