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
	<title>充值记录</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="channelRechargeRecordAll">
		<div class="rechargeManagementDeTop clearfix">
			<div>
				<h2>累计充值</h2>
				<h1>${pdCount.recharges }</h1>
				<p>笔数：${pdCount.rechargeTimes }</p>
			</div>
			<div>
				<h2>当月充值</h2>
				<h1>${pdMonth.recharges }</h1>
				<p>笔数：${pdMonth.rechargeTimes }</p>
			</div>
			<div>
				<h2>今日充值</h2>
				<h1>${pdDay.recharges }</h1>
				<p>笔数：${pdDay.rechargeTimes }</p>
			</div>
			<div>
				<h2>昨日充值</h2>
				<h1>${pdYesterday.recharges }</h1>
				<p>笔数：${pdYesterday.rechargeTimes }</p>
			</div>
		</div>
		<form action="<%=basePath%>rechargelog/sysUserReadReechargeLog.do" method="post" id="sysForm" name="sysForm">
		<input type="hidden" name="USERID" id="USERID" value="${pd.USERID}">
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL2 clearfix" style="margin-left:30px">
					<input id="sendtime" name="sendtime" value="${pd.sendtime}" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="日期/时间"/>
				</div>
				<div class="rechargeRecordSel clearfix">
					<p id="RECHARGE_TYPE"><c:if test="${pd.RECHARGE_TYPE == 0 }">普通充值</c:if><c:if test="${pd.RECHARGE_TYPE == 1 }">VIP充值</c:if><c:if test="${pd.RECHARGE_TYPE == null }">全部类型</c:if></p>
					<img src="static/read/images/myPic13.png" alt="状态查询"/>
					<ul>
						<li  onclick="readReechangeSubmint();">全部类型</li>
						<li onclick="readReechangeSubmint('0');">普通充值</li>
						<li onclick="readReechangeSubmint('1');">VIP充值</li>
					</ul>
				</div>
			</div>
			<a href="javascript:excelOut()" title="导出excel表"><span>导出列表</span></a>
		</div>
		<div class="channelRechargeRecordTit clearfix">
			<span style="width: 150px; margin-left: 19px;">充值日期</span>
			<span style="width: 110px;">VIP状态</span>
			<span style="width: 115px;">充值类型</span>
			<span style="width: 97px;">充值额度</span>
			<span style="width: 92px;">有效期</span>
			<span style="width: 96px;">充值金额</span>
			<span style="width: 109px;">获得阅读币</span>
			<span style="width: 96px;">平台收益</span>
			<span>累计充值</span>
		</div>
		<div class="channelRechargeRecordTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var">
				<li class="clearfix">
					<div class="channelRechargeRecordTex1">${var.CREATE_TIME }</div>
					<div class="channelRechargeRecordTex2">
						<c:if test="${var.ISVIP  == 0 }">
							普通会员
						</c:if>
						<c:if test="${var.ISVIP  == 1 }">
							VIP会员
						</c:if>
					</div>
					<div class="channelRechargeRecordTex3">
						<c:if test="${var.RECHARGE_TYPE  == 0 }">
							普通充值
						</c:if>
						<c:if test="${var.RECHARGE_TYPE  == 1 }">
							VIP充值
						</c:if>
					</div>
					<div class="channelRechargeRecordTex4">-------</div>
					<div class="channelRechargeRecordTex5">${var.EXP_DATE }</div>
					<div class="channelRechargeRecordTex6">${var.MONEY }</div>
					<div class="channelRechargeRecordTex7">${var.BOOK_CURRENCY_ALL }</div>
					<div class="channelRechargeRecordTex8">${var.INCOME }</div>
					<div class="channelRechargeRecordTex9">${var.RECHARGE_MONEY }</div>
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
		</form>
	</div>
	
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript">
		function readReechangeSubmint(data){
			if(data==null){
				data="";
			}
	   		$("#sysForm").attr("action","<%=basePath%>rechargelog/sysUserReadReechargeLog.do?RECHARGE_TYPE="+data);
			$("#sysForm").submit();
		}
		function excelOut(){
			var TYPE = $("#RECHARGE_TYPE").text();
			var RECHARGE_TYPE = "";
			if(TYPE=="普通充值"){
				RECHARGE_TYPE = 0; 
			}else if(TYPE=="VIP充值"){
				RECHARGE_TYPE = 1; 
			}
			var sendtime = $("#sendtime").val();
			var USERID = $("#USERID").val();
			window.location.href='<%=basePath%>rechargelog/sysUserReadReechargeExcel.do?RECHARGE_TYPE='+RECHARGE_TYPE+'&sendtime='+sendtime+'&USERID='+USERID;
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
		
		$('.rechargeRecordSel').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.rechargeRecordSel ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$('.rechargeRecordSel p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.rechargeRecordSel').find('ul').hide();
		})
	</script>
</body>
</html>