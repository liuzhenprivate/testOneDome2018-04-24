<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
	Integer signflag = (Integer)request.getAttribute("signflag");//0未开启签到功能 1 已签到2未签到
	UserInfo user = (UserInfo)request.getAttribute("user");
//	System.out.println(user.toString());
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>书籍目录</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body >
	<div class="bookCatalogues">
		<ul>
		<c:forEach items="${list}" var="var" varStatus="vs">
			<li class="clearfix">
				<c:if test="${var.consumes > 0}">
					<c:if test="${userCurrency < var.consumes }">
						<a href="javascript:articleChapterfee(${var.id},${var.consumes})">
					</c:if>
					<c:if test="${userCurrency > var.consumes }">
						<c:if test="${isorder == 0 }">
							<a href="javascript:articleChapterfee(${var.id},${var.consumes})" >
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${var.consumes > 0 && userCurrency > var.consumes && isorder == 1 }">
					<a href="<%=basePath%>articlechapter/articlechapterGetById/${user.webchatId }/${var.id}/${user.id }/0.do">
				</c:if>
				<c:if test="${var.consumes == 0}">
					<a href="<%=basePath%>articlechapter/articlechapterGetById/${user.webchatId }/${var.id}/${user.id }/0.do">
				</c:if>
				<p>${var.chapterName }</p>
				<c:if test="${var.isFree == 1 }">
					<img src="pages/home/read/images/myPic51.png" alt="" />
				</c:if>
				</a>
			</li>
		</c:forEach>
			 
		</ul>
	</div>
	
	<div class="textReadingBuy" id="textReadingBuy1" style="display:none">
		<div class="textReadingBuy1 clearfix">
			<p>我的余额：<i>（余额不足）</i></p>
			<span id="userCurrency">剩余：${userCurrency}阅读币</span>
		</div>
		<div class="textReadingBuy3 clearfix">
			<p>需支付：<i id="Currency1"></i></p>
			<a id="hrefCurrency1" ><span>充&nbsp;&nbsp;&nbsp;值</span></a>
		</div>
	</div>
	<div class="textReadingBuy" id="textReadingBuy2" style="display:none">
		<div class="textReadingBuy2 clearfix">
			<p>是否自动扣费：</p>
			<%if(user.getAutoOrder()==1){ %>
			<img src="pages/home/read/images/myPic67.png" alt="" />
			<%}else{ %>
			<img src="pages/home/read/images/myPic66.png" alt="" />
			<%} %>
		</div>
		<div class="textReadingBuy3 clearfix">
			<p>需支付：<i id="Currency2"></i></p>
			<span><a id="hrefCurrency2" href="<%=basePath%>articlechapter/articlechapterGetById/<%=user.getWebchatId()%>/${var.id}/<%=user.getId()%>/0.do?id=1">购&nbsp;&nbsp;&nbsp;买</a></span>
		</div>
	</div>
	
	<script>
		$('.textReadingBuy2 img').click(function(){
			if($(this).attr('src')=='pages/home/read/images/myPic66.png'){
				$(this).attr('src','pages/home/read/images/myPic67.png');
				auto(1);
			}else{
				$(this).attr('src','pages/home/read/images/myPic66.png');
				auto(0);
			}
		});
		
		//开启关闭自动购买付费章节功能
		function auto(autoOrder){
			var url = "<%=basePath%>recharge/autoOrder/<%=user.getWebchatId() %>/"+autoOrder;
			//alert(url);
			$.get(url,function(data){
				//alert(data);
			});
		}
	</script>
	<script>
	function articleChapterfee(articleChapterId,consumes){
		var url = "<%=basePath%>articlechapter/articleChapterfee/${user.webchatId }/"+articleChapterId;
		$.get(url,function(data){
			//alert(data);
			var arr = data.split('==');
			if(arr.length>1){
				var isread = arr[0];
				var bc = arr[1];
				$("#userCurrency").html("剩余："+bc+"阅读币");
				if(isread=='1'||isread=='6'){
					//未开启费用不够
					show(1,articleChapterId,consumes);
				}else if(isread=='2'){
					//已开启费用不够
					show(2,articleChapterId,consumes);
				}else{
					window.location.href='<%=basePath%>articlechapter/articlechapterGetById/${user.webchatId }/'+articleChapterId+'/${user.id }/0.do';
				}
			}
		});
	}		
		
		function show(type,cid, consumes){
			//阅读币不足
			if(type==1){
				$('#Currency1').text(consumes+'阅读币');
				$('#textReadingBuy2').hide();
				$('#textReadingBuy1').show();
				$("#hrefCurrency1").attr("href",'<%=basePath%>recharge/list/${user.webchatId }?rurl=articlechapter/articlechapterGetById/${user.webchatId }/'+cid+'/${user.id }/0');
			}else{
				//未开启自动扣费
				$('#Currency2').text(consumes+'阅读币');
				$('#hrefCurrency2').attr('href','<%=basePath%>articlechapter/articlechapterGetById/${user.webchatId }/'+cid+'/${user.id }/0.do?id=1');
				$('#textReadingBuy1').hide();
				$('#textReadingBuy2').show();
			}
		}
		//开启关闭自动购买付费章节功能
		function auto(autoOrder){
			var url = "<%=basePath%>recharge/autoOrder/${user.webchatId }/"+autoOrder;
			$.get(url,function(data){
			});
		}
		$('#textReadingBuy1').hide();
		$('#textReadingBuy2').hide();
	</script>
	
	<script type="text/javascript">
			function no(){
			$.ajax({
					type: "post",
					data:{
						articleChapterId:cid,
						articleId:aid
					},
					url: "<%=basePath%>articlechapter/findNextArticleChapter.do",
					dataType:'json', 
					success: function(data){
						$('.textReadingBuy1 p').remove();
						$('.textReadingBuy1 span').remove();
						$('.textReadingBuy3 p i').remove();
						$('.textReadingBuy1').append('<p>下一章：'+data.chapterName+'</p><span>'+data.consumes+'阅读币</span>');
						$('.textReadingBuy3 p').append('<i>'+data.consumes+'阅读币</i>');
					}
				});
		}
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>

