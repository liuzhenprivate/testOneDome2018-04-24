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
	<title>读者管理</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">读者管理</a>
	</div>
	<form action="<%=basePath%>wxuser/list.do" id="Form" name="Form" method="post">
	<div class="readerManagementDe clearfix">
		<div class="readerManagementDeTop clearfix">
			<div class="readerManagementDeIn clearfix">
				<p>累计读者</p>
				<h2>${pd.usersAll }</h2>
				<div class="readerManagementDeIn2">
					<div>
						<p>当月</p>
						<h3>${pd.usersMonth }</h3>
					</div>
					<div style="float: right; margin-right: 30px;">
						<p>今日</p>
						<h3>${pd.usersDay }</h3>
					</div>
				</div>
			</div>
			<div class="readerManagementDeIn clearfix">
				<p>渠道粉丝</p>
				<h2>${pd.SysUserFans }</h2>
				<div class="readerManagementDeIn2">
					<div>
						<p>当月</p>
						<h3>${pd.WXFansFeeMonth }</h3>
					</div>
					<div style="float: right; margin-right: 30px;">
						<p>今日</p>
						<h3>${pd.WXFansFeeDay }</h3>
					</div>
				</div>
			</div>
			<div class="readerManagementDeIn clearfix">
				<p>付费人数</p>
				<h2>${pd.SysUserFansFee }</h2>
				<div class="readerManagementDeIn2">
					<div>
						<p>当月</p>
						<h3>${pd.SysUserFansFeeMonth}</h3>
					</div>
					<div style="float: right; margin-right: 30px;">
						<p>今日</p>
						<h3>${pd.SysUserFansFeeDay }</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="readerManagementDeSel clearfix">
			<div class="readerManagementDeSelL clearfix">
				<div class="readerManagementDeSelL1 clearfix">
					<input type="text" />
					<p>输入分类名称</p>
					<img src="static/read/images/myPic26.png" alt="" />
				</div>
				<div class="readerManagementDeSelL2 clearfix">
					<input id="test6" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
			</div>
			<a href="javascript:void(0)" title=""><span>导出列表</span></a>
		</div>
		<div class="readerManagementDeClickdown">
			<span>展开搜索条件</span>
		</div>
		<div class="readerManagementDeClickup">
			<ul class="clearfix">
				<li style="margin-left: 19px;" class="clearfix">
					<span>关注状态</span>
					<div class="readerManagementDeClickupDiv clearfix">
						<c:if test="${pd.CTIME != null }"><p id="CTIME">${pd.CTIME}</p></c:if>
						<c:if test="${pd.CTIME == null }"><p id="CTIME">全部</p></c:if>
						<img src="static/read/images/myPic13.png" alt="" />
						<input type="hidden" name="FOLLOWSTATE" id="FOLLOWSTATE" value="${pd.FOLLOWSTATE }">
						<ul>
							<li onclick="changefs('');">全部</li>
							<li onclick="changefs('1');">已关注</li>
							<li onclick="changefs('0');">未关注</li>
						</ul>
					</div>
				</li>
				<li class="clearfix">
					<span>充值状态</span>
					<div class="readerManagementDeClickupDiv clearfix">
						<c:if test="${pd.STATEP != null }"><p id="STATEP">${pd.STATEP}</p></c:if>
						<c:if test="${pd.STATEP == null }"><p id="STATEP">全部</p></c:if>
						<input type="hidden" name="STATE" id="STATE" value="${pd.STATE }">
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li onclick="changeState('');">全部</li>
							<li onclick="changeState('0');">未付款</li>
							<li onclick="changeState('1');">已付款</li>
							<li onclick="changeState('2');">付款失败</li>
							<li onclick="changeState('3');">充值成功</li>
							<li onclick="changeState('4');">充值失败</li>
							<li onclick="changeState('5');">申请退款</li>
							<li onclick="changeState('6');">退款成功</li>
							<li onclick="changeState('7');">退款失败</li>
							<li onclick="changeState('8');">撤销支付</li>
						</ul>
					</div>
				</li>
				<li class="clearfix">
					<span>账号类型</span>
					<div class="readerManagementDeClickupDiv clearfix">
						<p>全部</p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li>全部</li>
							<li>微信</li>
							<li>手机号</li>
						</ul>
					</div>
				</li>
			</ul>
			<div class="readerManagementDeClickupKey clearfix">
				<a href="javascript:seachUserlist()"title="">查&nbsp;&nbsp;询</a>
				<span>重&nbsp;&nbsp;置</span>
			</div>
			<div class="readerManagementDeClickupBtn">
				<span>收起</span>
			</div>
		</div>
		<div class="readerManagementDeTit clearfix">
			<span style="width: 62px; margin-left: 19px;">头像</span>
			<span style="width: 125px;">昵称</span>
			<span style="width: 105px;">平台ID</span>
			<span style="width: 76px;">账号类型</span>
			<span style="width: 131px;">所属渠道</span>
			<span style="width: 76px;">关注状态</span>
			<span style="width: 121px;">注册日期</span>
			<span style="width: 90px;">阅读币余额</span>
			<span style="width: 76px;">累计充值</span>
			<span>累计阅读币</span>
		</div>
		<div class="readerManagementDeTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<a href="<%=basePath%>wxuser/readDetail.do?USERID=${var.USERID}&OPENID=${var.OPENID }" title="读者详情">
						<img src="${var.HEADIMGURL }" alt="" />
						<p class="readerManagementDeTex1">${var.NICKNAME }</p>
						<p class="readerManagementDeTex2">${var.USER_CODE }</p>
						<p class="readerManagementDeTex3">微信</p>
						<p class="readerManagementDeTex4">${var.NAME }</p>
						<p class="readerManagementDeTex5">
							<c:if test="${var.FOLLOWSTATE  == 0 }">
								未关注
							</c:if>
							<c:if test="${var.FOLLOWSTATE  == 1 }">
								已关注
							</c:if>
						</p>
						<p class="readerManagementDeTex6">${var.CREATE_TIME }</p>
						<p class="readerManagementDeTex7">${var.CUMULATIVE_CURRENCY  - var.BOOK_CURRENCY }</p>
						<p class="readerManagementDeTex8">${var.RECHARGE_MONEY }</p>
						<p class="readerManagementDeTex9">${var.CUMULATIVE_CURRENCY }</p>
						</a>
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
	</form>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript">
		function seachUserlist(){
			var CTIME = $("#CTIME").text();
			var STATEP = $("#STATEP").text();
			$("#Form").attr("action","<%=basePath%>wxuser/list.do?CTIME="+CTIME+"&STATEP="+STATEP);
			$("#Form").submit();
		}
	
		function changeState(data){
			if(data==null||data==''){
				$("#STATE").val("");
			}else{
				$("#STATE").val(data);
			}
		}
		function changefs(data){
			if(data==null||data==''){
				$("#FOLLOWSTATE").val("");
			}else{
				$("#FOLLOWSTATE").val(data);
			}
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
			$('.readerManagementDeClickupDiv').find('ul').hide();
		})
		$('.readerManagementDeClickupDiv').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.readerManagementDeClickupDiv ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent().siblings('p').text(val);
			e.stopPropagation();
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
		
		$('.readerManagementDeClickdown span').click(function(){
			$('.readerManagementDeClickdown').hide();
			$('.readerManagementDeClickup').show();
		})
		$('.readerManagementDeClickupBtn span').click(function(){
			$('.readerManagementDeClickdown').show();
			$('.readerManagementDeClickup').hide();
		})
		
		/* $('.readerManagementDeClickupKey a').click(function(){
			$('.RmciAlert', parent.document).show();
			(function(Thunder){
				$('.RmciAlert', parent.document).css('padding-top',Thunder);
			})((parent.document.documentElement.clientHeight-$('.RmciDe', parent.document).get(0).offsetHeight)/2);
		}) */
	</script>
</body>
</html>