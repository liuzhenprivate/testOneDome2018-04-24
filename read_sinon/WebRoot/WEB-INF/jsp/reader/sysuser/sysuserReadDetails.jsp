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
	<title>渠道读者列表</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="readerListAll">
		<div class="readerListTop clearfix">
			<div class="readerListTopD clearfix">
				<h2>累计读者</h2>
				<h1>${pdTotalRead.USERID }</h1>
				<div class="clearfix readerListTopDe">
					<div>
						<p>当月</p>
						<h1>${pdMonthRead.USERID }</h1>
					</div>
					<div style="float: right; margin-right: 30px;">
						<p>今日</p>
						<h1>${pdDayRead.USERID }</h1>
					</div>
				</div>
			</div>
			<div style="margin-left: 20px;" class="readerListTopD clearfix">
				<h2>渠道粉丝</h2>
				<h1>${pdTotalFans.USERID }</h1>
				<div class="clearfix readerListTopDe">
					<div>
						<p>当月</p>
						<h1>${pdMonthFans.USERID }</h1>
					</div>
					<div style="float: right; margin-right: 30px;">
						<p>今日</p>
						<h1>${pdDayFans.USERID }</h1>
					</div>
				</div>
			</div>
			<div style="margin-left: 20px;" class="readerListTopD clearfix">
				<h2>付费人数</h2>
				<h1>${pdTotalRechargeP.RECHARGE_LOG_ID }</h1>
				<div class="clearfix readerListTopDe">
					<div>
						<p>当月</p>
						<h1>${pdMonthRechargeP.RECHARGE_LOG_ID }</h1>
					</div>
					<div style="float: right; margin-right: 34px;">
						<p>今日</p>
						<h1>${pdDayRechargeP.RECHARGE_LOG_ID }</h1>
					</div>
				</div>
			</div>
		</div>
		<form action="<%=basePath%>user/sysuserReadDetails.do" method="post" id="sysForm" name="sysForm">
		<input type="hidden" id="USER_ID" name="USER_ID" value="${pd.USER_ID }"/>
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names }" />
					<p>输入昵称或ID查询</p>
					<img src="static/read/images/myPic26.png" alt="查询" onclick="sysReadSubmint();"/>
				</div>
				<div class="rechargeManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
				<div class="rechargeManagementDeSelL3 clearfix">
					<p id="FOLLOWSTATE"><c:if test="${pd.FOLLOWSTATE == 0 }">未关注</c:if><c:if test="${pd.FOLLOWSTATE == 1 }">已关注</c:if><c:if test="${pd.FOLLOWSTATE == null }">全部</c:if></p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul>
						<li onclick="sysReadSubmint();">全部</li>
						<li onclick="sysReadSubmint('0');">未关注</li>
						<li onclick="sysReadSubmint('1');">已关注</li>
					</ul>
				</div>
			</div>
			<a href="javascript:excelOut()" title=""><span>导出列表</span></a>
		</div>
		<div class="readerListTit clearfix">
			<span style="width: 52px; margin-left: 19px;">头像</span>
			<span style="width: 124px;">昵称</span>
			<span style="width: 105px;">平台ID</span>
			<span style="width: 116px;">渠道ID</span>
			<span style="width: 77px;">关注状态</span>
			<span style="width: 122px;">注册日期</span>
			<span style="width: 81px;">VIP状态</span>
			<span style="width: 99px;">阅读币余额</span>
			<span style="width: 86px;">累计充值</span>
			<span>累计阅读币</span>
		</div>
		<div class="readerListTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
				<li class="clearfix">
					<span value="${var.USERID }">
						<img src="${var.HEADIMGURL }" alt="" />
						<div class="readerListTex1">${var.NICKNAME }</div>
						<div class="readerListTex2">${var.USER_CODE }</div>
						<div class="readerListTex3">${var.USER_ID }</div>
						<div class="readerListTex4">
							<c:if test="${var.FOLLOWSTATE  == 0 }">
								未关注
							</c:if>
							<c:if test="${var.FOLLOWSTATE  == 1 }">
								已关注
							</c:if>
						</div>
						<div class="readerListTex5">${var.CREATE_TIME }</div>
						<div class="readerListTex6">
							<c:if test="${var.ISVIP  == 0 }">
								普通会员
							</c:if>
							<c:if test="${var.ISVIP  == 1 }">
								VIP会员
							</c:if>
						</div>
						<div class="readerListTex7">${var.SURPLUS_CURRENCY }</div>
						<div class="readerListTex8">${var.RECHARGE_MONEY }</div>
						<div class="readerListTex9">${var.CUMULATIVE_CURRENCY }</div>
					</span>
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
		function sysReadSubmint(data){
			if(null==data){
				var STATE = $("#FOLLOWSTATE").text();
				if("未关注"==STATE){
					var FOLLOWSTATE ="0";
				}else if("已关注"==STATE){
					var FOLLOWSTATE ="1";
				}else{
					var FOLLOWSTATE ="";
				}
			}else{
				var FOLLOWSTATE = data;
			}
	   		$("#sysForm").attr("action","<%=basePath%>user/sysuserReadDetails.do?FOLLOWSTATE="+FOLLOWSTATE);
			$("#sysForm").submit();
		}
		function excelOut(){
			var sendtime = $("#sendtime").val();
			var names = $("#names").val();
			var USER_ID = $("#USER_ID").val();
			var STATE = $("#FOLLOWSTATE").text();
			var FOLLOWSTATE ="";
			if("未关注"==STATE){
				var FOLLOWSTATE ="0";
			}else if("已关注"==STATE){
				var FOLLOWSTATE ="1";
			}
			window.location.href='<%=basePath%>user/sysuserReadExcel.do?sendtime='+sendtime+'&names='+names+'&USER_ID='+USER_ID+"&FOLLOWSTATE="+FOLLOWSTATE;
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
		
		$('.readerListTex ul li span').click(function(){
			var USERID = $(this).attr("value");
			var USER_ID = $("#USER_ID").val();
			$('#iframe', parent.parent.document).attr('src','<%=basePath%>wxuser/UserReadDetail.do?USERID='+USERID+'&USER_ID='+USER_ID);
		})
	</script>
</body>
</html>