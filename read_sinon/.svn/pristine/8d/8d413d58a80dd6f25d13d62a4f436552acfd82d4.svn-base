<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<title>榜单配置（编辑书籍）</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/moduleConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">榜单配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop1" href="listConfigurate.html" title="">榜单配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">编辑书籍</a>
	</div>
	<div class="editBooksDe">
		<div class="channelDetailsDeTop clearfix">
			<span id="editBooksOne0" onclick="srcTo('editBooksOne0')" name="<%=basePath%>boarddetail/list.do?BOARD_ID=${pd.BOARD_ID}&BOARD_TYPE=0" class="channelDetailsDeTopAct">总榜</span>
			<span id="editBooksOne1" onclick="srcTo('editBooksOne1')" name="<%=basePath%>boarddetail/list.do?BOARD_ID=${pd.BOARD_ID}&BOARD_TYPE=1">男榜</span>
			<span id="editBooksOne2" onclick="srcTo('editBooksOne2')" name="<%=basePath%>boarddetail/list.do?BOARD_ID=${pd.BOARD_ID}&BOARD_TYPE=2">女榜</span>
		</div>
		<div class="channelDetailsDeTab">
			<iframe id="channelDetails" src="<%=basePath%>boarddetail/list.do?BOARD_ID=${pd.BOARD_ID}&BOARD_TYPE=0" width="100%" height="100%" id="faker" frameborder="no" scrolling="no" allowtransparency="yes"></iframe>
		</div>
	</div>
	
	<script>
		$('.channelDetailsDeTop span').click(function(){
			$(this).addClass('channelDetailsDeTopAct').siblings().removeClass('channelDetailsDeTopAct');
		})
		
		function srcTo(id){
			var src=$('#'+id).attr('name');
			$('.channelDetailsDeTab iframe').attr('src',src);
		}
		function srcIframe() {           
		     var iframe = document.getElementById("channelDetails");           
		     try {               
		            var bHeight =iframe.contentWindow.document.body.scrollHeight;               
		            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;              
		            var height = Math.max(bHeight, dHeight);            
		            iframe.height = height;
		        } catch (ex) { }       
		}
		window.setInterval("srcIframe()", 100);
	</script>
</body>
</html>
