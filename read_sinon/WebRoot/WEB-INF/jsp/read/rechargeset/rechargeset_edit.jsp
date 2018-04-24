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
		if($("#RECHARGE_TYPE").val()==""){
			$("#RECHARGE_TYPE").tips({
				side:3,
	            msg:'请输入充值类型(默认0 普通充值1月卡2年卡)',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#RECHARGE_TYPE").focus();
			return false;
		}
		if($("#FREE_TIME").val()==""){
			$("#FREE_TIME").tips({
				side:3,
	            msg:'请输入免费时间段',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#FREE_TIME").focus();
			return false;
		}
		if($("#STATE").val()==""){
			$("#STATE").tips({
				side:3,
	            msg:'请输入状态 默认0正常1隐藏2删除',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STATE").focus();
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
	            msg:'请输入赠送书币',
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
		if($("#USE_SCOPE").val()==""){
			$("#USE_SCOPE").tips({
				side:3,
	            msg:'请输入可用范围',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USE_SCOPE").focus();
			return false;
		}
		if($("#BUY_LIMIT").val()==""){
			$("#BUY_LIMIT").tips({
				side:3,
	            msg:'请输入购买限制',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BUY_LIMIT").focus();
			return false;
		}
		if($("#EXP_DATE").val()==""){
			$("#EXP_DATE").tips({
				side:3,
	            msg:'请输入有效期',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#EXP_DATE").focus();
			return false;
		}
		if($("#SORT_NUM").val()==""){
			$("#SORT_NUM").tips({
				side:3,
	            msg:'请输入排序',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SORT_NUM").focus();
			return false;
		}
		if($("#MEMO").val()==""){
			$("#MEMO").tips({
				side:3,
	            msg:'请输入备注',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMO").focus();
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
	<form action="rechargeset/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="RECHARGE_SET_ID" id="RECHARGE_SET_ID" value="${pd.RECHARGE_SET_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">充值类型(默认0 普通充值1月卡2年卡):</td>
				<td><input type="number" name="RECHARGE_TYPE" id="RECHARGE_TYPE" value="${pd.RECHARGE_TYPE}" maxlength="32" placeholder="这里输入充值类型(默认0 普通充值1月卡2年卡)" title="充值类型(默认0 普通充值1月卡2年卡)"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">免费时间段:</td>
				<td><input type="text" name="FREE_TIME" id="FREE_TIME" value="${pd.FREE_TIME}" maxlength="32" placeholder="这里输入免费时间段" title="免费时间段"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">状态 默认0正常1隐藏2删除:</td>
				<td><input type="number" name="STATE" id="STATE" value="${pd.STATE}" maxlength="32" placeholder="这里输入状态 默认0正常1隐藏2删除" title="状态 默认0正常1隐藏2删除"/></td>
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
				<td style="width:70px;text-align: right;padding-top: 13px;">赠送书币:</td>
				<td><input type="number" name="BOOK_CURRENCY_GIFT" id="BOOK_CURRENCY_GIFT" value="${pd.BOOK_CURRENCY_GIFT}" maxlength="32" placeholder="这里输入赠送书币" title="赠送书币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">'总书币:</td>
				<td><input type="number" name="BOOK_CURRENCY_ALL" id="BOOK_CURRENCY_ALL" value="${pd.BOOK_CURRENCY_ALL}" maxlength="32" placeholder="这里输入'总书币" title="'总书币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">可用范围:</td>
				<td><input type="number" name="USE_SCOPE" id="USE_SCOPE" value="${pd.USE_SCOPE}" maxlength="32" placeholder="这里输入可用范围" title="可用范围"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">购买限制:</td>
				<td><input type="text" name="BUY_LIMIT" id="BUY_LIMIT" value="${pd.BUY_LIMIT}" maxlength="32" placeholder="这里输入购买限制" title="购买限制"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">有效期:</td>
				<td><input type="text" name="EXP_DATE" id="EXP_DATE" value="${pd.EXP_DATE}" maxlength="32" placeholder="这里输入有效期" title="有效期"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">排序:</td>
				<td><input type="number" name="SORT_NUM" id="SORT_NUM" value="${pd.SORT_NUM}" maxlength="32" placeholder="这里输入排序" title="排序"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">备注:</td>
				<td><input type="text" name="MEMO" id="MEMO" value="${pd.MEMO}" maxlength="32" placeholder="这里输入备注" title="备注"/></td>
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