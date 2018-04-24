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
	<title>书评管理（管理评论）</title>
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
	<div class="managementBookDe">
		<div class="bookManagementDeTop clearfix">
			<form action="<%=basePath%>discuss/goDiscuss.do" id="Form" name="Form" method="post">
			<input type="hidden" name="ARTICLE_ID" id="ARTICLE_ID" value="${pd.ARTICLE_ID }">
				<div class="labelManagementTopL clearfix">
					<div class="labelManagementTopL1 clearfix">
						<input type="text" name="names" id="names" value="${pd.names }"/>
						<p>输入用户昵称查询</p>
						<img src="static/read/images/myPic26.png" onclick="seachDiscusslist();" alt="" />
					</div>
					<div class="bookManagementTopL2 clearfix">
						<input id="sendtime" readonly="readonly" value="${pd.sendtime }"/>
						<img src="static/read/images/myPic13.png" alt="" />
					</div>
				</div>
			</form>
			<div class="managementReviewTopR"><a href="<%=basePath%>discuss/batchDiscuss_edit.do?ARTICLE_ID=${pd.ARTICLE_ID}"><span>批量编辑</span></a></div>
		</div>
		<div class="managementReviewTit clearfix">
			<span style="margin-left: 20px; width: 176px;">昵称</span>
			<span style="width: 135px;">平台ID</span>
			<span style="width: 466px;">评论内容</span>
			<span>评论时间</span>
		</div>
		<div class="managementReviewTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
				<li class="clearfix">
					<div class="managementReviewTex1">${var.NICKNAME }</div>
					<div class="managementReviewTex2">${var.USER_ID }</div>
					<div class="managementReviewTex3 clearfix">
						<p>${var.CONTENT }</p>
						<c:if test="${(fn:length(var.CONTENT))>57}">
							<span onclick="lookcontent('${var.DISCUSS_ID}')">查看</span>
						</c:if>
					</div>
					<div class="managementReviewTex4">${var.CREATE_TIME }</div>
					<img id="delDiscussid" src="static/read/images/myPic36.png" alt="${var.DISCUSS_ID }" title="删除评论" />
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
	
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript">
		function seachDiscusslist(){
			$('#Form').submit();
		}
		
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
		$(document).on('click','.managementReviewTex ul li img',function(){
			var discussid = $(this).attr('alt');
			$.get("<%=basePath%>discuss/delete.do?DISCUSS_ID="+discussid,function(data,status){
				location.reload();
			});
		});
	</script>
	<script>
		laydate.render({
		  elem: '#sendtime'
		  ,range: true
		});
		
		$('.labelManagementTopL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		});
		$('.labelManagementTopL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.labelManagementTopL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		});
		
		$('.labelManagementTopR2').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		});
		
		$(window).click(function(){
			$('.labelManagementTopR2 ul').hide();
		});
		
		
		
		$('.managementBookTexSe span').click(function(){
			var spanVal=$(this).text();
			$(this).siblings('.managementBookTexSeInp').show().find('input').val(spanVal);
			$(this).siblings('.managementBookTexSeInp').find('input').focus();
		});
		$('.managementBookTexSeInp input').blur(function(){
			var inpVal=$(this).val();
			$(this).parent().siblings('span').text(inpVal);
			$(this).parent('.managementBookTexSeInp').hide();
		});
			
	</script>
</body>
</html>
