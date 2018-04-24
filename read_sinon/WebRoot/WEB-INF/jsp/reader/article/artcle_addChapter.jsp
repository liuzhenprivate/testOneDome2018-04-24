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
	<title>添加章节（单章）</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">资源管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" href="bookManagement.html" title="">书籍管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" href="bookDetails.html" title="">书籍详情</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="addAChapter.html" title="">新增章节</a>
	</div>
	<form action="<%=basePath%>article/articleChapteradd.do"  id="ChapterForm" name="ChapterForm" method="post">
	<input type="hidden" name="ARTICLE_ID" id="ARTICLE_ID" value="${countarticlepd.ARTICLE_ID }"/>
	<div class="addAChapterDe">
		<h1>新增章节</h1>
		<div class="addAChapterDeInp clearfix">
			<span>章节序号</span>
			<div class="clearfix">
				<input name="ARTICLECHAPTER_NO" id="ARTICLECHAPTER_NO" value=""/>
				<p>请输入章节数(如果为空则自动添加为后一章)</p>
			</div>
			<h2>当前共${countChapterpd.ARTICLE_CHAPTER_IDS }章</h2>
		</div>
		<div class="addAChapterDeInp clearfix">
			<span>章节名称</span>
			<div class="clearfix">
				<input name="CHAPTER_NAME" id="CHAPTER_NAME" value=""/>
				<p>请输入章节名称 例：第一章 破败小山村</p>
			</div>
			<h3>最多20字</h3>
		</div>
		<div class="addAChapterDeInp clearfix">
			<span>关联书籍</span>
			<div class="clearfix">
				<input name="ARTICLE_NAMEAddc" id="ARTICLE_NAMEAddc" value="${countarticlepd.ARTICLE_NAME }" readonly="readonly"/>
				<p>请输入书籍名</p>
			</div>
		</div>
		<div class="addAChapterDeText clearfix">
			<span>章节内容</span>
			<div class="clearfix">
				<textarea id="chaptercontent" name="chaptercontent" onKeyUp="contentchange();"></textarea>
				<p>请输入章节内容</p>
			</div>
			<input type="hidden" name="NUMBER_CHAPTER" id="NUMBER_CHAPTER" value=""/>
			<i id="numberWord">共0字</i>
		</div>
		<div class="addAChapterDeBtn clearfix">
			<a class="AddBatchSectionBtn1" href="javascript:articleChapteradd()" title="">完成</a>
			<span  onclick="javascript:history.back(-1);">取&nbsp;&nbsp;消</span>
		</div>
	</div>
	</form>
	<script>
	
		function articleChapteradd(){
			var a = $('.AddBatchSectionBtn1').text();
			if(a=="完成"){
				$('.AddBatchSectionBtn1').text('提交中...');
				$("#ChapterForm").attr("action","<%=basePath%>article/articleChapteradd.do?NUMBER_CHAPTER="+$("#numberWord").text());
				$("#ChapterForm").submit();
			}
		}
		function contentchange(){
			var chaptercontent = $("#chaptercontent").val();
			$("#numberWord").text("共"+chaptercontent.length+"字");
			$("#NUMBER_CHAPTER").val(chaptercontent.length);
		}
	</script>
	
	<script>
		
		$(document).ready(function(){
			$(".addAChapterDeInp").each(function(){
			    if($(this).find('input').val()==""){
			    	$(this).find('p').css("display","block");
			    }else{
			    	$(this).find('p').css("display","none");
			    }
			});
		});
	
		$('.addAChapterDeInp p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.addAChapterDeInp input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.addAChapterDeInp input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		$('.addAChapterDeText p').click(function(){
			$(this).hide();
			$(this).siblings('textarea').focus();
		})
		$('.addAChapterDeText textarea').bind('textarea propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.addAChapterDeText textarea').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
	</script>
</body>
</html>