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
	<title>书评内容</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/public.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	
</head>
<body  >
	<div id="zhongxin">
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
		function closetitle(){
			top.Dialog.close();
		}
	</script>
	<script>
		$('.titleLibraryViewDeT img').click(function(){
			$('.titleLibraryViewAlert').hide();
		});
		
		$('.titleLibrarySingleDeCInp div').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		});
	</script>
</body>
</html>


