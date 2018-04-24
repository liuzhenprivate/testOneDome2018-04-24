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
	 
	function addtitle(){
		var content = $("#CONTENT").val();
		//var TITLE_CATEGORY_ID =  $("#TITLE_CATEGORY_ID").val();
		//alert(content+"=="+TITLE_CATEGORY_ID);
		if(null==content || ""==content){
			$("#CONTENT").tips({
				side:3,
	            msg:'请输入内容',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTENT").focus();
			return false;
		}else{
			$("#FORM").submit();
			$("#zhongxin").hide();
		}
		
		
		
	}
	</script>
</head>
<body  >
	<div id="zhongxin">
	<form id="FORM" name="FORM" action="title/edittemp.do">
	<input type="hidden" id="TITLE_ID" name="TITLE_ID" value="${pd.TITLE_ID }"/>
	<!--模板配置标题库编辑-->
	<div class="titleLibrarySingleAlert">
		<div class="titleLibrarySingleDe">
			 
			<div class="titleLibrarySingleDeC">
				<div class="titleLibrarySingleDeCInp clearfix">
					<i>男女频道：</i>
					<div>
						<span>
						<c:if test="${pd.TITLE_CATEGORY_ID=='1' }">男生频道</c:if>
						<c:if test="${pd.TITLE_CATEGORY_ID=='2' }">女生频道</c:if>
						</span>
						<ul id="TITLE_CATEGORY_IDdiv">
							<li value="1">男生频道</li>
							<li value="2">女生频道</li>
						</ul>
					</div>
					<input type="hidden" id="TITLE_CATEGORY_ID" name="TITLE_CATEGORY_ID" value="${pd.TITLE_CATEGORY_ID }"/>
				</div>
				<div class="titleLibrarySingleDeCTex clearfix">
					<span>标题内容：</span>
					<div class="clearfix">
						<textarea id="CONTENT" name="CONTENT">${pd.CONTENT }</textarea>
						<p></p>
					</div>
				</div>
			</div>
			<div class="titleLibrarySingleDeB" onclick="addtitle()">
				完&nbsp;&nbsp;成
			</div>
		</div>
	</div>
	</form>
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


