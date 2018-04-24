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
	<base href="<%=basePath%>">
	<title>数据统计公告管理</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/public.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script>
		//添加公告/编辑公告
		function addnotice(){
			var TITLE = $("#TITLE").val();
			var CONTENT = $("#CONTENT").val();
			var SEND_TIME = $("#SEND_TIME").val();
			var PUSHMODE = $("#PUSHMODE").val();
			if(''==TITLE || null==TITLE){
				$("#TITLE").tips({
					side:3,
		            msg:'请输入公告标题',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TITLE").focus();
				return false;
			}
			if(''==CONTENT || null==CONTENT){
				$("#CONTENT").tips({
					side:3,
		            msg:'请输入公告内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTENT").focus();
				return false;
			}
			var pushTime = $('#editpushmode').text();
			if('定时推送'==pushTime){
				if(''==SEND_TIME || null==SEND_TIME){
					$("#SEND_TIME").tips({
						side:3,
			            msg:'请选择发送日期',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#SEND_TIME").focus();
					return false;
				}
			}
			var state = 0;
			if(1==PUSHMODE){
				state = 3;
			}else{
				state = 0;
			}
			$("#STATE").val(state);
			$("#Form").submit();
			$("#zhongxin").hide();
		} 
	</script>
</head>
<style>
.layui-laydate-footer>span{
	margin-right: 180px;
}
	
</style>
<body >

<form action="noticelog/${msg }.do" name="Form" id="Form" method="post">
<div id="zhongxin">
	<!--数据统计公告管理编辑公告-->
	<div class="editorialNoticeAlert">
		<div class="editorialNotice">
			<input type="hidden" id="NOTICELOG_ID" name="NOTICELOG_ID" value="${pd.NOTICELOG_ID }"/>
			<c:if test="${empty pd.PUSHMODE }">
			<input type="hidden" id="PUSHMODE" name="PUSHMODE"  value="1"/>
			</c:if>
			<c:if test="${not empty pd.PUSHMODE }">
			<input type="hidden" id="PUSHMODE" name="PUSHMODE"  value="${pd.PUSHMODE }"/>
			</c:if>
			<input type="hidden" id="STATE" name="STATE"  value="${pd.STATE }"/>
			<div class="announcementTitle clearfix">
				<span>公告标题：</span>
				<div class="clearfix">
					<input type="text" name="TITLE" id="TITLE" value="${pd.TITLE }"  maxlength="32"/>
				</div>
			</div>
			<div class="announcementContent clearfix">
				<span>公告内容：</span>
				<div class="clearfix">
					<textarea   name="CONTENT" id="CONTENT">${pd.CONTENT }</textarea>
				</div>
			</div>
			<div class="pushMode clearfix">
				<span>推送方式：</span>
				<div class="clearfix">
					<p id="editpushmode">立即推送</p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul id="addpushmode">
						<li value="1" onclick="changeTime('1');">立即推送</li>
						<li value="2" onclick="changeTime('2');">定时推送</li>
					</ul>
				</div>
			</div>
			<div class="pushTime clearfix">
				<span>推送时间：</span>
				<div class="clearfix">
					 <input class="layui-input"  name="SEND_TIME" id="SEND_TIME" readonly="readonly"  value="${pd.SEND_TIME }" type="text">
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
			</div>
			<div class="pushSure" onclick="addnotice()"><span>完&nbsp;&nbsp;成</span></div>
			<img src="static/read/images/myPic19.png" alt="" />
		</div>
	</div>
	</div>
	</form>

	<script type="text/javascript">
		function changeTime(data){
			if(data=='1'){
				 $('.pushTime').css('display','none');
			}else if(data=='2'){
				 $('.pushTime').css('display','block');
			}
		}
	</script>

	<script>
	$('.pushTime').css('display','none');

		laydate.render({
		    elem: '#SEND_TIME'
		    ,type: 'datetime'
		  });
	var PUSHMODE ='${pd.PUSHMODE}';
	if(''!=PUSHMODE){
		if('1'==PUSHMODE){
			$("#editpushmode").html("立即推送");
		}
		if('2'==PUSHMODE){
			$("#editpushmode").html("定时推送");
		}
	}
	$(function(){
		$('#addpushmode').children('li').bind('click', function(){
			//alert($(this).attr('value'));
			var pmode = $(this).attr('value');
			$("#PUSHMODE").val(pmode);
			if(1==pmode){
				 var nowDate = new Date();     
				 var year = nowDate.getFullYear();    
				 var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;    
				 var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();    
				$("#SEND_TIME").val(year + "-" + month + "-" + date);
			}
		});
	});
	
	$('.pushMode div').click(function(e){
		if($(this).find('ul').css('display')=="none"){
			$(this).find('ul').show();
		}else{
			$(this).find('ul').hide();
		}
		e.stopPropagation();
	})
	$('.pushMode div ul li').click(function(e){
		$(this).css('color','#f37427');
		$(this).siblings().css('color','#666666');
		$(this).parent().hide();
		var val=$(this).text();
		$('.pushMode div p').text(val);
		e.stopPropagation();
	})
	$(window).click(function(){
		$('.pushMode div').find('ul').hide();
	})
	
	</script>
</body>
</html>
