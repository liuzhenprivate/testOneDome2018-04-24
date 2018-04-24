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
	<title>文案标题</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/promotionManagement.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="fileSon()">
	<div class="copywritingHeadlinesDe" >
		<form action="<%=basePath%>extensioncontent/goExtensiontitle.do" method="post" id="Form" name="Form">
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input id="CONTENT" name="CONTENT" value="" type="text" />
					<p>输入标题搜索</p>
					<img src="static/readchannel/images/myPic26.png" alt="" onclick="seachtitle1();"/>
				</div>
				<div class="rechargeManagementDeSelL3 clearfix">
					<p id="titlegategory"><c:if test="${pd.TITLE_CATEGORY_ID !=1 and pd.TITLE_CATEGORY_ID != 2 }">全部</c:if><c:if test="${pd.TITLE_CATEGORY_ID == 1 }">男</c:if><c:if test="${pd.TITLE_CATEGORY_ID == 2 }">女</c:if></p>
					<img src="static/readchannel/images/myPic13.png" alt="" />
					<ul>
						<li onclick="seachtitle2('0');">全部</li>
						<li onclick="seachtitle2('1');">男</li>
						<li onclick="seachtitle2('2');">女</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="copywritingHeadlinesTit clearfix">
			<span style="width: 53px; margin-left: 56px;">类型</span>
			<span>标题</span>
		</div>
		<div class="copywritingHeadlinesTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="copywritingHeadlinesTex1" title="${var.CONTENT }"></div>
						<div class="copywritingHeadlinesTex2">
							<c:if test="${var.TITLE_CATEGORY_ID  == 1 }">
								男
							</c:if>
							<c:if test="${var.TITLE_CATEGORY_ID  == 2 }">
								女
							</c:if>
						</div>
						<div class="copywritingHeadlinesTex3">${var.CONTENT }</div>
					</li>
				</c:forEach>
				</c:when>
				</c:choose>
			</ul>
		</div>
		<div class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
		</form>
	</div>
	<script type="text/javascript">
		function seachtitle1(){
				var title = $("#titlegategory").text();
				if(title=="男"){
					title = 1;
				}else if(title=="女"){
					title = 2;
				}else{
					title = "";
				}
				$("#Form").attr("action","<%=basePath%>extensioncontent/goExtensiontitle.do?TITLE_CATEGORY_ID="+title);
				$("#Form").submit();
		}
		function seachtitle2(data){
			if(data == "0"){
				data = "";
			}
			$("#Form").attr("action","<%=basePath%>extensioncontent/goExtensiontitle.do?TITLE_CATEGORY_ID="+data);
			$("#Form").submit();
		}
	</script>
	<script>
		function fileSon(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("faker"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		
		$('.rechargeManagementDeSelL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.rechargeManagementDeSelL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.rechargeManagementDeSelL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
		$('.rechargeManagementDeSelL3').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.rechargeManagementDeSelL3 ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent('ul').siblings('p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.rechargeManagementDeSelL3').find('ul').hide();
		})
		
		$('.copywritingHeadlinesTex1').click(function(){
			
			if($(this).hasClass('copywritingHeadlinesTexAct')){
				$(this).removeClass('copywritingHeadlinesTexAct');
				var title = "请选择标题";
				window.parent.addtitle(title);
			}else{
				$(this).addClass('copywritingHeadlinesTexAct').parent('li').siblings('li').find('.copywritingHeadlinesTex1').removeClass('copywritingHeadlinesTexAct');
				var title = $(this).attr("title");
				window.parent.addtitle(title);
			};
		})
	</script>
</body>
</html>
