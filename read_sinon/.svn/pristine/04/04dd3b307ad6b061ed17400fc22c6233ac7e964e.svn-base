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
			if($("#PIC_CATEGORY_ID").val()==""){
			$("#PIC_CATEGORY_ID").tips({
				side:3,
	            msg:'请输入类目名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PIC_CATEGORY_ID").focus();
			return false;
		}
		if($("#PIC_NAME").val()==""){
			$("#PIC_NAME").tips({
				side:3,
	            msg:'请输入图片名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PIC_NAME").focus();
			return false;
		}
		if($("#PIC_URL").val()==""){
			$("#PIC_URL").tips({
				side:3,
	            msg:'请输入图片路径',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PIC_URL").focus();
			return false;
		}
		if($("#USE_TIMES").val()==""){
			$("#USE_TIMES").tips({
				side:3,
	            msg:'请输入使用次数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USE_TIMES").focus();
			return false;
		}
		if($("#CREATE_TIME").val()==""){
			$("#CREATE_TIME").tips({
				side:3,
	            msg:'请输入上传时间',
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
	<form action="pic/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="PIC_ID" id="PIC_ID" value="${pd.PIC_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">类目名称:</td>
				<td><input type="text" name="PIC_CATEGORY_ID" id="PIC_CATEGORY_ID" value="${pd.PIC_CATEGORY_ID}" maxlength="32" placeholder="这里输入类目名称" title="类目名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">图片名称:</td>
				<td><input type="text" name="PIC_NAME" id="PIC_NAME" value="${pd.PIC_NAME}" maxlength="32" placeholder="这里输入图片名称" title="图片名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">图片路径:</td>
				<td><input type="text" name="PIC_URL" id="PIC_URL" value="${pd.PIC_URL}" maxlength="32" placeholder="这里输入图片路径" title="图片路径"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">使用次数:</td>
				<td><input type="text" name="USE_TIMES" id="USE_TIMES" value="${pd.USE_TIMES}" maxlength="32" placeholder="这里输入使用次数" title="使用次数"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">上传时间:</td>
				<td><input class="span10 date-picker" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="上传时间" title="上传时间"/></td>
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