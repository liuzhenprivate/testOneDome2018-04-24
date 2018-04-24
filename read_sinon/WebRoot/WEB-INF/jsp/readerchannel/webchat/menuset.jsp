<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<title>基础设置（菜单设置）</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/basicSetting.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
	<style>
	.menuSettingDeL>h1{
	cursor: pointer;
	font-weight: normal;
	font-size: 18px;
	color: #333;
	height: 44px;
	line-height: 44px;
	position:absolute;
	top:20px;left:0;
	width:100%;
	z-index:100;
	}
	</style>
</head>
 
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">基础设置</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">菜单设置</a>
	</div>
	<div class="necessarySettingDe">
		<div class="necessarySettingDeTop clearfix">
			<a href="javascript:void(0)" title=""><span>查看教程</span></a>
		</div>
		<div class="menuSettingDe clearfix">
			<div class="menuSettingDeL">
			<h1>小说热搜</h1>
				<div class="menuSettingDeLB clearfix">
					<div class="menuSettingDeLBPlus">
						<a href="javascript:void(0)" title="">添加菜单</a>
					</div>
				</div>
			</div>
			<div class="menuSettingDeR">
				<div class="menuSettingDeRNo">
					点击左侧菜单进行编辑操作
				</div>
				<div class="menuSettingDeRYesAll">
					<div class="menuSettingDeRYes">
						<h1>菜单名称</h1>
						<div class="menuSettingDeRYesInput clearfix">
							<span>名称</span>
							<div class="clearfix">
								<input placeholder="请输入子菜单名称" id="NAME" name="NAME" onchange="addmenu()"/>
							</div>
							<h2>子菜单名字不能超过8个汉字或16个字母</h2>
						</div>
						<h1>内容</h1>
						<div class="menuSettingDeRYesInput clearfix">
							<span>链接</span>
							<div class="clearfix">
								<input placeholder="请输入正确合法的跳转地址" id="MENUURL" name="MENUURL" onchange="addmenu()"/>
							</div>
							<i id="selectWebchatMenuUrl">选择链接</i>
						</div>
					</div>
				</div>
				
				<div class="menuSettingDeRBtn clearfix">
					<a href="javascript:save()" title="">应用到公众号</a>
					<!-- <span>默认菜单</span> -->
					<i>删除菜单</i>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="divid" name="divid" />
	<input type="hidden" id="divtype" name="divtype" />
	<input type="hidden" id="PDIV" name="PDIV" value="0"/>
	<input type="hidden" id="USER_ID" name="USER_ID" value="${pd.USER_ID }" />
	<script type="text/javascript">
		$('#selectWebchatMenuUrl').click(function(){
			var USER_ID = $('#USER_ID').val();
			var diag = new top.Dialog();
			diag.Drag = true; 
			diag.Title ="选择配置链接";
			diag.URL = '<%=basePath%>channelwebchatmenu/getListUrl.do?USER_ID='+USER_ID;
			diag.Width = 560;
			diag.Height = 356;
			diag.CancelEvent = function(){ //关闭事件
				if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					diag.close();
				}
				diag.close();
			};
			diag.show();
		});
	</script>
	
	<script>
	function save(){
		$.ajax({
			type: "POST",
			url: '<%=basePath%>channelwebchatmenu/savetowebchat.do?tm='+new Date().getTime(),
	    	data: {},
			dataType:'text',
			//beforeSend: validateData,
			cache: false,
			success: function(data){
				if(data=='0'){
					alert('应用到微信成功');
				}else{
				  alert(data);
				}
			}
		});
	}
	function addmenu(){
		var divid = $("#divid").val();
		var divtype = $("#divtype").val();
		var PDIV = $("#PDIV").val();
	//	alert(divtype+"="+divid);
		var NAME = $("#NAME").val();
		var MENUURL = $("#MENUURL").val();
		if(NAME!=''){
			//一级菜单
			if(divtype=='ptype'){
				$("#pid"+divid).text(NAME); 
				PDIV=0;
			}else{
				$("#cid"+divid).html(NAME);
			}
			$.ajax({
				type: "POST",
				url: '<%=basePath%>channelwebchatmenu/editmenu.do?tm='+new Date().getTime(),
		    	data: {
		    		NAME:NAME,
		    		MENUURL:MENUURL,
		    		SOURT:divid,
		    		PDIV:PDIV
		    	},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				success: function(data){
					  alert(data);
				}
			});
			
		}
	}
	function delmenu(){
		var divid = $("#divid").val();
		//alert(divid);
		if(divid!=''){
			$.ajax({
				type: "POST",
				url: '<%=basePath%>channelwebchatmenu/delmenu.do?tm='+new Date().getTime(),
		    	data: {
		    		SOURT:divid
		    	},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				success: function(data){
					  alert(data);
				}
			});
		}
	}
	function getmenu(){
		var divid = $("#divid").val();
		//alert(divid);
		if(divid!=''){
			$.ajax({
				type: "POST",
				url: '<%=basePath%>channelwebchatmenu/getmenu.do?tm='+new Date().getTime(),
		    	data: {
		    		SOURT:divid
		    	},
				dataType:'text',
				//beforeSend: validateData,
				cache: false,
				success: function(data){
					//alert("data="+data);
					if(data!='' && 'err'!=data){
						//alert("data--="+data);
					   var arr = data.split('====');
					   var name = arr[0];
					   var menuurl = arr[1];
					   //alert(name+menuurl);
					   $("#NAME").val(name);
						$("#MENUURL").val(menuurl);
						 
					}else{
						$("#NAME").val("");
						$("#MENUURL").val("");
					}
					//data = eval(data);
					//alert("data="+data);
				}
			});
		}
	}
	
	var vid =0;
	//点击新增一级菜单
	$(document).on('click','.menuSettingDeLBPlus',function(){
		vid = vid+1;
	//	alert(vid);
		var divLen=$('.menuSettingDeLB>div').length;
		if(divLen<3){
			//$("#divid").val(vid);
			var divHtml="<div><a href='javascript:void(0)' id='pid"+vid+"'>菜单"+divLen+"</a><ul value='"+vid+"'><li value='"+vid+"'><span></span></li></ul></div>";
			$(this).before(divHtml);
			divLen=$('.menuSettingDeLB>div').length;
			$('.menuSettingDeLB>div').css('width',(99/divLen)+"%");
			$('.menuSettingDeLB>div.menuSettingDeLBPlus').css('width',(100/divLen)+"%");
		}else if(divLen==3){
			//$("#divid").val(vid);
			var divHtml="<div><a href='javascript:void(0)' id='pid"+vid+"'>菜单"+divLen+"</a><ul value='"+vid+"'><li value='"+vid+"'><span></span></li></ul></div>";
			$(this).before(divHtml);
			$(this).remove();
			divLen=$('.menuSettingDeLB>div').length;
			$('.menuSettingDeLB>div').css('width',(99/divLen)+"%");
		}
	});
	//获取一级菜单的ID
	$(document).on('click','.menuSettingDeLB div',function(){
		
		if($(this).hasClass('menuSettingDeLBPlus')){
			$(this).css('background','none');
		}else{
			$('.menuSettingDeRNo').hide();
			$('.menuSettingDeRYesAll').show();
			$(this).addClass('menuSettingDeLBAct').siblings('div').removeClass('menuSettingDeLBAct').find('ul').css('display','none');
			$(this).find('ul').css('display','block');
			$(this).find('li').removeClass('menuSettingDeLBSonAct');
			var divid =$(this).find('ul').attr('value');
		//	alert("value="+divid);
			var text =$(this).find('ul').text();
		//	alert("text="+text);
			//addmenu();
			$("#divid").val(divid);
			$("#divtype").val("ptype");
			getmenu();
		}
	});
	//新增二级菜单
	$(document).on('click','.menuSettingDeLB div ul li span',function(e){
		var liLen=$(this).parents('ul').find('li').length;
		//var divid =$(this).parents('ul').attr('value');
		//alert("parents="+divid);
		if(liLen<5){
			//addmenu();
			vid = vid+1;
			//alert("子菜单id="+vid);
			
			var liHtml="<li><i id='cid"+vid+"' value='"+vid+"'>子菜单"+liLen+"</i></li>";
			//$("#NAME").val("子菜单"+liLen);
			$(this).parent('li').after(liHtml);
			liLen++;
		}
		e.stopPropagation();
	});
	$(document).on('click','.menuSettingDeLB div ul li i',function(e){
		$('.menuSettingDeRNo').hide();
		$('.menuSettingDeRYesAll').show();
		$(this).parent('li').addClass('menuSettingDeLBSonAct').siblings('li').removeClass('menuSettingDeLBSonAct');
		$(this).parents('.menuSettingDeLB').find('div').removeClass('menuSettingDeLBAct');
		e.stopPropagation();
		var Pdivid =$(this).parents('ul').attr('value');
		//alert("parents="+Pdivid);
		$("#PDIV").val(Pdivid);
		var divid =$(this).attr('value');
	//	alert("子菜单value="+divid);
		//addmenu();
		$("#divid").val(divid);
		$("#divtype").val("ctype");
		getmenu();
	});
	</script>
	<c:if test="${not empty mlist }">
	<c:forEach items="${mlist}" var="var" varStatus="vs">
	<script>
		var SOURT = parseInt('${var.SOURT}');
		var name = '${var.NAME}';
		//alert(SOURT+"=="+name);
		if(SOURT>vid){
			vid = SOURT;
		}
		var divLen=$('.menuSettingDeLB>div').length;
		//alert("divLen="+divLen);
		if(divLen<3){
			//$("#divid").val(vid);
			var divHtml="<div><a href='javascript:void(0)' id='pid"+SOURT+"'>"+name+"</a><ul value='"+SOURT+"'><li value='"+SOURT+"'><span></span></li></ul></div>";
			$('.menuSettingDeLBPlus').before(divHtml);
			divLen=$('.menuSettingDeLB>div').length;
			$('.menuSettingDeLB>div').css('width',(99/divLen)+"%");
			$('.menuSettingDeLB>div.menuSettingDeLBPlus').css('width',(100/divLen)+"%");
		}else if(divLen==3){
			//$("#divid").val(vid);
			var divHtml="<div><a href='javascript:void(0)' id='pid"+SOURT+"'>"+name+"</a><ul value='"+SOURT+"'><li value='"+SOURT+"'><span></span></li></ul></div>";
			$('.menuSettingDeLBPlus').before(divHtml);
			$('.menuSettingDeLBPlus').remove();
			divLen=$('.menuSettingDeLB>div').length;
			$('.menuSettingDeLB>div').css('width',(99/divLen)+"%");
		}
	</script>
	<c:if test="${not empty var.clist }">
	<c:forEach items="${var.clist}" var="cvar" varStatus="cvs">
		<script>
		var SOURT = parseInt('${cvar.SOURT}');
		var name = '${cvar.NAME}';
		//alert(SOURT+"=cc="+name);
		if(SOURT>vid){
			vid = SOURT;
		}
		//$('.menuSettingDeLB>div').eq(0).find('ul')
		//var liLen=$('.menuSettingDeLB div ul li span').parents('ul').find('li').length;
		//var divid =$(this).parents('ul').attr('value');
		//alert("parents="+divid);
		//alert(${vs.index});
		//if(liLen<5){
			var liHtml="<li><i id='cid"+SOURT+"' value='"+SOURT+"'>"+name+"</i></li>";
			$('.menuSettingDeLB>div').eq(${vs.index}).find('ul').append(liHtml);
			//alert($('.menuSettingDeLB>div').eq(${vs.index}).html());
			//liLen++;
		//}
		//e.stopPropagation();
		</script>
	</c:forEach>
	</c:if>
	</c:forEach>
	</c:if>
	<script>
		
		$(document).on('click','.menuSettingDeRBtn i',function(){
			delmenu();
			$('.menuSettingDeLB>div ul li.menuSettingDeLBSonAct').remove();
			if($('.menuSettingDeLB div').hasClass('menuSettingDeLBAct')){
				$('.menuSettingDeLB div.menuSettingDeLBAct').remove();
				if($('.menuSettingDeLB div').hasClass('menuSettingDeLBPlus')){
					var divLen=$('.menuSettingDeLB>div').length;
					$('.menuSettingDeLB>div').css('width',(99/divLen)+"%");	
				}else{
					var divHtml="<div class="+"menuSettingDeLBPlus"+"><a href="+"javascript:void(0)"+">添加菜单</a></div>";
					$('.menuSettingDeLB').append(divHtml);
					var divLen=$('.menuSettingDeLB>div').length;
					$('.menuSettingDeLB>div').css('width',(99/divLen)+"%");	
				};
				
			};
			
		})
		$('.menuSettingDeRYesInput div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.menuSettingDeRYesInput div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.menuSettingDeRYesInput div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
	</script>
</body>
</html>



