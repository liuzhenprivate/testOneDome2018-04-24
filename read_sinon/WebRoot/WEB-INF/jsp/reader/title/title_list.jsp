<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>模板配置标题库</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script>
	function search(){
		//alert("searchnotcie");
		//alert($("#STATE").val());
		$("#Form").submit();
	}
	$(function(){
		$('#titlecid').children('li').bind('click', function(){
			//alert($(this).attr('value'));
			var state =$(this).attr('value');
			if(state !='-1'){
				$("#TITLE_CATEGORY_ID").val($(this).attr('value'));
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
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">标题库</a>
	</div>
	<!-- 检索  -->
	 <form action="title/list.do" method="post" name="Form" id="Form">
	 <input type="hidden" id="TITLE_CATEGORY_ID" name="TITLE_CATEGORY_ID" value="${pd.TITLE_CATEGORY_ID }"/>
	<div class="titleLibraryDe">
		<div class="titleLibraryTop clearfix">
			<div class="titleLibraryTopL clearfix">
				<div class="titleLibraryTopL1 clearfix">
					<input type="text" onchange="search()" id="CONTENT" name="CONTENT" value="${pd.CONTENT }"/>
					<p>输入标题内容</p>
					<img src="static/read/images/myPic26.png" alt="" />
				</div>
				<div class="titleLibraryTopL2">
					<span id="titlecname">全部频道</span>
					<ul id="titlecid">
					<li value="">全部频道</li>
					<li value="1">男生频道</li>
					<li value="2">女生频道</li>
					</ul>
				</div>
			</div>
			<div class="titleLibraryTopR clearfix">
				<a style="cursor:pointer;" class="titleLibraryTopR1" onclick="editall()" title=""><i>批量编辑</i></a>
				<span class="titleLibraryTopR2">
					<i>新增标题</i>
					<ul>
						<li><a   href="javascript:goAdd()" title="">添加标题</a></li>
						<li><a href="title/goAddlist.do" title="">批量添加</a></li>
					</ul>
				</span>
			</div>
		</div>
		<div class="titleLibraryTitle clearfix">
			<span style="margin-left: 19px; width: 77px;">频道</span>
			<span style="width: 599px;">标题内容</span>
			<span style="width: 189px;">添加时间</span>
			<span>管理操作</span>
		</div>
		<div class="titleLibraryTex">
		<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:if test="${QX.cha == 1 }">
						<ul>
						<c:forEach items="${varList}" var="var" varStatus="vs">
						<li class="clearfix">
							<div class="titleLibraryTex1">${var.CATEGORY_NAME }</div>
							<div class="titleLibraryTex2 clearfix">
								<p>${var.CONTENT }</p>
								<c:if test="${(fn:length(var.CONTENT))>68}">
									<i onclick="looktitlecontent('${var.TITLE_ID}')">查看</i>
								</c:if>
							</div>
							<div class="titleLibraryTex3">${var.CREATE_TIME }</div>
							<div class="titleLibraryTex4">
								<span>操作</span>
								<div class="titleLibraryTex4Div">
									<a onclick="goEdit('${var.TITLE_ID}')"  >修改</a>
									<a onclick="deltitle('${var.TITLE_ID}')">删除</a>
								</div>
							</div>
						</li>
						
						</c:forEach>
						</ul>
						</c:if>
					</c:when>
				</c:choose>
		</div>
		<div style="margin-left: 30px;" class="flipTwo clearfix">
			${page.pageStr}
		</div>
	</div>
	</form>
	<script>
	function goAdd(){
		var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="新增标题";
		 diag.URL =  '<%=basePath%>title/goAdd.do';
		 diag.Width = 560;
		 diag.Height = 246;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 if('${page.currentPage}' == '0'){
					 setTimeout("self.location=self.location",100);
				 }else{
					 nextPage(${page.currentPage});
				 }
			}
			diag.close();
		 };
		 diag.show();
	}
	function goEdit(id){
		var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="编辑标题";
		 diag.URL =  '<%=basePath%>title/goEdit.do?TITLE_ID='+id;
		 diag.Width = 560;
		 diag.Height = 246;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 if('${page.currentPage}' == '0'){
					 setTimeout("self.location=self.location",100);
				 }else{
					 nextPage(${page.currentPage});
				 }
			}
			diag.close();
		 };
		 diag.show();
	}
	function looktitlecontent(id){
		var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="标题内容";
		 diag.URL =  '<%=basePath%>title/goLook.do?TITLE_ID='+id;
		 diag.Width = 560;
		 diag.Height = 364;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 diag.close();
			}
			diag.close();
		 };
		 diag.show();
	}
	$(function(){
		var pmode = '${pd.TITLE_CATEGORY_ID}';
		//alert(STATE);
		 
		 if('1'==pmode){
			$("#titlecname").html("男生频道");
		}else if('2'==pmode){
			$("#titlecname").html("女生频道");
		} 
		 
		
	});
		function editall(){
			 $("#Form").attr('action','<%=basePath%>title/editlist.do');
			$("#Form").submit();
		}
		function deltitle(id){
			$.get("<%=basePath%>title/delete.do?TITLE_ID="+id,function(data,status){
				location.reload();
			});
		}
		 
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("iframe"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		$('.titleLibraryTopL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.titleLibraryTopL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.titleLibraryTopL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		$(window).click(function(){
			$('.titleLibraryTopL2').find('ul').hide();
			$('.titleLibraryTopR2 ul').hide();
			$('.titleLibraryTex4 div').hide();
		})
		$('.titleLibraryTopL2').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.titleLibraryTopL2 ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$('.titleLibraryTopL2 span').text(val);
			e.stopPropagation();
		})
		$('.titleLibraryTopR2').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.titleLibraryTex4 span').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.titleLibraryTex4Div').hide();
			e.stopPropagation();
		})
		$('.titleLibraryTex4 div a').click(function(e){
			$(this).parent('div').hide();
			e.stopPropagation();
		})
		
		$('.titleLibraryTex2 i').click(function(){
			$('.titleLibraryViewAlert', parent.document).show();
			(function(Thunder){
				$('.titleLibraryViewAlert', parent.document).css('padding-top',Thunder);
			})((parent.document.documentElement.clientHeight-$('.titleLibraryViewDe', parent.document).get(0).offsetHeight)/2);
		})
		
		$('.titleLibraryTopR21').click(function(){
			$('.titleLibrarySingleAlert', parent.document).show();
			(function(Thunder){
				$('.titleLibrarySingleAlert', parent.document).css('padding-top',Thunder);
			})((parent.document.documentElement.clientHeight-$('.titleLibrarySingleDe', parent.document).get(0).offsetHeight)/2);
		})
	</script>
</body>
</html>


