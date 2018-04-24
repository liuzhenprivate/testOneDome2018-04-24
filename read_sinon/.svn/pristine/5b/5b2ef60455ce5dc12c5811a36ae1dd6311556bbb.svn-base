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
<title>通知公告</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/otherPages.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">通知公告</a>
	</div>
	<div class="noticeBulletinDe">
		<div class="noticeBulletinDeTop clearfix">
			<div class="clearfix">
				<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
				<img src="static/readchannel/images/myPic13.png" alt="" />
			</div>
		</div>
		<div class="noticeBulletinDeTit clearfix">
			<span style="width: 300px; margin-left: 19px;">标题</span>
			<span style="width: 511px;">内容</span>
			<span>推送日期</span>
		</div>
		<div class="noticeBulletinDeTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<p style="width: 300px; margin-left: 19px;">${var.TITLE }</p>
						<div style="width: 511px;" class="clearfix">
							<p>${var.CONTENT }</p>
							<c:if test="${(fn:length(var.CONTENT))>50}">
								<span onclick="lookcontent('${var.NOTICELOG_ID}')">展开</span>
							</c:if>
						</div>
						<p>${var.SEND_TIME }</p>
					</li>
				</c:forEach>
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
	<script src="static/readchannel/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
		function lookcontent(id){
			var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="公告内容";
			 diag.URL =  '<%=basePath%>noticelog/goLook.do?NOTICELOG_ID='+id;
			 diag.Width = 560;
			 diag.Height = 356;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 diag.close();
				}
				diag.close();
			 };
			 diag.show();
		}
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("Thunder"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		laydate.render({
		  elem: '#sendtime'
		  ,range: true
		});
		
	</script>
</body>
</html>
