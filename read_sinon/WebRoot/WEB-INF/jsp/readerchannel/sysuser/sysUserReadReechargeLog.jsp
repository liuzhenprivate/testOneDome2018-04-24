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
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/readerManagement.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="rechargeRecordAll">
		<div class="rechargeRecordTop clearfix">
			<div>
				<h2>累计充值</h2>
				<h3>${pdCount.recharges }</h3>
				<p>笔数：${pdCount.rechargeTimes }</p>
			</div>
			<div>
				<h2>当月充值</h2>
				<h3>${pdMonth.recharges }</h3>
				<p>笔数：${pdMonth.rechargeTimes }</p>
			</div>
			<div>
				<h2>今日充值</h2>
				<h3>${pdDay.recharges }</h3>
				<p>笔数：${pdDay.rechargeTimes }</p>
			</div>
			<div>
				<h2>昨日充值</h2>
				<h3>${pdYesterday.recharges }</h3>
				<p>笔数：${pdYesterday.rechargeTimes }</p>
			</div>
			<span style="left: 240px;"></span>
			<span style="left: 480px;"></span>
			<span style="left: 720px;"></span>
		</div>
		<form action="<%=basePath%>rechargelog/channelReadReechargeLog.do" method="post" id="sysForm" name="sysForm">
		<input type="hidden" name="USERID" id="USERID" value="${pd.USERID}">
		<div class="readerManagementDeSel clearfix">
			<div class="readerManagementDeSelL clearfix">
				<div style="margin-left: 30px;" class="readerManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime}" readonly="readonly" />
					<img src="static/readchannel/images/myPic13.png" alt="" />
				</div>
				<div class="rechargeRecordSel clearfix">
					<p id="RECHARGE_TYPE"><c:choose><c:when test="${not empty pd.RECHARGE_TYPE}"><c:if test="${pd.RECHARGE_TYPE == 0 }">普通充值</c:if><c:if test="${pd.RECHARGE_TYPE == 1 }">VIP充值</c:if></c:when><c:otherwise>全部类型</c:otherwise></c:choose></p>
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
		<div class="rechargeRecordTit clearfix">
			<span style="width: 150px; margin-left: 19px;">充值日期</span>
			<span style="width: 110px;">VIP状态</span>
			<span style="width: 96px;">充值类型</span>
			<span style="width: 104px;">充值额度</span>
			<span style="width: 81px;">有效期</span>
			<span style="width: 106px;">充值金额</span>
			<span style="width: 108px;">获得阅读币</span>
			<span style="width: 82px;">平台收益</span>
			<span>渠道累计充值</span>
		</div>
		<div class="rechargeManagementDeTex clearfix">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var">
				<li class="clearfix">
					<div class="rechargeManagementDeTex1">${var.CREATE_TIME }</div>
					<div class="rechargeManagementDeTex2">
						<c:if test="${var.ISVIP  == 0 }">
							<h2>普通用户</h2>
						<!-- 	<p>（VIP停用）</p> -->
						</c:if>
						<c:if test="${var.ISVIP  == 1 }">
							VIP会员
						</c:if>
					</div>
					<div class="rechargeManagementDeTex3">
						<c:if test="${var.RECHARGE_TYPE  == 2 }">
							普通充值
						</c:if>
						<c:if test="${var.RECHARGE_TYPE  == 1 }">
							VIP充值
						</c:if>
					</div>
					<div class="rechargeManagementDeTex4">---------</div>
					<div class="rechargeManagementDeTex5">
						<c:if test="${var.EXP_DATE  == null }">
							永久
						</c:if>
						<c:if test="${var.RECHARGE_TYPE  != null }">
							${var.EXP_DATE }
						</c:if>
					</div>
					<div class="rechargeManagementDeTex6">${var.MONEY }</div>
					<div class="rechargeManagementDeTex7">${var.BOOK_CURRENCY_ALL }</div>
					<div class="rechargeManagementDeTex8">${var.MONEY }</div>
					<div class="rechargeManagementDeTex9">${var.RECHARGE_MONEY }</div>
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
	<script type="text/javascript">
		function readReechangeSubmint(data){
			if(data==null){
				data="";
			}
	   		$("#sysForm").attr("action","<%=basePath%>rechargelog/channelReadReechargeLog.do?RECHARGE_TYPE="+data);
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
			window.location.href='<%=basePath%>rechargelog/channelReadReechargeExcel.do?RECHARGE_TYPE='+RECHARGE_TYPE+'&sendtime='+sendtime+'&USERID='+USERID;
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
			$(this).parent().siblings('p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.rechargeRecordSel').find('ul').hide();
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