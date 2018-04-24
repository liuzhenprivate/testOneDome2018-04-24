<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>数据统计公告管理</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/public.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/channelList.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script>
		//添加渠道
		function adduser(){
			var COMPANY = $("#COMPANY").val();
			var NAME = $("#NAME").val();
			var USERNAME = $("#USERNAME").val();
			var PASSWORD = $("#PASSWORD").val();
			var username = $("#username").val();
			if(''==COMPANY || null==COMPANY){
				$("#COMPANY").tips({
					side:3,
		            msg:'请输入所属公司',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMPANY").focus();
				return false;
			}
			if(''==NAME || null==NAME){
				$("#NAME").tips({
					side:3,
		            msg:'请输入渠道名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
				return false;
			}
			if(''==USERNAME || null==USERNAME){
				$("#USERNAME").tips({
					side:3,
		            msg:'请输入渠道账号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#USERNAME").focus();
				return false;
			}
			if(''==PASSWORD || null==PASSWORD){
				$("#PASSWORD").tips({
					side:3,
		            msg:'请输入密码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PASSWORD").focus();
				return false;
			}
			if(username=='1'){
				$("#USERNAME").tips({
					side:3,
		            msg:'账号已存在',
		            bg:'#AE81FF',
		            time:2
		        });
				return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
		} 
	</script>
</head>
<body >
<div id="zhongxin">
<form action="user/${msg }.do" name="Form" id="Form" method="post">
	<div class="newChannelsAlert">
		<div class="newChannelsDe">
			<div class="newChannelsDeInpAll">
				<div class="newChannelsDeInp clearfix">
					<span>所属公司：</span>
					<div class="clearfix">
						<input ID="COMPANY" name="COMPANY" value="" placeholder="请输入所属公司"/>
					</div>
				</div>
				<div class="newChannelsDeInp clearfix">
					<span>渠道名称：</span>
					<div class="clearfix">
						<input type="hidden" id="username" name="username" value="0">
						<input ID="NAME" name="NAME" value="" placeholder="请输入渠道名称"/>
					</div>
				</div>
				<div class="newChannelsDeInp clearfix">
					<span>渠道账号：</span>
					<div class="clearfix">
						<input id="USERNAME" name="USERNAME" value="" placeholder="请输入渠道账号"/>
					</div>
				</div>
				<div class="newChannelsDeInp clearfix">
					<span>渠道密码：</span>
					<div class="clearfix">
						<input id="PASSWORD" name="PASSWORD" value="" placeholder="请输入渠道密码"/>
						<img src="static/read/images/myPic64.png" alt="" />
					</div>
				</div>
			</div>
			<div class="newChannelsDeBtn clearfix">
				<a href="javascript:adduser()" title="">完&nbsp;&nbsp;成</a>
				<span onclick="top.Dialog.close();">取&nbsp;&nbsp;消</span>
			</div>
		</div>
	</div>
	</form>
</div>
	<script type="text/javascript">
		$('#USERNAME').blur(function(){
			var USERNAME = $('#USERNAME').val();
			if(USERNAME!=null&&USERNAME!=''){
				$.ajax({
					type:'post',
					url:'<%=basePath%>user/checkName.do',
					data:{
						'USERNAME':USERNAME
					},
					dataType:'text', 
					success: function(data){
						if(data==null||data==''||data=='true'){
							$('#username').val('1');
							$("#USERNAME").tips({
								side:3,
					            msg:'账号已存在',
					            bg:'#AE81FF',
					            time:2
					        });
							return false;
						}else{
							$('#username').val('0');
						}
					}
				});
			}
		});
	</script>
	<script>
		$('.newChannelsDeInp div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		});
		$('.newChannelsDeInp div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
        	}
		});
		$('.newChannelsDeInp div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		});
		$('.newChannelsDeTop img').click(function(){
			$('.newChannelsAlert').hide();
		});
	</script>
</body>
</html>
