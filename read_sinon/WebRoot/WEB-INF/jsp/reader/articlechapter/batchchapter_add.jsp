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
		<title>添加章节（批量章节）</title>
	    <base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">资源管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" href="chapterManagement.html" title="">书籍管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="addBatchSection.html" title="">批量新增</a>
	</div>
	<form action="<%=basePath%>articlechapter/batchChapteraAdd.do" method="post" id="ChapterForm" name="ChapterForm" enctype="multipart/form-data">
	<input type="hidden" id="CONTENT_URL" name="CONTENT_URL" value="${pd.CONTENT_URL }">
	<div class="batchAddBooksDe">
		<div class="batchAddBooksFile">
			<h1>批量导入书籍文档</h1>
			<div class="batchAddBooksFileDe clearfix">
				<div class="batchAddBooksFileDeL clearfix">
					<p>已导入 <i>${pd.articleNumber }</i> 本 </p>
					<span>有效书籍  ${pd.articleNumberY } 本</span>
				</div>
				<div class="AddBatchSectionR clearfix">
					<a class="AddBatchSectionR1" href="javascript:void(0)" title=""><span>查看规则</span></a>
					<a class="AddBatchSectionR2" style="position: relative;"  href="javascript:void(0)" title=""><span>导入文本</span><input style="width:118px;height:34px;position: absolute;top:0;left:0; opacity: 0; filter:alpha(opacity:0);" name="file" id="file" type="file" onchange="fun();"></a>
				</div>
			</div>
		</div>
		<div class="AddBatchSectionTit clearfix">
			<span style="margin-left: 20px; width: 84px;">书籍名称</span>
			<span style="width: 71px;">章节数</span>
			<span style="width: 84px;">总字数</span>
			<span style="width: 120px;">匹配状态</span>
			<span style="width: 283px;">首章</span>
			<span>最后一章</span>
		</div>
		<c:if test=" ${pd.articleNumberY > 0}">
			<div class="AddBatchSectionTex">
				<ul>
					<li class="clearfix">
						<div class="AddBatchSectionTex1">${pd.ARTICLE_NAME }</div>
						<div class="AddBatchSectionTex2">${pd.number }</div>
						<div class="AddBatchSectionTex3">${pd.booknumber }字</div>
						<div class="AddBatchSectionTex4">
							<c:if test=" ${pd.articleNumberY > 0}">
								匹配成功
							</c:if>
							<c:if test=" ${pd.articleNumberY == 0}">
								匹配失败
							</c:if>
						</div>
						<div class="AddBatchSectionTex5 clearfix">
							<p>${startpd.CHAPTER_NAME }</p>
							<a href="javascript:void(0)" title="">查看</a>
						</div>
						<div class="AddBatchSectionTex6 clearfix">
							<p>${endpd.CHAPTER_NAME }</p>
							<a href="javascript:void(0)" title="">查看</a>
							<img src="static/read/images/myPic36.png" alt="" />
						</div>
					</li>
				</ul>
			</div>
		</c:if>
		<div class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
		<div class="AddBatchSectionBtn clearfix">
			<a class="AddBatchSectionBtn1" href="<%=basePath%>articlechapter/overAdd.do?ARTICLE_ID=${pd.ARTICLE_ID }" title="">完&nbsp;&nbsp;成</a>
			<a class="AddBatchSectionBtn2" href="javascript:void(0)" title="">取&nbsp;&nbsp;消</a>
		</div>
		
	</div>
	</form>
	<script type="text/javascript">
		function fun(){
			$("#ChapterForm").submit();
		}
	</script>
	<script>
		$('.AddBatchSectionTex6 img').click(function(){
			$(this).parents('li').remove();
		});
		
		$('.AddBatchSectionTex5 a,.AddBatchSectionTex6 a').click(function(){
			$('.seMatchBooksAlert', parent.document).show();
			(function(Thunder){
				$('.seMatchBooksAlert', parent.document).css('padding-top',Thunder);
			})((parent.document.documentElement.clientHeight-$('.seMatchBooks', parent.document).get(0).offsetHeight)/2);
		});
	</script>
</body>
</html>