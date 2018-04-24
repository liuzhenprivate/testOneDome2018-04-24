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
	<title>添加章节</title>
	    <base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="readText();">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">资源管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" href="chapterManagement.html" title="">书籍管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="newChapter.html" title="">章节修改</a>
	</div>
	<form action="<%=basePath%>articlechapter/articleChapteradd.do"  id="ChapterForm" name="ChapterForm" method="post">
	<div class="newChapterDe">
		<h1>新增章节</h1>
		<input type="hidden" name="ARTICLE_CHAPTER_ID" id="ARTICLE_CHAPTER_ID" value="${pd.ARTICLE_CHAPTER_ID }"/>
		<input type="hidden" name="ARTICLE_ID" id="ARTICLE_ID" value="${pd.ARTICLE_ID }"/>
		<input type="hidden" name="CONTENT_URL" id="CONTENT_URL" value="${pd.CONTENT_URL }"/>
		<div class="newChapterDeInp clearfix">
			<span>书籍名称</span>
			<div class="clearfix">
				<input name="ARTICLE_NAME" id="ARTICLE_NAME" value="${pd.ARTICLE_NAME }" disabled="disabled"/>
			</div>
			<p>当前书籍共${pd.COUNT_CHAPTERS}章</p>
		</div>
		<div class="newChapterDeInp clearfix">
			<span>章节序号</span>
			<div class="clearfix">
				<input type="hidden" name="CHAPTER_NO1" id="CHAPTER_NO1" value="${pd.CHAPTER_NO }"/>
				<input name="CHAPTER_NO" id="CHAPTER_NO" value="${pd.CHAPTER_NO }" />
			</div>
		</div>
		<div class="newChapterDeInp clearfix">
			<span>章节名称</span>
			<div class="clearfix">
				<input type="hidden" name="CHAPTER_NAME1" id="CHAPTER_NAME1" value="${pd.CHAPTER_NAME }"/>
				<input name="CHAPTER_NAME" id="CHAPTER_NAME" value="${pd.CHAPTER_NAME }" />
			</div>
		</div>
		<div class="newChapterDeTex clearfix">
			<span>章节内容</span>
			<div class="clearfix">
				<textarea id="chaptercontent" name="chaptercontent" onKeyUp="contentchange();">${pd.txt}</textarea>
				<p>请输入章节内容</p>
			</div>
			共<i id="numberWord">0</i>字
		</div>
		<div style="margin-left: 76px;" class="AddBatchSectionBtn clearfix">
			<a class="AddBatchSectionBtn1" href="javascript:void(0)" title="">完成</a>
			<a class="AddBatchSectionBtn2" href="javascript:history.go(-1);" title="">取消</a>
		</div>
	</div>
	</form>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script>
		$('#CHAPTER_NO').blur(function(){
			var no = $('#CHAPTER_NO').val();
			var reg =/^([1-9]{1,3}(.\d{2})?|1000(.[0]{2})?)$/;
			if (reg.test(no) != true) {
				var CHAPTER_NO1 = $('#CHAPTER_NO1').val();
				$('#CHAPTER_NO').val(CHAPTER_NO1);
			}
		});
		
		 $('.AddBatchSectionBtn1').click(function(){
		 	if($("#ARTICLE_NAME").val()==""){
				$("#ARTICLE_NAME").tips({
					side:3,
			        msg:'请输入书籍名',
			        bg:'#FF6600',
			        time:2
			     });
				$("#ARTICLE_NAME").focus();
				return false;
			}
			if($("#CHAPTER_NAME").val()==""){
				$("#CHAPTER_NAME").tips({
					side:3,
			        msg:'请输入章节名称',
			        bg:'#FF6600',
			        time:2
			     });
				$("#CHAPTER_NAME").focus();
				return false;
			}
			var a = $(this).text();
			var textlen = $('#chaptercontent').val();
			if(a=="完成"){
				$('.AddBatchSectionBtn1').text('提交中...');
				$("#ChapterForm").attr("action","<%=basePath%>articlechapter/articleChapteredit.do?NUMBER_CHAPTER="+textlen.length);
				$("#ChapterForm").submit();
			}
		 
		 });
			
		
		
		function contentchange(){
			var chaptercontent = $("#chaptercontent").val();
			$("#numberWord").text(chaptercontent.length);
		}
	</script>
	<script>
		$(document).ready(function(){
			$(".newChapterDeInp").each(function(){
			    if($(this).find('input').val()==""){
			    	$(this).find('p').css("display","block");
			    }else{
			    	$(this).find('p').css("display","none");
			    }
			});
		});
		function newChapter(data){
			window.parent.selectzdnAllBook(data);
			$('.newMatchBooksAlert', parent.document).show();
			(function(Thunder){
				$('.newMatchBooksAlert', parent.document).css('padding-top',Thunder);
			})((parent.document.documentElement.clientHeight-$('.newMatchBooks', parent.document).get(0).offsetHeight)/2);
		
		}
	</script>
</body>
</html>
