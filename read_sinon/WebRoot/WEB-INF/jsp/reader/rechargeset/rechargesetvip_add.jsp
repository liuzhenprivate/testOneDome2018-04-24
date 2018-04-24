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
	<title>vip充值（充值设置）</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		
	</script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">充值配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" href="vipRecharge.html" title="">vip充值</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">充值设置</a>
	</div>
	<form action="<%=basePath%>rechargeset/editvip.do" id="rechargeForm" name="rechargeForm" method="post">
	<div class="vipRechargeSetDe">
		<h1>充值设置</h1>
		<div class="vipRechargeSetDeSel clearfix">
			<span>充值类型</span>
			<div class="vipRechargeSetDeSelDiv clearfix">
				<input type="hidden" name="RECHARGE_TYPE" id="RECHARGE_TYPE" value="0">
				<p>单个包月</p>
				<img src="static/read/images/myPic15.png" alt="" />
				<ul>
					<li  onclick="RECHARGE_TYPE('0');">单个包月</li>
					<li  onclick="RECHARGE_TYPE('1');">连续包月</li>
				</ul>
			</div>
		</div>
		<div class="vipRechargeSetDeInp clearfix">
			<span>充值金额</span>
			<div class="clearfix">
				<input name="MONEY" id="MONEY" value="" placeholder="请输入充值金额单位元"/>
			</div>
			<i>/月</i>
		</div>
		<div class="vipRechargeSetDeTim clearfix">
			<span>免费时段</span>
			<div class="clearfix">
				<input id="startTime" name="startTime" value="" readonly="readonly" />
				<img src="static/read/images/myPic15.png" alt="" />
			</div>
			<i>~</i>
			<div style="margin-left: 0;" class="clearfix">
				<input id="endTime" name="endTime" value="" readonly="readonly" />
				<img src="static/read/images/myPic15.png" alt="" />
			</div>
		</div>
		<div class="vipRechargeSetDeSel clearfix">
			<span>购买限制</span>
			<div class="vipRechargeSetDeSelDiv clearfix">
				<p>无限制</p>
				<input type="hidden" name="BUY_LIMIT" id="BUY_LIMIT" value="0">
				<img src="static/read/images/myPic15.png" alt="" />
				<ul>
					<li  onclick="BUY_LIMIT('0');">无限制</li>
					<li  onclick="BUY_LIMIT('1');">VIP读者</li>
				</ul>
			</div>
		</div>
		<div class="vipRechargeSetDeTim clearfix">
			<span>有效期</span>
			<div class="clearfix"  style="width:498px">
				<input id="EXP_DATE" name="EXP_DATE" value="日期为空或不选择日期，则有效期为永久有效" readonly="readonly" style="width:498px"/>
				<img src="static/read/images/myPic15.png" alt="" />
			</div>
		</div>
		<div class="vipRechargeSetDeInp clearfix">
			<span>排列序号</span>
			<div class="clearfix">
				<input name="SORT_NUM" id="SORT_NUM" value="" placeholder="请输入排列序号"/>
			</div>
		</div>
		<div class="vipRechargeSetDeInp clearfix">
			<span>VIP名称</span>
			<div class="clearfix">
				<input name="RECHARGE_NAME" id="RECHARGE_NAME" value="" placeholder="请输入VIP名称"/>
			</div>
		</div>
		<div class="vipRechargeSetDeTex clearfix">
			<span>备注</span>
			<div class="clearfix">
				<textarea name="MEMO" id="MEMO" placeholder="请输入备注内容"></textarea>
			</div>
		</div>
		<div class="vipRechargeSetDeBtn clearfix">
			<a href="javascript:rechargesetvip()" title="">完&nbsp;&nbsp;成</a>
			<span><a href="javascript:history.go(-1);" title="">取&nbsp;&nbsp;消</a></span>
		</div>
	</div>
	</form>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script>
		function rechargesetvip(){
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
			if($("#RECHARGE_NAME").val()==""){
				$("#RECHARGE_NAME").tips({
					side:3,
		            msg:'请输入VIP名称',
		            bg:'#FF6600',
		            time:2
		        });
				$("#MONEY").focus();
				return false;
			}
			$("#rechargeForm").attr("action","<%=basePath%>rechargeset/savevip.do");
			$("#rechargeForm").submit();
		}
		function RECHARGE_TYPE(data){
			$("#RECHARGE_TYPE").val(data);
		}
		function BUY_LIMIT(data){
			$("#BUY_LIMIT").val(data);
		}
	</script>
	<script>
		var ins22 = laydate.render({
		  elem: '#startTime'
		  ,type: 'time'
		});
		var ins23 = laydate.render({
		  elem: '#endTime'
		  ,type: 'time'
		});
		var ins24 = laydate.render({
		  elem: '#EXP_DATE'
		  ,min: '2016-01-01'
		  ,max: '2080-12-31'
		  ,ready: function(){
		    ins24.hint('日期可选值设定在 <br> 2016-01-01 到 2080-12-31');
		  }
		});
		
		$(document).on('click','.laydate-btns-clear',function(){
			alert();
		});
		
		$(window).click(function(){
			$('.vipRechargeSetDeSelDiv').find('ul').hide();
		})
		$('.vipRechargeSetDeSelDiv').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.vipRechargeSetDeSelDiv ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent().siblings('p').text(val);
			e.stopPropagation();
		})
		
		$('.vipRechargeSetDeInp div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.vipRechargeSetDeInp div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.vipRechargeSetDeInp div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
		$('.vipRechargeSetDeTex div p').click(function(){
			$(this).hide();
			$(this).siblings('textarea').focus();
		})
		$('.vipRechargeSetDeTex div textarea').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.vipRechargeSetDeTex div textarea').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
	</script>
</body>
</html>

