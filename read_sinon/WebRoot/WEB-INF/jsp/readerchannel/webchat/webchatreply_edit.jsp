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
	<title>基础设置（自动回复）新增</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/public.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/basicSetting.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			 
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
		if($("#CONTENT").val()==""){
			$("#CONTENT").tips({
				side:3,
	            msg:'请输入回复内容',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTENT").focus();
			return false;
		}
		 
		 
		$("#Form").submit();
		$("#zhongxin").hide();
	}
	
</script>
</head>
<body>
<form action="channelwebchatreply/${msg }.do" name="Form" id="Form" method="post">
<input type="hidden" name="WEBCHATREPLY_ID" id="WEBCHATREPLY_ID" value="${pd.WEBCHATREPLY_ID}"/>
		<div id="zhongxin">
	<div class="automaticReplyPlusAlert">
		<div class="automaticReplyPlusDe">
			<div class="automaticReplyPlusInp clearfix">
				<span>关键词：</span>
				<div class="clearfix">
					<input placeholder="请输入关键词" name="KEYWORDS" id="KEYWORDS" value="${pd.KEYWORDS}"/>
				</div>
			</div>
			<div class="automaticReplyPlusTex clearfix">
				<span>回复内容：</span>
				<div class="clearfix">
					<textarea placeholder="请输入回复内容"  name="CONTENT" id="CONTENT" > ${pd.CONTENT} </textarea>
				</div>
			</div>
			<div class="automaticReplyPlusSel clearfix">
				<span>失效时间：</span>
				<div class="clearfix">
					<input  readonly="readonly" name="LOSE_TIME" id="LOSE_TIME" value="${pd.LOSE_TIME}"/>
					<img src="static/readchannel/images/myPic13.png" alt="" />
				</div>
			</div>
		</div>
		<div class="automaticReplyBtn" onclick="save();">
			完成并应用
		</div>
	</div>
	</div>
</form>
	<script src="static/readchannel/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
		$('.automaticReplyPlusInp div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.automaticReplyPlusInp div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.automaticReplyPlusInp div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		$('.automaticReplyPlusTex div p').click(function(){
			$(this).hide();
			$(this).siblings('textarea').focus();
		})
		$('.automaticReplyPlusTex div textarea').bind('textarea propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.automaticReplyPlusTex div textarea').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
		laydate.render({
		  elem: '#LOSE_TIME'
		  ,type: 'datetime'
		});
	</script>
</body>
</html>
