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
	<title>数据统计月报</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/otherPages.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<form action="<%=basePath%>channelrechargelog/goStatisticsMonth.do" method="post" id="Form" name="Form">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">数据统计</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">充值统计</a>
	</div>
	<div class="dataStatisticsDe">
		<div class="dataStatisticsDeTop clearfix">
			<a href="<%=basePath%>channelrechargelog/goStatisticsDay.do" title="">日统计</a>
			<a class="dataStatisticsDeTopAct" href="<%=basePath%>channelrechargelog/goStatisticsMonth.do" title="">月统计</a>
		</div>
		<div class="dataStatisticsDailyDe">
			<div class="rechargeManagementDeSel clearfix">
				<div class="rechargeManagementDeSelL clearfix">
					<div style="margin-left: 30px;" class="rechargeManagementDeSelL2 clearfix">
						<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
						<img src="static/readchannel/images/myPic13.png" alt="" />
					</div>
				</div>
				<a href="javascript:excelOut()" title="导出excel表"><span>导出列表</span></a>
			</div>
			<div class="monthlyDataStatisticsTit clearfix">
				<span style="margin-left: 19px; width: 236px;">时间</span>
				<span style="width: 214px;">充值人数</span>
				<span style="width: 216px;">充值笔数</span>
				<span style="width: 207px;">充值金额</span>
				<span>总累计</span>
			</div>
			<div class="monthlyDataStatisticsTex">
				<ul>
					<c:choose>
					<c:when test="${not empty varList}">
					<c:forEach items="${varList}" var="var" varStatus="vs">
						<li class="clearfix">
							<p class="monthlyDataStatisticsTex1">${var.COUNT_MONTH }</p>
							<p class="monthlyDataStatisticsTex2">${var.RECHARGEUSERS }</p>
							<p class="monthlyDataStatisticsTex3">${var.RECHARGETIMES }</p>
							<p class="monthlyDataStatisticsTex4">${var.RECHARGES }</p>
							<p>${var.toMonthSum }</p>
						</li>
					</c:forEach>
					</c:when>
					<c:otherwise>
						没有相关数据
					</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<div class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
	</div>
	</form>
	<script src="static/readchannel/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript">
		function excelOut(){
			var sendtime = $("#sendtime").val();
			window.location.href='<%=basePath%>channelrechargelog/goStatisticsMonthExcel.do?sendtime='+sendtime;
		}
	</script>
	<script>
		$('.dataStatisticsDeTop a').click(function(){
			$(this).addClass('dataStatisticsDeTopAct').siblings().removeClass('dataStatisticsDeTopAct');
		})
		laydate.render({
	    	elem: '#sendtime'
	    	,type: 'month'
	    	,range: true
	  	});
		
	</script>
</body>
</html>