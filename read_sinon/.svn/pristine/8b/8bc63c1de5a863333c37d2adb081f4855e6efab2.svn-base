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
	            msg:'请输入书籍表主键',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ARTICLE_ID").focus();
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
		if($("#CHAPTER_NO").val()==""){
			$("#CHAPTER_NO").tips({
				side:3,
	            msg:'请输入章节序号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHAPTER_NO").focus();
			return false;
		}
		if($("#CONTENT_URL").val()==""){
			$("#CONTENT_URL").tips({
				side:3,
	            msg:'请输入章节内容保存路径',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTENT_URL").focus();
			return false;
		}
		if($("#IS_FREE").val()==""){
			$("#IS_FREE").tips({
				side:3,
	            msg:'请输入是否收费(默认0免费 1收费)',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#IS_FREE").focus();
			return false;
		}
		if($("#CONSUMES").val()==""){
			$("#CONSUMES").tips({
				side:3,
	            msg:'请输入阅读币',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONSUMES").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="articlechapter/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ARTICLE_CHAPTER_ID" id="ARTICLE_CHAPTER_ID" value="${pd.ARTICLE_CHAPTER_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">书籍表主键:</td>
				<td><input type="number" name="ARTICLE_ID" id="ARTICLE_ID" value="${pd.ARTICLE_ID}" maxlength="32" placeholder="这里输入书籍表主键" title="书籍表主键"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">章节名称:</td>
				<td><input type="text" name="CHAPTER_NAME" id="CHAPTER_NAME" value="${pd.CHAPTER_NAME}" maxlength="32" placeholder="这里输入章节名称" title="章节名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">章节序号:</td>
				<td><input type="text" name="CHAPTER_NO" id="CHAPTER_NO" value="${pd.CHAPTER_NO}" maxlength="32" placeholder="这里输入章节序号" title="章节序号"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">章节内容保存路径:</td>
				<td><input type="text" name="CONTENT_URL" id="CONTENT_URL" value="${pd.CONTENT_URL}" maxlength="32" placeholder="这里输入章节内容保存路径" title="章节内容保存路径"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">是否收费(默认0免费 1收费):</td>
				<td><input type="number" name="IS_FREE" id="IS_FREE" value="${pd.IS_FREE}" maxlength="32" placeholder="这里输入是否收费(默认0免费 1收费)" title="是否收费(默认0免费 1收费)"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">'阅读币:</td>
				<td><input type="number" name="CONSUMES" id="CONSUMES" value="${pd.CONSUMES}" maxlength="32" placeholder="这里输入'阅读币" title="'阅读币"/></td>
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