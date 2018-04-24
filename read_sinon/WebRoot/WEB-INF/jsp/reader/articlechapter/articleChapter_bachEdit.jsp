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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>书籍详情</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="ovreread();">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">资源管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" href='<%=basePath%>articlechapter/list.do' title="">章节管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">章节批量编辑</a>
	</div>
	<div class="bookDetailsDe">
	<form action="<%=basePath%>articlechapter/chapterBatchEdit.do" id="Form" name="Form" method="post">
		<input type="hidden" name="CATEGORY" id="CATEGORY" value="${pd.CATEGORY }">
		<input type="hidden" name="CHAPTER_STATE" id="CHAPTER_STATE" value="${pd.CHAPTER_STATE }">
		<input type="hidden" name="IS_FREE" id="IS_FREE" value="${pd.IS_FREE }">
		<input type="hidden" name="CHANNEL_TYPE" id="CHANNEL_TYPE" value="${pd.CHANNEL_TYPE }">
		<div class="bookDetailsDeAf" style="display: block">
			<div class="bookDetailsDeCiAf">
				<h1>章节信息</h1>
				<div class="bookDetailsDeCiAfall clearfix">
					<div class="clearfix">
						<span onclick="editstate('0');">显示</span>
						<span onclick="editstate('1');">隐藏</span>
						<span onclick="editstate('2');">删除</span>
					</div>
					<a href="javascript:submit()" title=""><i>完成编辑</i></a>
				</div>
			</div>
			<div class="bookDetailsDeAfTit clearfix">
				<a href="javascript:void(0)" title=""><i></i></a>
				<span  style="width: 180px; margin-left: 20px;">书籍名称</span>
				<span style="width: 271px;">章节名称</span>
				<span style="width: 107px;">章节状态</span>
				<span style="width: 107px;">章节字数</span>
				<span style="width: 123px;">章节价格</span>
				<span>添加日期</span>
			</div>
			<div class="bookDetailsDeAfTex">
				<ul>
				<c:choose>
				<c:when test="${not empty varchapterlist}">
				<c:forEach items="${varchapterlist}" var="var" varStatus="vs">
					<li class="clearfix">
						<a href="javascript:void(0)" title=""><i title="${var.ARTICLE_CHAPTER_ID }"></i></a>
						<span style="width: 180px; margin-left: 20px;">${var.ARTICLE_NAME }</span>
						<span style="width: 271px;">${var.CHAPTER_NAME }</span>
						<span style="width: 107px;"><c:if test="${var.CHAPTER_STATE  == 0 }">显示</c:if><c:if test="${var.CHAPTER_STATE  == 1 }">隐藏</c:if></span>
						<span style="width: 107px;">${var.NUMBER_CHAPTER }</span>
						<span style="width: 123px;">${var.CONSUMES }</span>
						<span>${var.CREATE_TIME }</span>
					</li>
				</c:forEach>
				</c:when>
				</c:choose>
				</ul>
			</div>
			<div class="flipTwo clearfix">
			 	${page.pageStr}
			</div>
		</div>
	</form>
	</div>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript">
		function submit(){
			$("#Form").attr("action","<%=basePath%>articlechapter/list.do");
			$("#Form").submit();
		}
		function editstate(data){
			var ARTICLE_CHAPTER_ID = "";
			$(".bookDetailsDeAfTexAct").each(function(n,value){
				ARTICLE_CHAPTER_ID = ARTICLE_CHAPTER_ID + value.title+",";
			});
			if(ARTICLE_CHAPTER_ID!=""){
				$.get("<%=basePath%>articlechapter/chapterEditState.do?ARTICLE_CHAPTER_ID="+ARTICLE_CHAPTER_ID+"&CHAPTER_STATE="+data,function(data,status){
				});
				location.reload();
			}else{
				alert("请选择章节");
			}
		}
	</script>
	<script>
		laydate.render({
		  elem: '#CHAPTER_TIME',
		  range: true
		});
		
		$('.labelManagementTopL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		});
		$('.labelManagementTopL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.labelManagementTopL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		});
		
		$('.bookDetailsDeCiAlldown span').click(function(){
			$('.bookDetailsDeCiAlldown').hide();
			$('.bookDetailsDeCiAllup').show();
		});
		$('.bookDetailsDeCiAllupBtn span').click(function(){
			$('.bookDetailsDeCiAlldown').show();
			$('.bookDetailsDeCiAllup').hide();
		});
		
		$('.bookDetailsDeTexFi span').click(function(){
			var spanVal=$(this).text();
			$(this).siblings('.bookDetailsDeTexFiInp').show().find('input').val(spanVal);
			$(this).siblings('.bookDetailsDeTexFiInp').find('input').focus();
		});
		$('.bookDetailsDeTexFiInp input').blur(function(){
			var inpVal=$(this).val();
			var inpId=$(this).attr("title");
				$.ajax({
					type: "post",
					data:{
						'inpVal':inpVal,
						'inpId':inpId
					},
					url: "<%=basePath%>articlechapter/updateConsums.do",
					dataType:'text', 
					success: function(data){
						if(data==""){
							alert("数据错误");
						}else{
							$("#COUNT_CONSUMES").text(data);
						}
					}
				});
			$(this).parent().siblings('span').text(inpVal);
			$(this).parent('.bookDetailsDeTexFiInp').hide();
		});
		
		$(window).click(function(){
			$('.bookDetailsDeTexSe div').hide();
			$('.bookDetailsDeCiAllR3 div').hide();
		});
		$(".bookDetailsDeAfTex").show();
		$(".bookDetailsDeAfTex").siblings('div').css('display')=="block";
		
		//完成编辑
		$('.bookDetailsDeCiAfall a').click(function(){
			
		});
		/* $(document).on('click','.bookDetailsDeAfTit a i',function(){
			if($(this).hasClass('batchEditingDeTitAct')){
				$(this).removeClass('batchEditingDeTitAct');
				$('.batchEditingDeCont ul li>a i').removeClass('bookDetailsDeAfTex');
			}else{
				$(this).addClass('batchEditingDeTitAct');
				$('.bookDetailsDeAfTex ul li>a i').addClass('batchEditingDeContAct');
			}
		}) */
		$('.bookDetailsDeCiAllR3 span').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			e.stopPropagation();
		});
		$('.bookDetailsDeCiAllR3 div a').click(function(e){
			e.stopPropagation();
		});
		$('.bookDetailsDeCiAllR1').click(function(){
			var ARTICLE_ID = $('#ARTICLE_ID').val();
			window.parent.invalbookid(ARTICLE_ID);
			$('.batchEditingPrice', parent.document).show();
			(function(Thunder) {$('.batchEditingPrice', parent.document).css('padding-top', Thunder);})
			((parent.document.documentElement.clientHeight - $('.batchEditingPriceDe', parent.document).get(0).offsetHeight) / 2);
		});
		
		
		$('.bookDetailsDeAfTit a i').click(function(){
			if($(this).hasClass('bookDetailsDeAfTitAct')){
				$(this).removeClass('bookDetailsDeAfTitAct');
				$('.bookDetailsDeAfTex ul li a i').removeClass('bookDetailsDeAfTexAct');
			}else{
				$(this).addClass('bookDetailsDeAfTitAct');
				$('.bookDetailsDeAfTex ul li a i').addClass('bookDetailsDeAfTexAct');
			}
		});
		$('.bookDetailsDeAfTex ul li a i').click(function(){
			if($(this).hasClass('bookDetailsDeAfTexAct')){
				$(this).removeClass('bookDetailsDeAfTexAct');
				$('.bookDetailsDeAfTit a i').removeClass('bookDetailsDeAfTitAct');
			}else{
				$(this).addClass('bookDetailsDeAfTexAct');
			}
		});
	</script>
</body>
</html>
