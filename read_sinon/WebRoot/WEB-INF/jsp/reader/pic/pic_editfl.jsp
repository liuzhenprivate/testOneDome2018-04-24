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
	<link rel="stylesheet" type="text/css" href="static/read/css/public.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script>
	function addcategory(){
		 var CATEGORY_NAME = $("#CATEGORY_NAME").val();
		 if(''==CATEGORY_NAME || null==CATEGORY_NAME){
			 $("#CATEGORY_NAME").tips({
					side:3,
		            msg:'请输入分类名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CATEGORY_NAME").focus();
			 return false;
		 }else{
			 $.post("<%=basePath %>pic/addcategory.do",
			{
				"CATEGORY_NAME":CATEGORY_NAME
			},
			function(data,status){
				 //alert(data);
				 if(''!=data && 'fail'!=data){
					 $("#CATEGORY_NAME").val('');
					 $("#PIC_CATEGORY_ID").val(data );
					 $('#piccname').text(CATEGORY_NAME);
				 }else{
					 $("#CATEGORY_NAME").tips({
							side:3,
				            msg:'添加失败，已存在',
				            bg:'#AE81FF',
				            time:2
				        });
						$("#CATEGORY_NAME").focus();
				 }
				
				 
			});
		 }
	 }
	function editpiccategory(){
		var PIC_CATEGORY_ID = $("#PIC_CATEGORY_ID").val();
		var picids = $("#picids").val();
		if(''==PIC_CATEGORY_ID || null==PIC_CATEGORY_ID){
			 $("#piccname").tips({
					side:3,
		            msg:'请选择分类',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#piccname").focus();
			return false;
		}else{
			 $.post("<%=basePath %>pic/goEditflok.do",
			{
				"PIC_CATEGORY_ID":PIC_CATEGORY_ID,
				"picids":picids
			},
			function(data,status){
				  //alert(data);
				 $("#piccname").tips({
					side:3,
		            msg:'分类修改完成',
		            bg:'#AE81FF',
		            time:5
		        });
				$("#piccname").focus();
				//$("#zhongxin").hide();
				setTimeout("top.Dialog.close()",1000);
				
			});
			
		}
		
	}
	</script>
</head>
<body >
 
<div id="zhongxin">

	<!--图片库（编辑信息）更改分类-->
	<div class="changeClassifiAlert">
		<div class="changeClassifiDe">
			 <input type="hidden" id="picids" name="picids" value="${pd.picids }"/>
			 <input type="hidden" id="PIC_CATEGORY_ID" name="PIC_CATEGORY_ID" value="${pd.PIC_CATEGORY_ID }"/>
			<div class="changeClassifiDeC">
				<div class="changeClassifiDeCT clearfix">
					<div class="changeClassifiDeCT1">选择分类</div>
					<div class="changeClassifiDeCT2">
						<span id="piccname">请选择</span>
						<ul>
							<c:forEach  items="${list}" var="var" varStatus="vs">
							<li value="${var.PIC_CATEGORY_ID }">${var.CATEGORY_NAME }</li>
							</c:forEach>
						</ul>
					</div>
					<div class="changeClassifiDeCT3">
						<input type="text" id="CATEGORY_NAME" name="CATEGORY_NAME"/>
						<p>输入分类名称</p>
					</div>
					<div class="changeClassifiDeCT4" onclick="addcategory()"><span>新增分类</span></div>
				</div>
			</div>
			<div class="changeClassifiDeB" onclick="editpiccategory()">
				完&nbsp;&nbsp;成
			</div>
		</div>
	</div>
	
</div>	
	<script>
//	图片库（编辑信息）更改分类
	$('.changeClassifiDeT img').click(function(){
		$('.changeClassifiAlert').hide();
	})
	
	$('.changeClassifiDeCT2').click(function(e){
		if($(this).find('ul').css('display')=="none"){
			$(this).find('ul').show();
		}else{
			$(this).find('ul').hide();
		}
		e.stopPropagation();
	})
	$('.changeClassifiDeCT2 ul li').click(function(e){
		$(this).css('color','#f37427');
		$(this).siblings().css('color','#666666');
		$(this).parent().hide();
		var val=$(this).text();
		$('.changeClassifiDeCT2 span').text(val);
		e.stopPropagation();
		 $("#PIC_CATEGORY_ID").val($(this).attr('value'));
	})
	
	$('.changeClassifiDeCT3 p').click(function(){
		$(this).hide();
		$(this).siblings('input').focus();
	})
	$('.changeClassifiDeCT3 input').bind('input propertychange', function(){
    	if($(this).val()==''){
    		$(this).siblings('p').show();
//  		$(this).blur();
    	}
	});
	$('.changeClassifiDeCT3 input').blur(function(){
		if($(this).val()==''){
			$(this).siblings('p').show();
		}
	})
	
	</script>
</body>
</html>

