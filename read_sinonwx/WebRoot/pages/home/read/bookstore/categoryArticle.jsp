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
    <title>${articleCategory.gategory }</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
		<input type="hidden" name="sort" id="sort" value="${sort }">
		<input type="hidden" name="labelId" id="labelId" value="${labelId }">
		<input type="hidden" name="serialState" id="serialState" value="${serialState }">
		<input type="hidden" name="feeType" id="feeType" value="${feeType }">
		<input type="hidden" name="webchatId" id="webchatId" value="${webchatId }">
		<input type="hidden" name="articleCategoryId" id="articleCategoryId" value="${articleCategory.id }">
 		<div class="vipBookStoreT clearfix">
 			<c:if test="${sort ==0}">
				<span class="vipBookStoreTAct" id="hotboard"><a href="read/listCategoryArticle/${webchatId }/${articleCategory.id}.do?sort=0&labelId=${labelId}&labelId=${labelId}&serialState=${serialState }&feeType=${feeType}&labelId=${labelId}&serialState=${serialState }&feeType=${feeType}">热门</a></span>
				<span id="newestboard"><a href="read/listCategoryArticle/${webchatId }/${articleCategory.id}.do?sort=1&labelId=${labelId}&labelId=${labelId}&serialState=${serialState }&feeType=${feeType}">最新</a></span>
				<span id="praiseboard"><a href="read/listCategoryArticle/${webchatId }/${articleCategory.id}.do?sort=2&labelId=${labelId}&labelId=${labelId}&serialState=${serialState }&feeType=${feeType}">好评</a></span>
			</c:if>
			<c:if test="${sort ==1}">
				<span id="hotboard"><a href="read/listCategoryArticle/${webchatId }/${articleCategory.id}.do?sort=0&labelId=${labelId}&labelId=${labelId}&serialState=${serialState }&feeType=${feeType}">热门</a></span>
				<span class="vipBookStoreTAct" id="newestboard"><a href="read/listCategoryArticle/${webchatId }/${articleCategory.id}.do?sort=1&labelId=${labelId}&labelId=${labelId}&serialState=${serialState }&feeType=${feeType}">最新</a></span>
				<span id="praiseboard"><a href="read/listCategoryArticle/${webchatId }/${articleCategory.id}.do?sort=2&labelId=${labelId}&labelId=${labelId}&serialState=${serialState }&feeType=${feeType}">好评</a></span>
			</c:if>
			<c:if test="${sort ==2}">
				<span id="hotboard"><a href="read/listCategoryArticle/${webchatId }/${articleCategory.id}.do?sort=0&labelId=${labelId}&labelId=${labelId}&serialState=${serialState }&feeType=${feeType}">热门</a></span>
				<span id="newestboard"><a href="read/listCategoryArticle/${webchatId }/${articleCategory.id}.do?sort=1&labelId=${labelId}&labelId=${labelId}&serialState=${serialState }&feeType=${feeType}">最新</a></span>
				<span class="vipBookStoreTAct" id="praiseboard"><a href="read/listCategoryArticle/${webchatId }/${articleCategory.id}.do?sort=2&labelId=${labelId}&labelId=${labelId}&serialState=${serialState }&feeType=${feeType}">好评</a></span>
			</c:if>
		<i>筛选</i>
	</div>
	<div style="background: #ffffff; margin-top: 55px;" class="detailsListDe">
		<ul>
		<c:forEach items="${list}" var="var" varStatus="vs">
			<li class="clearfix">
				<div class="detailsListDeL"><img src="http://kuwx.xinxinwx.cn/read/${var.bookCover }" alt="" /></div>
				<div class="detailsListDeR">
				<a href="<%=basePath%>article/findArticleId/<%=user.getWebchatId()%>/${var.id }/<%=user.getId()%>.do">
					<div class="detailsListDeRT clearfix">
						<!-- img src="pages/home/read/images/myPic2.png" alt="" /-->
						<p>${var.articleName }</p>
					</div>
					<div class="detailsListDeRC">
						 ${var.summary }
					</div>
					<div class="detailsListDeRB clearfix">
						<p>${var.author }</p>
						<div class="clearfix">
							<c:if test="${var.countConsumes > 0}">
								<span style="color: #fe925b; border: 0.01rem solid #fc955b;">VIP</span>
							</c:if>
							<c:if test="${var.countConsumes == 0}">
								<span style="color: #fe925b; border: 0.01rem solid #fc955b;">免费</span>
							</c:if>
							<span>${var.articleCategory.gategory }</span><span>${var.displayReaders }人在看</span>
						</div>
					</div>
				</a>
				</div>
			</li>
		</c:forEach>
			 
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
				<span>状态</span>
				<div class="clearfix">
					<i title="" class="serialStateS">全部</i>
					<c:if test="${serialState == 0}">
						<i title="0" class="serialStateS"><font size="4" color="#FFB90F">连载</font></i>
						<i title="1" class="serialStateS">完结</i>
					</c:if>
					<c:if test="${serialState == 1}">
						<i title="0" class="serialStateS">连载</i>
						<i title="1" class="serialStateS"><font size="4" color="#FFB90F">完结</font></i>
					</c:if>
					<c:if test="${serialState == null}">
						<i title="0" class="serialStateS">连载</i>
						<i title="1" class="serialStateS">完结</i>
					</c:if>
				</div>
			</li>
			<li class="clearfix">
				<span>方式</span>
				<div class="clearfix">
					<i title="" class="feeTypeF">全部</i>
					<c:if test="${feeType == 0}">
						<i title="0" class="feeTypeF"><font size="4" color="#FFB90F">免费</font></i>
						<i title="1" class="feeTypeF">付费</i>
					</c:if>
					<c:if test="${feeType == 1}">
						<i title="0" class="feeTypeF">免费</i>
						<i title="1" class="feeTypeF"><font size="4" color="#FFB90F">付费</font></i>
					</c:if>
					<c:if test="${feeType == null}">
						<i title="0" class="feeTypeF">免费</i>
						<i title="1" class="feeTypeF">付费</i>
					</c:if>
				</div>
			</li>
			<li class="clearfix">
				<span>标签</span>
				<div class="clearfix">
					<i title="" class="labelC">全部</i>
					<c:forEach items="${labelList}" var="var" varStatus="vs">
						<c:if test="${labelId == var.id}">
							<i title="${var.id }" class="labelC"><font size="4" color="#FFB90F">${var.labelName }</font></i>
						</c:if>
						<c:if test="${labelId != var.id}">
							<i title="${var.id }" class="labelC">${var.labelName }</i>
						</c:if>
					</c:forEach>
				</div>
			</li>
		</ul>
	</div>
	
	<script src="pages/home/read/js/rem.js"></script>
	<script>
		$('.labelC').click(function(){
			var labelId = $(this).attr('title');
			var sort = $('#sort').val();
			var feeType = $('#feeType').val();
			var serialState = $('#serialState').val();
			var articleCategoryId = $('#articleCategoryId').val();
			var webchatId = $('#webchatId').val();
			window.location.href='<%=basePath%>read/listCategoryArticle/'+webchatId+'/'+articleCategoryId+'.do?sort='+sort+'&&labelId='+labelId+'&&feeType='+feeType+'&&serialState='+serialState;
		});
		$('.serialStateS').click(function(){
			var serialState = $(this).attr('title');
			var sort = $('#sort').val();
			var feeType = $('#feeType').val();
			var labelId = $('#labelId').val();
			var articleCategoryId = $('#articleCategoryId').val();
			var webchatId = $('#webchatId').val();
			window.location.href='<%=basePath%>read/listCategoryArticle/'+webchatId+'/'+articleCategoryId+'.do?sort='+sort+'&&labelId='+labelId+'&&feeType='+feeType+'&&serialState='+serialState;
		});
		$('.feeTypeF').click(function(){
			var feeType = $(this).attr('title');
			var sort = $('#sort').val();
			var serialState = $('#serialState').val();
			var labelId = $('#labelId').val();
			var articleCategoryId = $('#articleCategoryId').val();
			var webchatId = $('#webchatId').val();
			window.location.href='<%=basePath%>read/listCategoryArticle/'+webchatId+'/'+articleCategoryId+'.do?sort='+sort+'&&labelId='+labelId+'&&feeType='+feeType+'&&serialState='+serialState;
		});
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
</body>
</html>

