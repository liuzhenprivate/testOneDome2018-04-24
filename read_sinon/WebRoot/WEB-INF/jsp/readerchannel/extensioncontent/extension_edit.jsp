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
	<title>文案标题推广链接</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/public.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<form action="<%=basePath%>extensioncontent/extensionedit.do" id="Form" name="Form" method="post">
	<input type="hidden" name="ARTICLE_ID" id="ARTICLE_ID" value="${pd.ARTICLE_ID }"/>
	<input type="hidden" name="ARTICLE_NAME" id="ARTICLE_NAME" value="${pd.ARTICLE_NAME }"/>
	<input type="hidden" name="EXTENSION_CONTENT_ID" id="EXTENSION_CONTENT_ID" value="${pd.EXTENSION_CONTENT_ID }"/>
	<div id="zhongxin">
	<div class="extensionLinkAlert">
		<div class="extensionLinkDe">
			<div class="extensionLinkDeT clearfix">
				<span>推广书籍：</span>
				<p id="ARTICLE_ID">${pd.ARTICLE_NAME }</p>
			</div>
			<div class="extensionLinkDeInp clearfix">
				<span>渠道名称：</span>
				<div class="clearfix">
					<input name="NAME" id="NAME" value="${pd.NAME }"/>
					<p>输入渠道名称</p>
				</div>
			</div>
			<div class="extensionLinkDeAudio clearfix">
				<input type="hidden" name="ISFORCE" id="ISFORCE" value="${ISFORCE }"/>
				<span>是否带号：</span>
				<div class="clearfix">
				<c:if test="${pd.FORCE_CHAPTER != null }">
						<i class="extensionLinkDeAudio1 extensionLinkDeAudioAct" onclick="fun1(1);">是</i>
						<i class="extensionLinkDeAudio1"  onclick="fun1(2);">否</i>
					</c:if>
					<c:if test="${pd.FORCE_CHAPTER == null }">
						<i class="extensionLinkDeAudio1" onclick="fun1(1);">是</i>
						<i class="extensionLinkDeAudio1 extensionLinkDeAudioAct"  onclick="fun1(2);">否</i>
					</c:if>
				</div>
				<a href="javascript:void(0)" title="">什 么是强制带号？</a>
			</div>
			<div class="extensionLinkDeSel clearfix">
				<input type="text" name="" id="" value=""/>
				<span>带号章节：</span>
				<div class="clearfix">
					<p id="CHAPTER_NAME">
						<c:if test="${pd1.CHAPTER_NAME != null }">
							${pd1.CHAPTER_NAME}
						</c:if>
					
						<c:if test="${pd1.CHAPTER_NAME == null }">
							请选择带号章节
						</c:if>
					</p>
					<input type="hidden" name="FORCE_CHAPTER" id="FORCE_CHAPTER" value="${pd.FORCE_CHAPTER }"/>
					<img src="static/readchannel/images/myPic15.png" alt="" />
					<ul>
						<c:choose>
						<c:when test="${not empty list}">
						<c:forEach items="${list}" var="var" varStatus="vs">
							<li onclick="fun2(${var.ARTICLE_CHAPTER_ID });">${var.CHAPTER_NAME }</li>
						</c:forEach>
						</c:when>
						<c:otherwise>
							没有相关数据
						</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
		<div class="automaticReplyBtn" onclick="scsubmit();">
			完&nbsp;&nbsp;成
		</div>
	</div>
	</div>
	</form>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		function scsubmit(){
			if($("#name").val()==""){
				$("#name").tips({
					side:3,
		            msg:'请输入渠道名称',
		            bg:'#FF6600',
		            time:2
		        });
				$("#name").focus();
				return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
		}
		function fun1(data){
			$("#ISFORCE").val(data);
		}
		function fun2(data){
			$("#CHAPTERS").val(data);
		}
	</script>
	<script>
		
		
		$('.extensionLinkDeInp div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.extensionLinkDeInp div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.extensionLinkDeInp div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
		$('.extensionLinkDeAudio div i').click(function(){
			if(!$(this).hasClass('extensionLinkDeAudioAct')){
				$(this).addClass('extensionLinkDeAudioAct').siblings().removeClass('extensionLinkDeAudioAct');
			}
		})
		
		$('.extensionLinkDeSel div').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.extensionLinkDeSel div ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent('ul').siblings('p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.extensionLinkDeSel div').find('ul').hide();
		})
	</script>
</body>
</html>