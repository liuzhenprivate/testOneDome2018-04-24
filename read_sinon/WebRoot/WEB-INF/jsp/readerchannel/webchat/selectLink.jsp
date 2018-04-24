<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
	<title>添加书籍</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/alert.css"/>

	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script>
	function search(){
		
		$("#FORM").submit();
	}
	
	function save(){
		//var aid = $("#aid").val();
		
		//$("#FORM").submit();
		//$("#zhongxin").hide();
		top.Dialog.close();
	}
	
	</script>
</head>
<body>
<div id="zhongxin">
<form id="FORM" name="FORM" action="htmlmodle/getbook" method="post">
	<div>
		<div style="background: #fff; padding-bottom: 20px;">
			<div class="selectLinkTit clearfix">
				<div style="width: 170px;margin-left: 100px">链接名称</div>
				<div>链接地址</div>
			</div>
			<div class="selectLinkTex">
				<ul>
					<li>
						<div class="selectLinkTex1" title="${var.CONTENT }"></div>
						<div class="selectLinkTex2">
							<p>laskdjlkajsdl</p>				
						</div>
						<div class="selectLinkTex3">
							<p>dkjahskdjhka</p>					
						</div>
					</li>
				</ul>
		</div>
		</div>
		<div class="sortBtn" onclick="save()">
			完&nbsp;&nbsp;成
		</div>
	</div>
	</form>
</div>
	<script type="text/javascript">
	
		
	</script>
		


</body>
</html>
