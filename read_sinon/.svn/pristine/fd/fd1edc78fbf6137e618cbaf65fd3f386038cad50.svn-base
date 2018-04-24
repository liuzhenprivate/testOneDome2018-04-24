<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<title>提现记录</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/accountInformation.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
	<script>
		function search(){
			$("#Form").submit();
		}
	</script>
</head>
 
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">账户信息</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">提现记录</a>
	</div>
	<div class="presentRecordDe">
		<div class="presentRecordDeH1">收益详情</div>
		<div style="margin-top: 20px;" class="rechargeManagementDeTop clearfix">
			<div>
				<h2>累计充值</h2>
				<c:choose>
					<c:when test="${empty recharge }">
					<h1>0</h1>
					<p>笔数：0</p>
					</c:when>
					<c:otherwise>
					<h1>${recharge.MONEY}</h1>
					<p>笔数：${recharge.TIMES }</p>
					</c:otherwise>
				</c:choose>
				
			</div>
			<span></span>
			<div>
				<h2>当月充值</h2>
				<c:choose>
					<c:when test="${empty monthrs }">
					<h1>0</h1>
					<p>笔数：0</p>
					</c:when>
					<c:otherwise>
					<h1>${monthrs.MONEY }</h1>
					<p>笔数：${monthrs.TIMES }</p>
					</c:otherwise>
				</c:choose>
			</div>
			<span></span>
			<div>
				<h2>今日充值</h2>
				<c:choose>
					<c:when test="${empty today }">
					<h1>0</h1>
					<p>笔数：0</p>
					</c:when>
					<c:otherwise>
					<h1>${today.MONEY }</h1>
					<p>笔数：${today.TIMES }</p>
					</c:otherwise>
				</c:choose>
			</div>
			<span></span>
			<div>
				<h2>昨日充值</h2>
				<c:choose>
					<c:when test="${empty dayrs }">
					<h1>0</h1>
					<p>笔数：0</p>
					</c:when>
					<c:otherwise>
					<h1>${dayrs.MONEY/100 }</h1>
					<p>笔数：${dayrs.TIMES }</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<form action="channelaccount/presentRecord.do" method="post" name="Form" id="Form">
		<div style="margin-top: 30px;" class="presentRecordDeH1">提现记录</div>
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div style="margin-left: 30px;" class="rechargeManagementDeSelL2 clearfix">
					<input id="ctime" name="ctime" value="${pd.ctime }" readonly="readonly" />
					<img src="static/readchannel/images/myPic13.png" alt="" />
				</div>
				<input type="hidden" id="STATE" name="STATE" value="${pd.STATE }"/>
				<div class="rechargeManagementDeSelL3 clearfix">
					<p id="pstate">提现状态</p>
					<img src="static/readchannel/images/myPic13.png" alt="" />
					<ul>
						<li value="0">打款中</li>
						<li value="1">已打款</li>
						<li value="2">冻结</li>
					</ul>
				</div>
			</div>
			<a  href="javascript:goadd()" title=""><span>申请提现</span></a>
		</div>
		<div class="presentRecordDeTit clearfix">
			<span style="width: 56px; margin-left: 19px;">序号</span>
			<span style="width: 113px;">流水号</span>
			<span style="width: 131px;">提现日期</span>
			<span style="width: 97px;">提现金额</span>
			<span style="width: 93px;">实际金额</span>
			<span style="width: 96px;">服务费</span>
			<span style="width: 84px;">提现状态</span>
			<span>备注</span>
		</div>
		<div class="presentRecordDeTex">
		<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
					<ul>
						<c:forEach items="${varList}" var="var" varStatus="vs">
						<li class="clearfix" <c:if test="${(vs.index+1)%2==0}">style="background: #fafafa;"</c:if>>
							<p class="presentRecordDeTex1">${vs.index+1 }</p>
							<p class="presentRecordDeTex2">${var.REMITTANCE_LOG_ID }</p>
							<p class="presentRecordDeTex3">${var.CREATE_TIME }</p>
							<p class="presentRecordDeTex4">${var.MONEY/100 }</p>
							<p class="presentRecordDeTex5">${var.MONEY/100 }</p>
							<p class="presentRecordDeTex6">0</p>
							<p class="presentRecordDeTex7">
							<c:if test="${var.STATE==0}">打款中</c:if>
							<c:if test="${var.STATE==1}">已打款</c:if>
							<c:if test="${var.STATE==2}">冻结</c:if></p>
							<p class="presentRecordDeTex8">${var.MEMO }</p>
						</li>
						</c:forEach>
				</ul>
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
				</c:choose>
				 
		</div>
		<div style="margin-left: 30px;" class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
		</form>
	</div>
	<script src="static/readchannel/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
	function goadd(){
		var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="申请提现";
		 diag.URL =  '<%=basePath%>channelaccount/presentRecordtoadd.do';
		 diag.Width = 560;
		 diag.Height = 258;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 if('${page.currentPage}' == '0'){
					 setTimeout("self.location=self.location",100);
				 }else{
					 nextPage(${page.currentPage});
				 }
			}
			diag.close();
		 };
		 diag.show();
	}
	
		var pstate ='${pd.STATE}';
		if(pstate=='0'){
			$("#pstate").html("打款中");
		}else if(pstate=='1'){
			$("#pstate").html("已打款");
		}else if(pstate=='2'){
			$("#pstate").html("冻结");
		}  
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("iframe"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		laydate.render({
		  elem: '#ctime'
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
			var state =$(this).attr('value');
			$("#STATE").val(state);
			search();
		})
		$(window).click(function(){
			$('.rechargeManagementDeSelL3').find('ul').hide();
		})
		
		 
	</script>
</body>
</html>

