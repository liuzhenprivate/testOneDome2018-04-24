<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
	//List<Rechargeset> rechargelist = (List<Rechargeset>)request.getAttribute("rechargelist");
//	UserInfo user = (UserInfo)request.getAttribute("user");
//	System.out.println(user.toString());
	String rurl = request.getParameter("rurl");
	String webchatid = request.getParameter("webchatid");
	System.out.println("rurl="+rurl);
	if(null==rurl || "".equals(rurl) || "null".equals(rurl)){
		rurl = "recharge/viplist/"+webchatid;
	}
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>开通VIP</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f8f8f8;">
	<div class="openVipT">
		<h2>￥<span>${rechargeset.money/100 }</span>/月</h2>
		<p>${rechargeset.rechargeName }</p>
	</div>
	<div class="openVipC clearfix">
		<span>微信支付</span>
		<img src="pages/home/read/images/myPic75.png" alt="" />
	</div>
	<div class="openVipB">
		<h2>连续包月VIP说明</h2>
		<p>1.连续包月套餐享购书超低折扣。</p>
		<p>2.扣费方式: 以1个月为期，到期前一天，自动扣除费用。</p>
		<p>3.连续包月套餐可随时退订，具体退订以及绑定说明待完善。</p>
	</div>
	<div style="height: 1.2rem;"></div>
	<div class="openVipBtn" onclick="pay()">确认支付</div>
	<script>
	function onBridgeReady(){
		//alert('${appId}'+"=timeStamp="+"${timeStamp}"+"=nonceStr="+"${nonceStr}"+"=prepayId=="+"${prepayId}"+"=paySign="+"${paySign}");
   		WeixinJSBridge.invoke(
	       'getBrandWCPayRequest', {
	           "appId":"${appId}",     //公众号名称，由商户传入     
	           "timeStamp":"${timeStamp}",         //时间戳，自1970年以来的秒数     
	           "nonceStr":"${nonceStr}", //随机串     
	           "package":"${prepayId}",     
	           "signType":"MD5",         //微信签名方式：     
	           "paySign":"${paySign}" //微信签名 
	       },
	       function(res){  
	    	   //alert(res.err_msg);
	           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
	        	   save(1);
	        	   //alert("ok");
	        	   window.location.href="<%=basePath+rurl%>";
	           }else{
	        	   save(8);
	           }
	           // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
       		}
   		); 
	}
	function pay(){
		//alert("pay");
		if (typeof WeixinJSBridge == "undefined"){
		   if( document.addEventListener ){
		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		   }else if (document.attachEvent){
		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		   }
		}else{
		   onBridgeReady();
		}
	}
	
	function save(state){
		var url = "<%=basePath%>recharge/rechargeover/${webchatid}/${tradeNo}/"+state;
		//alert(url);
		$.get(url,function(data){
			//alert(data);
		});
		
	}
	</script>

	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>

