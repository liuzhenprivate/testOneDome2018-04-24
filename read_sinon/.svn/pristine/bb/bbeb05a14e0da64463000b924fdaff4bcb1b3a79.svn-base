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
			if($("#ARTICLE_CHAPTERLOG_ID").val()==""){
			$("#ARTICLE_CHAPTERLOG_ID").tips({
				side:3,
	            msg:'请输入id',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ARTICLE_CHAPTERLOG_ID").focus();
			return false;
		}
		if($("#ARTICLE_ID").val()==""){
			$("#ARTICLE_ID").tips({
				side:3,
	            msg:'请输入书籍主键id',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ARTICLE_ID").focus();
			return false;
		}
		if($("#ARTICLELOG_ID").val()==""){
			$("#ARTICLELOG_ID").tips({
				side:3,
	            msg:'请输入书籍阅读记录表主键id',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ARTICLELOG_ID").focus();
			return false;
		}
		if($("#CHAPTER_NAME").val()==""){
			$("#CHAPTER_NAME").tips({
				side:3,
	            msg:'请输入章节名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHAPTER_NAME").focus();
			return false;
		}
		if($("#USERID").val()==""){
			$("#USERID").tips({
				side:3,
	            msg:'请输入用户表主键id',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USERID").focus();
			return false;
		}
		if($("#CHANNEL_ID").val()==""){
			$("#CHANNEL_ID").tips({
				side:3,
	            msg:'请输入渠道表主键',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHANNEL_ID").focus();
			return false;
		}
		if($("#WEBCHAT_ID").val()==""){
			$("#WEBCHAT_ID").tips({
				side:3,
	            msg:'请输入微信公众表主键',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#WEBCHAT_ID").focus();
			return false;
		}
		if($("#CREATE_TIME").val()==""){
			$("#CREATE_TIME").tips({
				side:3,
	            msg:'请输入阅读时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CREATE_TIME").focus();
			return false;
		}
		if($("#FEE").val()==""){
			$("#FEE").tips({
				side:3,
	            msg:'请输入所需阅读币 默认为0免费',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#FEE").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="articlechapterlog/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ARTICLECHAPTERLOG_ID" id="ARTICLECHAPTERLOG_ID" value="${pd.ARTICLECHAPTERLOG_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">id:</td>
				<td><input type="number" name="ARTICLE_CHAPTERLOG_ID" id="ARTICLE_CHAPTERLOG_ID" value="${pd.ARTICLE_CHAPTERLOG_ID}" maxlength="32" placeholder="这里输入id" title="id"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">书籍主键id:</td>
				<td><input type="number" name="ARTICLE_ID" id="ARTICLE_ID" value="${pd.ARTICLE_ID}" maxlength="32" placeholder="这里输入书籍主键id" title="书籍主键id"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">书籍阅读记录表主键id:</td>
				<td><input type="number" name="ARTICLELOG_ID" id="ARTICLELOG_ID" value="${pd.ARTICLELOG_ID}" maxlength="32" placeholder="这里输入书籍阅读记录表主键id" title="书籍阅读记录表主键id"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">章节名称:</td>
				<td><input type="text" name="CHAPTER_NAME" id="CHAPTER_NAME" value="${pd.CHAPTER_NAME}" maxlength="32" placeholder="这里输入章节名称" title="章节名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户表主键id:</td>
				<td><input type="number" name="USERID" id="USERID" value="${pd.USERID}" maxlength="32" placeholder="这里输入用户表主键id" title="用户表主键id"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">渠道表主键:</td>
				<td><input type="number" name="CHANNEL_ID" id="CHANNEL_ID" value="${pd.CHANNEL_ID}" maxlength="32" placeholder="这里输入渠道表主键" title="渠道表主键"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">微信公众表主键:</td>
				<td><input type="number" name="WEBCHAT_ID" id="WEBCHAT_ID" value="${pd.WEBCHAT_ID}" maxlength="32" placeholder="这里输入微信公众表主键" title="微信公众表主键"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">阅读时间:</td>
				<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" maxlength="32" placeholder="这里输入阅读时间" title="阅读时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">所需阅读币 默认为0免费:</td>
				<td><input type="number" name="FEE" id="FEE" value="${pd.FEE}" maxlength="32" placeholder="这里输入所需阅读币 默认为0免费" title="所需阅读币 默认为0免费"/></td>
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