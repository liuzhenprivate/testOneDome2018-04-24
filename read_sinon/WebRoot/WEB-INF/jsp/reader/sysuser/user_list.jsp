﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>渠道管理渠道列表</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">渠道管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">渠道列表</a>
	</div>
	<div class="channelListDe">
		<form action="<%=basePath%>user/list.do" method="post" id="uForm" name="uForm">
		<div class="channelListDeSel clearfix">
			<div class="channelListDeSelL clearfix">
				<div class="channelListDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names }"/>
					<p>输入渠道名称或公众号名称搜索</p>
					<img src="static/read/images/myPic26.png"  alt="查询" onclick="sysUserSubmint();"/>
				</div>
				<div class="channelListDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
				<div class="channelListDeSelL3 clearfix">
					<p id="uSTATE"><c:if test="${pd.data  == null }">全部状态</c:if><c:if test="${pd.data != null }">${pd.data}</c:if></p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul>
						<li onclick="sysUserSubmint('全部状态');">全部状态</li>
						<li onclick="sysUserSubmint('正常');">正常</li>
						<li onclick="sysUserSubmint('冻结');">冻结</li>
						<li onclick="sysUserSubmint('删除');">删除</li>
					</ul>
				</div>
			</div>
			<a href="javascript:add()" title=""><span>新增渠道</span></a>
		</div>
		<div class="channelListDeTit clearfix">
			<span style="width: 115px; margin-left: 19px;">渠道名称</span>
			<span style="width: 107px;">所属公司</span>
			<span style="width: 116px;">渠道状态</span>
			<span style="width: 96px;">分成比例</span>
			<span style="width: 94px;">当前用户</span>
			<span style="width: 97px;">累计充值</span>
			<span style="width: 101px;">平台收益</span>
			<span style="width: 138px;">创建时间</span>
			<span>管理操作</span>
		</div>
		<div class="channelListDeTex clearfix">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
				<li class="clearfix">
					<div style="width: 115px; margin-left: 19px;">${var.NAME }</div>
					<div style="width: 107px;">${var.COMPANY }</div>
					<div style="width: 116px;">
					<c:if test="${var.STATE  == 0 }">
						正常
					</c:if>
					<c:if test="${var.STATE  == 1 }">
						冻结
					</c:if>
					<c:if test="${var.STATE  == 2 }">
						删除
					</c:if>
					</div>
					<div style="width: 96px;">${var.FRONTDIVIDES}:${var.DIVIDES }</div>
					<div style="width: 94px;">${var.CUSERID }</div>
					<div style="width: 97px;">${var.SUMRECHARGE_MONEY }</div>
					<div style="width: 101px;">${var.INCOMES }</div>
					<div style="width: 138px;">${var.CREATE_TIME }</div>
					<div class="clearfix">
						<i>操作</i>
						<div class="channelListDeTexDown">
							<a href="<%=basePath%>user/goSysUserDetail.do?USER_ID=${var.USER_ID}">查看详情</a>
							<c:if test="${var.STATE  == 0 }">
								<a href="javascript:updateState(${var.USER_ID},1)">冻结</a>
							</c:if>
							<c:if test="${var.STATE  == 1 }">
								<a href="javascript:updateState(${var.USER_ID},0)">解除冻结</a>
							</c:if>
							<!-- <a>重置密码</a> -->
							<a href="javascript:updateState(${var.USER_ID},2)">删除</a>
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
		</form>
	</div>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	
	<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
	<script type="text/javascript">
	
		function updateState(id,state){
			$.get("<%=basePath%>user/editstate.do?USER_ID="+id+"&STATE="+state,function(data,status){
				location.reload();
			});
		}
		function sysUserSubmint(data){
			var STATE = "";
			if(data==null){
				var data = $("#uSTATE").text();
				if(data=="正常"){
					STATE = '0';
				}else if(data=="冻结"){
					STATE = '1';
				}else if(data=="删除"){
					STATE = '2';
				}
			}else{
				if(data=="正常"){
					STATE = '0';
				}else if(data=="冻结"){
					STATE = '1';
				}else if(data=="删除"){
					STATE = '2';
				}
			}
	   		$("#uForm").attr("action","<%=basePath%>user/list.do?STATE="+STATE+"&data="+data);
			$("#uForm").submit();
		}
	</script>
	<script>
		function add(){
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增渠道";
			 diag.URL = '<%=basePath%>user/goAdd.do';
			 diag.Width = 560;
			 diag.Height = 305;
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
		$('.channelListDeSelL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.channelListDeSelL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.channelListDeSelL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
		$('.channelListDeSelL3').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.channelListDeSelL3 ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent('ul').siblings('p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.channelListDeSelL3').find('ul').hide();
			$('.channelListDeTexDown').hide();
		})
		
		
		$('.channelListDeTex ul li>div i').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.channelListDeTexDown').hide();
			e.stopPropagation();
		})
		$('.channelListDeTex ul li>div div a').click(function(e){
			e.stopPropagation();
		})
		
		$('.flipTwoC1').click(function(){
			var spanVal=parseInt($('.flipTwoC span').text());
			spanVal++;
			$('.flipTwoC span').text(spanVal+'条/页');
		})
		$('.flipTwoC2').click(function(){
			var spanVal=parseInt($('.flipTwoC span').text());
			if(spanVal>0){
				spanVal--;
			}
			$('.flipTwoC span').text(spanVal+'条/页');
		})
		
		
	</script>
</body>
</html>
