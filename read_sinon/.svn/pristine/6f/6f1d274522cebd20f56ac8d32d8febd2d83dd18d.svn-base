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
			if($("#ARTICLE_ID").val()==""){
			$("#ARTICLE_ID").tips({
				side:3,
	            msg:'请输入书籍ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ARTICLE_ID").focus();
			return false;
		}
		if($("#USERID").val()==""){
			$("#USERID").tips({
				side:3,
	            msg:'请输入读者ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USERID").focus();
			return false;
		}
		if($("#ZANS").val()==""){
			$("#ZANS").tips({
				side:3,
	            msg:'请输入点赞数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ZANS").focus();
			return false;
		}
		if($("#LEVELS").val()==""){
			$("#LEVELS").tips({
				side:3,
	            msg:'请输入几星',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#LEVELS").focus();
			return false;
		}
		if($("#CONTENT").val()==""){
			$("#CONTENT").tips({
				side:3,
	            msg:'请输入评论内容',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTENT").focus();
			return false;
		}
		if($("#STATE").val()==""){
			$("#STATE").tips({
				side:3,
	            msg:'请输入状态',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STATE").focus();
			return false;
		}
		if($("#USER_CODE").val()==""){
			$("#USER_CODE").tips({
				side:3,
	            msg:'请输入平台ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USER_CODE").focus();
			return false;
		}
		if($("#CREATE_TIME").val()==""){
			$("#CREATE_TIME").tips({
				side:3,
	            msg:'请输入评论时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CREATE_TIME").focus();
			return false;
		}
		if($("#CHECK_TIME").val()==""){
			$("#CHECK_TIME").tips({
				side:3,
	            msg:'请输入审核时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHECK_TIME").focus();
			return false;
		}
		if($("#MEMO").val()==""){
			$("#MEMO").tips({
				side:3,
	            msg:'请输入审核备注说明',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMO").focus();
			return false;
		}
		if($("#ADMIN").val()==""){
			$("#ADMIN").tips({
				side:3,
	            msg:'请输入审核人',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ADMIN").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="discuss/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="DISCUSS_ID" id="DISCUSS_ID" value="${pd.DISCUSS_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">书籍ID:</td>
				<td><input type="number" name="ARTICLE_ID" id="ARTICLE_ID" value="${pd.ARTICLE_ID}" maxlength="32" placeholder="这里输入书籍ID" title="书籍ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">读者ID:</td>
				<td><input type="number" name="USERID" id="USERID" value="${pd.USERID}" maxlength="32" placeholder="这里输入读者ID" title="读者ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">点赞数:</td>
				<td><input type="number" name="ZANS" id="ZANS" value="${pd.ZANS}" maxlength="32" placeholder="这里输入点赞数" title="点赞数"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">几星:</td>
				<td><input type="number" name="LEVELS" id="LEVELS" value="${pd.LEVELS}" maxlength="32" placeholder="这里输入几星" title="几星"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">评论内容:</td>
				<td><input type="text" name="CONTENT" id="CONTENT" value="${pd.CONTENT}" maxlength="32" placeholder="这里输入评论内容" title="评论内容"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">状态:</td>
				<td><input type="number" name="STATE" id="STATE" value="${pd.STATE}" maxlength="32" placeholder="这里输入状态" title="状态"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">平台ID:</td>
				<td><input type="text" name="USER_CODE" id="USER_CODE" value="${pd.USER_CODE}" maxlength="32" placeholder="这里输入平台ID" title="平台ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">评论时间:</td>
				<td><input class="span10 date-picker" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="评论时间" title="评论时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">审核时间:</td>
				<td><input class="span10 date-picker" name="CHECK_TIME" id="CHECK_TIME" value="${pd.CHECK_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="审核时间" title="审核时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">审核备注说明:</td>
				<td><input type="text" name="MEMO" id="MEMO" value="${pd.MEMO}" maxlength="32" placeholder="这里输入审核备注说明" title="审核备注说明"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">审核人:</td>
				<td><input type="text" name="ADMIN" id="ADMIN" value="${pd.ADMIN}" maxlength="32" placeholder="这里输入审核人" title="审核人"/></td>
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