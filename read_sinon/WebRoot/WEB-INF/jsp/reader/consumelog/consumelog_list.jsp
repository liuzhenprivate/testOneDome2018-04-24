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
	<title>消费管理</title>
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
				<h2>累计阅读币</h2>
				<h1>${pdConsume.CONSUMEMONEY }</h1>
				<p>累计消费：${pdConsume.CONSUMENUMBERS }</p>
			</div>
			<span></span>
			<div>
				<h2>当月消费</h2>
				<h1>${pdConsumeMonth.CONSUMEMONEY }</h1>
				<p>笔数：${pdConsumeMonth.CONSUMENUMBERS }</p>
			</div>
			<span></span>
			<div>
				<h2>今日消费</h2>
				<h1>${pdConsumeDay.CONSUMEMONEY }</h1>
				<p>笔数：${pdConsumeDay.CONSUMENUMBERS }</p>
			</div>
			<span></span>
			<div>
				<h2>昨日消费</h2>
				<h1>${pdConsumeYDay.CONSUMEMONEY }</h1>
				<p>笔数：${pdConsumeYDay.CONSUMENUMBERS }</p>
			</div>
		</div>
		<form action="<%=basePath%>consumelog/list.do" method="post" id="cForm" name="cForm">
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names }" />
					<p>输入昵称、ID或渠道名称搜索</p>
					<img src="static/read/images/myPic26.png"  alt="查询" onclick="ConsumeSubmint();"/>
				</div>
				<div class="rechargeManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime}" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
				<div class="rechargeManagementDeSelL3 clearfix">
					<p id="FOLLOWSTATE"><c:if test="${pd.STATE  == null }">全部状态</c:if><c:if test="${pd.STATE  != null }">${pd.STATE}</c:if></p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul>
						<li onclick="ConsumeSubmint('全部状态');">全部状态</li>
						<li onclick="ConsumeSubmint('未关注');">未关注</li>
						<li onclick="ConsumeSubmint('已关注');">已关注</li>
					</ul>
				</div>
			</div>
			<a href="javascript:excel()" title="导出excel表"><span>导出列表</span></a>
		</div>
		<div class="consumptionManagementDeTit clearfix">
			<span style="width: 106px; margin-left: 19px;">昵称</span>
			<span style="width: 97px;">渠道ID</span>
			<span style="width: 147px;">关联渠道</span>
			<span style="width: 175px;">最近消费日期</span>
			<span style="width: 75px;">关注状态</span>
			<span style="width: 149px;">累计阅读币</span>
			<span>最近消费</span>
		</div>
		<div class="consumptionManagementDeTex clearfix">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
				<li class="clearfix">
					<div class="consumptionManagementDeTex1">${var.NICKNAME }</div>
					<div class="consumptionManagementDeTex2">${var.USER_ID }</div>
					<div class="consumptionManagementDeTex3">${var.NAME }</div>
					<div class="consumptionManagementDeTex4">${var.CREATE_TIME }</div>
					<div class="consumptionManagementDeTex5">
						<c:if test="${var.FOLLOWSTATE  == 0 }">
							未关注
						</c:if>
						<c:if test="${var.FOLLOWSTATE  == 1 }">
							已关注
						</c:if>
					</div>
					<div class="consumptionManagementDeTex6" onclick="findCURRENCY(${var.BOOKCURRENCYS },${var.BOOK_CURRENCY },${var.QBOOK_CURRENCY })">${var.CUMULATIVE_CURRENCY }</div>
					<div class="consumptionManagementDeTex10">
						<h2>${var.ARTICLE_NAME }</h2>
						<p>（${var.CHAPTER_NAME }）</p>
					</div>
				</li>
				</c:forEach>
				</c:when>
				<c:otherwise>
					没有相关数据
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div style="margin-left: 30px;" class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
		</form>
	</div>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<!-- 引入 -->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
	<script type="text/javascript">
			function findCURRENCY(BOOKCURRENCYS,BOOK_CURRENCY,QBOOK_CURRENCY){
				 var diag = new top.Dialog();
				 diag.Drag=true;
				 diag.Title ="阅读币详情";
				 diag.URL = '<%=basePath%>consumelog/goCURRENCY.do?BOOKCURRENCYS='+BOOKCURRENCYS+'&BOOK_CURRENCY='+BOOK_CURRENCY+'&QBOOK_CURRENCY='+QBOOK_CURRENCY;
				 diag.Width = 480;
				 diag.Height = 200;
				 diag.CancelEvent = function(){ //关闭事件
					 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
						 
					}
					 diag.close();
				 };
				 diag.show();
			}
	</script>
	<script type="text/javascript">
		function ConsumeSubmint(data){
			var STATE = data;
			var FOLLOWSTATE = "";
			if(data==null){
			STATE=$("#FOLLOWSTATE").text();
				if(STATE=="未关注"){
					FOLLOWSTATE = '0';
				}else if(STATE=="已关注"){
					FOLLOWSTATE = '1';
				}
			}else{
				if(STATE=="未关注"){
					FOLLOWSTATE = '0';
				}else if(STATE=="已关注"){
					FOLLOWSTATE = '1';
				}
			}
	   		$("#cForm").attr("action","<%=basePath%>consumelog/list.do?&FOLLOWSTATE="+FOLLOWSTATE+"&STATE="+STATE);
			$("#cForm").submit();
		}
		
		function excel(){
			var STATE = $("#FOLLOWSTATE").text();
			if(STATE=="未关注"){
				var FOLLOWSTATE = '0';
			}else if(STATE=="已关注"){
				var FOLLOWSTATE = '1';
			}else if(STATE=="全部状态"){
				var FOLLOWSTATE = "";
			}
			var sendtime = $("#sendtime").val();
			var names = $("#names").val();
			window.location.href='<%=basePath%>consumelog/excel.do?FOLLOWSTATE='+FOLLOWSTATE+'&sendtime='+sendtime+'&names='+names;
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

