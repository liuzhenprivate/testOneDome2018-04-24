<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
	<title>资源管理标签管理</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<%@ include file="../../system/admin/top.jsp"%>
	
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">资源管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">标签管理</a>
	</div>
	<div class="editTagDe">
		<div class="editTagDeT clearfix">
			<div class="clearfix">
				<span class="editTagDeT1" id="addType">添加分类</span>
				<span  id="delType">移除分类</span>
				<span id="delLabel">删除</span>
			</div>
			<a href="<%=basePath%>label/list.do" title=""><i>完成编辑</i></a>
		</div>
		<div class="editTagDeB">
			<ul class="clearfix">
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<li id="${var.LABEL_ID}">${var.LABEL_NAME}</li>
						</c:forEach>
					</c:when>
				<c:otherwise>
					没有相关数据
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
	<script>
		//删除标签
		$("#delLabel").click(function(){
			var delid = new Array();
			$(".editTagDeBAct").each(function (index, item) {
               delid[index]=item.id;
            });
            if(""==delid){
	            alert("请选择标签");
	        }else{
	            $.ajax({
					type: "post",
					data:{"delid":JSON.stringify(delid)},
					url: "<%=basePath%>label/delId.do",
					dataType:'json', 
					success: function(data){
						location.reload();
					}
				});
			}
		});
		//移除标签分类
		$("#delType").click(function(){
	    	var labelid = "";
			$(".editTagDeBAct").each(function (index, item) {
				labelid= item.id +"," +labelid;
	        });
	        if(""==labelid){
	            alert("请选择标签");
	        }else{
		        var con=confirm("你确定移除吗？"); //在页面上弹出对话框
				if(con==true){
					$.ajax({
						type: "post",
						data:{"labelid":labelid},
						url: "<%=basePath%>categorylabel/delLabelId.do",
						success: function(data){
							alert("移除成功");
						 }
					});
				}
	        }
		});
		
		function fun(){
			alert();
			$('.editTagDeBAct ul li').each(function (index, obj) {
                alert(obj.value);
            });
		}
		
		$(document).on('click','.editTagDeB ul li',function(){
			if($(this).hasClass('editTagDeBAct')){
				$(this).removeClass('editTagDeBAct');
			}else{
				$(this).addClass('editTagDeBAct');
			}
		});
		
		$('.editTagDeT1').click(function(){
			var labelid = "";
			$(".editTagDeBAct").each(function (index, item) {
				if(index!=1){
					labelid= labelid + item.id;
				}else{
					labelid= labelid +","+ item.id;
				}
            });
            if(""==labelid){
	            	alert("请选择标签");
	            }else{
					parent.document.getElementById("labelid").value=labelid;
					$('.editTagPlusAlert', parent.document).show();
					(function(Thunder){
						$('.editTagPlusAlert', parent.document).css('padding-top',Thunder);
					})((parent.document.documentElement.clientHeight-$('.editTagPlus', parent.document).get(0).offsetHeight)/2);
			}
		});
	</script>
</body>
</html>