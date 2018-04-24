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
<input type="hidden" id="htmlid" name="htmlid" value="${pd.htmlid }"/>
<input type="hidden" id="modleid" name="modleid" value="${pd.modleid }"/>
<input type="hidden" id="cnums" name="cnums" value="${cnums }"/>
<input type="hidden" id="aid" name="aid" value=""/>
	<div class="addBooks">
		<div style="background: #fff; padding-bottom: 20px;">
			<div class="addBooksTop clearfix">
				<span>已选书籍</span>
				<p>已有 ${nums }本书籍，可选 ${cnums } 本书籍</p>
			</div>
			<div class="addBooksSel clearfix">
				<div class="addBooksSelL clearfix">
					<input type="text" id="SEARCHKEY" name="SEARCHKEY" value="${pd.SEARCHKEY }"/>
					<p>输入书籍名称或作者搜索</p>
					<img src="static/read/images/myPic26.png" alt="" onclick="search()"/>
				</div>
				  
			</div>
			<div class="addBooksSelTit clearfix">
				 
				<div style="width: 70px; margin-left: 21px;">封面</div>
				<div style="width: 146px;">基本信息</div>
				<div style="width: 103px;">书籍ID</div>
				<div style="width: 89px;">书籍类型</div>
				<div style="width: 75px;">付费类型</div>
				<div>男女频</div>
			</div>
			<div class="addBooksSelTex">
			<ul>
			<c:choose>
				<c:when test="${not empty list }">
					<c:forEach items="${list}" var="var" varStatus="vs">
					<li class="clearfix" value="${var.ARTICLE_ID }">
					<a><span value="${var.ARTICLE_ID }"></span></a>
					<div class="addBooksSelTexTw">
						<img src="${var.BOOK_COVER }" alt="" />
					</div>
					<div class="addBooksSelTexTh">
						<p><i>书名：</i>${var.ARTICLE_NAME }</p>
						<p><i>作者：</i>${var.AUTHOR } </p>
						<p><i>章节数：</i>${var.COUNT_CHAPTERS }</p>
						<p><i>总字数：</i>${var.COUNT_LETTER }字</p>
					</div>
					<div class="addBooksSelTexFo">
						${var.ARTICLE_ID }
					</div>
					<div class="addBooksSelTexFi">
						<p>${var.CATEGORY }<i>（连载）</i></p>
					</div>
					<div class="addBooksSelTexSi">
					<c:if test="${var.FEE_TYPE=='0' }">
					免费
					</c:if>
					<c:if test="${var.FEE_TYPE=='1' }">
					付费
					</c:if>
					</div>
					<div class="addBooksSelTexSe">
					<c:if test="${var.CATEGORY_TYPE=='0' }">
					女频
					</c:if>
					<c:if test="${var.CATEGORY_TYPE=='1' }">
					男频
					</c:if>
						
					</div>
				</li>
					</c:forEach>
				</c:when>
			</c:choose>
				  
				 
			</ul>
		</div>
		</div>
		<div class="sortBtn" onclick="save()">
			完&nbsp;&nbsp;成
		</div>
	</div>
	</form>
</div>
	<script>
		$('.addBooksSelL p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.addBooksSelL input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.addBooksSelL input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		$('.addBooksSelC').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.addBooksSelC ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$('.addBooksSelC p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.addBooksSelC').find('ul').hide();
		})
		
		$('.addBooksSelTit span i').click(function(){
			if($(this).hasClass('addBooksSelTitAct')){
				$(this).removeClass('addBooksSelTitAct');
				$('.addBooksSelTex ul li a span').removeClass('addBooksSelTexAct');
			}else{
				$(this).addClass('addBooksSelTitAct');
				$('.addBooksSelTex ul li a span').addClass('addBooksSelTexAct');
			}
		})
		$('.addBooksSelTex ul li a span').click(function(){
			var cnums = parseInt($("#cnums").val());
			var id =$(this).attr('value');
			//alert(cnums);
			var aid = $("#aid").val();
			if($(this).hasClass('addBooksSelTexAct')){
				$(this).removeClass('addBooksSelTexAct');
				$('.addBooksSelTit span i').removeClass('addBooksSelTitAct');
				aid = aid.replace(','+id+',','');
				$("#aid").val(aid);
			}else{
				if(''==aid){
					$(this).addClass('addBooksSelTexAct');
					aid=aid + ','+id+',';
					$("#aid").val(aid);
				}else{
					var str1 = aid.split(",,");
					var len = parseInt(str1.length);
					if(len>=cnums){
						alert("可选数已达");
					}else{
						$(this).addClass('addBooksSelTexAct');
						aid=aid + ','+id+',';
						$("#aid").val(aid);
					}
				}
				
			}
		})
	</script>


</body>
</html>
