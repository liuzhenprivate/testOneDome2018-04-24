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
	<title>普通充值</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">充值配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">普通充值</a>
	</div>
	<form action="<%=basePath%>rechargeset/list.do" id="rForm" name="rForm" method="post">
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
			<a href="<%=basePath%>rechargeset/goAdd.do" title=""><span>新增充值</span></a>
		</div>
		<div class="generalRechargeTit clearfix">
			<span style="margin-left: 19px; width: 116px;">充值额度</span>
			<span style="width: 103px;">阅读币</span>
			<span style="width: 95px;">赠送</span>
			<span style="width: 126px;">可用范围</span>
			<span style="width: 116px;">购买限制</span>
			<span style="width: 96px;">排序</span>
			<span style="width: 106px;">有效期</span>
			<span style="width: 106px;">状态</span>
			<span>管理操作</span>
		</div>
		<div class="generalRechargeTex clearfix">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="generalRechargeTexOn">${var.MONEY }</div>
						<div class="generalRechargeTexTw">${var.BOOK_CURRENCY }</div>
						<div class="generalRechargeTexTh">${var.BOOK_CURRENCY_GIFT }</div>
						<div class="generalRechargeTexFo">
							<c:if test="${var.USE_SCOPE  == 0 }">
								所有读者
							</c:if>
							<c:if test="${var.USE_SCOPE  == 1 }">
								VIP读者
							</c:if>
						</div>
						<div class="generalRechargeTexFi">
							<c:if test="${var.BUY_LIMIT  == 0 }">
								无限制
							</c:if>
							<c:if test="${var.BUY_LIMIT  == 1 }">
								VIP读者
							</c:if>
						</div>
						<div class="generalRechargeTexSi">${var.SORT_NUM }</div>
						<div class="generalRechargeTexSe">
							<c:choose>
								<c:when test="${not empty var.EXP_DATE}">
									${var.EXP_DATE}
								</c:when>
								<c:otherwise>
									永久有效
								</c:otherwise>
							</c:choose>
						</div>
						<div class="generalRechargeTexEi">
							<c:if test="${var.STATE  == 0 }">
								显示
							</c:if>
							<c:if test="${var.STATE  == 1 }">
								隐藏
							</c:if>
						</div>
						<div class="generalRechargeTexNi">
							<span>操作</span>
							<div>
								<a href="<%=basePath%>rechargeset/goEdit?RECHARGE_SET_ID=${var.RECHARGE_SET_ID}')" title="">编辑</a>
								<c:if test="${var.STATE  == 0 }">
									<a  href="<%=basePath%>rechargeset/update.do?RECHARGE_SET_ID=${var.RECHARGE_SET_ID}&STATE=1">隐藏</a>
								</c:if>
								<c:if test="${var.STATE  == 1 }">
									<a  href="<%=basePath%>rechargeset/update.do?RECHARGE_SET_ID=${var.RECHARGE_SET_ID}&STATE=0">显示</a>
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
			$("#rForm").attr("action","<%=basePath%>rechargeset/list.do?STATE="+data);
			$("#rForm").submit();
		}
	
		//删除
		function del(Id){
			$("#rForm").attr("action","<%=basePath%>rechargeset/delete.do?RECHARGE_SET_ID="+Id);
			$("#rForm").submit();
		}
		
		function fun222(){
			$.post("<%=basePath%>rechargeset/delete.do?RECHARGE_SET_ID="+Id,function(data,status){
				location.reload();
			});
		}
	</script>
	<script>
		function CrbtOrders(){
			//location.reload();
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("Thunder"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		
		$(window).click(function(){
			$('.vipRechargeTopL1').find('ul').hide();
			$('.generalRechargeTexNi div').hide();
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
		
		$('.generalRechargeTexNi span').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			e.stopPropagation();
		})
		$('.generalRechargeTexNi div a').click(function(e){
			$(this).parent('div').hide();
			e.stopPropagation();
		})
	</script>
</body>
</html>