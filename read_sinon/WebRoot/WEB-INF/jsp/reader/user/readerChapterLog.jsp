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
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="myTab()"  style="background:#fff">
	<div class="recordsOfConsumptionAll">
			<div class="rechargeRecordTop clearfix">
				<div>
					<h3>累计消费阅读币</h3>
					<h2>${pdCount.FEES }</h2>
					<p>笔数：${pdCount.readerChapterTimes }</p>
				</div>
				<div>
					<h3>当月消费</h3>
					<h2>${pdMonth.FEES }</h2>
					<p>笔数：${pdMonth.readerChapterTimes }</p>
				</div>
				<div>
					<h3>今日消费</h3>
					<h2>${pdDay.FEES }</h2>
					<p>笔数：${pdDay.readerChapterTimes }</p>
				</div>
				<div>
					<h3>昨日消费</h3>
					<h2>${pdYesterday.FEES }</h2>
					<p>笔数：${pdYesterday.readerChapterTimes }</p>
				</div>
				<span style="left: 240px;"></span>
				<span style="left: 480px;"></span>
				<span style="left: 720px;"></span>
			</div>
			<form action="<%=basePath%>articlechapterlog/readerChapterLog.do" method="post" id="rForm" name="rForm">
			<input type="hidden" name="USERID" id="USERID" value="${pd.USERID}">
			<input type="hidden" name="OPENID" id="OPENID" value="${pd.OPENID}">
			<div class="readerManagementDeSel clearfix">
				<div class="readerManagementDeSelL clearfix">
					<div class="readerManagementDeSelL1 clearfix">
						<input id="ARTICLE_NAME" name="ARTICLE_NAME" value="${pd.ARTICLE_NAME}"/>
						<p>输入书籍名</p>
						<img src="static/read/images/myPic26.png" alt="查询" onclick="readChapterSubmint();"/>
					</div>
					<div class="readerManagementDeSelL2 clearfix">
						<input id="sendtime" name="sendtime" value="${pd.sendtime}" readonly="readonly" />
						<img src="static/read/images/myPic13.png" alt="" />
					</div>
				</div>
				<a href="javascript:excelOut()" title="导出excel表"><span>导出列表</span></a>
			</div>
			<div class="recordsOfConsumptionTit clearfix">
				<span style="margin-left: 19px; width: 126px;">关联渠道</span>
				<span style="width: 151px;">渠道ID</span>
				<span style="width: 159px;">消费日期</span>
				<span style="width: 126px;">消费阅读币</span>
				<span style="width: 224px;">消费书籍</span>
				<span>消费章节</span>
			</div>
			<div class="recordsOfConsumptionTex">
				<ul>
					<c:choose>
					<c:when test="${not empty varList}">
					<c:forEach items="${varList}" var="var">
					<li class="clearfix">
						<p class="recordsOfConsumptionTex1">${var.NAME }</p>
						<p class="recordsOfConsumptionTex2">${var.USERID }</p>
						<p class="recordsOfConsumptionTex3">${var.CREATE_TIME }</p>
						<p class="recordsOfConsumptionTex4">${var.FEE }</p>
						<p class="recordsOfConsumptionTex5">${var.ARTICLE_NAME }</p>
						<p class="recordsOfConsumptionTex6">${var.CHAPTER_NAME }</p>
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
		function readChapterSubmint(){
	   		$("#rForm").attr("action","<%=basePath%>consumelog/readerConsumeLog.do");
			$("#rForm").submit();
		}
		function excelOut(){
			var sendtime = $("#sendtime").val();
			var ARTICLE_NAME = $("#ARTICLE_NAME").val();
			var USERID = $("#USERID").val();
			var OPENID = $("#OPENID").val();
			window.location.href='<%=basePath%>consumelog/exportExcelRead.do?sendtime='+sendtime+'&ARTICLE_NAME='+ARTICLE_NAME+'&USERID='+USERID+'&OPENID='+OPENID;
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
