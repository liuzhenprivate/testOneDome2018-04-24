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
	<title>普通充值（充值设置）</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">充值配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" href="generalRecharge.html" title="">普通充值</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">充值设置</a>
	</div>
	<form action="<%=basePath%>rechargeset/edit.do" id="rechargeForm" name="rechargeForm" method="post">
	<div class="generalRechargeSetDe">
		<h1>充值设置</h1>
		<div class="generalRechargeSetDeInp clearfix">
			<span>充值金额</span>
			<div class="clearfix">
				<input name="MONEY" id="MONEY" value="" onKeyUp="BOOK_CURRENCYVAL();" placeholder="请输入充值金额单位元"/>
			</div>
		</div>
		<div class="generalRechargeSetDeNor clearfix">
			<span>阅读币</span>
			<p><i id="BOOK_CURRENCY1">0</i></p>个
			<input type="hidden" name="BOOK_CURRENCY" id="BOOK_CURRENCY" value="0" />
		</div>
		<div style="margin-top: 9px;" class="generalRechargeSetDeInp clearfix">
			<span>赠送</span>
			<div class="clearfix">
				<input  name="BOOK_CURRENCY_GIFT" id="BOOK_CURRENCY_GIFT" value="" placeholder="请输入赠送阅读币数量"/>
			</div>
		</div>
		<div class="generalRechargeSetDeSel clearfix">
			<span>选择可用范围</span>
			<div class="generalRechargeSetDeSelDiv clearfix">
				<p>所有读者</p>
				<input type="hidden" name="USE_SCOPE" id="USE_SCOPE" value="0">
				<img src="static/read/images/myPic15.png" alt="" />
				<ul>
					<li onclick="USE_SCOPE('0');">所有读者</li>
					<li onclick="USE_SCOPE('1');">VIP读者</li>
				</ul>
			</div>
		</div>
		<div class="generalRechargeSetDeSel clearfix">
			<span>选择购买限制</span>
			<div class="generalRechargeSetDeSelDiv clearfix">
				<p>无限制</p>
				<input type="hidden" name="BUY_LIMIT" id="BUY_LIMIT" value="0">
				<img src="static/read/images/myPic15.png" alt="" />
				<ul>
					<li  onclick="BUY_LIMIT('0');">无限制</li>
					<li  onclick="BUY_LIMIT('1');">VIP读者</li>
				</ul>
			</div>
		</div>
		<div class="generalRechargeSetDeInp clearfix">
			<span>选择有效期</span>
			<div class="clearfix">
				<input id="EXP_DATE" name="EXP_DATE"  value="永久有效" style="width:455px; margin-right:10px;"> 
			</div>
		</div>
		
		<div class="generalRechargeSetDeInp clearfix datevalue">
			<span>排列序号</span>
			<div class="clearfix">
				<input  name="SORT_NUM" id="SORT_NUM" value="" placeholder="请输入排列序号"/>
			</div>
		</div>
		<div class="generalRechargeSetDeTex clearfix">
			<span>备注</span>
			<div class="clearfix">
				<textarea  name="MEMO" id="MEMO" placeholder="请输入备注"></textarea>
			</div>
		</div>
		<div class="generalRechargeSetDeBtn clearfix">
			<a href="javascript:rechargeset()" title="">完&nbsp;&nbsp;成</a>
			<span><a href="javascript:history.go(-1);" title="">取&nbsp;&nbsp;消</a></span>
		</div>
	</div>
	</form>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script src="static/read/DateJs/laydate.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script>
		function rechargeset(){
			if($("#MONEY").val()==""){
				$("#MONEY").tips({
					side:3,
			        msg:'请输入充值金额',
			        bg:'#FF6600',
			        time:2
			     });
				$("#MONEY").focus();
				return false;
			}
			$("#rechargeForm").attr("action","<%=basePath%>rechargeset/save.do");
			$("#rechargeForm").submit();
		}
	</script>
	<script>
		function BOOK_CURRENCYVAL(){
			var num = $("#MONEY").val()*100;
			$("#BOOK_CURRENCY1").text(num);
			$("#BOOK_CURRENCY").val(num);
		}
		function USE_SCOPE(data){
			$("#USE_SCOPE").val(data);
		}
		function BUY_LIMIT(data){
			$("#BUY_LIMIT").val(data);
		}
	</script>
	<script>
		$(document).on("click","#laydate_clear",function(){
			$("#EXP_DATE").val("永久有效");
		})
		
		$(window).click(function(){
			if($("#EXP_DATE").val()==""){
				$("#EXP_DATE").val("永久有效");
			}
		});

		!function(){
			laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
			laydate({elem: '#EXP_DATE'});//绑定元素
		}();
		
		$('.generalRechargeSetDeInp div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.generalRechargeSetDeInp div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
        	}
		});
		
		$('.generalRechargeSetDeInp div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
		$(window).click(function(){
			$('.generalRechargeSetDeSelDiv').find('ul').hide();
		})
		$('.generalRechargeSetDeSelDiv').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.generalRechargeSetDeSelDiv ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent().siblings('p').text(val);
			e.stopPropagation();
		})
		
		$('.generalRechargeSetDeTex div p').click(function(){
			$(this).hide();
			$(this).siblings('textarea').focus();
		})
		$('.generalRechargeSetDeTex div textarea').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.generalRechargeSetDeTex div textarea').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
	</script>
</body>
</html>

