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
	<title>读者信息总览</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="myTab()"  style="background:#fff">
	<div class="readerInformationAll">
		<h1>基本信息</h1>
		<div class="readerInformationBasic">
			<div class="clearfix">
				<span>平台 ID</span>
				<p>${wxpd.USER_CODE }</p>
			</div>
			<div class="clearfix">
				<span>用户昵称</span>
				<p>${wxpd.NICKNAME }</p>
			</div>
			<div class="clearfix">
				<span>微信号</span>
				<p>1245874598mn</p>
			</div>
			<div class="clearfix">
				<span>绑定手机</span>
				<p>
					<c:if test="${var.PHONE  == null }">
							无
					</c:if>
					<c:if test="${var.PHONE  != null }">
						${var.PHONE }
					</c:if>
				</p>
			</div>
			<div class="clearfix">
				<span>注册时间</span>
				<p>${wxpd.CREATE_TIME }</p>
			</div>
			<div class="clearfix">
				<span>平台累计充值</span>
				<p>${wxpd.RECHARGE_MONEY }元</p>
			</div>
		</div>
		<h1 style="margin-top: 30px;">渠道信息</h1>
		<div class="readerInformationChannelTit clearfix">
			<span style="margin-left: 19px; width: 84px;">渠道名称</span>
			<span style="width: 114px;">渠道ID</span>
			<span style="width: 86px;">关注状态</span>
			<span style="width: 129px;">注册日期</span>
			<span style="width: 71px;">VIP状态</span>
			<span style="width: 86px;">累计充值</span>
			<span style="width: 100px;">累计阅读币</span>
			<span style="width: 87px;">阅读币余额</span>
			<span style="width: 96px;">使用阅读币</span>
			<span>签到阅读币</span>
		</div>
		<div class="readerInformationChannelTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varlist}">
				<c:forEach items="${varlist}" var="var">
				<li class="clearfix">
					<p class="readerInformationChannelTex1">${var.NAME }</p>
					<p class="readerInformationChannelTex2">${var.USER_ID }</p>
					<p class="readerInformationChannelTex3">
						<c:if test="${var.FOLLOWSTATE  == 0 }">
							未关注
						</c:if>
						<c:if test="${var.FOLLOWSTATE  == 1 }">
							已关注
						</c:if>
					</p>
					<p class="readerInformationChannelTex4">${var.CREATE_TIME }</p>
					<p class="readerInformationChannelTex5">
						<c:if test="${var.ISVIP  == 0 }">
							普通会员
						</c:if>
						<c:if test="${var.ISVIP  == 1 }">
							VIP会员
						</c:if>
					</p>
					<p class="readerInformationChannelTex6">${var.RECHARGE_MONEY }</p>
					<p class="readerInformationChannelTex7">${var.CUMULATIVE_CURRENCY }</p>
					<p class="readerInformationChannelTex8">${var.BOOK_CURRENCY }</p>
					<p class="readerInformationChannelTex9">${var.SURPLS }</p>
					<p class="readerInformationChannelTex10">
						<c:if test="${var.SLYDB  == null }">
							0
						</c:if>
						<c:if test="${var.SLYDB  != null }">
							${var.SLYDB }
						</c:if>
					</p>
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
	</div>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
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
		  elem: '#test6'
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