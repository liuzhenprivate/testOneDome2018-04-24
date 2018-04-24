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
	<title>必要设置修改</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/basicSetting.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
	//保存
	function save(){
		if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入公众号名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		if($("#TYPE").val()==""){
			$("#TYPE").tips({
				side:3,
	            msg:'请选择公众号类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ptype").focus();
			return false;
		}
		if($("#WEBCHAT_CODE").val()==""){
			$("#WEBCHAT_CODE").tips({
				side:3,
	            msg:'请输入微信号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#WEBCHAT_CODE").focus();
			return false;
		}
		if($("#APPID").val()==""){
			$("#APPID").tips({
				side:3,
	            msg:'请输入App ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#APPID").focus();
			return false;
		}
		if($("#APPSECRET").val()==""){
			$("#APPSECRET").tips({
				side:3,
	            msg:'请输入App Secret',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#APPSECRET").focus();
			return false;
		}
		 
		var msg = '${msg }';
		if(msg=='savewebchat'){ 
			if($("#file").val()==""){
				$("#file").tips({
					side:3,
		            msg:'请上传公众号二维码图片',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#file").focus();
				return false;
			}
		}
		$("#Form").submit();
	}
	function goindex(){
		window.location.href="<%=basePath%>channelwebchat/index";
	}
</script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">基础设置</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">必要设置</a>
	</div>
	<form action="channelwebchat/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
	<input type="hidden" id="WEBCHAT_ID" name="WEBCHAT_ID" value="${webchat.WEBCHAT_ID }"/>
	<input type="hidden" id="TYPE" name="TYPE" value="${webchat.TYPE }"/>
	<div class="necessarySettingDe">
		<div class="necessarySettingDeTop clearfix">
			<p>公众号后台配置</p>
			<a href="javascript:void(0)" title=""><span>查看教程</span></a>
		</div>
		<div class="necessarySettingDeInput clearfix">
			<span>公众号名称</span>
			<div class="clearfix">
				<input placeholder="输入公众号名称" type="text" id="NAME" name="NAME" value="${webchat.NAME }"/>
			</div>
		</div>
		<div class="necessarySettingDeSel clearfix">
			<span>公众号类型</span>
			<div class="clearfix">
				<p id="ptype">选择类型</p>
				<img src="static/readchannel/images/myPic15.png" alt="" />
				<ul>
					<li value="1">服务号</li>
					<li value="2">订阅号</li>
				</ul>
			</div>
		</div>
		<div class="necessarySettingDeInput clearfix">
			<span>微信号</span>
			<div class="clearfix">
				<input placeholder="输入微信号" type="text" id="WEBCHAT_CODE" name="WEBCHAT_CODE" value="${webchat.WEBCHAT_CODE }"/>
			</div>
		</div>
		<div class="necessarySettingDeInput clearfix">
			<span>App ID</span>
			<div class="clearfix">
				<input placeholder="输入App ID" type="text" id="APPID" name="APPID" value="${webchat.APPID }"/>
			</div>
		</div>
		<div class="necessarySettingDeInput clearfix">
			<span>App Secret</span>
			<div class="clearfix">
				<input placeholder="输入App Secret" type="text" id="APPSECRET" name="APPSECRET" value="${webchat.APPSECRET }"/>
			</div>
		</div>
		<div class="necessarySettingDeInput clearfix">
			<span>令牌</span>
			<div class="clearfix">
				<input placeholder="输入令牌" type="text" id="TOKEN" name="TOKEN" value="${webchat.TOKEN }"/>
			</div>
		</div>
		<div class="necessarySettingDeFile clearfix">
			<span>二维码</span>
			<div class="necessarySettingDeFileC">
				<img src="${webchat.PIC_URL }" alt="" />
				<i>上传二维码</i>
				<input type="file" id="file" name="file"/>
			</div>
			<div class="necessarySettingDeFileR">
				<p>推荐尺寸：300×300 px</p>
				<p>支持jpg、jpeg、gif、png，大小不超过5MB</p>
			</div>
		</div>
		<div class="necessarySettingDeBot clearfix">
			<a href="javascript:save()" title="">保&nbsp;&nbsp;存</a>
			<span onclick="javascript:goindex();" >取&nbsp;&nbsp;消</span>
		</div>
	</div>
	</form>
	<script>
		var type ='${webchat.TYPE}';
		if(type=='1'){
			$("#ptype").html("服务号");
		}else if(type=='2'){
			$("#ptype").html("订阅号");
		}
		$('.necessarySettingDeInput p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.necessarySettingDeInput input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.necessarySettingDeInput input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		$(window).click(function(){
			$('.necessarySettingDeSel div').find('ul').hide();
		})
		$('.necessarySettingDeSel div').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.necessarySettingDeSel div ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent().siblings('p').text(val);
			e.stopPropagation();
			var type=$(this).attr('value');
			$("#TYPE").val(type);
		})
		
		$(".necessarySettingDeFileC input").change(function() {
			var $file = $(this);
			var fileObj = $file[0];
			var windowURL = window.URL || window.webkitURL;
			var dataURL;
			var $img = $(this).siblings('img');
			if(fileObj && fileObj.files && fileObj.files[0]){
				dataURL = windowURL.createObjectURL(fileObj.files[0]);
				$img.attr('src',dataURL);
			}else{
				dataURL = $file.val();
				var imgObj = $img.get(0);
				imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
				}
		});
	</script>
</body>
</html>



