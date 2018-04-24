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
	<title>站点设置修改</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/basicSetting.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
	//保存
	function save(){
		if($("#SETNAME").val()==""){
			$("#SETNAME").tips({
				side:3,
	            msg:'请输入站点名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SETNAME").focus();
			return false;
		}
		if($("#KEYWORDS").val()==""){
			$("#KEYWORDS").tips({
				side:3,
	            msg:'请输入关键词',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEYWORDS").focus();
			return false;
		}
		if($("#CUSTOM_PHONE").val()==""){
			$("#CUSTOM_PHONE").tips({
				side:3,
	            msg:'请输入客服电话',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CUSTOM_PHONE").focus();
			return false;
		}
		if($("#CUSTOM_QQ").val()==""){
			$("#CUSTOM_QQ").tips({
				side:3,
	            msg:'请输入客服QQ',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CUSTOM_QQ").focus();
			return false;
		}
		if($("#CUSTOM_EMAIL").val()==""){
			$("#CUSTOM_EMAIL").tips({
				side:3,
	            msg:'请输入客服EMAIL',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CUSTOM_EMAIL").focus();
			return false;
		}
		if($("#CUSTOM_WEBCHAT").val()==""){
			$("#CUSTOM_WEBCHAT").tips({
				side:3,
	            msg:'请输入客服微信号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CUSTOM_WEBCHAT").focus();
			return false;
		}
		$("#Form").submit();
	}
	
</script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">基础设置</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">站点设置</a>
	</div>
	<form action="channelwebchat/editsiteset.do" name="Form" id="Form" method="post">
	<input type="hidden" id="WEBCHAT_ID" name="WEBCHAT_ID" value="${webchat.WEBCHAT_ID }"/>
	<div class="siteSettingModifiDe">
		<div class="necessarySettingDeTop clearfix">
			<p>站点信息</p>
			<a href="javascript:void(0)" title=""><span>查看教程</span></a>
		</div>
		<div class="siteSettingModifiInput clearfix">
			<span>站点名称</span>
			<div>
				<input placeholder="请输入站点名称" id="SETNAME" name="SETNAME" value="${webchat.SETNAME }"/>
				<p>请输入站点名称</p>
			</div>
		</div>
		<div class="siteSettingModifiInput clearfix">
			<span>关键词</span>
			<div>
				<input placeholder="请输入关键词" id="KEYWORDS" name="KEYWORDS" value="${webchat.KEYWORDS }"/>
			</div>
		</div>
		<div class="siteSettingModifiInput clearfix">
			<span>客服电话</span>
			<div>
				<input placeholder="请输入客服电话" id="CUSTOM_PHONE" name="CUSTOM_PHONE" value="${webchat.CUSTOM_PHONE }"/>
			</div>
		</div>
		<div class="siteSettingModifiInput clearfix">
			<span>客服QQ</span>
			<div>
				<input placeholder="请输入客服QQ" id="CUSTOM_QQ" name="CUSTOM_QQ" value="${webchat.CUSTOM_QQ }"/>
			</div>
		</div>
		<div class="siteSettingModifiInput clearfix">
			<span>客服邮箱</span>
			<div>
				<input placeholder="请输入客服邮箱" id="CUSTOM_EMAIL" name="CUSTOM_EMAIL" value="${webchat.CUSTOM_EMAIL }"/>
			</div>
		</div>
		<div class="siteSettingModifiInput clearfix">
			<span>客服微信</span>
			<div>
				<input placeholder="请输入客服微信" id="CUSTOM_WEBCHAT" name="CUSTOM_WEBCHAT" value="${webchat.CUSTOM_WEBCHAT }"/>
			</div>
		</div>
		<div class="siteSettingModifiInput clearfix">
			<span>公众号名称</span>
			<div>
			<input placeholder="${webchat.NAME }" id="NAME" name="NAME" value="${webchat.NAME }" readonly/>
			</div>
		</div>
		<div class="siteSettingModifiTxt clearfix">
			<span>描述</span>
			<div>
				<textarea placeholder="请输入描述" id="MEMO" name="MEMO"  >${webchat.MEMO }</textarea>
			</div>
		</div>
		<div class="siteSettingModifiBtn clearfix">
			<a href="javascript:save()" title="">保&nbsp;&nbsp;存</a>
			<span>取&nbsp;&nbsp;消</span>
		</div>
	</div>
	</form>
	<script>
		$('.siteSettingModifiInput div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.siteSettingModifiInput div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.siteSettingModifiInput div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		$('.siteSettingModifiTxt div p').click(function(){
			$(this).hide();
			$(this).siblings('textarea').focus();
		})
		$('.siteSettingModifiTxt div textarea').bind('textarea propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.siteSettingModifiTxt div textarea').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
	</script>
</body>
</html>

