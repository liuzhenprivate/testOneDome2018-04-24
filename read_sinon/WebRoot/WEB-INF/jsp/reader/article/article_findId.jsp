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
		<a class="announcementManagementTop2" href='javascript:history.go(-1)' title="">书籍管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">书籍详情</a>
	</div>
	
	<div class="bookDetailsDe">
		<div class="bookDetailsDeTop clearfix">
			<img src="${articleById.BOOK_COVER}" alt="" />
			<div class="bookDetailsDeTopR">
				<div class="bookDetailsDeTopRtit clearfix">
					<h2>${articleById.ARTICLE_NAME}</h2>
					<p>${articleById.AUTHOR}&nbsp;&nbsp;著</p>
				</div>
				<div class="bookDetailsDeTopRsel clearfix">
					<a class="bookDetailsDeTopRsel1" href="javascript:void(0)" title="">
							<c:if test="${articleById.SERIAL_STATE == 0 }">
								连载中
							</c:if>
							<c:if test="${articleById.SERIAL_STATE == 1 }">
								已完结
							</c:if>
					</a>
					<a class="bookDetailsDeTopRsel2" href="javascript:void(0)" title="">VIP</a>
					<a class="bookDetailsDeTopRsel3" href="javascript:void(0)" title="">${articleById.CATEGORY}</a>
					<c:choose>
						<c:when test="${not empty varlabellist}">
							<c:if test="${QX.cha == 1 }">
							<c:forEach items="${varlabellist}" var="var" varStatus="vs">
								<a class="bookDetailsDeTopRsel4" href="javascript:void(0)" title="">${var.LABEL_NAME }</a>
								</c:forEach>
							</c:if>
							<c:if test="${QX.cha == 0 }">
								您无权查看
							</c:if>
						</c:when>
					</c:choose>
				</div>
				<div class="bookDetailsDeTopint clearfix">
					<h2>${articleById.COUNT_LETTER}<span>万字</span></h2>
					<h2 style="margin-left: 20px;">${articleById.COUNT_CHAPTERS}<span>章节</span></h2>
				</div>
				<div class="bookDetailsDeTopnub">
					书籍编号：${articleById.ARTICLE_CODE}
				</div>
				<div class="bookDetailsDeToppri">
					<p>全书价格：<span id="COUNT_CONSUMES">${articleById.COUNT_CONSUMES}</span>阅读币</p>
				</div>
				<div class="bookDetailsDeTopnub">
					推荐指数：${articleById.RECOMMEND}
				</div>
				<div class="bookDetailsDeTopbtn clearfix">
						<c:if test="${articleById.STATE  == 0 }">
							<a class="bookDetailsDeTopbtn1" href="javascript:bookonshelves(${articleById.ARTICLE_ID},'1')" title="">上架</a>
						</c:if>
						<c:if test="${articleById.STATE  == 2 }">
							<a class="bookDetailsDeTopbtn1" href="javascript:bookonshelves(${articleById.ARTICLE_ID},'1')" title="">上架</a>
						</c:if>
						<c:if test="${articleById.STATE  == 1 }">
							<a class="bookDetailsDeTopbtn1" href="javascript:bookonshelves(${articleById.ARTICLE_ID},'2')" title="">下架</a>
						</c:if>
					<a class="bookDetailsDeTopbtn2" href="javascript:bookeditid(${articleById.ARTICLE_ID})" title="">编辑</a>
				</div>
			</div>
		</div>
		<div class="bookDetailsDeCs">
			<h1>消费统计</h1>
			<ul class="clearfix">
				<li>
					<h3>累计消费</h3>
					<h2>
						<c:if test="${chapterpd.FEES  == null }">
							0
						</c:if>
						<c:if test="${chapterpd.FEES  != null }">
							${chapterpd.FEES }
						</c:if>
					</h2>
				</li>
				<li>
					<h3>付费人数</h3>
					<h2>
						<c:if test="${chapterpd.USERS  == null }">
							0
						</c:if>
						<c:if test="${chapterpd.USERS  != null }">
							${chapterpd.USERS }
						</c:if>
					</h2>
				</li>
				<li>
					<h3>收藏人数</h3>
					<h2>
						<c:if test="${articleById.COLLECTION  == null }">
							0
						</c:if>
						<c:if test="${articleById.COLLECTION  != null }">
							${articleById.COLLECTION }
						</c:if>
					</h2>
				</li>
				<li>
					<h3>阅读人数</h3>
					<h2>
						<c:if test="${articleById.READERS  == null }">
							0
						</c:if>
						<c:if test="${articleById.READERS  != null }">
							${articleById.READERS }
						</c:if>
					</h2>
				</li>
			</ul>
		</div>
		<form action="<%=basePath%>article/findById.do" method="post" name="Form" id="Form">
		<input type="hidden" id="ARTICLE_ID" name="ARTICLE_ID" value="${articleById.ARTICLE_ID}"/>
		<div class="bookDetailsDeBe">
			<div class="bookDetailsDeCi">
				<h1>章节信息</h1>
				<div class="bookDetailsDeCiAll clearfix">
					<div class="labelManagementTopL clearfix">
						<div class="labelManagementTopL1 clearfix">
							<input id="CHAPTER_NAME" name="CHAPTER_NAME" type="text" value=""/>
							<p>请输入章节名称</p>
							<img src="static/read/images/myPic26.png" alt="" onclick="submitchapter();"/>
						</div>
						<div class="bookManagementTopL2 clearfix">
							<input id="CHAPTER_TIME" name="CHAPTER_TIME" readonly="readonly"  value="${pd.CHAPTER_TIME }"/>
							<img src="static/read/images/myPic13.png" alt="" />
						</div>
					</div>
					<div class="bookDetailsDeCiAllR clearfix">
						<a class="bookDetailsDeCiAllR1" href="javascript:void(0)" title=""><span>价格设置</span></a>
						<a class="bookDetailsDeCiAllR2" href="<%=basePath%>article/findById.do?ARTICLE_ID=${articleById.ARTICLE_ID}&batch=0" title=""><span>批量编辑</span></a>
						<div class="bookDetailsDeCiAllR3">
							<a href="<%=basePath%>article/goAddChapter.do?ARTICLE_ID=${articleById.ARTICLE_ID}" title=""><span>添加章节</span></a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="bookDetailsDeTit clearfix">
				<span style="margin-left: 20px; width: 48px;">序号</span>
				<span style="width: 275px;">章节名称</span>
				<span style="width: 117px;">章节状态</span>
				<span style="width: 135px;">章节字数</span>
				<span style="width: 148px;">章节价格</span>
				<span style="width: 140px;">添加日期</span>
				<span>管理操作</span>
			</div>
			<div class="bookDetailsDeTex">
				<ul>
				<c:choose>
						<c:when test="${not empty varchapterlist}">
							<c:if test="${QX.cha == 1 }">
							<c:forEach items="${varchapterlist}" var="var" varStatus="vs">
							<li class="clearfix">
								<div class="bookDetailsDeTexOn">${vs.index+1+(10*(page.currentPage-1))}</div>
								<div class="bookDetailsDeTexTw">${var.CHAPTER_NAME }</div>
								<div class="bookDetailsDeTexTh"><c:if test="${var.CHAPTER_STATE  == 0 }">显示</c:if><c:if test="${var.CHAPTER_STATE  == 1 }">隐藏</c:if></div>
								<div class="bookDetailsDeTexFo">${var.NUMBER_CHAPTER }</div>
								<div class="bookDetailsDeTexFi">
									<span>${var.CONSUMES }</span>
									<div class="bookDetailsDeTexFiInp clearfix"><input name="CONSUMES" id="CONSUMES"  title="${var.ARTICLE_CHAPTER_ID }" value="${var.CONSUMES }"/></div>
								</div>
								<div class="bookDetailsDeTexSi">${var.CREATE_TIME }</div>
								<div class="bookDetailsDeTexSe">
									<span>操作</span>
									<div>
										<a href="<%=basePath%>articlechapter/goBookEdit.do?ARTICLE_CHAPTER_ID=${var.ARTICLE_CHAPTER_ID }">编辑</a>
										<c:if test="${var.CHAPTER_STATE  == 0 }">
											<a onclick="upState('${var.ARTICLE_CHAPTER_ID}','1');">隐藏</a>
										</c:if>
										<c:if test="${var.CHAPTER_STATE  == 1 }">
											<a onclick="upState('${var.ARTICLE_CHAPTER_ID}','0');">显示</a>
										</c:if>
										<a onclick="upState('${var.ARTICLE_CHAPTER_ID}','2');">删除</a>
									</div>
								</div>
							</li>
							</c:forEach>
							</c:if>
							<c:if test="${QX.cha == 0 }">
								您无权查看
							</c:if>
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
		function editstate(data){
			var ARTICLE_CHAPTER_ID = "";
			$(".bookDetailsDeAfTexAct").each(function(n,value){
				ARTICLE_CHAPTER_ID = ARTICLE_CHAPTER_ID + value.title+",";
			});
			var ARTICLE_ID = $("#ARTICLE_ID").val();
			if(ARTICLE_CHAPTER_ID!=""){
				$.get("<%=basePath%>articlechapter/chapterEditState.do?ARTICLE_ID="+ARTICLE_ID+"&ARTICLE_CHAPTER_ID="+ARTICLE_CHAPTER_ID+"&CHAPTER_STATE="+data,function(data,status){
					location.reload();
				});
			}else{
				alert("请选择章节");
			}
		}
	</script>
	<script>
		function readlocation(){
			location.reload();
		}
		function upState(id,state){
			var ARTICLE_ID = $("#ARTICLE_ID").val();
			$.get("<%=basePath%>articlechapter/editstate.do?ARTICLE_ID="+ARTICLE_ID+"&ARTICLE_CHAPTER_ID="+id+"&CHAPTER_STATE="+state,function(data,status){
				location.reload();
			});
		}
		function bookeditid(data){
			$("#Form").attr("action","<%=basePath%>article/goEdit.do?ARTICLE_ID="+data);
			$("#Form").submit();
		}
		
		function submitchapter(){
			$("#Form").submit();
		}
		function bookonshelves(data,stata){
			var msg = "修改成功";
			$.ajax({
				type:"post",
				data:{ARTICLE_ID:data,STATE:stata},
				url: "<%=basePath%>article/editState.do",
				error: function(){msg ="修改失败";}
			});
			if(msg=="修改成功"){
				location.reload();
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
		
		$('.bookDetailsDeTexSe span').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			e.stopPropagation();
		});
		$('.bookDetailsDeTexSe div a').click(function(e){
			$(this).parent('div').hide();
			e.stopPropagation();
		});
		$('.bookDetailsDeCiAfall a').click(function(){
			$('.bookDetailsDeBe').show();
			$('.bookDetailsDeAf').hide();
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