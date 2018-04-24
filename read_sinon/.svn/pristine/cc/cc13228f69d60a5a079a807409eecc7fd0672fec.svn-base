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
	            msg:'请输入SYS_USER表主键（外键）',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHANNEL_ID").focus();
			return false;
		}
		if($("#TYPE").val()==""){
			$("#TYPE").tips({
				side:3,
	            msg:'请输入类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TYPE").focus();
			return false;
		}
		if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		if($("#WEBCHAT_CODE").val()==""){
			$("#WEBCHAT_CODE").tips({
				side:3,
	            msg:'请输入微信号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#WEBCHAT_CODE").focus();
			return false;
		}
		if($("#APPID").val()==""){
			$("#APPID").tips({
				side:3,
	            msg:'请输入APPID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#APPID").focus();
			return false;
		}
		if($("#APPSECRET").val()==""){
			$("#APPSECRET").tips({
				side:3,
	            msg:'请输入秘钥  AppSecret',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#APPSECRET").focus();
			return false;
		}
		if($("#SHOP_KEY").val()==""){
			$("#SHOP_KEY").tips({
				side:3,
	            msg:'请输入商户KEY',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SHOP_KEY").focus();
			return false;
		}
		if($("#MCH_ID").val()==""){
			$("#MCH_ID").tips({
				side:3,
	            msg:'请输入商户ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MCH_ID").focus();
			return false;
		}
		if($("#ACCESS_TOKEN").val()==""){
			$("#ACCESS_TOKEN").tips({
				side:3,
	            msg:'请输入access_token',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ACCESS_TOKEN").focus();
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
		if($("#SIGN_SWITCH").val()==""){
			$("#SIGN_SWITCH").tips({
				side:3,
	            msg:'请输入签到开关（默认0开启1关闭）',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SIGN_SWITCH").focus();
			return false;
		}
		if($("#OPEN_DATE").val()==""){
			$("#OPEN_DATE").tips({
				side:3,
	            msg:'请输入开启签到日期',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#OPEN_DATE").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="webchat/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="WEBCHAT_ID" id="WEBCHAT_ID" value="${pd.WEBCHAT_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">SYS_USER表主键（外键）:</td>
				<td><input type="number" name="CHANNEL_ID" id="CHANNEL_ID" value="${pd.CHANNEL_ID}" maxlength="32" placeholder="这里输入SYS_USER表主键（外键）" title="SYS_USER表主键（外键）"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">类型:</td>
				<td><input type="text" name="TYPE" id="TYPE" value="${pd.TYPE}" maxlength="32" placeholder="这里输入类型" title="类型"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">名称:</td>
				<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入名称" title="名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">微信号:</td>
				<td><input type="text" name="WEBCHAT_CODE" id="WEBCHAT_CODE" value="${pd.WEBCHAT_CODE}" maxlength="32" placeholder="这里输入微信号" title="微信号"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">APPID:</td>
				<td><input type="text" name="APPID" id="APPID" value="${pd.APPID}" maxlength="32" placeholder="这里输入APPID" title="APPID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">秘钥  AppSecret:</td>
				<td><input type="text" name="APPSECRET" id="APPSECRET" value="${pd.APPSECRET}" maxlength="32" placeholder="这里输入秘钥  AppSecret" title="秘钥  AppSecret"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商户KEY:</td>
				<td><input type="text" name="SHOP_KEY" id="SHOP_KEY" value="${pd.SHOP_KEY}" maxlength="32" placeholder="这里输入商户KEY" title="商户KEY"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商户ID:</td>
				<td><input type="text" name="MCH_ID" id="MCH_ID" value="${pd.MCH_ID}" maxlength="32" placeholder="这里输入商户ID" title="商户ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">access_token:</td>
				<td><input type="text" name="ACCESS_TOKEN" id="ACCESS_TOKEN" value="${pd.ACCESS_TOKEN}" maxlength="32" placeholder="这里输入access_token" title="access_token"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">创建时间:</td>
				<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" maxlength="32" placeholder="这里输入创建时间" title="创建时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">签到开关（默认0开启1关闭）:</td>
				<td><input type="number" name="SIGN_SWITCH" id="SIGN_SWITCH" value="${pd.SIGN_SWITCH}" maxlength="32" placeholder="这里输入签到开关（默认0开启1关闭）" title="签到开关（默认0开启1关闭）"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">开启签到日期:</td>
				<td><input type="text" name="OPEN_DATE" id="OPEN_DATE" value="${pd.OPEN_DATE}" maxlength="32" placeholder="这里输入开启签到日期" title="开启签到日期"/></td>
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