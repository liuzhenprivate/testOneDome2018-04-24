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
	<title>渠道信息总览</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<div class="channelformationAll">
		<div class="channelDetailsDeInfor clearfix">
			<h2>基本信息</h2>
			<div class="clearfix">
				<c:if test="${upd.STATE  == 0 }">
					<a class="channelDetailsDeInfor1" href="javascript:updateState(${pd.USER_ID},1)" title=""><span>冻结渠道</span></a>			
				</c:if>
				<c:if test="${upd.STATE  == 1 }">
					<a class="channelDetailsDeInfor1" href="javascript:updateState(${pd.USER_ID},0)" title=""><span>解除冻结</span></a>			
				</c:if>
				<c:if test="${upd.STATE  == 2 }">
					<a class="channelDetailsDeInfor1" href="javascript:void(0)" title=""><span><font style="color:red">已删除</font></span></a>	
				</c:if>
				<a class="channelDetailsDeInfor2" href="javascript:void(0)" title=""><span>重置密码</span></a>	
			</div>
		</div>
		<div class="channelDetailsDeAll clearfix">
			<ul>
				<li class="clearfix">
					<span>渠道账号</span>
					<p>${upd.USERNAME }</p>
				</li>
				<li class="clearfix">
					<span>渠道 ID</span>
					<p>${upd.USER_ID }</p>
				</li>
				<li class="clearfix">
					<span>添加日期</span>
					<p>${upd.CREATE_TIME }</p>
				</li>
				<li class="clearfix">
					<span>渠道充值</span>
					<p>${upd.RECHARGES }</p>
				</li>
				<li class="clearfix">
					<span>渠道状态</span>
					<p>
					<c:if test="${upd.STATE  == 0 }">
						正常
					</c:if>
					<c:if test="${upd.STATE  == 1 }">
						冻结
					</c:if>
					<c:if test="${upd.STATE  == 2 }">
						<font style="color:red">已删除</font>
					</c:if>
					</p>
				</li>
			</ul>
		</div>
		<div class="channelDetailsDeInfor clearfix">
			<h2>公众号信息</h2>
		</div>
		<div class="channelDetailsDeAll clearfix">
			<ul>
				<li class="clearfix">
					<span>公众号名称</span>
					<p>${wpd.NAME}</p>
				</li>
				<li class="clearfix">
					<span>公众号类型</span>
					<p>${wpd.TYPE }</p>
				</li>
				<li class="clearfix">
					<span>原始 ID</span>
					<p>空</p>
				</li>
				<li class="clearfix">
					<span>App ID</span>
					<p>${wpd.APPID }</p>
				</li>
				<li class="clearfix">
					<span>App Secret</span>
					<p>${wpd.APPSECRET }</p>
				</li>
				<li class="clearfix">
					<span>公众号二维码</span>
					<div>
						<img class="channelDetailsDeAll1" src="static/read/images/myPic63.png" alt="" />
						<img class="channelDetailsDeAll2" src="static/read/images/myPic63.png" alt="" />
					</div>
				</li>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		function updateState(id,state){
			$.get("<%=basePath%>user/editstate.do?USER_ID="+id+"&STATE="+state,function(data,status){
				location.reload();
			});
		}
	</script>
	<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("faker"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		
		$('.channelDetailsDeAll ul li div').mouseenter(function(){
			$('.channelDetailsDeAll2').show();
		});
		$('.channelDetailsDeAll ul li div').mouseleave(function(){
			$('.channelDetailsDeAll2').hide();
		});
	</script>
</body>
</html>
