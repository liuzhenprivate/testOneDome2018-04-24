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
	<title>图片库（编辑信息）</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/rechargeConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<meta charset="UTF-8">
	<title>模板配置图片库</title>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">模板配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">图片库</a>
	</div>
	<div class="libraryEditeInformationDe">
		<div class="libraryEditeInformationDeTop clearfix">
			<span class="libraryEditeInformationDeTop1" onclick="goEditfl()">更改分类</span>
			<span class="libraryEditeInformationDeTop2" onclick="delpic()">删除</span>
			<a href="pic/list.do" title=""><i>完成编辑</i></a>
		</div>
		<input type="hidden" id="picids" name="picids" value=""/>
		<div class="libraryEditeInformationDePic">
			<ul class="clearfix">
			<c:forEach items="${varList}" var="var" varStatus="vs">
			<li value="${var.PIC_ID }">
				<img title="${var.PIC_ID }" class="libraryEditeInformationDePic1" src="${var.PIC_URL }" alt="" />
				<img class="libraryEditeInformationDePic2" src="static/read/images/myPic56.png" alt="" />
			</li>
			</c:forEach>
				 
			</ul>
		</div>
	</div>
	
	<script>
	function goEditfl(){
		var picids = $("#picids").val();
		var reg=/undefined/g;
		picids=picids.replace(reg,'');
		//alert(picids);
		if(''==picids){
			alert("请选择要分类的图片");
			return false;
		}else{
			var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="更改分类";
			 diag.URL =  '<%=basePath%>pic/goEditfl.do?picids='+picids;
			 diag.Width = 560;
			 diag.Height = 156;
			 diag.CancelEvent = function(){ //关闭事件
				//hangge();
			 //alert(1);
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					//alert(11);
					 diag.close();
					 setTimeout("self.location=self.location",100);
				}
				diag.close();
			 };
			 diag.show();
		}
	}
	function delpic(){
		var picids = $("#picids").val();
		var reg=/undefined/g;
		picids=picids.replace(reg,'');
		//alert(picids);
		if(''==picids){
			alert("请选择要删除的图片");
			return false;
		}else{
			$.ajax({
				type: "POST",
				url: '<%=basePath%>pic/deleteAll.do?tm='+new Date().getTime(),
		    	data: {DATA_IDS:picids},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				success: function(data){
					 $.each(data.list, function(i, list){
						 location.reload();
					 });
				}
			});
		}
		
	}
		$('.libraryEditeInformationDePic ul li').click(function(){
			var state =$(this).attr('value');
			//alert(state);
			var picids = $("#picids").val();
			if($(this).hasClass('libraryEditeInformationDePicAct')){
				picids=picids.replace(","+state+",");
				$(this).removeClass('libraryEditeInformationDePicAct');
				$(this).find('.libraryEditeInformationDePic2').attr('src','static/read/images/myPic56.png');
			}else{
				picids=picids+","+state+",";
				$(this).addClass('libraryEditeInformationDePicAct');
				$(this).find('.libraryEditeInformationDePic2').attr('src','static/read/images/myPic55.png');
			}
			//alert(picids);
			$("#picids").val(picids);
		})
		
		 
		
		 
	</script>
</body>
</html>


