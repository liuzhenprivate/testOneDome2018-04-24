<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>模板配置标题库（标题库批量）</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	 
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">模板配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop1" href="titleLibrary.html" title="">标题库</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">批量导入</a>
	</div>
	<form action="title/listtemp.do" method="post" name="Form" id="Form">
				<input type="hidden" id="allnums" name="allnums" value="${pd.allnums }"/>
				<input type="hidden" id="curnums" name="curnums" value="${pd.curnums }"/>
				
	</form>
	<div class="newTitleBatchDe">
		<h1>批量导入标题文档</h1>
		<div class="newTitleBatchDeTop clearfix">
			<div class="newTitleBatchDeTopL clearfix">
				<c:choose>
				<c:when test="${not empty varList}">
				<p>已导入 <i>${pd.allnums }</i> 条 </p>
				<span>有效${pd.curnums }条</span>
				</c:when>
				</c:choose>
			</div>
			<div class="newTitleBatchDeTopR clearfix">
				<a class="newTitleBatchDeTopR1" href="static/file/title.xls" title=""><span>下载模板</span></a>
				 <form id="formUpload" name="formUploadFile" action="title/uploadfile.do" method="POST" enctype="multipart/form-data">  
				<a class="newTitleBatchDeTopR2" href="javascript:void(0)" title=""><input type="file" id="file" name="file"/><span>导入文本</span></a>
				</form>
			</div>
		</div>
		<c:choose>
				<c:when test="${not empty varList}">
				
		<div class="newTitleBatchDeTitle clearfix">
			<span style="width: 168px; margin-left: 20px;">频道</span>
			<span style="width: 696px;">标题内容</span>
			<span>管理操作</span>
		</div>
		<div class="newTitleBatchDeTex">
			<ul>
			<c:forEach items="${varList}" var="var" varStatus="vs">
				<c:choose>
					<c:when test="${(vs.index+1)%2==0}">
					<li style="background: #fafafa;" class="clearfix">
					</c:when>
					<c:otherwise>
					<li class="clearfix">
					</c:otherwise>
				</c:choose>
					<div class="newTitleBatchDeTex1">${var.CATEGORY_NAME }</div>
					<div class="newTitleBatchDeTex2 clearfix">
						<p>${var.CONTENT }</p>
						<span onclick="looktitlecontent('${var.TITLE_ID}')">查看</span>
					</div>
					<div class="newTitleBatchDeTex3">
						<span>操作</span>
						<div class="newTitleBatchDeTex3Div">
							<a onclick="goEdit('${var.TITLE_ID}')"  >修改</a>
							 <a onclick="deltitle('${var.TITLE_ID}')">删除</a>
						</div>
					</div>
				</li>
			</c:forEach>
				 
			</ul>
		</div>
		<div style="margin-left: 50px;" class="flipTwo clearfix">
			${page.pageStr}
		</div>
		<div class="newTitleBatchDeBot clearfix">
			<a href="<%=basePath%>title/addalltemp.do" title="">完&nbsp;&nbsp;成</a>
			<span><a href="<%=basePath%>title/deletealltemp.do" title="">取&nbsp;&nbsp;消</a></span>
		</div>
		</c:when>
		</c:choose>
	</div>
	
	<script>
	function deltitle(id){
		$.get("<%=basePath%>title/deletetemp.do?TITLE_ID="+id,function(data,status){
			if('${page.currentPage}' == '0'){
				 nextPage(1);
			 }else{
				 nextPage(${page.currentPage});
			 }
		});
	}
	function delall(){
		$.get("<%=basePath%>title/deletealltemp.do",function(data,status){
			if('${page.currentPage}' == '0'){
				 nextPage(1);
			 }else{
				 nextPage(${page.currentPage});
			 }
		});
	}
	function goEdit(id){
		var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="编辑标题";
		 diag.URL =  '<%=basePath%>title/goEdittemp.do?TITLE_ID='+id;
		 diag.Width = 560;
		 diag.Height = 246;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 if('${page.currentPage}' == '0'){
					 nextPage(1);
				 }else{
					 nextPage(${page.currentPage});
				 }
			}
			diag.close();
		 };
		 diag.show();
	}
	function looktitlecontent(id){
		var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="标题内容";
		 diag.URL =  '<%=basePath%>title/goLooktemp.do?TITLE_ID='+id;
		 diag.Width = 560;
		 diag.Height = 364;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 diag.close();
			}
			diag.close();
		 };
		 diag.show();
	}
	 // form 内的文件选择内容被改变则立即提交  
	  $('#file').on('change', function()  
	  {  
	    // 当 file 框内容改变则提交 form  
	    $('#formUpload').submit();  
	   // alert('formUpload to submit');  
	  });  
	 
		$(window).click(function(){
			$('.newTitleBatchDeTex3 div').hide();
		})
		$('.newTitleBatchDeTex3 span').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.newTitleBatchDeTex3Div').hide();
			e.stopPropagation();
		})
		$('.newTitleBatchDeTex3 div a').click(function(e){
			$(this).parent('div').hide();
			e.stopPropagation();
		})
		
		$('.newTitleBatchDeTex2 span').click(function(){
			$('.titleLibraryViewAlert', parent.document).show();
			(function(Thunder){
				$('.titleLibraryViewAlert', parent.document).css('padding-top',Thunder);
			})((parent.document.documentElement.clientHeight-$('.titleLibraryViewDe', parent.document).get(0).offsetHeight)/2);
		})
	</script>
</body>
</html>



