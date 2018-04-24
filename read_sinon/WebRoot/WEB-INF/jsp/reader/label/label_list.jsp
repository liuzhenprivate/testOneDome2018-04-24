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
	<title>资源管理标签管理</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">资源管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">标签管理</a>
	</div>
	<div class="editTagDe">
		<div class="labelManagementTop clearfix">
			<div class="labelManagementTopL clearfix">
				<div class="labelManagementTopL1 clearfix">
					<input id="LABEL_NAME" name="LABEL_NAME" type="text" value=""/>
					<input type="hidden" id="hiddenval" name="hiddenval" value=""/>
					<p>输入标签名</p>
					<img src="static/read/images/myPic26.png" alt="" onclick="lableChange1();"/>
				</div>
				<div class="labelManagementTopL2 clearfix">
					<span id="typecategoryname">全部类名</span>
					<ul id="typeLabel">
						<li onclick="lableChange('');">全部类名</li>
					</ul>
				</div>
			</div>
			<div class="labelManagementTopR clearfix">
				<a class="labelManagementTopR1" href="<%=basePath%>label/goEdit" title=""><i>编辑标签</i></a>
				<span class="labelManagementTopR2"><i id="addLabel">新增标签</i></span>
			</div>
		</div>
		<div class="editTagDeB">
			<ul class="clearfix" id="labelList">
				<c:choose>
						<c:when test="${not empty varList}">
							<c:forEach items="${varList}" var="var" varStatus="vs">
								<li>${var.LABEL_NAME}</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							没有相关数据
						</c:otherwise>
					</c:choose>
			</ul>
		</div>
	</div>
	<script>
		
		function lableChange1(){
			var LABEL_NAME = $("#LABEL_NAME").val();
			var CATEGORY_NAME = document.getElementById("typecategoryname");
			if(CATEGORY_NAME.innerHTML=="全部类名"){
				$.ajax({
					type:"post",
					data:{
						"LABEL_NAME":LABEL_NAME
					},
					url: "<%=basePath%>label/listJson.do",
					dataType:'json', 
					success: function(data){
						$("#labelList li").remove();  
						$.each(data, function(index, item){
			               	$("#labelList").append("<li>" + item.LABEL_NAME + "</li>");
			           	});
					}
				});
			}else{
				$.ajax({
					type:"post",
					data:{
						"LABEL_NAME":LABEL_NAME,
						"CATEGORY_NAME":CATEGORY_NAME.innerHTML
					},
					url: "<%=basePath%>categorylabel/listJson.do",
					dataType:'json', 
					success: function(data){
						$("#labelList li").remove();  
						$.each(data, function(index, item){
			               	$("#labelList").append("<li>" + item.LABEL_NAME + "</li>");
			           	});
					}
				});
			}
			
			$("#LABEL_NAME").val(LABEL_NAME);
			document.getElementById("typecategoryname").innerHTML=CATEGORY_NAME.innerHTML;
		}
		
		function lableChange(data){
			if(data==""){
				$.ajax({
					type:"post",
					url: "<%=basePath%>label/listJson.do",
					dataType:'json', 
					success: function(data){
						$("#labelList li").remove();  
						$.each(data, function(index, item){
			               	$("#labelList").append("<li>" + item.LABEL_NAME + "</li>");
			           	});
					}
				});
			}else{
				var LABEL_NAME = $("#LABEL_NAME").val();
				$.ajax({
					type:"post",
					data:{
						"CATEGORY_NAME":data,
						"LABEL_NAME":LABEL_NAME
					},
					url: "<%=basePath%>categorylabel/listJson.do",
					dataType:'json', 
					success: function(data){
						$("#labelList li").remove();  
						$.each(data, function(index, item){
			               	$("#labelList").append("<li>" + item.LABEL_NAME + "</li>");
			           	});
					}
				});
			}
			document.getElementById("typecategoryname").innerHTML=data;
		}
		
		function CrbtOrders(){
		 $.ajax({
				type: "post",
				data:{},
				url: "<%=basePath%>labelcategory/listJson.do",
				dataType:'json', 
				success: function(data){
				     $.each(data, function(index, item){
                     	$("#typeLabel").append("<li onclick=lableChange('"+item.CATEGORY_NAME+"');>" + item.CATEGORY_NAME + "</li>");
                     });
				 }
			});
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("Thunder"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		
		$('.labelManagementTopL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		});
		$('.labelManagementTopL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
        	}
		});
		$('.labelManagementTopL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		});
		
		$(window).click(function(){
			$('.labelManagementTopL2').find('ul').hide();
		});
		$('.labelManagementTopL2').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		});
		$('.labelManagementTopL2 ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$('.labelManagementTopL2 span').text(val);
			e.stopPropagation();
		});
		
		$('.editTagDeB ul li').click(function(){
			if($(this).hasClass('editTagDeBAct')){
				$(this).removeClass('editTagDeBAct');
			}else{
				$(this).addClass('editTagDeBAct');
			}
		});
		
		$('.labelManagementTopR span').click(function(){
			$('.labelManagementPlusAlert', parent.document).show();
			(function(Thunder){
				$('.labelManagementPlusAlert', parent.document).css('padding-top',Thunder);
			})((parent.document.documentElement.clientHeight-$('.labelManagementPlus', parent.document).get(0).offsetHeight)/2);
		});
	</script>
</body>
</html>
