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
	<title>文案封面</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/promotionManagement.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="fileSon()">
	<div class="copywritingHeadlinesDe">
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div style="margin-left: 0;" class="rechargeManagementDeSelL3 clearfix">
					<p>全部类型</p>
					<img src="static/readchannel/images/myPic13.png" alt="" />
					<ul>
					<c:forEach  items="${piccategorylist}" var="var" varStatus="vs">
						<li value="${var.PIC_CATEGORY_ID }">${var.CATEGORY_NAME }</li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="copywritingImg">
			<ul class="clearfix">
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li>
						<img onclick="upcover('<%=basePath%>${var.PIC_URL }');"  class="copywritingImg1" src="${var.PIC_URL }" alt="" />
					</li>
				</c:forEach>
				</c:when>
				</c:choose>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		function upcover(data){
			window.parent.pupcover(data);
		}
	</script>
	<script>
		function fileSon(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("faker"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		
		
		$('.rechargeManagementDeSelL3').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.rechargeManagementDeSelL3 ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent('ul').siblings('p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.rechargeManagementDeSelL3').find('ul').hide();
		})
		
		$('.copywritingImg ul li').click(function(){
			if($(this).find('.copywritingImg2').attr('src')=="static/readchannel/images/myPic56.png"){
				$(this).css('border','1px solid #f37526');
				$(this).find('.copywritingImg2').attr('src',"static/readchannel/images/myPic55.png");
			}else{
				$(this).find('.copywritingImg2').attr('src',"static/readchannel/images/myPic56.png");
				$(this).css('border','1px solid #cccccc');
			}
		})
	</script>
</body>
</html>
