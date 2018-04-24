<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.sinontech.modle.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>VIP书库</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body>
	<div class="vipBookStoreT clearfix">
		<c:if test="${boardType ==1 }">
			<a href="<%=basePath%>board/listBoard/1.do"><span class="vipBookStoreTAct" id="allboard">总榜</span></a>
			<a href="<%=basePath%>board/listBoard/2.do"><span id="manboard">男生</span></a>
			<a href="<%=basePath%>board/listBoard/3.do"><span id="girlboard">女生</span></a>
		</c:if>
		<c:if test="${boardType ==2 }">
			<a href="<%=basePath%>board/listBoard/1.do"><span id="allboard">总榜</span></a>
			<a href="<%=basePath%>board/listBoard/2.do"><span class="vipBookStoreTAct" id="manboard">男生</span></a>
			<a href="<%=basePath%>board/listBoard/3.do"><span id="girlboard">女生</span></a>
		</c:if>
		<c:if test="${boardType ==3 }">
			<a href="<%=basePath%>board/listBoard/1.do"><span id="allboard">总榜</span></a>
			<a href="<%=basePath%>board/listBoard/2.do"><span id="manboard">男生</span></a>
			<a href="<%=basePath%>board/listBoard/3.do"><span class="vipBookStoreTAct" id="girlboard">女生</span></a>
		</c:if>
		<i>筛选</i>
	</div>
	<div class="detailsListDe">
		<ul>
			<c:if test="${varList != null}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
				<a href="javascript:void(0);" title="${var.id }">
					<li class="clearfix">
						<div class="detailsListDeL">
							<img src="${var.bookCover}" alt="" />
						</div>
						<div class="detailsListDeR">
							<div class="detailsListDeRT clearfix">
								<img src="pages/home/read/images/myPic10${vs.index+1}.png" alt="" />
								<p>${var.articleName }</p>
							</div>
							<div class="detailsListDeRC">${var.summary }</div>
							<div class="detailsListDeRB clearfix">
								<p>${var.author }</p>
								<div class="clearfix">
									<span>玄幻</span> <span>${var.readers }人在看</span>
								</div>
							</div>
						</div>
					</li>
				</a>
				</c:forEach>
			</c:if>
		</ul>
	</div>
	<div class="vipBottom clearfix">
		<span style="margin-left: 0.3rem;"></span>
		<p>我是有底线的</p>
		<span></span>
	</div>
	
	<!--筛选下拉-->
	<div class="vipBookStoreDown">
		<ul>
			<li class="clearfix">
				<span>全部</span>
				<div class="clearfix">
					<i>连载</i>
					<i>完结</i>
				</div>
			</li>
			<li class="clearfix">
				<span>全部</span>
				<div class="clearfix">
					<i>免费</i>
					<i>付费</i>
					<i>VIP</i>
				</div>
			</li>
			<li class="clearfix">
				<span>全部</span>
				<div class="clearfix">
					<i>扮猪吃虎</i>
					<i>扮猪吃虎</i>
					<i>扮猪吃虎</i>
				</div>
			</li>
		</ul>
	</div>
	<script>
		$('.vipBookStoreT span').click(function(){
			$(this).addClass('vipBookStoreTAct').siblings('span').removeClass('vipBookStoreTAct');
		});
		
		$('.vipBookStoreT i').click(function(){
			if($(this).hasClass('vipBookStoreTIAct')){
				$(this).removeClass('vipBookStoreTIAct');
				$('.vipBookStoreDown').hide();
			}else{
				$(this).addClass('vipBookStoreTIAct');
				$('.vipBookStoreDown').show();
			}
		});
	</script>

	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>


