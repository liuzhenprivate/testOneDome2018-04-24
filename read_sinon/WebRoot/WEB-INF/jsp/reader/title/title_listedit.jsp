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
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">标题库</a>
	</div>
	<div class="titleLibraryBatchDe">
		<div class="titleLibraryBatchTop clearfix">
			<span onclick="deltitle()">删除</span>
			<a href="title/list.do" title=""><i>完成编辑</i></a>
		</div>
		<div class="titleLibraryBatchTitle clearfix">
			<div class="titleLibraryBatchTitle1 clearfix"><span><i></i></span></div>
			<div class="titleLibraryBatchTitle2">频道</div>
			<div class="titleLibraryBatchTitle3">标题内容</div>
			<div class="titleLibraryBatchTitle4">添加时间</div>
		</div>
		<input type="hidden" id="TITLE_ID" name="TITLE_ID" value=""/> 
		<input type="hidden" id="ALLTITLE_ID" name="ALLTITLE_ID" value=""/>
		<div class="titleLibraryBatchTex">
			<ul>
			<c:forEach items="${varList}" var="var" varStatus="vs">
			<script>
			var tid = '${var.TITLE_ID }';
			var ALLTITLE_ID = $("#ALLTITLE_ID").val();
			$("#ALLTITLE_ID").val(ALLTITLE_ID+","+tid+",");
			</script>
				<li class="clearfix" value="${var.TITLE_ID }">
					<div class="titleLibraryBatchTex1 clearfix"><span><i value="${var.TITLE_ID }"></i></span></div>
					<div class="titleLibraryBatchTex2">${var.CATEGORY_NAME }</div>
					<div class="titleLibraryBatchTex3 clearfix">
						<p>${var.CONTENT }</p>
						<span>查看</span>
					</div>
					<div class="titleLibraryBatchTex4">${var.CREATE_TIME }</div>
				</li>
			</c:forEach>
				
			</ul>
		</div>
		<div style="margin-left: 30px;" class="flipTwo clearfix">
			${page.pageStr}
		</div>
	</div>
	
	<script>
		function deltitle(){
			var TITLE_IDS = $("#TITLE_ID").val();
			//alert(TITLE_IDS);
			var reg=/undefined/g;
			TITLE_IDS=TITLE_IDS.replace(reg,'');
			//alert(TITLE_IDS);
			if(''==TITLE_IDS){
				alert("请选择要删除的标题");
				return false;
			}else{
				$.ajax({
					type: "POST",
					url: '<%=basePath%>title/deleteAll.do?tm='+new Date().getTime(),
			    	data: {DATA_IDS:TITLE_IDS},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){
						 $.each(data.list, function(i, list){
							 location.reload();
						 });
					}
				});
			}
		}
		$('.titleLibraryBatchTitle1 span i').click(function(){
			if($(this).hasClass('titleLibraryBatchTitleAct')){
				 $("#TITLE_ID").val("");
				$(this).removeClass('titleLibraryBatchTitleAct');
				$('.titleLibraryBatchTex1 span i').removeClass('titleLibraryBatchTexAct');
			}else{
				var ALLTITLE_ID = $("#ALLTITLE_ID").val();
				 $("#TITLE_ID").val(ALLTITLE_ID);
				$(this).addClass('titleLibraryBatchTitleAct');
				$('.titleLibraryBatchTex1 span i').addClass('titleLibraryBatchTexAct');
			}
		})
		
		$('.titleLibraryBatchTex1 span i').click(function(){
			var TITLE_ID =$(this).attr('value');
			//alert(TITLE_ID);
			var TITLE_IDS = $("#TITLE_ID").val();
			if($(this).hasClass('titleLibraryBatchTexAct')){
				TITLE_IDS=TITLE_IDS.replace(","+TITLE_ID+",");
				$(this).removeClass('titleLibraryBatchTexAct');
				$('.titleLibraryBatchTitle1 span i').removeClass('titleLibraryBatchTitleAct');
			}else{
				TITLE_IDS=TITLE_IDS+","+TITLE_ID+",";
				$(this).addClass('titleLibraryBatchTexAct');
			}
			$("#TITLE_ID").val(TITLE_IDS);
		})
	</script>
</body>
</html>



