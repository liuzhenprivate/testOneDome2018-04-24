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
	<title>新建推广（选择书籍）</title>
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
	<form action="<%=basePath%>extensioncontent/goExtensionbook.do" method="post" id="Form" name="Form">
	<div class="promotionManagementDe">
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names }" placeholder="输入书籍名称或作者搜索"/>
					<img src="static/readchannel/images/myPic26.png" alt="" onclick="submit();"/>
				</div>
				<div class="rechargeManagementDeSelL3 clearfix">
					<input type="hidden" name="ARTICLE_CATEGORY_ID" id="ARTICLE_CATEGORY_ID" value="${pd1.ARTICLE_CATEGORY_ID }">
					<p>
						<c:if test="${pd1.ARTICLE_CATEGORY_ID != null}">
							${pd1.CATEGORY}
						</c:if>	
						<c:if test="${pd1.ARTICLE_CATEGORY_ID == null}">
							书籍类型
						</c:if>
					</p>
					<img src="static/readchannel/images/myPic13.png" alt="" />
					<ul>
						<li onclick="gategoryEdit('')">全部类型</li>
						<c:choose>
						<c:when test="${not empty chapterList}">
						<c:forEach items="${chapterList}" var="var" varStatus="vs">
							<li onclick="gategoryEdit('${var.ARTICLE_CATEGORY_ID}')">${var.CATEGORY }</li>
						</c:forEach>
						</c:when>
						<c:otherwise>
							没有相关数据
						</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="rechargeManagementDeSelL3 clearfix">
					<input type="hidden" name="CHANNEL_TYPE" id="CHANNEL_TYPE" value="${pd.CHANNEL_TYPE }">
					<p>
						<c:choose>
							<c:when test="${pd.CHANNEL_TYPE == 1}">
								女频
							</c:when>
							<c:when test="${pd.CHANNEL_TYPE == 0}">
								男频
							</c:when>
							<c:otherwise>
								全部频道
							</c:otherwise>
						</c:choose>
					</p>
					<img src="static/readchannel/images/myPic13.png" alt="" />
					<ul>
						<li onclick="channelEdit('')">全部频道</li>
						<li onclick="channelEdit('0')">男频</li>
						<li onclick="channelEdit('1')">女频</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="newPromotionTit clearfix">
			<span style="width: 80px; margin-left: 20px;">封面</span>
			<span style="width: 159px;">基本信息</span>
			<span style="width: 292px;">简介</span>
			<span style="width: 92px;">是否热门</span>
			<span style="width: 75px;">类型</span>
			<span style="width: 66px;">频道</span>
			<span style="width: 95px;">推荐指数</span>
			<span>操作</span>
		</div>
		<div class="newPromotionTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="newPromotionTex1">
							<img src="${var.BOOK_COVER}" alt="" />
						</div>
						<div class="newPromotionTex2">
							<p>书名：<span>${var.ARTICLE_NAME }</span></p>
							<p>作者：<span>${var.AUTHOR }</span></p>
							<p>章节数：<span>${var.COUNT_CHAPTERS }</span></p>
						</div>
						<div class="newPromotionTex3">
							<p>${var.SUMMARY }</p>
						</div>
						<div class="newPromotionTex4">
							<c:choose>
							<c:when test="${not empty var.STATE}">
								<c:if test="${var.IS_HOT == 0 }">
									否
								</c:if>
								<c:if test="${var.IS_HOT == 1 }">
									是
								</c:if>
							</c:when>
							<c:otherwise>
								没有相关数据
							</c:otherwise>
							</c:choose>
						</div>
						<div class="newPromotionTex5">
							${var.CATEGORY }
						</div>
						<div class="newPromotionTex6">
							<c:choose>
							<c:when test="${not empty var.STATE}">
								<c:if test="${var.CHANNEL_TYPE == 0 }">
									男频
								</c:if>
								<c:if test="${var.CHANNEL_TYPE == 1 }">
									女频
								</c:if>
							</c:when>
							<c:otherwise>
								没有相关数据
							</c:otherwise>
							</c:choose>
						</div>
						<div class="newPromotionTex7">
							${var.RECOMMEND }
						</div>
						<div class="newPromotionTex8 clearfix">
							<a href="<%=basePath%>article/goExtensionchapter.do?ARTICLE_ID=${var.ARTICLE_ID }" title="推广">推广</a>
						</div>
					</li>
				</c:forEach>
				</c:when>
				<c:otherwise>
					没有相关数据
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
	</div>
	</form>
	<script src="static/readchannel/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript">
		function submit(){
			$("#Form").attr("action","<%=basePath%>extensioncontent/goExtensionbook.do");
			$("#Form").submit();
		}
		function gategoryEdit(data){
			$('#ARTICLE_CATEGORY_ID').val(data);
			submit();
		}
		function channelEdit(data){
			$('#CHANNEL_TYPE').val(data);
			submit();
		}
	</script>
	<script>
		laydate.render({
		  elem: '#sendtime'
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
		
	</script>
</body>
</html>
