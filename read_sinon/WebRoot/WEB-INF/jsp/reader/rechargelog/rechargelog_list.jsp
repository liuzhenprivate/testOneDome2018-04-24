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
	<title>充值管理</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">充值管理</a>
	</div>
	<div class="rechargeManagementDe">
		<div class="rechargeManagementDeTop clearfix">
			<div>
				<h2>累计充值</h2>
				<h1>${pd.recharges }</h1>
				<p>笔数：${pd.rechargeTimes }</p>
			</div>
			<span></span>
			<div>
				<h2>当月充值</h2>
				<h1>${pd.rechargesMonth }</h1>
				<p>笔数：${pd.rechargeTimesMonth }</p>
			</div>
			<span></span>
			<div>
				<h2>今日充值</h2>
				<h1>${pd.rechargesDay }</h1>
				<p>笔数：${pd.rechargeTimesDay }</p>
			</div>
			<span></span>
			<div>
				<h2>昨日充值</h2>
				<h1>${pd.rechargesYesterDay }</h1>
				<p>笔数：${pd.rechargeTimesYesterDay }</p>
			</div>
		</div>
		<form action="<%=basePath%>rechargelog/list.do" method="post" id="reForm" name="reForm">
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names}"/>
					<p>输入昵称或渠道名搜索</p>
					<img src="static/read/images/myPic26.png" alt="查询" onclick="ReechangeSubmint();"/>
				</div>
				<div class="rechargeManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
				<div class="rechargeManagementDeSelL3 clearfix">
					<p id="FOLLOWSTATE"><c:if test="${pd.FOLLOWSTATE  == null }">全部状态</c:if><c:if test="${pd.FOLLOWSTATE  != null }">${pd.FOLLOWSTATE}</c:if></p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul>
						<li onclick="ReechangeSubmint('全部状态');">全部状态</li>
						<li onclick="ReechangeSubmint('未关注');">未关注</li>
						<li onclick="ReechangeSubmint('已关注');">已关注</li>
					</ul>
				</div>
				<div class="rechargeManagementDeSelL3 clearfix">
					<p id="RECHARGE_TYPE"><c:if test="${pd.RECHARGE_TYPE  == null }">全部类型</c:if><c:if test="${pd.RECHARGE_TYPE  != null }">${pd.RECHARGE_TYPE}</c:if></p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul>
						<li onclick="ReechangeSubmint('全部类型');">全部类型</li>
						<li onclick="ReechangeSubmint('普通充值');">普通充值</li>
						<li onclick="ReechangeSubmint('VIP充值');">VIP充值</li>
					</ul>
				</div>
			</div>
			<a href="javascript:excel()" title="导出excel表"><span>导出列表</span></a>
		</div>
		<div class="rechargeManagementDeTit clearfix">
			<span style="width: 110px; margin-left: 20px;">昵称</span>
			<span style="width: 106px;">渠道ID</span>
			<span style="width: 78px;">关联渠道</span>
			<span style="width: 147px;">充值日期</span>
			<span style="width: 70px;">VIP状态</span>
			<span style="width: 78px;">充值类型</span>
			<span style="width: 112px;">有效期</span>
			<span style="width: 76px;">充值金额</span>
			<span style="width: 89px;">获得阅读币</span>
			<span>平台收益</span>
		</div>
		<div class="rechargeManagementDeTex clearfix">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
				<li class="clearfix">
					<div class="rechargeManagementDeTex1">${var.NICKNAME }</div>
					<div class="rechargeManagementDeTex2">${var.USER_ID }</div>
					<div class="rechargeManagementDeTex3">${var.NAME }</div>
					<div class="rechargeManagementDeTex4">${var.CREATE_TIME }</div>
					<div class="rechargeManagementDeTex5">
					<c:if test="${var.ISVIP  != 1 }">
						<h2>普通用户</h2>
					</c:if>
					<c:if test="${var.ISVIP  == 1 }">
						VIP会员
					</c:if>
					</div>
					<div class="rechargeManagementDeTex6">
					<c:if test="${var.RECHARGE_TYPE  == 0 }">
						普通充值
					</c:if>
					<c:if test="${var.RECHARGE_TYPE  == 1 }">
						VIP充值
					</c:if>
					<c:if test="${var.RECHARGE_TYPE  == 2 }">
						连续包月充值
					</c:if>
					</div>
					<div class="rechargeManagementDeTex8">
						<c:if test="${var.EXP_DATE  == '' || var.EXP_DATE == null }">
							永久有效
						</c:if>
						<c:if test="${var.EXP_DATE  != '' || var.EXP_DATE != null }">
							${var.EXP_DATE }
						</c:if>
					</div>
					<div class="rechargeManagementDeTex9">${var.MONEY }元</div>
					<div class="rechargeManagementDeTex10">${var.BOOK_CURRENCY_ALL }</div>
					<div class="rechargeManagementDeTex11">${var.INCOME }</div>
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
		function ReechangeSubmint(data){
			if(data==null||data==""){
				var RECHARGE_TYPE = $("#RECHARGE_TYPE").text();
				var FOLLOWSTATE = $("#FOLLOWSTATE").text();
			}
			if(data=="普通充值"){
				var RECHARGE_TYPE = "普通充值";
				var FOLLOWSTATE = $("#FOLLOWSTATE").text(); 
			}else if(data=="VIP充值"){
				var RECHARGE_TYPE = "VIP充值"; 
				var FOLLOWSTATE = $("#FOLLOWSTATE").text();
			}else if(data=="已关注"){
				var FOLLOWSTATE = "已关注";
				var RECHARGE_TYPE = $("#RECHARGE_TYPE").text();
			}else if(data=="未关注"){
				var FOLLOWSTATE = "未关注";
				var RECHARGE_TYPE = $("#RECHARGE_TYPE").text();
			}else if(data=="全部类型"){
				var RECHARGE_TYPE = "全部类型";
				var FOLLOWSTATE = $("#FOLLOWSTATE").text();
			}else if(data=="全部状态"){
				var FOLLOWSTATE = "全部状态";
				var RECHARGE_TYPE = $("#RECHARGE_TYPE").text();
			}
	   		$("#reForm").attr("action","<%=basePath%>rechargelog/list.do?RECHARGE_TYPE="+RECHARGE_TYPE+"&FOLLOWSTATE="+FOLLOWSTATE);
			$("#reForm").submit();
		}
		
		function excel(){
			var RECHARGE_TYPE = $("#RECHARGE_TYPE").text();
			var FOLLOWSTATE = $("#FOLLOWSTATE").text();
			var sendtime = $("#sendtime").val();
			var names = $("#names").val();
			window.location.href='<%=basePath%>rechargelog/excel.do?RECHARGE_TYPE='+RECHARGE_TYPE+'&FOLLOWSTATE='+FOLLOWSTATE+'&sendtime='+sendtime+'&names='+names;
		}
	</script>
	<script>
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
		
		$('.rechargeManagementDeSelL3').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.rechargeManagementDeSelL3 ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent('ul').siblings('p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.rechargeManagementDeSelL3').find('ul').hide();
		})
	</script>
</body>
</html>