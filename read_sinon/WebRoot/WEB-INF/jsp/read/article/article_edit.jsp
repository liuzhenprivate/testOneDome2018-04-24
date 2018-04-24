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
	if($("#ARTICLE_CATEGORY_ID").val()==""){
			$("#ARTICLE_CATEGORY_ID").tips({
				side:3,
	            msg:'请输入书籍类目编号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ARTICLE_CATEGORY_ID").focus();
			return false;
		}
		if($("#ARTICLE_CODE").val()==""){
			$("#ARTICLE_CODE").tips({
				side:3,
	            msg:'请输入书籍编号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ARTICLE_CODE").focus();
			return false;
		}
		if($("#ARTICLE_NAME").val()==""){
			$("#ARTICLE_NAME").tips({
				side:3,
	            msg:'请输入书籍名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ARTICLE_NAME").focus();
			return false;
		}
		if($("#AUTHOR").val()==""){
			$("#AUTHOR").tips({
				side:3,
	            msg:'请输入作者',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#AUTHOR").focus();
			return false;
		}
		if($("#FEE_TYPE").val()==""){
			$("#FEE_TYPE").tips({
				side:3,
	            msg:'请输入付费类型(默认0免费 1付费)',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#FEE_TYPE").focus();
			return false;
		}
		if($("#PAY_WAY").val()==""){
			$("#PAY_WAY").tips({
				side:3,
	            msg:'请输入付费方式(默认0阅读币购买阅读1 VIP免费指定章节免费时间段阅读)',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PAY_WAY").focus();
			return false;
		}
		/* if($("#PAY_CONSUMES").val()==""){
			$("#PAY_CONSUMES").tips({
				side:3,
	            msg:'请输入总阅读币',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PAY_CONSUMES").focus();
			return false;
		} */
		if($("#IS_HOT").val()==""){
			$("#IS_HOT").tips({
				side:3,
	            msg:'请输入是否热门(默认0否 1是)',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#IS_HOT").focus();
			return false;
		}
		if($("#SUMMARY").val()==""){
			$("#SUMMARY").tips({
				side:3,
	            msg:'请输入简介',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SUMMARY").focus();
			return false;
		}
		if($("#COUNT_LETTER").val()==""){
			$("#COUNT_LETTER").tips({
				side:3,
	            msg:'请输入总字数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#COUNT_LETTER").focus();
			return false;
		}
		if($("#COUNT_CHAPTERS").val()==""){
			$("#COUNT_CHAPTERS").tips({
				side:3,
	            msg:'请输入总章节',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#COUNT_CHAPTERS").focus();
			return false;
		}
		if($("#STATE").val()==""){
			$("#STATE").tips({
				side:3,
	            msg:'请输入状态(默认0未上架 2已上架 2下架 -1删除)',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STATE").focus();
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
		if($("#READERS").val()==""){
			$("#READERS").tips({
				side:3,
	            msg:'请输入阅读人数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#READERS").focus();
			return false;
		}
		if($("#COUNT_CONSUMES").val()==""){
			$("#COUNT_CONSUMES").tips({
				side:3,
	            msg:'请输入购买书籍总阅读币',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#COUNT_CONSUMES").focus();
			return false;
		}
		if($("#CHANNEL_TYPE").val()==""){
			$("#CHANNEL_TYPE").tips({
				side:3,
	            msg:'请输入频道  0男频1女频',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CHANNEL_TYPE").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="article/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ARTICLE_ID" id="ARTICLE_ID" value="${pd.ARTICLE_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">书籍类目ID:</td>
				<td><input type="number" name="ARTICLE_CATEGORY_ID" id="ARTICLE_CATEGORY_ID" value="${pd.ARTICLE_CATEGORY_ID}" maxlength="32" placeholder="这里输入书籍类目ID" title="书籍类目ID"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">书籍编号:</td>
				<td><input type="text" name="ARTICLE_CODE" id="ARTICLE_CODE" value="${pd.ARTICLE_CODE}" maxlength="32" placeholder="这里输入书籍编号" title="书籍编号"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">书籍名称:</td>
				<td><input type="text" name="ARTICLE_NAME" id="ARTICLE_NAME" value="${pd.ARTICLE_NAME}" maxlength="32" placeholder="这里输入书籍名称" title="书籍名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">作者:</td>
				<td><input type="text" name="AUTHOR" id="AUTHOR" value="${pd.AUTHOR}" maxlength="32" placeholder="这里输入作者" title="作者"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">付费类型(默认0免费 1付费):</td>
				<td><input type="number" name="FEE_TYPE" id="FEE_TYPE" value="${pd.FEE_TYPE}" maxlength="32" placeholder="这里输入付费类型(默认0免费 1付费)" title="付费类型(默认0免费 1付费)"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">付费方式(默认0阅读币购买阅读1 VIP免费指定章节免费时间段阅读):</td>
				<td><input type="number" name="PAY_WAY" id="PAY_WAY" value="${pd.PAY_WAY}" maxlength="32" placeholder="这里输入付费方式(默认0阅读币购买阅读1 VIP免费指定章节免费时间段阅读)" title="付费方式(默认0阅读币购买阅读1 VIP免费指定章节免费时间段阅读)"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">总阅读币:</td>
				<td><input type="number" name="PAY_CONSUMES" id="PAY_CONSUMES" value="${pd.PAY_CONSUMES}" maxlength="32" placeholder="这里输入总阅读币" title="总阅读币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">是否热门(默认0否 1是):</td>
				<td><input type="number" name="IS_HOT" id="IS_HOT" value="${pd.IS_HOT}" maxlength="32" placeholder="这里输入是否热门(默认0否 1是)" title="是否热门(默认0否 1是)"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">简介:</td>
				<td><input type="text" name="SUMMARY" id="SUMMARY" value="${pd.SUMMARY}" maxlength="32" placeholder="这里输入简介" title="简介"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">总字数:</td>
				<td><input type="number" name="COUNT_LETTER" id="COUNT_LETTER" value="${pd.COUNT_LETTER}" maxlength="32" placeholder="这里输入总字数" title="总字数"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">总章节:</td>
				<td><input type="number" name="COUNT_CHAPTERS" id="COUNT_CHAPTERS" value="${pd.COUNT_CHAPTERS}" maxlength="32" placeholder="这里输入总章节" title="总章节"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">状态(默认0未上架 2已上架 2下架 -1删除):</td>
				<td><input type="number" name="STATE" id="STATE" value="${pd.STATE}" maxlength="32" placeholder="这里输入状态(默认0未上架 2已上架 2下架 -1删除)" title="状态(默认0未上架 2已上架 2下架 -1删除)"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">添加时间:</td>
				<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" maxlength="32" placeholder="这里输入添加时间" title="添加时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">阅读人数:</td>
				<td><input type="number" name="READERS" id="READERS" value="${pd.READERS}" maxlength="32" placeholder="这里输入阅读人数" title="阅读人数"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">购买书籍总阅读币:</td>
				<td><input type="number" name="COUNT_CONSUMES" id="COUNT_CONSUMES" value="${pd.COUNT_CONSUMES}" maxlength="32" placeholder="这里输入购买书籍总阅读币" title="购买书籍总阅读币"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">频道  0男频1女频:</td>
				<td><input type="number" name="CHANNEL_TYPE" id="CHANNEL_TYPE" value="${pd.CHANNEL_TYPE}" maxlength="32" placeholder="这里输入频道  0男频1女频" title="频道  0男频1女频"/></td>
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