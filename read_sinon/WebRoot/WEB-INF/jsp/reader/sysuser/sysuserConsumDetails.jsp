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
	<title>消费记录</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="recordsConsumptionAll">
		<div class="rechargeManagementDeTop clearfix">
			<div>
				<h2>累计消费</h2>
				<h1>${pdTotal.CONSUMES }</h1>
				<p>累计笔数：${pdTotal.CONSUMELOG_ID }</p>
			</div>
			<span></span>
			<div>
				<h2>当月消费</h2>
				<h1>${pdMonth.CONSUMES }</h1>
				<p>笔数：${pdMonth.CONSUMELOG_ID }</p>
			</div>
			<span></span>
			<div>
				<h2>今日消费</h2>
				<h1>${pdDay.CONSUMES }</h1>
				<p>笔数：${pdDay.CONSUMELOG_ID }</p>
			</div>
			<span></span>
			<div>
				<h2>昨日消费</h2>
				<h1>${pdYester.CONSUMES }</h1>
				<p>笔数：${pdYester.CONSUMELOG_ID }</p>
			</div>
		</div>
		<div class="channelDetailsDeInfor clearfix">
			<h2>全部消费记录</h2>
		</div>
		<form action="<%=basePath%>user/sysuserConsumeDetails.do" method="post" id="sysForm" name="sysForm">
		<input type="hidden" id="USER_ID" name="USER_ID" value="${pd.USER_ID }"/>
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names }" />
					<p>请输入昵称或渠道ID</p>
					<img src="static/read/images/myPic26.png"  alt="查询" onclick="sysConsumeSubmint();"/>
				</div>
				<div class="rechargeManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
			</div>
			<a href="javascript:excelOut()" title="导出excel表"><span>导出列表</span></a>
		</div>
		<div class="recordsConsumptionTit clearfix">
			<span style="width: 166px; margin-left: 19px;">昵称</span>
			<span style="width: 194px;">渠道ID</span>
			<span style="width: 166px;">消费日期</span>
			<span style="width: 325px;">消费内容</span>
			<span>使用阅读币</span>
		</div>
		<div class="recordsConsumptionTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<p class="recordsConsumptionTex1">${var.NICKNAME }</p>
						<p class="recordsConsumptionTex2">${var.USER_ID }</p>
						<p class="recordsConsumptionTex3">${var.CREATE_TIME }</p>
						<p class="recordsConsumptionTex4">${var.ARTICLE_NAME }  ${var.CHAPTER_NAME }</p>
						<p class="recordsConsumptionTex5">${var.CONSUMES }</p>
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
		function sysConsumeSubmint(){
	   		$("#sysForm").attr("action","<%=basePath%>user/sysuserConsumeDetails.do");
			$("#sysForm").submit();
		}
		function excelOut(){
			var sendtime = $("#sendtime").val();
			var names = $("#names").val();
			var USER_ID = $("#USER_ID").val();
			window.location.href='<%=basePath%>user/sysuserConsumeExcel.do?sendtime='+sendtime+'&names='+names+'&USER_ID='+USER_ID;
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
		
	</script>
</body>
</html>
