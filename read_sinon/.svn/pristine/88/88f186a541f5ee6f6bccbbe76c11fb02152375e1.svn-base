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
	<title>账户信息</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script src="static/read/js/brokenLine.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="accountInformationAll">
		<div class="channelDetailsDeInfor clearfix">
			<h2 style="margin-top: 0;">账户信息</h2>
		</div>
		<div class="channelDetailsDeAll clearfix">
			<ul>
				<li class="clearfix">
					<span>姓名</span>
					<p>${upd.ACCOUNT_NAME }</p>
				</li>
				<li class="clearfix">
					<span>银行账号</span>
					<p>${upd.CARD_NUM }</p>
				</li>
				<li class="clearfix">
					<span>开户行</span>
					<p>${upd.OPEN_CARD }</p>
				</li>
				<li class="clearfix">
					<span>绑定手机</span>
					<p>${upd.PHONE }</p>
				</li>
			</ul>
		</div>
		<div class="channelDetailsDeInfor clearfix">
			<h2>收款信息</h2>
		</div>
		<form action="<%=basePath%>singlog/readerSignLog.do" method="post" id="sysForm" name="sysForm">
		<input type="hidden" id="USER_ID" name="USER_ID" value="${pd.USER_ID }"/>
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div style="margin-left:30px;" class="rechargeManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
				<div class="rechargeManagementDeSelL3 clearfix">
					<p id="STATE"><c:if test="${pd.STATE == null }">全部状态</c:if><c:if test="${pd.STATE == 1 }">待收款</c:if><c:if test="${pd.STATE == 0}">确认收款</c:if><c:if test="${pd.STATE == 2 }">未收款</c:if></p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul>
						<li onclick="sysUserConsumSubmint('-1');">全部状态</li>
						<li onclick="sysUserConsumSubmint('2');">未收款</li>
						<li onclick="sysUserConsumSubmint('1');">待收款</li>
						<li onclick="sysUserConsumSubmint('0');">确认收款</li>
					</ul>
				</div>
			</div>
			<a href="javascript:excelOut()" title="导出excel表"><span>导出列表</span></a>
		</div>
		<div class="accountInformationTit clearfix">
			<span style="margin-left: 19px; width: 47px;">序号</span>
			<span style="width: 125px;">结算单号</span>
			<span style="width: 107px;">申请时间</span>
			<span style="width: 96px;">账户余额</span>
			<span style="width: 72px;">提现金额</span>
			<span style="width: 73px;">累计提现</span>
			<span style="width: 58px;">------</span>
			<span style="width: 95px;">------</span>
			<span style="width: 82px;">------</span>
			<span style="width: 130px;">付款时间</span>
			<span>状态</span>
		</div>
		<div class="accountInformationTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
				<li class="clearfix">
					<span style="margin-left: 19px; width: 47px;">${vs.index+1}</span>
					<span style="width: 125px;">${var.REMITTANCE_LOG_ID }</span>
					<span style="width: 107px;">${var.CREATE_TIME }</span>
					<span style="width: 96px;">${var.SURPLUS }</span>
					<span style="width: 72px;">${var.MONEY }</span>
					<span style="width: 73px;">${var.SUMMONEY }</span>
					<span style="width: 58px;">--------</span>
					<span style="width: 95px;">--------</span>
					<span style="width: 82px;">--------</span>
					<span style="width: 130px;">${var.CHECK_TIME }</span>
					<span style="color: #f14530;">
						<c:if test="${var.STATE  == 0 }">
							待收款
						</c:if>
						<c:if test="${var.STATE  == 1 }">
							确认收款
						</c:if>
						<c:if test="${var.STATE  == 2 }">
							未收款
						</c:if>
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
		function sysUserConsumSubmint(data){
	   		$("#sysForm").attr("action","<%=basePath%>user/sysuserAccountDetails.do?STATE="+data);
			$("#sysForm").submit();
		}
		function excelOut(){
			var sendtime = $("#sendtime").val();
			var USER_ID = $("#USER_ID").val();
			var STATE = $("#STATE").text();
			if(STATE=="待收款"){
				STATE = '0';
			}else if(STATE=="确认收款"){
				STATE = '1';
			}else if(STATE=="未收款"){
				STATE = '2';
			}else{
				STATE = "";
			}
			window.location.href="<%=basePath%>user/exportExcelsysuserAccount.do?sendtime="+sendtime+"&STATE="+STATE+"&USER_ID="+USER_ID;
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
