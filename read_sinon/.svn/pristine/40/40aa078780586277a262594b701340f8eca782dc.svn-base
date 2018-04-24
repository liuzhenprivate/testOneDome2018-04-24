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
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="myTab()"  style="background:#fff">
	<div class="rechargeRecordAll">
		<div class="rechargeRecordTop clearfix">
			<div>
				<h3>累计充值</h3>
				<h2>${pdCount.recharges }</h2>
				<p>笔数：${pdCount.rechargeTimes }</p>
			</div>
			<div>
				<h3>当月充值</h3>
				<h2>${pdDay.recharges }</h2>
				<p>笔数：${pdDay.rechargeTimes }</p>
			</div>
			<div>
				<h3>今日充值</h3>
				<h2>${pdMonth.recharges }</h2>
				<p>笔数：${pdMonth.rechargeTimes }</p>
			</div>
			<div>
				<h3>昨日充值</h3>
				<h2>${pdYesterday.recharges }</h2>
				<p>笔数：${pdYesterday.rechargeTimes }</p>
			</div>
			<span style="left: 240px;"></span>
			<span style="left: 480px;"></span>
			<span style="left: 720px;"></span>
		</div>
		<form action="<%=basePath%>rechargelog/readReechargeLog.do" method="post" id="rForm" name="rForm">
		<input type="hidden" name="USERID" id="USERID" value="${pd.USERID}">
		<input type="hidden" name="OPENID" id="OPENID" value="${pd.OPENID}">
		<div class="readerManagementDeSel clearfix">
			<div class="readerManagementDeSelL clearfix">
				<div class="readerManagementDeSelL1 clearfix">
					<input id="NAME" name="NAME" value="${pd.NAME}"/>
					<p>请输入渠道名</p>
					<img src="static/read/images/myPic26.png" alt="查询" onclick="readReechangeSubmint();"/>
				</div>
				<div class="readerManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime}" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="日期/时间"/>
				</div>
				<div class="rechargeRecordSel clearfix">
					<p id="RECHARGE_TYPE"><c:if test="${pd.RECHARGE_TYPE == 0 }">普通充值</c:if><c:if test="${pd.RECHARGE_TYPE == 1 }">VIP充值</c:if><c:if test="${pd.RECHARGE_TYPE == null }">全部类型</c:if></p>
					<img src="static/read/images/myPic13.png" alt="状态查询"/>
					<ul>
						<li  onclick="readReechangeSubmint();">全部类型</li>
						<li onclick="readReechangeSubmint('普通充值');">普通充值</li>
						<li onclick="readReechangeSubmint('VIP充值');">VIP充值</li>
					</ul>
				</div>
			</div>
			<a href="javascript:excelOut()" title="导出excel表"><span>导出列表</span></a>
		</div>
		
		<div class="rechargeRecordTit clearfix">
			<span style="width: 86px; margin-left: 19px;">管理渠道</span>
			<span style="width: 120px;">渠道ID</span>
			<span style="width: 110px;">充值日期</span>
			<span style="width: 71px;">VIP状态</span>
			<span style="width: 78px;">充值类型</span>
			<span style="width: 77px;">充值额度</span>
			<span style="width: 82px;">有效期</span>
			<span style="width: 76px;">充值金额</span>
			<span style="width: 89px;">获得阅读币</span>
			<span style="width: 76px;">平台收益</span>
			<span>渠道充值</span>
		</div>
		<div class="rechargeRecordTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var">
				<li class="clearfix">
					<p class="rechargeRecordTex1">${var.NAME }</p>
					<p class="rechargeRecordTex2">${var.USER_ID }</p>
					<p class="rechargeRecordTex3">${var.CREATE_TIME }</p>
					<p style="padding-top: 12px; line-height: 14px;" class="rechargeRecordTex4">
						<c:if test="${var.ISVIP  == 0 }">
							普通会员
							</br><span style="color: #999999; font-size: 10px; line-height: 12px;"></span>
						</c:if>
						<c:if test="${var.ISVIP  == 1 }">
							VIP会员
						</c:if>
					</p>
					<p class="rechargeRecordTex5">
						<c:if test="${var.RECHARGE_TYPE  == 0 }">
							普通充值
						</c:if>
						<c:if test="${var.RECHARGE_TYPE  == 1 }">
							VIP充值
						</c:if>
					</p>
					<p class="rechargeRecordTex6">--</p>
					<p class="rechargeRecordTex7">${var.EXP_DATE }</p>
					<p class="rechargeRecordTex8">${var.MONEY }元</p>
					<p class="rechargeRecordTex9">${var.BOOK_CURRENCY_ALL }</p>
					<p class="rechargeRecordTex10">${var.INCOME }</p>
					<p class="rechargeRecordTex11"></p>
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
	<script>
			
		function readReechangeSubmint(data){
			var RECHARGE_TYPE = "";
			if(data=="普通充值"){
				RECHARGE_TYPE = 0; 
			}else if(data=="VIP充值"){
				RECHARGE_TYPE = 1; 
			}
	   		$("#rForm").attr("action","<%=basePath%>rechargelog/readReechargeLog.do?RECHARGE_TYPE="+RECHARGE_TYPE);
			$("#rForm").submit();
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
			var NAME = $("#NAME").val();
			var USERID = $("#USERID").val();
			var OPENID = $("#OPENID").val();
			window.location.href='<%=basePath%>rechargelog/exportExcelRead.do?RECHARGE_TYPE='+RECHARGE_TYPE+'&sendtime='+sendtime+'&NAME='+NAME+'&USERID='+USERID+'&OPENID='+OPENID;
		}
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
