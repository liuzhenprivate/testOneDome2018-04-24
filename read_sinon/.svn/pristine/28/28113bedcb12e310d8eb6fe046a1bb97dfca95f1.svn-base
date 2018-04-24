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
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/readerManagement.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="rechargeRecordAll">
		<div class="rechargeRecordTop clearfix">
			<div>
				<h3>累计签到阅读币</h3>
				<h2>${pdCount.BOOK_CURRENCYS }</h2>
				<p>次数：${pdCount.SIGN_LOG_ID }</p>
			</div>
			<div>
				<h3>当月签到阅读币</h3>
				<h2>${pdMonth.BOOK_CURRENCYS }</h2>
				<p>次数：${pdMonth.SIGN_LOG_ID }</p>
			</div>
			<div>
				<h3>今日签到</h3>
					<h2>
						<c:if test="${pdDay == null }">
							未签到
						</c:if>
						<c:if test="${pdDay != null }">
							已签到
							<%-- <p>签到渠道：${pdDay.NAME }</p> --%>
						</c:if>
					</h2>
			</div>
			<div>
				<h3>月平均签到</h3>
				<h2>${pdMonth.SIGN_LOG_ID }次</h2>
			</div>
			<span style="left: 240px;"></span>
			<span style="left: 480px;"></span>
			<span style="left: 720px;"></span>
		</div>
		<form action="<%=basePath%>singlog/channelReadSignLog.do" method="post" id="sysForm" name="sysForm">
		<input type="hidden" name="USERID" id="USERID" value="${pd.USERID}">
		<div class="readerManagementDeSel clearfix">
			<div class="readerManagementDeSelL clearfix">
				<div class="readerManagementDeSelL2 clearfix">
						<input name="sendtime" id="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/readchannel/images/myPic13.png" alt="" />
				</div>
			</div>
			<a href="javascript:excelOut()" title="导出excel表"><span>导出列表</span></a>
		</div>
		<div class="rechargeRecordTit clearfix">
			<span style="width: 426px; margin-left: 19px;">签到日期</span>
			<span style="width: 400px;">签到阅读币</span>
			<span>签到累计</span>
		</div>
		<div class="signRecordTex clearfix">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="signRecordTex1">${var.CREATE_TIME }</div>
						<div class="signRecordTex2">${var.BOOK_CURRENCY }</div>
						<div class="signRecordTex3">${var.SBC }</div>
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
	<script src="static/readchannel/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
		function readSingSubmint(){
	   		$("#sysForm").attr("action","<%=basePath%>singlog/channelReadSignLog.do");
			$("#sysForm").submit();
		}
		function excelOut(){
			var sendtime = $("#sendtime").val();
			var USERID = $("#USERID").val();
			window.location.href='<%=basePath%>singlog/channelReadSingExcel.do?sendtime='+sendtime+'&USERID='+USERID;
		}
	</script>
	<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("faker"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		$('.readerDetailsDeTop span').click(function(){
			$(this).addClass('readerDetailsDeTopAct').siblings().removeClass('readerDetailsDeTopAct');
		})
		laydate.render({
		  elem: '#sendtime'
		  ,range: true
		});
		$('.readerManagementDeSelL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.readerManagementDeSelL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.readerManagementDeSelL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
		
		
		function srcTo(id){
			var src=$('#'+id).attr('name');
			$('.readerDetailsDeTab iframe').attr('src',src);
		}
		function srcIframe() {           
		     var iframe = document.getElementById("readerDetails");           
		     try {               
		            var bHeight =iframe.contentWindow.document.body.scrollHeight;               
		            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;              
		            var height = Math.max(bHeight, dHeight);            
		            iframe.height = height;
		        } catch (ex) { }       
		}
		window.setInterval("srcIframe()", 100);
	</script>
</body>
</html>
