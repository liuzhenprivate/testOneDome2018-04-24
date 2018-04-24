<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>模板配置图片库</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<!--引入弹窗组件start-->
	<script type="text/javascript" src="static/read/js/zDrag.js"></script>
	<script type="text/javascript" src="static/read/js/zDialog.js"></script>
    <!--引入弹窗组件end-->
	<script>
	function search(){
		//alert("searchnotcie");
		//alert($("#STATE").val());
		$("#Form").submit();
	}
	$(function(){
		$('#piccid').children('li').bind('click', function(){
			//alert($(this).attr('value'));
			var state =$(this).attr('value');
			if(state !='-1'){
				$("#PIC_CATEGORY_ID").val($(this).attr('value'));
			}
			search();
		});
	});
	</script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">模板配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">图片库</a>
	</div>
	<!-- 检索  -->
	 <form action="pic/list.do" method="post" name="Form" id="Form">
	 <input type="hidden" id="PIC_CATEGORY_ID" name="PIC_CATEGORY_ID" value="${pd.PIC_CATEGORY_ID }"/>
	<div class="configuratePictureLibraryDe">
		<div class="configuratePictureLibraryTop clearfix">
			<div class="configuratePictureLibraryTopL">
				<span id="piccname">全部分类</span>
				<ul id="piccid">
				<c:forEach  items="${piccategorylist}" var="var" varStatus="vs">
				<li value="${var.PIC_CATEGORY_ID }">${var.CATEGORY_NAME }</li>
				</c:forEach>
				</ul>
			</div>
			<div class="configuratePictureLibraryTopR clearfix">
				<c:choose>
					<c:when test="${not empty varList}">
				<a href="pic/goEdit" title=""><i>编辑图片</i></a>
				</c:when>
				</c:choose>
				<span onclick="goadd()"><i>新增图片</i></span>
			</div>
		</div>
		
			
			<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
					<div class="configuratePictureLibraryedPic">
						<c:if test="${QX.cha == 1 }">
						<ul class="clearfix">
						<c:forEach items="${varList}" var="var" varStatus="vs">
						<li>
							<img src="${var.PIC_URL }" alt="" title="${var.PIC_CATEGORY_ID }"/>
						</li>
						</c:forEach>
						</ul>
						</c:if>
						<c:if test="${QX.cha == 0 }">
							 您无权查看 
						</c:if>
						</div>
					</c:when>
				<c:otherwise>
					<div class="configuratePictureLibraryPic">
						<div onclick="goadd()">
							<img src="static/read/images/myPic51.png" alt="" />
							<p>新增图片</p>
						</div>
					</div>
			
				</c:otherwise>
			</c:choose>
	</div>
	</form>
    	
	<script>
	var category_name = '${category.CATEGORY_NAME}';
	//alert(category_name);
	if(''!=category_name){
		$("#piccname").html(category_name);
	}
	function goadd(){
		var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="新增图片";
		 diag.URL =  '<%=basePath%>pic/goAdd.do';
		 diag.Width = 560;
		 diag.Height = 428;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 diag.close();
				 setTimeout("self.location=self.location",100);
			}
			 diag.close();
		 };
		 diag.show();
	}
	
	function CrbtOrders(){
		//alert(this.document.body.scrollHeight); //弹出当前页面的高度
		var obj = parent.document.getElementById("iframe"); //取得父页面IFrame对象
		//alert(obj.height); //弹出父页面中IFrame中设置的高度
		obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
	}
	$(window).click(function(){
		$('.configuratePictureLibraryTopL').find('ul').hide();
	})
	$('.configuratePictureLibraryTopL').click(function(e){
		if($(this).find('ul').css('display')=="none"){
			$(this).find('ul').show();
		}else{
			$(this).find('ul').hide();
		}
		e.stopPropagation();
	})
	$('.configuratePictureLibraryTopL ul li').click(function(e){
		$(this).css('color','#f37427');
		$(this).siblings().css('color','#666666');
		$(this).parent().hide();
		var val=$(this).text();
		$('.configuratePictureLibraryTopL span').text(val);
		e.stopPropagation();
	})
	
	 
		 
	</script>
</body>
</html>

