<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
//	Integer signflag = (Integer)request.getAttribute("signflag");//0未开启签到功能 1 已签到2未签到
//	UserInfo user = (UserInfo)request.getAttribute("user");
//	System.out.println(user.toString());
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>写书评</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
     <script>
     function save(){
    	 var len=$("#content").val().length;
    	 if(len>5){
    		 $("#form").submit();
    	 }
     }
     </script>
</head>
<body>
<form id="form" name="form" action="<%=basePath%>discuss/saveDiscuss/${articleId}" method="post">
<input type="hidden" id="level" name="level" value="0"/>
	<div class="reviewTop">
		<div>
			<span class="clearfix">
				<img src="pages/home/read/images/myPic41.png" alt="" />
				<img src="pages/home/read/images/myPic41.png" alt="" />
				<img src="pages/home/read/images/myPic41.png" alt="" />
				<img src="pages/home/read/images/myPic41.png" alt="" />
				<img src="pages/home/read/images/myPic41.png" alt="" />
			</span>
		</div>
		<p>点亮星星评分</p>
	</div>
	<div class="reviewInput clearfix">
		<textarea placeholder="写写你对该书籍的感想吧~~（不少于5个字）" id="content" name="content"></textarea>
		<p><span>0</span>/1000</p>
	</div>
	<div style="height: 1.2rem;"></div>
	<div class="reviewBtn" onclick="save()">提&nbsp;&nbsp;&nbsp;交</div>
	</form>
	<script>
		$('.reviewInput textarea').bind('input propertychange', function() {
			var len=$(this).val().length;
			$(this).siblings('p').find('span').text(len);
			if(len>=5){
				$('.reviewBtn').css('background','#f37526');
			}else{
				$('.reviewBtn').css('background','#cccccc');
			}
		});
		$('.reviewTop div span img').click(function(){
			var eq=$(this).index()+1;
			$("#level").val(eq);
			//alert(eq);
			$(this).attr('src','pages/home/read/images/myPic42.png');
			$(this).prevAll().attr('src','pages/home/read/images/myPic42.png');
			$(this).nextAll().attr('src','pages/home/read/images/myPic41.png');
		})

	</script>

	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>


