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
	<title>读者管理</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/readerManagement.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a style="color: #999999;" class="announcementManagementTop1" href="javascript:void(0)" title="">读者管理</a>
	</div>
	<div class="readerManagementDe clearfix">
		<div class="readerManagementDeTop clearfix">
			<div class="readerManagementDeIn clearfix">
				<p>累计读者</p>
				<h2>${pdTotalRead.USERID }</h2>
				<div class="readerManagementDeIn2">
					<div>
						<p>当月</p>
						<h3>${pdMonthRead.USERID }</h3>
					</div>
					<div style="float: right; margin-right: 30px;">
						<p>今日</p>
						<h3>${pdDayRead.USERID }</h3>
					</div>
				</div>
			</div>
			<div class="readerManagementDeIn clearfix">
				<p>渠道粉丝</p>
				<h2>${pdTotalFans.USERID }</h2>
				<div class="readerManagementDeIn2">
					<div>
						<p>当月</p>
						<h3>${pdMonthFans.USERID }</h3>
					</div>
					<div style="float: right; margin-right: 30px;">
						<p>今日</p>
						<h3>${pdDayFans.USERID }</h3>
					</div>
				</div>
			</div>
			<div class="readerManagementDeIn clearfix">
				<p>付费人数</p>
				<h2>${pdTotalRechargeP.UsersFee }</h2>
				<div class="readerManagementDeIn2">
					<div>
						<p>当月</p>
						<h3>${pdMonthRechargeP.USERID }</h3>
					</div>
					<div style="float: right; margin-right: 30px;">
						<p>今日</p>
						<h3>${pdDayRechargeP.USERID }</h3>
					</div>
				</div>
			</div>
		</div>
		<form action="<%=basePath%>channelsysuserread/list.do" method="post" id="sysForm" name="sysForm">
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names }" />
					<p>输入昵称或ID查询</p>
					<img src="static/readchannel/images/myPic26.png" alt="查询" onclick="sysReadSubmint();"/>
				</div>
				<div class="rechargeManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/readchannel/images/myPic13.png" alt="" />
				</div>
				<div class="rechargeManagementDeSelL3 clearfix">
					<p id="RECHARGE_TYPE"><c:choose><c:when test="${not empty pd.FOLLOWSTATE}"><c:if test="${pd.FOLLOWSTATE == 0 }">未关注</c:if><c:if test="${pd.FOLLOWSTATE == 1 }">已关注</c:if></c:when><c:otherwise>全部</c:otherwise></c:choose></p>
					<img src="static/readchannel/images/myPic13.png" alt="" />
					<ul>
						<li onclick="sysReadSubmint('-1');">全部</li>
						<li onclick="sysReadSubmint('0');">未关注</li>
						<li onclick="sysReadSubmint('1');">已关注</li>
					</ul>
				</div>
			</div>
			<a href="javascript:excelOut()" title=""><span>导出列表</span></a>
		</div>
		
		<div class="readerManagementDeTit clearfix">
			<span style="width: 72px; margin-left: 19px;">头像</span>
			<span style="width: 134px;">昵称</span>
			<span style="width: 134px;">读者ID</span>
			<span style="width: 106px;">关注状态</span>
			<span style="width: 140px;">注册日期</span>
			<span style="width: 90px;">VIP状态</span>
			<span style="width: 100px;">阅读币余额</span>
			<span style="width: 86px;">累计充值</span>
			<span>累计阅读币</span>
		</div>
		<div class="readerManagementDeTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<a href="<%=basePath%>channelsysuserread/UserReadDetail.do?USERID=${var.USERID}" title="读者详情">
							<img src="${var.HEADIMGURL }" alt="" />
							<div class="readerManagementDeTex1">${var.NICKNAME }</div>
							<div class="readerManagementDeTex2">${var.USERID }</div>
							<div class="readerManagementDeTex3">
								<c:if test="${var.FOLLOWSTATE  == 0 }">
									未关注
								</c:if>
								<c:if test="${var.FOLLOWSTATE  == 1 }">
									已关注
								</c:if>
							</div>
							<div class="readerManagementDeTex4">${var.CREATE_TIME }</div>
							<div class="readerManagementDeTex5">
								<c:if test="${var.ISVIP  == 0 }">
									<h2>普通会员</h2>
									</br><span style="color: #999999; font-size: 10px; line-height: 4px;"><!-- （VIP停用） --></span>
								</c:if>
								<c:if test="${var.ISVIP  == 1 }">
									VIP会员
								</c:if>
							</div>
							<div class="readerManagementDeTex6">${var.SURPLUS_CURRENCY }</div>
							<div class="readerManagementDeTex7">${var.RECHARGE_MONEY }</div>
							<div class="readerManagementDeTex8">${var.CUMULATIVE_CURRENCY }</div>
						</a>
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
		function sysReadSubmint(data){
			if(null==data){
				var STATE = $("#FOLLOWSTATE").text();
				if("未关注"==STATE){
					var FOLLOWSTATE ="0";
				}else if("已关注"==STATE){
					var FOLLOWSTATE ="1";
				}else{
					var FOLLOWSTATE ="-1";
				}
			}else{
				var FOLLOWSTATE = data;
			}
	   		$("#sysForm").attr("action","<%=basePath%>channelsysuserread/list.do?FOLLOWSTATE="+FOLLOWSTATE);
			$("#sysForm").submit();
		}
		function excelOut(){
			var sendtime = $("#sendtime").val();
			var names = $("#names").val();
			var STATE = $("#FOLLOWSTATE").text();
			var FOLLOWSTATE ="";
			if("未关注"==STATE){
				var FOLLOWSTATE ="0";
			}else if("已关注"==STATE){
				var FOLLOWSTATE ="1";
			}
			window.location.href='<%=basePath%>channelsysuserread/ReadExcel.do?sendtime='+sendtime+'&names='+names+"&FOLLOWSTATE="+FOLLOWSTATE;
		}
	</script>
	<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("Thunder"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		
		$(window).click(function(){
			$('.readerManagementDeClickupDiv').find('ul').hide();
		})
		$('.readerManagementDeClickupDiv').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.readerManagementDeClickupDiv ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent().siblings('p').text(val);
			e.stopPropagation();
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