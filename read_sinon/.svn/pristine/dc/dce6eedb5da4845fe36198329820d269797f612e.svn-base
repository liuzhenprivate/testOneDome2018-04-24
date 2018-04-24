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
	<title>渠道打款</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script>
		function search(){
			$("#Form").submit();
		}
	</script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">渠道管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">渠道打款</a>
	</div>
	<div class="channelMoneyDe">
		<div class="channelMoneyTop">
			<div>
				<p>平台余额</p>
				<h2>${balance }</h2>
			</div>
			<span></span>
			<div>
				<p>累计打款金额</p>
				<h2>${countrs.MONEY }</h2>
			</div>
			<span></span>
			<div>
				<p>本月打款</p>
				<h2>${monthrs.MONEY }</h2>
			</div>
			<span></span>
			<div>
				<p>昨日打款</p>
				<h2>${ysdayrs.MONEY }</h2>
			</div>
		</div>
		<!-- 检索  -->
		<form action="remittancelog/list.do" method="post" name="Form" id="Form">
		<div class="channelDetailsDeInfor clearfix">
			<h2 style="margin-top: 30px;">收款信息</h2>
		</div>
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input type="text" name="names" id="names" value="${pd.names }"/>
					<p>输入渠道名称或开户人姓名搜索</p>
					<img src="static/read/images/myPic26.png" alt="" onclick="search();"/>
				</div>
				<div class="rechargeManagementDeSelL2 clearfix">
					<input id="ctime" name="ctime" value="${pd.ctime }" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
				<input type="hidden" id="STATE" name="STATE" value="${pd.STATE }"/>
				<div class="rechargeManagementDeSelL3 clearfix">
					<p id="pstate">打款状态</p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul>
						<li value="0">未打款</li>
						<li value="1">已打款</li>
						<li value="2">冻结</li>
					</ul>
				</div>
			</div>
			<a href="javascript:toExcel()" title=""><span>导出列表</span></a>
		</div>
		<div class="channelMoneyTit clearfix">
			<span style="width: 65px; margin-left: 19px;">渠道名称</span>
			<span style="width: 65px;">所属公司</span>
			<span style="width: 174px;">申请时间</span>
			<span style="width: 124px;">银行账户</span>
			<span style="width: 76px;">开户人姓名</span>
			<span style="width: 68px;">提现金额</span>
			<span style="width: 128px;">累计提现</span>
			<span style="width: 88px;">状态</span>
			<span>操作</span>
		</div>
		<div class="channelMoneyTex clearfix">
			<ul>
			<c:forEach items="${varList}" var="var" varStatus="vs">
			<li class="clearfix" <c:if test="${(vs.index+1)%2==0}">style="background: #fafafa;"</c:if>>
					<div class="channelMoneyTex1">${var.NAME}</div>
					<div class="channelMoneyTex2">${var.COMPANY}</div>
					<div class="channelMoneyTex3">${var.CREATE_TIME}</div>
					<div class="channelMoneyTex4">${var.OPEN_CARD}/${var.CARD_NUM }</div>
					<div class="channelMoneyTex5">${var.ACCOUNT_NAME}</div>
					<div class="channelMoneyTex6">${var.MONEY}</div>
					<div class="channelMoneyTex7">${var.WITHDRAW}</div>
					<div class="channelMoneyTex9">${var.STATE}
					<c:if test="${var.STATE==0}">未打款</c:if>
					<c:if test="${var.STATE==1}">已打款</c:if>
					<c:if test="${var.STATE==2}">冻结</c:if>
					</div>
					<div class="channelMoneyTex10 clearfix">
						<i>操作</i>
						<div class="channelMoneyTexDown">
							<a>完成打款</a>
							<a>冻结</a>
						</div>
					</div>
				</li>
			</c:forEach>
				 
				 
			</ul>
		</div>
		<div style="margin-left: 30px;" class="flipTwo clearfix">
			${page.pageStr}
		</div>
		</form>
	</div>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
		var pstate ='${pd.STATE}';
		if(pstate=='0'){
			$("#pstate").html("未打款");
		}else if(pstate=='1'){
			$("#pstate").html("已打款");
		}else if(pstate=='2'){
			$("#pstate").html("冻结");
		}  
		//导出excel
		function toExcel(){
			 $("#Form").attr('action','<%=basePath%>remittancelog/excel.do');
			 $("#Form").submit();
			 $("#Form").attr('action','<%=basePath%>remittancelog/list.do');
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
			$('.channelMoneyTexDown').hide();
		})
		
		$('.channelMoneyTex ul li>div i').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.channelMoneyTexDown').hide();
			e.stopPropagation();
		})
		$('.channelMoneyTex ul li>div div a').click(function(e){
			e.stopPropagation();
		})
	</script>
</body>
</html>



