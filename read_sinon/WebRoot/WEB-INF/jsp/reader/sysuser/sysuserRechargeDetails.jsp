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
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="rechargeRecordAll">
		<div class="rechargeManagementDeTop clearfix">
			<div>
				<h2>累计充值</h2>
				<h1>${pdTotal.MONEY }</h1>
				<p>笔数：${pdTotal.RECHARGE_LOG_ID }</p>
			</div>
			<span></span>
			<div>
				<h2>当月充值</h2>
				<h1>${pdMonth.MONEY }</h1>
				<p>笔数：${pdMonth.RECHARGE_LOG_ID }</p>
			</div>
			<span></span>
			<div>
				<h2>今日充值</h2>
				<h1>${pdDay.MONEY }</h1>
				<p>笔数：${pdDay.RECHARGE_LOG_ID }</p>
			</div>
			<span></span>
			<div>
				<h2>昨日充值</h2>
				<h1>${pdYester.MONEY }</h1>
				<p>笔数：${pdYester.RECHARGE_LOG_ID }</p>
			</div>
		</div>
		<form action="<%=basePath%>user/sysuserRechargeDetails.do" method="post" id="sysForm" name="sysForm">
		<input type="hidden" id="USER_ID" name="USER_ID" value="${pd.USER_ID }"/>
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names }" />
					<p>输入书籍名称或作者搜索</p>
					<img src="static/read/images/myPic26.png"  alt="查询" onclick="sysRechargeSubmint();"/>
				</div>
				<div class="rechargeManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
				<div class="rechargeManagementDeSelL3 clearfix">
					<p id="RECHARGE_TYPE"><c:if test="${pd.RECHARGE_TYPE == 0 }">普通充值</c:if><c:if test="${pd.RECHARGE_TYPE == 1 }">VIP充值</c:if><c:if test="${pd.RECHARGE_TYPE == null }">全部类型</c:if></p>
					<img src="static/read/images/myPic13.png" alt="充值状态" />
					<ul>
						<li onclick="sysRechargeSubmint();">全部类型</li>
						<li onclick="sysRechargeSubmint('0');">普通充值</li>
						<li onclick="sysRechargeSubmint('1');">VIP充值</li>
					</ul>
				</div>
			</div>
			<a href="javascript:excelOut()" title="导出excel表"><span>导出列表</span></a>
		</div>
		<div class="rechargeManagementDeTit clearfix">
			<span style="width: 110px; margin-left: 20px;">昵称</span>
			<span style="width: 106px;">平台ID</span>
			<span style="width: 78px;">关联渠道</span>
			<span style="width: 120px;">充值日期</span>
			<span style="width: 70px;">VIP状态</span>
			<span style="width: 78px;">充值类型</span>
			<span style="width: 77px;">充值额度</span>
			<span style="width: 62px;">有效期</span>
			<span style="width: 76px;">充值金额</span>
			<span style="width: 89px;">获得阅读币</span>
			<span>平台收益</span>
		</div>
		<div class="rechargeManagementDeTex clearfix">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="rechargeManagementDeTex1">${var.NICKNAME }</div>
						<div class="rechargeManagementDeTex2">${var.USER_ID }</div>
						<div class="rechargeManagementDeTex3">${var.NAME }</div>
						<div class="rechargeManagementDeTex4">${var.CREATE_TIME }</div>
						<div class="rechargeManagementDeTex5">
						<c:if test="${var.ISVIP  == 0 }">
							普通会员
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
						</div>
						<div class="rechargeManagementDeTex7">-----</div>
						<div class="rechargeManagementDeTex8">${var.EXP_DATE }</div>
						<div class="rechargeManagementDeTex9">${var.MONEY }</div>
						<div class="rechargeManagementDeTex10">${var.BOOK_CURRENCY_ALL }</div>
						<div class="rechargeManagementDeTex11">${var.INCOME }</div>
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
		</form>
	</div>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript">
		function sysRechargeSubmint(data){
			if(null==data){
				var TYPE = $("#RECHARGE_TYPE").text();
				if("普通充值"==TYPE){
					var RECHARGE_TYPE ="0";
				}else if("VIP充值"==TYPE){
					var RECHARGE_TYPE ="1";
				}else{
					var RECHARGE_TYPE ="";
				}
			}else{
				var RECHARGE_TYPE = data;
			}
	   		$("#sysForm").attr("action","<%=basePath%>user/sysuserRechargeDetails.do?RECHARGE_TYPE="+RECHARGE_TYPE);
			$("#sysForm").submit();
		}
		function excelOut(){
			var sendtime = $("#sendtime").val();
			var names = $("#names").val();
			var USER_ID = $("#USER_ID").val();
			var RECHARGE_TYPE = "";
			var TYPE = $("#RECHARGE_TYPE").text();
			if(TYPE=="普通充值"){
				RECHARGE_TYPE = '0';
			}else if(TYPE=="VIP充值"){
				RECHARGE_TYPE = '1';
			}
			window.location.href='<%=basePath%>user/sysuserRechargeExcel.do?sendtime='+sendtime+'&names='+names+'&USER_ID='+USER_ID+"&RECHARGE_TYPE="+RECHARGE_TYPE;
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