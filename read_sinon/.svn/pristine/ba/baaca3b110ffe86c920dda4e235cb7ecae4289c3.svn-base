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
	function search(){
		//alert("searchnotcie");
		//alert($("#STATE").val());
		$("#Form").submit();
	}
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
					 $("<li value='"+data+"'>" + CATEGORY_NAME +"</li>").prependTo("ul");
					 $('.libraryNewPicturesDeCT2 span').text(CATEGORY_NAME);
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
	</script>
</head>
<body >
<div id="zhongxin">
	<form id="FORM" name="FORM" action="<%=basePath %>pic/save.do" method="POST" enctype="multipart/form-data"> 
	<input type="hidden" id="PIC_CATEGORY_ID" name="PIC_CATEGORY_ID" value="${pd.PIC_CATEGORY_ID }"/>
	<input type="hidden" id="picurl" name="picurl" value=""/>
	<!--模板配置图片库新增图片-->
	<div class="libraryNewPicturesAlert">
		<div class="libraryNewPicturesDe">
			<div class="libraryNewPicturesDeC">
				<div class="libraryNewPicturesDeCT clearfix">
					<div class="libraryNewPicturesDeCT1">选择分类</div>
					<div class="libraryNewPicturesDeCT2">
						<span id="piccname">请选择</span>
						<ul>
							<c:forEach  items="${list}" var="var" varStatus="vs">
							<li value="${var.PIC_CATEGORY_ID }">${var.CATEGORY_NAME }</li>
							</c:forEach>
						</ul>
					</div>
					<div class="libraryNewPicturesDeCT3">
						<input type="text" id="CATEGORY_NAME" name="CATEGORY_NAME"/>
						<p>输入分类名称</p>
					</div>
					<div class="libraryNewPicturesDeCT4" onclick="addcategory()"><span>新增分类</span></div>
				</div>
				<div class="libraryNewPicturesDeCB clearfix">
					<h2>添加图片</h2>
					<div class="libraryNewPicturesDeCBPic clearfix">
						<div class="libraryNewPicturesDeCBPic1">
							<img src="static/read/images/myPic51.png" alt="" />
							<p>添加图片</p>
							<input type="file" id="file" name="file"/>
						</div>
					</div>
				</div>
			</div>
			<div class="libraryNewPicturesDeB">
				<a  href="javascript:addpic()" title="">完&nbsp;&nbsp;成</a>
				
			</div>
		</div>
	</div>
	</form>
</div>
	<script>
	function addpic(){
		var PIC_CATEGORY_ID = $("#PIC_CATEGORY_ID").val();
		//var file = $("#file").val();
		var picurl = $("#picurl").val();
		//alert(TITLE_IDS);
		var reg=/undefined/g;
		picurl=picurl.replace(reg,'');
		 $("#picurl").val(picurl);
		//alert(PIC_CATEGORY_ID+"=="+file);
		if(''==PIC_CATEGORY_ID){
			$("#piccname").tips({
				side:3,
	            msg:'请选择分类',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#piccname").focus();
			return false;
		}else{
			if(''==picurl){
				 
				$("#file").tips({
					side:3,
		            msg:'请上传图片',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#file").focus();
				return false;
			}else{
				$("#FORM").submit();
				$("#zhongxin").hide();
			}
		}
	}
	
	//		模板配置图片库新增图片
		$('.libraryNewPicturesDeCT2').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.libraryNewPicturesDeCT2 ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$('.libraryNewPicturesDeCT2 span').text(val);
			e.stopPropagation();
			//var state =$(this).attr('value');
			//alert(state);
			 $("#PIC_CATEGORY_ID").val($(this).attr('value'));
			 
		})
		
		$('.libraryNewPicturesDeCT3 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.libraryNewPicturesDeCT3 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.libraryNewPicturesDeCT3 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
		$(document).on('mouseenter','.libraryNewPicturesDeCBPic2',function(){
			$(this).find('span').show();
		})
		$(document).on('mouseleave','.libraryNewPicturesDeCBPic2',function(){
			$(this).find('span').hide();
		})
		$(document).on('click','.libraryNewPicturesDeCBPic2 span',function(){
			$(this).parent('.libraryNewPicturesDeCBPic2').remove();
			var val=$(this).attr('value');
			//alert(val);
			var picurl = $("#picurl").val();
			picurl=picurl.replace(val+",");
	        $("#picurl").val(picurl);
		})
		$(".libraryNewPicturesDeCBPic1 input").change(function() {
			$.ajax({
		        url: "<%=basePath %>pic/upfile.do",
		        type: 'POST',
		        cache: false,
		        data: new FormData($('#FORM')[0]),
		        processData: false,
		        contentType: false,
		        dataType:"text",
		        beforeSend: function(){
		            uploading = true;
		        },
		        success : function(data) {
		           //alert(data);
		           var picurl = $("#picurl").val();
		           $("#picurl").val(picurl+data+",");
		           var html="<div class="+"libraryNewPicturesDeCBPic2"+"><img src=<%=basePath %>"+data+" /><span value='"+data+"'><i>删除</i></span></div>";
					$('.libraryNewPicturesDeCBPic1').after(html);
		        }
		    });
			/**
			var $file = $(this);
			var fileObj = $file[0];
			var windowURL = window.URL || window.webkitURL;
			var dataURL;
			if(fileObj && fileObj.files && fileObj.files[0]){
				dataURL = windowURL.createObjectURL(fileObj.files[0]);
				var html="<div class="+"libraryNewPicturesDeCBPic2"+"><img src="+dataURL+" /><span><i>删除</i></span></div>";
				$('.libraryNewPicturesDeCBPic1').after(html);
				alert("1=="+dataURL);
			}else{
				dataURL = $file.val();
				var imgObj = $img.get(0);
				imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
				alert("2=="+dataURL);
			}
			**/
			//alert(dataURL);
		});
		
		 
		</script>
</body>
</html>

