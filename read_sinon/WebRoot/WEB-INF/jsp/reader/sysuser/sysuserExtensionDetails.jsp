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
	<base href="<%=basePath%>">
	<title>推广统计</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<form action="<%=basePath%>extensioncontent/sysuserExtensionDetails.do" method="post" id="eForm" name="eForm">
	<div class="recordsConsumptionAll">
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names }" />
					<p>输入书籍或推广标题搜索</p>
					<img src="static/read/images/myPic26.png" alt="" onclick="seachSubmint('${pd.USER_ID}');"/>
				</div>
				<div class="rechargeManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
			</div>
		</div>
		<div class="extensionStatisticsTit clearfix">
			<span style="width: 171px; margin-left: 18px;">推广渠道名称</span>
			<span style="width: 161px;">创建时间</span>
			<span style="width: 265px;">入口页面</span>
			<span style="width: 133px;">用户</span>
			<span style="width: 135px;">充值</span>
			<span>收益</span>
		</div>
		<div class="extensionStatisticsTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
				<li class="clearfix">
					<div class="extensionStatisticsTex1">
						<p>${var.NAME }</p>
					</div>
					<div class="extensionStatisticsTex2">${var.CREATE_TIME }</div>
					<div class="extensionStatisticsTex3 clearfix">
						<img src="${var.COVER }" alt="" />
						<div>
							<p><span>书名：</span>${var.ARTICLE_NAME }</p>
							<p><span>章节：</span>${var.ARTICLE_CHAPTERS }</p>
							<p>${var.FORCE_CHAPTER }</p>
						</div>
					</div>
					<div class="extensionStatisticsTex4">
						<p><span>关&nbsp;&nbsp;&nbsp;注：</span>${var.FOLLOW }</p>
						<p><span>引&nbsp;&nbsp;&nbsp;导：</span>${var.GUIDE }</p>
						<p><span>关注率：</span>${var.FOLLOWGUIDE }%</p>
					</div>
					<div class="extensionStatisticsTex5">
						<p><span>充值金额：</span>${var.RECHARGE }</p>
						<p><span>笔&nbsp;&nbsp;&nbsp;数：</span>${var.RECHARGE_TIMES }</p>
						<p><span>充值率：</span>${var.RECHARGEFOLLOW }%</p>
					</div>
					<div class="extensionStatisticsTex6">
						<p>收益：<span>${var.PROFIT }</span></p>
					</div>
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
	</form>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
		function seachSubmint(USER_ID){
	   		$("#eForm").attr("action","<%=basePath%>extensioncontent/sysuserExtensionDetails.do?USER_ID="+USER_ID);
			$("#eForm").submit();
		}
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("faker"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		laydate.render({
		  elem: '#sendtime'
		  ,range: true
		});
		$('.rechargeManagementDeSelL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.rechargeManagementDeSelL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.rechargeManagementDeSelL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
	</script>
</body>
</html>
