﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>添加书籍（批量）</title>
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
		<a class="announcementManagementTop2" style="color: #999999;" href="bookManagement.html" title="">批量新增</a>
	</div>
	<form action="article/listtemp.do" method="post" name="Form" id="Form">
				<input type="hidden" id="allnums" name="allnums" value="${pd.allnums }"/>
				<input type="hidden" id="curnums" name="curnums" value="${pd.curnums }"/>
				
	</form>
	<div class="batchAddBooksDe">
		<div class="batchAddBooksFile">
			<h1>批量导入书籍文档</h1>
			<div class="batchAddBooksFileDe clearfix">
				<div class="batchAddBooksFileDeL clearfix">
					<c:choose>
					<c:when test="${not empty varList}">
						<p>共导入 <i>${pd.allnums }</i> 条 </p>
						<span>有效${pd.curnums }条</span>
					</c:when>
					</c:choose>
				</div>
				<div class="batchAddBooksFileDeR clearfix">
					
					<form id="formUpload" name="formUploadFile" action="article/uploadfile.do" method="POST" enctype="multipart/form-data">  
						<a class="batchAddBooksFileDeR1" href="<%=basePath%>uploadFiles/file/batchAddactile.xls" title=""><span>下载模板</span></a>
						<a class="batchAddBooksFileDeR2" style="position: relative;" href="javascript:void(0)" title=""><span>导入文档</span><input style="width:118px;height:34px;position: absolute;top:0;left:0; opacity: 0; filter:alpha(opacity:0);" name="file" id="file" type="file"/></a>
					</form>
				</div>
			</div>
		</div>
		<div class="batchAddBooksTit clearfix">
			<span style="margin-left: 21px; width: 48px;">序号</span>
			<span style="width: 112px;">书名</span>
			<span style="width: 112px;">作者</span>
			<span style="width: 77px;">付费类型</span>
			<span style="width: 157px;">付费方式</span>
			<span style="width: 77px;">是否热门</span>
			<span style="width: 88px;">书籍标签</span>
			<span style="width: 192px;">书籍简介</span>
			<span>管理操作</span>
		</div>
		<div class="batchAddBooksTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="batchAddBooksTex1"><p>${var.ARTICLE_CODE }</p></div>
						<div class="batchAddBooksTex2"><p>${var.ARTICLE_NAME }</p></div>
						<div class="batchAddBooksTex3"><p>${var.AUTHOR }</p></div>
						<div class="batchAddBooksTex4"><p>
							<c:if test="${var.FEE_TYPE == 0 }">
								免费
							</c:if>
							<c:if test="${var.FEE_TYPE == 1 }">
								付费
							</c:if>
						</p></div>
						<div class="batchAddBooksTex5"><p>
							<c:if test="${var.PAY_WAY == 0 }">
								阅读币
							</c:if>
							<c:if test="${var.PAY_WAY == 1 }">
								VIP免费
							</c:if>
						</p></div>
						<div class="batchAddBooksTex6"><p>
							<c:if test="${var.IS_HOT == 0 }">
								否
							</c:if>
							<c:if test="${var.IS_HOT == 1 }">
								是
							</c:if>
						</p></div>
						<div class="batchAddBooksTex7"><p>${var.SUMMARY }</p></div>
						<div class="batchAddBooksTex8"><p>${var.LABEL }</p></div>
						<div class="batchAddBooksTex9">
							<span>操作</span>
							<div>
								<a class="batchAddBooksTex9One" href="goEdit('${var.ARTICLE_IDTEMP}')" title="">编辑</a>
								<a href="javascript:delarticle('${var.ARTICLE_IDTEMP}')" title="">删除</a>
							</div>
						</div>
					</li>
				</c:forEach>
				</c:if>
				<c:if test="${QX.cha == 0 }">
					您无权查看
				</c:if>
				</c:when>
				<c:otherwise>
					没有相关数据
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div style="margin-left: 50px;" class="flipTwo clearfix">
			${page.pageStr}
		</div>
		<div class="batchAddBooksCover">
			<h1>批量导入书籍文档</h1>
			<div class="batchAddBooksCoverDe clearfix">
				<div class="batchAddBooksCoverDeL clearfix">
					<span>已导入<i> 50 </i>张</span>
					<p>有效 49 张</p>
					<small>注：请导入JPG格式的图片（90×120），且名称都设置为对应小说序号</small>
				</div>
				<a href="javascript:void(0)" title=""><span>导入封面</span></a>
			</div>
		</div>
		<div class="batchAddBooksCoverUpload">
			<ul class="clearfix">
				
			</ul>
		</div>
		<div class="batchAddBooksCoverBtnNone clearfix">
			<a class="batchAddBooksCoverBtnNone1" href="javascript:void(0)" title="">完&nbsp;&nbsp;成</a>
			<a class="batchAddBooksCoverBtnNone2" href="javascript:void(0)" title="">取&nbsp;&nbsp;消</a>
		</div>
		<div class="batchAddBooksCoverBtn clearfix">
			<a class="batchAddBooksCoverBtn1" href="javascript:void(0)" title="">完&nbsp;&nbsp;成</a>
			<a class="batchAddBooksCoverBtn2" href="javascript:void(0)" title="">取&nbsp;&nbsp;消</a>
		</div>
	</div>
	<script type="text/javascript">
		function delarticle(id){
			$.get("<%=basePath%>article/deletetemp.do?ARTICLE_IDTEMP="+id,function(data,status){
				if('${page.currentPage}' == '0'){
					 nextPage(1);
				 }else{
					 nextPage(${page.currentPage});
				 }
			});
		}
		$('#file').on('change', function()  
		{  
		    // 当 file 框内容改变则提交 form  
		    $('#formUpload').submit();  
		});  
	</script>
	<script>
		$('.batchAddBooksCoverDe a').click(function(){
			$('.batchAddBooksCoverBtnNone').hide();
			$('.batchAddBooksCoverBtn').show();
		})
		
		$(window).click(function(){
			$('.batchAddBooksTex9 div').hide();
		})
		
		$('.batchAddBooksTex9 span').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.batchAddBooksTex9 div').hide();
			e.stopPropagation();
		})
		$('.batchAddBooksTex9 div a').click(function(e){
			$('.batchAddBooksTex9 div').hide();
			e.stopPropagation();
		})
		
		$('.batchAddBooksTex9One').click(function(){
			$('.addBookBatchAlert', parent.document).show();
			(function(Thunder){
				$('.addBookBatchAlert', parent.document).css('padding-top',Thunder);
			})((parent.document.documentElement.clientHeight-$('.addBookBatchDe', parent.document).get(0).offsetHeight)/2);
		})
	</script>
</body>
</html>