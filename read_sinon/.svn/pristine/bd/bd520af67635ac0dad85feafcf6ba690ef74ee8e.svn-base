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
	<title>选择章节</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/promotionManagement.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">推广管理</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" href="<%=basePath%>extensioncontent/list.do" title="">推广列表</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">新建推广</a>
	</div>
	<div class="selectedChapters">
		<div class="selectedChaptersT clearfix">
			<div class="selectedChaptersTL">
				<img src="${articleById.BOOK_COVER}" alt="" />
			</div>
			<div class="selectedChaptersTR">
				<div class="selectedChaptersTR1 clearfix">
					<h2>${articleById.ARTICLE_NAME}</h2>
					<p>${articleById.AUTHOR}&nbsp;&nbsp;著</p>
				</div>
				<div class="selectedChaptersTR2 clearfix">
					<p>${articleById.SUMMARY }</p>
					<a href="javascript:void(0)" title="">
						【更多】
						<div class="bookIntroducte">
							<div>
								<img src="static/readchannel/images/myPic69.png" alt="" />
							</div>
							<h2>书籍简介</h2>
							<p>${articleById.SUMMARY }</p>
						</div>
					</a>
				</div>
				<div class="selectedChaptersTR3 clearfix">
					<a href="javascript:void(0)" title="">
							<c:if test="${articleById.SERIAL_STATE == 0 }">
								连载中
							</c:if>
							<c:if test="${articleById.SERIAL_STATE == 1 }">
								已完结
							</c:if>
					</a>
					<span>VIP</span>
					<i>${articleById.CATEGORY}</i>
					<c:choose>
						<c:when test="${not empty varlabellist}">
							<c:forEach items="${varlabellist}" var="var" varStatus="vs">
								<i>${var.LABEL_NAME }</i>
							</c:forEach>
						</c:when>
					</c:choose>
				</div>
				<div class="selectedChaptersTR4 clearfix">
					<p>${articleById.COUNT_LETTER}<span>万字</span></p>
					<p style="margin-left: 20px;">${articleById.COUNT_CHAPTERS}<span>章节</span></p>
				</div>
				<div class="selectedChaptersTR5">
					书籍编号：${articleById.ARTICLE_CODE}
				</div>
				<div class="selectedChaptersTR6">
					<p>全书价格：<span>${articleById.COUNT_CONSUMES}</span> 阅读币</p>
				</div>
				<div class="selectedChaptersTR7">
					推荐指数：${articleById.RECOMMEND}
				</div>
			</div>
		</div>
		<div class="selectedChaptersC">
			章节信息
		</div>
		<div class="selectedChaptersTit clearfix">
			<span style="margin-left: 20px; width: 363px;">章节名称</span>
			<span style="width: 237px;">字数</span>
			<span style="width: 264px;">价格</span>
			<span>管理操作</span>
		</div>
		<div class="selectedChaptersTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varchapterlist}">
				<c:forEach items="${varchapterlist}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="selectedChaptersTex1">${var.CHAPTER_NAME }</div>
						<div class="selectedChaptersTex2">${var.NUMBER_CHAPTER }</div>
						<div class="selectedChaptersTex3">${var.CONSUMES }</div>
						<div class="selectedChaptersTex4">
							<i>操作</i>
							<div class="selectedChaptersTexD">
								<input TYPE="hidden" name="httpURL" id="httpURL" value="<%=basePath%>">
								<a href="<%=basePath%>extensioncontent/goExtensioncopy.do?ARTICLE_CHAPTER_ID=${var.ARTICLE_CHAPTER_ID }&ARTICLE_ID=${articleById.ARTICLE_ID}" title="推广文案">推广文案</a>
								<a href="javascript:void(0)" title="">推广链接</a>
							</div>
						</div>
					</li>
				</c:forEach>
				</c:when>
				</c:choose>
			</ul>
		</div>
		<div class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
	</div>
	<script src="static/readchannel/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
		laydate.render({
		  elem: '#test6'
		  ,range: true
		});
		$('.rechargeManagementDeSelL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.rechargeManagementDeSelL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.rechargeManagementDeSelL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
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
		
		
		$('.flipTwoC1').click(function(){
			var spanVal=parseInt($('.flipTwoC span').text());
			spanVal++;
			$('.flipTwoC span').text(spanVal+'条/页');
		})
		$('.flipTwoC2').click(function(){
			var spanVal=parseInt($('.flipTwoC span').text());
			if(spanVal>0){
				spanVal--;
			}
			$('.flipTwoC span').text(spanVal+'条/页');
		})
		
		$(window).click(function(){
			$('.selectedChaptersTex4 div').hide();
		})
		$('.selectedChaptersTex4 i').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.selectedChaptersTexD').hide();
			e.stopPropagation();
		})
		$('.selectedChaptersTex4 div a').click(function(e){
			e.stopPropagation();
		})
		
		$('.selectedChaptersTR2 a').click(function(){
			if($('.bookIntroducte').css('display')=='none'){
				$('.bookIntroducte').show();
			}else{
				$('.bookIntroducte').hide();
			}
		})
		$('.bookIntroducte').click(function(e){
			e.stopPropagation();
		})
		$('.bookIntroducte div img').click(function(){
			$('.bookIntroducte').hide();
		})
	</script>
</body>
</html>
