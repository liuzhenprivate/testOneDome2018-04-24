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
	<title>模块配置（榜单配置）</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/moduleConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">模板配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">页面配置</a>
	</div>
	<div class="listConfigurateDe">
		<h1>榜单列表</h1>
		<div class="listConfigurateDeTit clearfix">
			<span style="margin-left: 19px; width: 188px;">榜单名称</span>
			<span style="width: 254px;">排名数量</span>
			<span style="width: 221px;">排序</span>
			<span style="width: 201px;">点击数</span>
			<span>管理操作</span>
		</div>
		<div class="listConfigurateDeTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="listConfigurateDeTexOn">${var.BOARD_NAME }</div>
						<div class="listConfigurateDeTexTw">
							<span>${var.RANKING_NUMBER }</span>
							<div class="listConfigurateDeTexTwInp clearfix">
								<input title="${var.BOARD_ID }" value="" />
							</div>
						</div>
						<div class="listConfigurateDeTexTh">
							<c:if test="${var.BOARD_NO ==0 }">固定</c:if>
							<c:if test="${var.BOARD_NO !=0 }">${var.SORT}</c:if>
						</div>
						<div class="listConfigurateDeTexFo">${var.CLICK_NUMBER }</div>
						<div class="listConfigurateDeTexFi">
							<span>操作</span>
							<div class="listConfigurateDeTexFiDiv">
								<a href="<%=basePath%>boarddetail/goBoardDetail.do?BOARD_ID=${var.BOARD_ID }&BOARD_TYPE=0">编辑</a>
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
	
	
	<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("Thunder"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		$('.listConfigurateDeTexTw span').click(function(){
			var spanVal=$(this).text();
			$(this).siblings('.listConfigurateDeTexTwInp').show().find('input').val(spanVal);
			$(this).siblings('.listConfigurateDeTexTwInp').find('input').focus();
		});
		$('.listConfigurateDeTexTwInp input').blur(function(){
			var inpVal=$(this).val();
			var BOARD_ID = $(this).attr('title');
			var reg = /^[1-9]\d*$/ ;
			if (reg.test(inpVal) == true) {
				<%-- $.ajax({
					type: "post",
					data:{
						'BOARD_ID':BOARD_ID,
						'RANKING_NUMBER':inpVal
					},
					url: "<%=basePath%>board/updateRankingnum.do",
				}); --%>
				//$(this).parent().siblings('span').text(inpVal);
			}
			$(this).parent('.listConfigurateDeTexTwInp').hide();
		});
		
		$(window).click(function(){
			$('.listConfigurateDeTexFi div').hide();
		});
		$('.listConfigurateDeTexFi span').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.listConfigurateDeTexFiDiv').hide();
			e.stopPropagation();
		});
		$('.listConfigurateDeTexFi div a').click(function(e){
			$(this).parent('div').hide();
			e.stopPropagation();
		});
		
	</script>
</body>
</html>

