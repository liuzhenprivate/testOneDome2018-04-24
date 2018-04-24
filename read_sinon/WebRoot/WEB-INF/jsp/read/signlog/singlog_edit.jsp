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
	            msg:'请输入用户id',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USER_ID").focus();
			return false;
		}
		if($("#CHANNEL_ID").val()==""){
			$("#CHANNEL_ID").tips({
				side:3,
	            msg:'请输入渠道',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHANNEL_ID").focus();
			return false;
		}
		if($("#WEHCHAT_ID").val()==""){
			$("#WEHCHAT_ID").tips({
				side:3,
	            msg:'请输入微信公众号ID 外键',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#WEHCHAT_ID").focus();
			return false;
		}
		if($("#LOG_TYPE").val()==""){
			$("#LOG_TYPE").tips({
				side:3,
	            msg:'请输入记录类型 默认1每日签到2领取奖励',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#LOG_TYPE").focus();
			return false;
		}
		if($("#TIMES").val()==""){
			$("#TIMES").tips({
				side:3,
	            msg:'请输入对应奖励次数  对应奖励设置表的times字段',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TIMES").focus();
			return false;
		}
		if($("#BOOK_CURRENCY").val()==""){
			$("#BOOK_CURRENCY").tips({
				side:3,
	            msg:'请输入获取的书币',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BOOK_CURRENCY").focus();
			return false;
		}
		if($("#CREATE_TIME").val()==""){
			$("#CREATE_TIME").tips({
				side:3,
	            msg:'请输入获取时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CREATE_TIME").focus();
			return false;
		}
		if($("#SIGN_MONTH").val()==""){
			$("#SIGN_MONTH").tips({
				side:3,
	            msg:'请输入签到月份',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SIGN_MONTH").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="singlog/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="SIGN_LOG_ID" id="SIGN_LOG_ID" value="${pd.SIGN_LOG_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户id:</td>
				<td><input type="number" name="USER_ID" id="USER_ID" value="${pd.USER_ID}" maxlength="32" placeholder="这里输入用户id" title="用户id"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">渠道:</td>
				<td><input type="text" name="CHANNEL_ID" id="CHANNEL_ID" value="${pd.CHANNEL_ID}" maxlength="32" placeholder="这里输入渠道" title="渠道"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">微信公众号ID 外键:</td>
				<td><input type="text" name="WEHCHAT_ID" id="WEHCHAT_ID" value="${pd.WEHCHAT_ID}" maxlength="32" placeholder="这里输入微信公众号ID 外键" title="微信公众号ID 外键"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">记录类型 默认1每日签到2领取奖励:</td>
				<td><input type="number" name="LOG_TYPE" id="LOG_TYPE" value="${pd.LOG_TYPE}" maxlength="32" placeholder="这里输入记录类型 默认1每日签到2领取奖励" title="记录类型 默认1每日签到2领取奖励"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">对应奖励次数  对应奖励设置表的times字段:</td>
				<td><input type="number" name="TIMES" id="TIMES" value="${pd.TIMES}" maxlength="32" placeholder="这里输入对应奖励次数  对应奖励设置表的times字段" title="对应奖励次数  对应奖励设置表的times字段"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">获取的书币:</td>
				<td><input type="number" name="BOOK_CURRENCY" id="BOOK_CURRENCY" value="${pd.BOOK_CURRENCY}" maxlength="32" placeholder="这里输入获取的书币" title="获取的书币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">获取时间:</td>
				<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" maxlength="32" placeholder="这里输入获取时间" title="获取时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">签到月份:</td>
				<td><input type="text" name="SIGN_MONTH" id="SIGN_MONTH" value="${pd.SIGN_MONTH}" maxlength="32" placeholder="这里输入签到月份" title="签到月份"/></td>
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