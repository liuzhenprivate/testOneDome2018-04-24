<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	 
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
    <title>正文阅读</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<style>
	html,body{
		height:100%;
	}
</style>
<body style="background: #f7f7f7;">
	<div class="textReadingT">
		<h2><sapn>${articleChapter.chapterName }</sapn></h2>
		<p><font id="font" size="2">${chapterTxt }</font></p>
	</div>
	<input type="hidden" name="ARTICLE_CHAPTER_ID" id="ARTICLE_CHAPTER_ID" value="${articleChapter.id}"/>
	<input type="hidden" name="ARTICLE_ID" id="ARTICLE_ID" value="${articleChapter.article.id}"/>
	<input type="hidden" name="EXTENSION_CONTENT_ID" id="EXTENSION_CONTENT_ID" value="${extensionContent.id}"/>
	<div class="textReadingBtn">
		<div class="changeColorT">
			<span id="A-">A-</span>
			<font id="middle">2</font>
			<i onclick="AUP();">A+</i>
		</div>
		<div class="changeColorSel">
			<div class="changeColorSelAct"><span></span></div>
			<div><span></span></div>
			<div><span></span></div>
			<div><span></span></div>
		</div>
		<div class="textReadingBtnT clearfix">
			<span>上一章</span>
			<div>
				<span></span>
				<i></i>
			</div>
			<i>下一章</i>
		</div>
		<div class="textReadingBtnB clearfix">
			<a href="javascript:void(0)"><span>目录</span></a>
			<span id="conf" class="h">设置</span>
		</div>
	</div>
	<!-- <div class="textReadingBtn">
		<div class="textReadingBtnT clearfix">
			<span>上一章</span>
			<div>
				<span></span>
				<i></i>
			</div>
			<i>下一章</i>
		</div>
		<div class="textReadingBtnB clearfix">
			<span>目录</span>
			<span>设置</span>
		</div>
	</div>
	<div class="textReadPrompt clearfix">
		<div class="textReadPromptL">
			<img src="pages/home/read/images/myPic69.png" alt="" />
			<p>上一页</p>
		</div>
		<div class="textReadPromptLine"></div>
		<div class="textReadPromptDe">
			<img class="textReadPromptDe1" src="pages/home/read/images/myPic71.png" alt="" />
			<p>菜单区</p>
			<img class="textReadPromptDe2" src="pages/home/read/images/myPic72.png" alt="" />
		</div>
		<div class="textReadPromptR">
			<img src="pages/home/read/images/myPic70.png" alt="" />
			<p>下一页</p>
		</div>
	</div> -->
	<script src="pages/home/read/js/rem.js"></script>

	<script>
		$(window).on("touchstart", function(e) {
			startX = e.originalEvent.changedTouches[0].pageX,
			startY = e.originalEvent.changedTouches[0].pageY;
			e.stopPropagation();
		});
		$(window).on("touchend", function(e) {
			moveEndX = e.originalEvent.changedTouches[0].pageX;
			moveEndY = e.originalEvent.changedTouches[0].pageY;
			X = moveEndX - startX;
			Y = moveEndY - startY;
			var total=document.documentElement.clientWidth;
			if(Y<=5&&Y>=-5){
				if(startX<total/3){
			    	if(getScrollTop()<=0){
			    		window.open("http://www.jb51.net");
			    	}else{
			    		window.scrollBy(0,-getClientHeight());
			    	}
				}else if(startX>total*2/3){
			    	if(getScrollTop()+getClientHeight()==getScrollHeight()){
			    		window.open("http://www.jb51.net");
			    	}else if(getScrollHeight()>=getClientHeight()){
			    		var articleId = $('#ARTICLE_ID').val();
			    		var articleChpterId = $('#ARTICLE_CHAPTER_ID').val();
			    		var extensionContentId = $('#EXTENSION_CONTENT_ID').val();
			    		window.open("<%=basePath%>articlechapter/extensionChapterReadNext/<%=user.getWebchatId()%>/<%=user.getId()%>.do?articleId="+articleId+"&articleChpterId="+articleChpterId+"&extensionContentId="+extensionContentId);
			    	}else{
			    		window.scrollBy(0,getClientHeight());
			    	}
			    }else{
					if($('.textReadingBtn').css('display')=='none'){
						$('.textReadingBtn').css('display','block');
					}else{
						$('.textReadingBtn').css('display','none');
						
					}
				}
			}
			
		});
//		滚动条高度
		function getScrollTop(){  
		    var scrollTop=0;  
		    if(document.documentElement&&document.documentElement.scrollTop){  
		        scrollTop=document.documentElement.scrollTop;  
		    }else if(document.body){  
		        scrollTop=document.body.scrollTop;  
		    }  
		    return scrollTop;  
		};
//		文本实际高度
		function getScrollHeight(){  
		    return Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);  
		};
//		手机屏幕高度
		function getClientHeight(){  
		    var clientHeight=0;  
		    if(document.body.clientHeight&&document.documentElement.clientHeight){  
		        var clientHeight=(document.body.clientHeight<document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
		    }else{  
		        var clientHeight=(document.body.clientHeight>document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
		    }
		    return clientHeight;  
		}
		
		$('.textReadingBtn').on("touchstart", function(e) {
			e.stopPropagation();
		});
		
		var fontsize = $('#fontsize').text();
		$('#A-').on("touchstart", function(e) {
			var num = Number($('#font').attr('size'))-1;
			if(num>0){
				$('#font').attr('size',num);
				$('#middle').text(num);
			}
			e.stopPropagation();
		});
		function AUP(e){
			var num = Number($('#font').attr('size'))+1;
			if(num<=5){
				$('#font').attr('size',num);
				$('#middle').text(num);
			}
			e.stopPropagation();
		}
		
		$('.changeColorSel div').on("touchstart", function(e) {
			$(this).addClass('changeColorSelAct').siblings().removeClass('changeColorSelAct');
		});
		
		$('.changeColorSel div:first-child').on("touchstart", function(e) {
			$('body').css('background','#f7f7f7');
			$('.textReadingT h2').css('color','#7b5a40');
			$('.textReadingT p').css('color','#474747');
		});
		$('.changeColorSel div:nth-child(2)').on("touchstart", function(e) {
			$('body').css('background','#dfefd5');
			$('.textReadingT h2').css('color','#7b5a40');
			$('.textReadingT p').css('color','#474747');
		});
		$('.changeColorSel div:nth-child(3)').on("touchstart", function(e) {
			$('body').css('background','#f3e3bd');
			$('.textReadingT h2').css('color','#7b5a40');
			$('.textReadingT p').css('color','#474747');
		});
		$('.changeColorSel div:last-child').on("touchstart", function(e) {
			$('body').css('background','#373531');
			$('.textReadingT h2').css('color','#a08168');
			$('.textReadingT p').css('color','#92908c');
		});
	</script>
	
</body>
</html>