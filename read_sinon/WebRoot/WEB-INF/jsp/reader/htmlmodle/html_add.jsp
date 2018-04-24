<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>数据统计公告管理</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/public.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script>
		//添加公告/编辑公告
		function addnotice(){
			var NAME = $("#NAME").val();
			if(''==NAME || null==NAME){
				$("#NAME").tips({
					side:3,
		            msg:'请输入页面名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
				return false;
			}
			 
			$("#Form").submit();
			$("#zhongxin").hide();
		} 
	</script>
</head>
<body >

<form action="html/save.do" name="Form" id="Form" method="post">
<div id="zhongxin">
	<!--数据统计公告管理编辑公告-->
	<div class="editorialNoticeAlert">
		<div class="editorialNotice">
			<div class="announcementTitle clearfix">
				<span>页面名称：</span>
				<div class="clearfix">
					<input type="text" name="NAME" id="NAME" value="${pd.NAME }"  maxlength="32"/>
				</div>
			</div>
			   
			<div class="pushSure" onclick="addnotice()"><span>完&nbsp;&nbsp;成</span></div>
			<img src="static/read/images/myPic19.png" alt="" />
		</div>
	</div>
	</div>
	</form>

	<script type="text/javascript">
		 
	     
	
	</script>
</body>
</html>
