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
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">资源管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" href="javascript:history.go(-1);" title="">章节管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="newChapter.html" title="">章节新增</a>
	</div>
	<form action="<%=basePath%>articlechapter/articleChapteradd.do"  id="ChapterForm" name="ChapterForm" method="post">
	<div class="newChapterDe">
		<h1>新增章节</h1>
		<input type="hidden" name="ARTICLE_ID" id="ARTICLE_ID" value=""/>
		<div class="newChapterDeInp clearfix">
			<span>关联书籍</span>
			<div class="clearfix">
				<input name="ARTICLE_NAME" id="ARTICLE_NAME" value="" placeholder="请输入书籍名进行匹配，此处必须要点击匹配书籍"/>
				<a href="javascript:matchingBook()" title="">匹配书籍</a>
			</div>
			<p id="ARTICLE_NAMEP">暂无关联书籍</p>
		</div>
		<div class="newChapterDeInp clearfix">
			<span>章节序号</span>
			<div class="clearfix">
				<input name="CHAPTER_NO" id="CHAPTER_NO" value="" placeholder="请输入序号，若为空则自动设置序号为最后一章"/>
			</div>
		</div>
		<div class="newChapterDeInp clearfix">
			<span>章节名称</span>
			<div class="clearfix">
				<input name="CHAPTER_NAME" id="CHAPTER_NAME" value="" placeholder="请输入章节名称"/>
			</div>
		</div>
		<div class="newChapterDeTex clearfix">
			<span>章节内容</span>
			<div class="clearfix">
				
				<textarea id="chaptercontent" name="chaptercontent" onKeyUp="contentchange();" placeholder="请输入章节内容"></textarea>
			</div>
			共<i id="numberWord">0</i>字
		</div>
		<div style="margin-left: 76px;" class="AddBatchSectionBtn clearfix">
			<a class="AddBatchSectionBtn1" href="javascript:articleChapteradd()" title="">完成</a>
			<a class="AddBatchSectionBtn2" href="javascript:history.go(-1);" title="">取消</a>
		</div>
	</div>
	</form>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$('#CHAPTER_NO').blur(function(){
			var no = $('#CHAPTER_NO').val();
			var reg =/^([1-9]{1,3}(.\d{2})?|1000(.[0]{2})?)$/;
			if (reg.test(no) != true) {
				$('#CHAPTER_NO').val('');
			}
		});
	</script>
	<script>
		function articleChapteradd(){
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
			var a = $('.AddBatchSectionBtn1').text();
			var textlen = $('#chaptercontent').val();
			if(a=="完成"){
				$('.AddBatchSectionBtn1').text('提交中...');
				$("#ChapterForm").attr("action","<%=basePath%>articlechapter/articleChapteradd.do?NUMBER_CHAPTER="+textlen.length);
				$("#ChapterForm").submit();
			}
		}
		
		function contentchange(){
			var chaptercontent = $("#chaptercontent").val();
			$("#numberWord").text(chaptercontent.length);
			$("#NUMBER_CHAPTER").val(chaptercontent.length);
		}
	
		function chapterAddtxt(name,num,id){
			$("#ARTICLE_ID").val(id);
			$("#ARTICLE_NAME").val(name);
			$("#ARTICLE_NAMEP").text("匹配成功  （当前共"+num+"章）");
		}
		//匹配书籍
		function matchingBook(){
			var ARTICLE_NAME = $("#ARTICLE_NAME").val();
			var num = 0;
			$("#ARTICLE_NAME").val("");
			$("#ARTICLE_NAMEP").text("暂无关联书籍");
			if(""!=ARTICLE_NAME&&null!=ARTICLE_NAME){
				$.ajax({
					type: "post",
					data:{
						"ARTICLE_NAME":ARTICLE_NAME
					},
					url: "<%=basePath%>article/matchingBook.do",
					success: function(data){
						if(data!=null&&data!=""){
							$.each(data, function(index, item){
								num++;
								$("#ARTICLE_NAME").val("");
								$("#ARTICLE_NAMEP").text("暂无关联书籍");
					       		$("#ARTICLE_NAME").val(item.ARTICLE_NAME);
					       		$("#ARTICLE_NAMEP").text("匹配成功  （当前共"+item.COUNT_CHAPTERS+"章）");
					       		$("#ARTICLE_ID").val(item.ARTICLE_ID);
					        });
					       if(num>1){
								newChapter(ARTICLE_NAME);
							}
						}
					}
				});
			}
		}
	</script>
	<script>
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
