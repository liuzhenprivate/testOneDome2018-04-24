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
	<base href="<%=basePath%>">
	<title>VIP充值设置</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">充值配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">VIP充值</a>
	</div>
	<form action="<%=basePath%>rechargeset/listvip.do" id="rForm" name="rForm" method="post">
	<div class="vipRechargeDe">
		<div class="vipRechargeTop clearfix">
			<div class="vipRechargeTopL clearfix">
				<div class="vipRechargeTopL1 clearfix">
					<span>状态</span>
					<ul>
						<li onclick="seachState();">全部状态</li>
						<li onclick="seachState('0');">显示</li>
						<li onclick="seachState('1');">隐藏</li>
					</ul>
				</div>
			</div>
			<a href="<%=basePath%>rechargeset/goVipAdd.do" title=""><span>新增充值</span></a>
		</div>
		<div class="vipRechargeTit clearfix">
			<span style="margin-left: 19px; width: 136px;">名称</span>
			<span style="width: 134px;">充值类型</span>
			<span style="width: 125px;">充值金额</span>
			<span style="width: 131px;">免费时段</span>
			<span style="width: 95px;">排序</span>
			<span style="width: 116px;">VIP到期时间</span>
			<span style="width: 126px;">状态</span>
			<span>管理操作</span>
		</div>
		<div class="vipRechargeTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="vipRechargeTexOn">${var.RECHARGE_NAME }</div>
						<div class="vipRechargeTexTw">
							<c:if test="${var.RECHARGE_TYPE  == 0 }">
								单独包月
							</c:if>
							<c:if test="${var.RECHARGE_TYPE  == 1 }">
								连续包月
							</c:if>
						</div>
						<div class="vipRechargeTexTh">${var.MONEY }元</div>
						<div class="vipRechargeTexFo">
							<c:choose>
								<c:when test="${not empty var.FREE_TIME}">
									${var.FREE_TIME}
								</c:when>
								<c:otherwise>
									全天
								</c:otherwise>
							</c:choose>
						</div>
						<div class="vipRechargeTexFi">${vs.index+1}</div>
						<div class="vipRechargeTexSi">
							<c:choose>
								<c:when test="${not empty var.EXP_DATE}">
									${var.EXP_DATE}
								</c:when>
								<c:otherwise>
									永久有效
								</c:otherwise>
							</c:choose>
						</div>
						<div class="vipRechargeTexSe">
							<c:if test="${var.STATE  == 0 }">
								显示
							</c:if>
							<c:if test="${var.STATE  == 1 }">
								隐藏
							</c:if>
						</div>
						<div class="vipRechargeTexEi">
							<span>操作</span>
							<div>
								<a href="<%=basePath%>rechargeset/goVipEdit?RECHARGE_SET_ID=${var.RECHARGE_SET_ID}')" title="">编辑</a>
								<c:if test="${var.STATE  == 0 }">
									<a  href="<%=basePath%>rechargeset/updatevip.do?RECHARGE_SET_ID=${var.RECHARGE_SET_ID}&STATE=1">隐藏</a>
								</c:if>
								<c:if test="${var.STATE  == 1 }">
									<a  href="<%=basePath%>rechargeset/updatevip.do?RECHARGE_SET_ID=${var.RECHARGE_SET_ID}&STATE=0">显示</a>
								</c:if>
								<a href="javascript:del('${var.RECHARGE_SET_ID}')" title="">删除</a>
							</div>
						</div>
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
	</div>
	</form>
	<script>
		function seachState(data){
			if(data!=null&&data!=""){
				data = data;
			}else{
				data="";
			}
			$("#rForm").attr("action","<%=basePath%>rechargeset/listvip.do?STATE="+data);
			$("#rForm").submit();
		}
		
		//删除
		function del(Id){
			$("#rForm").attr("action","<%=basePath%>rechargeset/deletevip.do?RECHARGE_SET_ID="+Id);
			$("#rForm").submit();
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
			$('.vipRechargeTopL1').find('ul').hide();
			$('.vipRechargeTexEi div').hide();
		})
		$('.vipRechargeTopL1').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.vipRechargeTopL1 ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent().siblings('span').text(val);
			e.stopPropagation();
		})
		
		$('.vipRechargeTexEi span').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			e.stopPropagation();
		})
		$('.vipRechargeTexEi div a').click(function(e){
			$(this).parent('div').hide();
			e.stopPropagation();
		})
	</script>
</body>
</html>