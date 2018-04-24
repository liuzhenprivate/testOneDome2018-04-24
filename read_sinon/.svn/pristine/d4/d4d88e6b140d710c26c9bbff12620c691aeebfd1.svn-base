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
	<title>签到记录</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="channelRechargeRecordAll">
		<div class="rechargeManagementDeTop clearfix">
			<div>
					<h2>累计签到阅读币</h2>
					<h1>${pdCount.BOOK_CURRENCYS }</h1>
					<p>次数：${pdCount.SIGN_LOG_ID }</p>
				</div>
				<div>
					<h2>当月签到阅读币</h2>
					<h1>${pdMonth.BOOK_CURRENCYS }</h1>
					<p>次数：${pdMonth.SIGN_LOG_ID }</p>
				</div>
				<div>
					<h2>今日签到</h2>
					<h1>
						<c:if test="${pdDay == null }">
							未签到
						</c:if>
						<c:if test="${pdDay != null }">
							已签到
							<p>签到渠道：${pdDay.NAME }</p>
						</c:if>
					</h1>
				</div>
				<div>
					<h2>
						当前渠道
					</h2>
					<h1>
						<c:choose>
						<c:when test="${not empty wxvarList}">
						<c:forEach items="${wxvarList}" var="var">
							${var.NAME }&nbsp;&nbsp;
						</c:forEach>
						</c:when>
						<c:otherwise>
							没有相关数据
						</c:otherwise>
						</c:choose>
					</h1>
				</div>
		</div>
		<form action="<%=basePath%>singlog/sysUserReadSignLog.do" method="post" id="sysForm" name="sysForm">
		<input type="hidden" name="USERID" id="USERID" value="${pd.USERID}">
		<div class="rechargeManagementDeSel clearfix">
			<div style="margin-left: 20px;" class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL2 clearfix">
					<input name="sendtime" id="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
			</div>
			<a href="javascript:excelOut()" title="导出excel表"><span>导出列表</span></a>
		</div>
		<div class="channelSignTit clearfix">
			<span style="width: 426px; margin-left: 19px;">签到日期</span>
			<span style="width: 438px;">签到阅读币</span>
			<span>签到累计</span>
		</div>
		<div class="channelSignTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var">
					<li class="clearfix">
						<div class="channelSignTex1">${var.CREATE_TIME }</div>
						<div class="channelSignTex2">${var.BOOK_CURRENCY }</div>
						<div class="channelSignTex3">${var.SBC }</div>
					</li>
				<</c:forEach>
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
			</form>
	</div>
	
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
			function readSingSubmint(){
		   		$("#sysForm").attr("action","<%=basePath%>singlog/sysUserReadSignLog.do");
				$("#sysForm").submit();
			}
			function excelOut(){
				var sendtime = $("#sendtime").val();
				var USERID = $("#USERID").val();
				window.location.href='<%=basePath%>singlog/sysUserReadSingExcel.do?sendtime='+sendtime+'&USERID='+USERID;
			}
		</script>
	<script>
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
