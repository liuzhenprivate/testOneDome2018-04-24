<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>模板配置标题库</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/public.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script>
	 
	function closetitle(){
		top.Dialog.close();
	}
	</script>
</head>
<body  >
	<div id="zhongxin">
	<!--模板配置标题库查看-->
	<div class="titleLibraryViewAlert">
		<div class="titleLibraryViewDe">
			 
			<div class="titleLibraryViewDeC">
				<p>${pd.CONTENT }</p>
			</div>
			<div class="titleLibraryViewDeB" onclick="closetitle()">
				完&nbsp;&nbsp;成
			</div>
		</div>
	</div>
	</div>
	<script>
//	模板配置标题库查看
	$('.titleLibraryViewDeT img').click(function(){
		$('.titleLibraryViewAlert').hide();
	})
	
//	模板配置标题库编辑
	$('.titleLibrarySingleDeCInp div').click(function(e){
		if($(this).find('ul').css('display')=="none"){
			$(this).find('ul').show();
		}else{
			$(this).find('ul').hide();
		}
		e.stopPropagation();
	})
	$('.titleLibrarySingleDeCInp div ul li').click(function(e){
		$(this).css('color','#f37427');
		$(this).siblings().css('color','#666666');
		$(this).parent().hide();
		var val=$(this).text();
		$('.titleLibrarySingleDeCInp div span').text(val);
		e.stopPropagation();
		$("#TITLE_CATEGORY_ID").val($(this).attr('value'));
		//alert($(this).attr('value'));
	})
	
	$('.titleLibrarySingleDeCTex div p').click(function(){
		$(this).hide();
		$(this).siblings('textarea').focus();
	})
	$('.titleLibrarySingleDeCTex div textarea').bind('input propertychange', function(){
    	if($(this).val()==''){
    		$(this).siblings('p').show();
//  		$(this).blur();
    	}
	});
	$('.titleLibrarySingleDeCTex div textarea').blur(function(){
		if($(this).val()==''){
			$(this).siblings('p').show();
		}
	})
	
	
	</script>
</body>
</html>


