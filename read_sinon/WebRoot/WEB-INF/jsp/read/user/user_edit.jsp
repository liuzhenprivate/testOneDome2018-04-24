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
	            msg:'请输入微信公众号ID  外键',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#WEHCHAT_ID").focus();
			return false;
		}
		if($("#USER_CODE").val()==""){
			$("#USER_CODE").tips({
				side:3,
	            msg:'请输入用户编号ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USER_CODE").focus();
			return false;
		}
		if($("#OPENID").val()==""){
			$("#OPENID").tips({
				side:3,
	            msg:'请输入微信OPENID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#OPENID").focus();
			return false;
		}
		if($("#NICKNAME").val()==""){
			$("#NICKNAME").tips({
				side:3,
	            msg:'请输入微信昵称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NICKNAME").focus();
			return false;
		}
		if($("#RECHARGE_MONEY").val()==""){
			$("#RECHARGE_MONEY").tips({
				side:3,
	            msg:'请输入累计充值金额',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#RECHARGE_MONEY").focus();
			return false;
		}
		if($("#SEX").val()==""){
			$("#SEX").tips({
				side:3,
	            msg:'请输入0 未知  1男  2女',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SEX").focus();
			return false;
		}
		if($("#PROVINCE").val()==""){
			$("#PROVINCE").tips({
				side:3,
	            msg:'请输入省份',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PROVINCE").focus();
			return false;
		}
		if($("#CITY").val()==""){
			$("#CITY").tips({
				side:3,
	            msg:'请输入城市',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CITY").focus();
			return false;
		}
		if($("#COUNTRY").val()==""){
			$("#COUNTRY").tips({
				side:3,
	            msg:'请输入国家',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#COUNTRY").focus();
			return false;
		}
		if($("#HEADIMGURL").val()==""){
			$("#HEADIMGURL").tips({
				side:3,
	            msg:'请输入微信头像 （用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。）',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#HEADIMGURL").focus();
			return false;
		}
		if($("#PRIVILEGE").val()==""){
			$("#PRIVILEGE").tips({
				side:3,
	            msg:'请输入用户特权信息',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PRIVILEGE").focus();
			return false;
		}
		if($("#UNIONID").val()==""){
			$("#UNIONID").tips({
				side:3,
	            msg:'请输入UNIONID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#UNIONID").focus();
			return false;
		}
		if($("#CTIME").val()==""){
			$("#CTIME").tips({
				side:3,
	            msg:'请输入关注时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CTIME").focus();
			return false;
		}
		if($("#BOOK_CURRENCY").val()==""){
			$("#BOOK_CURRENCY").tips({
				side:3,
	            msg:'请输入书币',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BOOK_CURRENCY").focus();
			return false;
		}
		if($("#LEVEL").val()==""){
			$("#LEVEL").tips({
				side:3,
	            msg:'请输入等级',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#LEVEL").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="wxuser/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="USER_ID" id="USER_ID" value="${pd.USER_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">渠道:</td>
				<td><input type="text" name="CHANNEL_ID" id="CHANNEL_ID" value="${pd.CHANNEL_ID}" maxlength="32" placeholder="这里输入渠道" title="渠道"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">微信公众号ID  外键:</td>
				<td><input type="text" name="WEHCHAT_ID" id="WEHCHAT_ID" value="${pd.WEHCHAT_ID}" maxlength="32" placeholder="这里输入微信公众号ID  外键" title="微信公众号ID  外键"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户编号ID:</td>
				<td><input type="text" name="USER_CODE" id="USER_CODE" value="${pd.USER_CODE}" maxlength="32" placeholder="这里输入用户编号ID" title="用户编号ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">微信OPENID:</td>
				<td><input type="text" name="OPENID" id="OPENID" value="${pd.OPENID}" maxlength="32" placeholder="这里输入微信OPENID" title="微信OPENID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">微信昵称:</td>
				<td><input type="text" name="NICKNAME" id="NICKNAME" value="${pd.NICKNAME}" maxlength="32" placeholder="这里输入微信昵称" title="微信昵称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">累计充值金额:</td>
				<td><input type="number" name="RECHARGE_MONEY" id="RECHARGE_MONEY" value="${pd.RECHARGE_MONEY}" maxlength="32" placeholder="这里输入累计充值金额" title="累计充值金额"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">0 未知  1男  2女:</td>
				<td><input type="number" name="SEX" id="SEX" value="${pd.SEX}" maxlength="32" placeholder="这里输入0 未知  1男  2女" title="0 未知  1男  2女"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">省份:</td>
				<td><input type="text" name="PROVINCE" id="PROVINCE" value="${pd.PROVINCE}" maxlength="32" placeholder="这里输入省份" title="省份"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">城市:</td>
				<td><input type="text" name="CITY" id="CITY" value="${pd.CITY}" maxlength="32" placeholder="这里输入城市" title="城市"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">国家:</td>
				<td><input type="text" name="COUNTRY" id="COUNTRY" value="${pd.COUNTRY}" maxlength="32" placeholder="这里输入国家" title="国家"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">微信头像 （用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。）:</td>
				<td><input type="text" name="HEADIMGURL" id="HEADIMGURL" value="${pd.HEADIMGURL}" maxlength="32" placeholder="这里输入微信头像 （用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。）" title="微信头像 （用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。）"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户特权信息:</td>
				<td><input type="text" name="PRIVILEGE" id="PRIVILEGE" value="${pd.PRIVILEGE}" maxlength="32" placeholder="这里输入用户特权信息" title="用户特权信息"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">UNIONID:</td>
				<td><input type="text" name="UNIONID" id="UNIONID" value="${pd.UNIONID}" maxlength="32" placeholder="这里输入UNIONID" title="UNIONID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">关注时间:</td>
				<td><input type="text" name="CTIME" id="CTIME" value="${pd.CTIME}" maxlength="32" placeholder="这里输入关注时间" title="关注时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">书币:</td>
				<td><input type="number" name="BOOK_CURRENCY" id="BOOK_CURRENCY" value="${pd.BOOK_CURRENCY}" maxlength="32" placeholder="这里输入书币" title="书币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">等级:</td>
				<td><input type="text" name="LEVEL" id="LEVEL" value="${pd.LEVEL}" maxlength="32" placeholder="这里输入等级" title="等级"/></td>
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