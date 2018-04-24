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
	<title>正文模板</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/promotionManagement.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="fileSon()">
	<div class="copywritingHeadlinesDe">
		<div class="textTemplate">
			<ul>
				<li class="clearfix">
					<span></span>
					<p>第一章 我就指望着你的不快生活</p>
				</li>
			</ul>
		</div>
	</div>
	<script>
		function fileSon(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("faker"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		
		$('.textTemplate ul li span').click(function(){
			if($(this).hasClass('textTemplateAct')){
				$(this).removeClass('textTemplateAct');
			}else{
				$(this).addClass('textTemplateAct');
				$(this).parent().siblings().find('span').removeClass('textTemplateAct');
			}
			
		})
		
	</script>
</body>
</html>
