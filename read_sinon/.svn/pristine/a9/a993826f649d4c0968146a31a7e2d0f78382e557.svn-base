<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<title>书评管理（管理评论）批量</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/moduleConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">模板配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop1" href="<%=basePath%>discuss/list.do" title="">书评管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">管理评论</a>
	</div>
	<div class="reviewManagementBatchDe">
		<div class="reviewManagementBatchDeT clearfix">
			<span id="batchDel">删除</span>
			<a href="<%=basePath%>discuss/goDiscuss.do?ARTICLE_ID=${pd.ARTICLE_ID}" title="完成编辑"><i>完成编辑</i></a>
		</div>
		<div class="reviewManagementBatchDeTit clearfix">
			<span><i></i></span>
			<div style="width: 156px; margin-left: 21px;">昵称</div>
			<div style="width: 155px;">平台ID</div>
			<div style="width: 477px;">评论内容</div>
			<div>评论时间</div>
		</div>
		<div class="reviewManagementBatchDeTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<a><span title="${var.DISCUSS_ID }"></span></a>
						<div class="reviewManagementBatchDeTex2">${var.NICKNAME }</div>
						<div class="reviewManagementBatchDeTex3">暂无</div>
						<div class="reviewManagementBatchDeTex4 clearfix">
							<p>${var.CONTENT }</p>
							<c:if test="${(fn:length(var.CONTENT))>17}">
								<span onclick="lookcontent('${var.DISCUSS_ID}')">查看</span>
							</c:if>
						</div>
						<div class="reviewManagementBatchDeTex5">${var.CREATE_TIME }</div>
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
		<div class="flipTwo clearfix">
			${page.pageStr}
		</div>
	</div>
	<script type="text/javascript">
		
		$('#batchDel').click(function(){
			var DISCUSS_ID = "";
			$(".reviewManagementBatchDeTexAct").each(function(n,v){
				DISCUSS_ID = DISCUSS_ID + v.title+",";
			});
			if(DISCUSS_ID!=""){
				$.get("<%=basePath%>discuss/batchDelete.do?DISCUSS_ID="+DISCUSS_ID,function(data,status){
					location.reload();
				});
			}
		});
	
	
		function lookcontent(id){
			var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="评论内容";
			 diag.URL =  '<%=basePath%>discuss/goLook.do?DISCUSS_ID='+id;
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
	</script>
	<script>
		$('.reviewManagementBatchDeTit span i').click(function(){
			if($(this).hasClass('reviewManagementBatchDeTitAct')){
				$(this).removeClass('reviewManagementBatchDeTitAct');
				$('.reviewManagementBatchDeTex ul li a span').removeClass('reviewManagementBatchDeTexAct');
			}else{
				$(this).addClass('reviewManagementBatchDeTitAct');
				$('.reviewManagementBatchDeTex ul li a span').addClass('reviewManagementBatchDeTexAct');
			}
		})
		$('.reviewManagementBatchDeTex ul li a span').click(function(){
			if($(this).hasClass('reviewManagementBatchDeTexAct')){
				$(this).removeClass('reviewManagementBatchDeTexAct');
				$('.reviewManagementBatchDeTit span i').removeClass('reviewManagementBatchDeTitAct');
			}else{
				$(this).addClass('reviewManagementBatchDeTexAct');
			}
		})
	</script>
</body>
</html>
