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
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/readerManagement.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="rechargeRecordAll">
		<div class="rechargeRecordTop clearfix">
			<div>
				<h3>累计消费</h3>
				<h2>${pdConsume.CONSUMES }</h2>
				<p>累计笔数：${pdConsume.CONSUMELOG_ID }</p>
			</div>
			<div>
				<h3>当月消费</h3>
				<h2>${pdConsumeMonth.CONSUMES }</h2>
				<p>笔数：${pdConsumeMonth.CONSUMELOG_ID }</p>
			</div>
			<div>
				<h3>今日消费</h3>
				<h2>${pdConsumeDay.CONSUMES }</h2>
				<p>笔数：${pdConsumeDay.CONSUMELOG_ID }</p>
			</div>
			<div>
				<h3>昨日消费</h3>
				<h2>${pdConsumeYDay.CONSUMES }</h2>
				<p>笔数：${pdConsumeYDay.CONSUMELOG_ID }</p>
			</div>
			<span style="left: 240px;"></span>
			<span style="left: 480px;"></span>
			<span style="left: 720px;"></span>
		</div>
		<form action="<%=basePath%>user/sysUserReadConsumelog.do" method="post" id="sysForm" name="sysForm">
		<input type="hidden" id="USERID" name="USERID" value="${pd.USERID }"/>
		<div class="readerManagementDeSel clearfix">
			<div class="readerManagementDeSelL clearfix">
				<div class="readerManagementDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names }" />
					<p>输入书籍名称或章节名搜索</p>
					<img src="static/readchannel/images/myPic26.png"  alt="查询" onclick="sysConsumeSubmint();"/>
				</div>
			</div>
			<a href="javascript:excelOut()" title="导出excel表"><span>导出列表</span></a>
		</div>
		<div class="rechargeRecordTit clearfix">
			<span style="width: 256px; margin-left: 19px;">消费日期</span>
			<span style="width: 216px;">消费阅读币</span>
			<span style="width: 315px;">消费书籍</span>
			<span>消费章节</span>
		</div>
		<div class="recordsConsumptionTex clearfix">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="recordsConsumptionTex1">${var.CREATE_TIME }</div>
						<div class="recordsConsumptionTex2">${var.CONSUMES }</div>
						<div class="recordsConsumptionTex3">${var.ARTICLE_NAME }</div>
						<div class="recordsConsumptionTex4">${var.CHAPTER_NAME }</div>
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
	<script type="text/javascript">
		function sysConsumeSubmint(){
	   		$("#sysForm").attr("action","<%=basePath%>consumelog/channelReadConsumelog.do");
			$("#sysForm").submit();
		}
		function excelOut(){
			var names = $("#names").val();
			var USERID = $("#USERID").val();
			window.location.href='<%=basePath%>consumelog/channelReadConsumeExcel.do?names='+names+'&USERID='+USERID;
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