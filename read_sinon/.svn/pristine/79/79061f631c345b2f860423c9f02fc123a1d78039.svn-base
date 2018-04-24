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
			if($("#HTMLMODLE_ID").val()==""){
			$("#HTMLMODLE_ID").tips({
				side:3,
	            msg:'请输入页面模板ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#HTMLMODLE_ID").focus();
			return false;
		}
		if($("#TITLE").val()==""){
			$("#TITLE").tips({
				side:3,
	            msg:'请输入主标题',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TITLE").focus();
			return false;
		}
		if($("#SUBHEAD").val()==""){
			$("#SUBHEAD").tips({
				side:3,
	            msg:'请输入副标题',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SUBHEAD").focus();
			return false;
		}
		if($("#CONTENT").val()==""){
			$("#CONTENT").tips({
				side:3,
	            msg:'请输入内容',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTENT").focus();
			return false;
		}
		if($("#IMG_URL").val()==""){
			$("#IMG_URL").tips({
				side:3,
	            msg:'请输入图片路径',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#IMG_URL").focus();
			return false;
		}
		if($("#HTML_TYPE").val()==""){
			$("#HTML_TYPE").tips({
				side:3,
	            msg:'请输入页面类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#HTML_TYPE").focus();
			return false;
		}
		if($("#SORT").val()==""){
			$("#SORT").tips({
				side:3,
	            msg:'请输入排序',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SORT").focus();
			return false;
		}
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
		if($("#HTML_URL").val()==""){
			$("#HTML_URL").tips({
				side:3,
	            msg:'请输入页面链接',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#HTML_URL").focus();
			return false;
		}
		if($("#MEMO").val()==""){
			$("#MEMO").tips({
				side:3,
	            msg:'请输入说明',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MEMO").focus();
			return false;
		}
		if($("#CREATE_TIME").val()==""){
			$("#CREATE_TIME").tips({
				side:3,
	            msg:'请输入添加时间',
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
	<form action="htmlmodledetail/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="HTMLMODLEDETAIL_ID" id="HTMLMODLEDETAIL_ID" value="${pd.HTMLMODLEDETAIL_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">页面模板ID:</td>
				<td><input type="text" name="HTMLMODLE_ID" id="HTMLMODLE_ID" value="${pd.HTMLMODLE_ID}" maxlength="32" placeholder="这里输入页面模板ID" title="页面模板ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">主标题:</td>
				<td><input type="text" name="TITLE" id="TITLE" value="${pd.TITLE}" maxlength="32" placeholder="这里输入主标题" title="主标题"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">副标题:</td>
				<td><input type="text" name="SUBHEAD" id="SUBHEAD" value="${pd.SUBHEAD}" maxlength="32" placeholder="这里输入副标题" title="副标题"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">内容:</td>
				<td><input type="text" name="CONTENT" id="CONTENT" value="${pd.CONTENT}" maxlength="32" placeholder="这里输入内容" title="内容"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">图片路径:</td>
				<td><input type="text" name="IMG_URL" id="IMG_URL" value="${pd.IMG_URL}" maxlength="32" placeholder="这里输入图片路径" title="图片路径"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">页面类型:</td>
				<td><input type="text" name="HTML_TYPE" id="HTML_TYPE" value="${pd.HTML_TYPE}" maxlength="32" placeholder="这里输入页面类型" title="页面类型"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">排序:</td>
				<td><input type="text" name="SORT" id="SORT" value="${pd.SORT}" maxlength="32" placeholder="这里输入排序" title="排序"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">书籍ID:</td>
				<td><input type="text" name="ARTICLE_ID" id="ARTICLE_ID" value="${pd.ARTICLE_ID}" maxlength="32" placeholder="这里输入书籍ID" title="书籍ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">页面链接:</td>
				<td><input type="text" name="HTML_URL" id="HTML_URL" value="${pd.HTML_URL}" maxlength="32" placeholder="这里输入页面链接" title="页面链接"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">说明:</td>
				<td><input type="text" name="MEMO" id="MEMO" value="${pd.MEMO}" maxlength="32" placeholder="这里输入说明" title="说明"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">添加时间:</td>
				<td><input class="span10 date-picker" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="添加时间" title="添加时间"/></td>
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