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
<title>资源管理书籍管理</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">资源管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">章节管理</a>
	</div>
	<form action="<%=basePath%>articlechapter/list.do" id="chapterForm" name="chapterForm" method="post">
	<div class="bookManagementDe">
		<div class="bookManagementDeTop clearfix">
			<div class="labelManagementTopL clearfix">
				<div class="labelManagementTopL1 clearfix">
					<input type="text" name="booknamez" id="booknamez" value="${pd.booknamez }"/>
					<p>输入书籍名称或作者搜索</p>
					<a href="javascript:seachListchapter()" title=""><img src="static/read/images/myPic26.png" alt="" /></a>
				</div>
				<div class="bookManagementTopL2 clearfix">
					<input id="sendtime" name="sendtime" readonly="readonly" value="${pd.sendtime }"/>
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
			</div>
			<div class="labelManagementTopR clearfix">
				<a class="labelManagementTopR1" href="<%=basePath%>articlechapter/chapterBatchEdit.do?CATEGORY=${pd.CATEGORY}&CHAPTER_STATE=${pd.CHAPTER_STATE}&IS_FREE=${pd.IS_FREE}&CHANNEL_TYPE=${pd.CHANNEL_TYPE}&booknamez=${pd.booknamez }" title=""><i>批量编辑</i></a>
				<span class="labelManagementTopR2">
					<i>添加章节</i>
					<ul>
						<li><a href="<%=basePath%>articlechapter/goAdd.do" title="">新增章节</a></li>
						<li><a href="<%=basePath%>articlechapter/goBatchAdd.do" title="">批量章节</a></li>
					</ul>
				</span>
			</div>
		</div>
		<div class="bookManagementDeClickdown">
			<span>展开搜索条件</span>
		</div>
		<div class="bookManagementDeClickup">
			<ul class="clearfix" onclick="seachcategory();">
				<li style="margin-left: 19px;" class="clearfix">
					<span>章节类型</span>
					<input type="hidden" name="CATEGORY" id="CATEGORY" value="${pd.CATEGORY }">
					<div class="bookManagementDeClickupDiv clearfix"  onclick="seachcategory();">
						<p id="CATEGORY1" onclick="seachcategory();">
							<c:choose>
								<c:when test="${not empty pd.CATEGORY}">
									${pd.CATEGORY}
								</c:when>
								<c:otherwise>
									全部类型
								</c:otherwise>
							</c:choose>
						</p>
						<img src="static/read/images/myPic13.png" alt="" onclick="seachcategory();"/>
						<ul id="CATEGORY_UL">
						</ul>
					</div>
				</li>
				<li class="clearfix">
					<span>章节状态</span>
						<input type="hidden" name="CHAPTER_STATE" id="CHAPTER_STATE" value="${pd.CHAPTER_STATE }">
					<div class="bookManagementDeClickupDiv clearfix">
						<p>
						<c:choose>
							<c:when test="${not empty pd.CHAPTER_STATE}">
								<c:if test="${pd.CHAPTER_STATE == 0}">
									显示
								</c:if>
								<c:if test="${pd.CHAPTER_STATE == 1}">
									隐藏
								</c:if>
							</c:when>
							<c:otherwise>
								全部状态
							</c:otherwise>
						</c:choose>
						</p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li onclick="chapterState('');">全部状态</li>
							<li onclick="chapterState('0');">显示</li>
							<li onclick="chapterState('1');">隐藏</li>
						</ul>
					</div>
				</li>
				<li class="clearfix">
					<span>是否收费</span>
					<input type="hidden" name="IS_FREE" id="IS_FREE" value="${pd.IS_FREE }">
					<div class="bookManagementDeClickupDiv clearfix">
						<p>
						<c:choose>
							<c:when test="${not empty pd.IS_FREE}">
								<c:if test="${pd.IS_FREE == 0}">
									免费
								</c:if>
								<c:if test="${pd.IS_FREE == 1}">
									收费
								</c:if>
							</c:when>
							<c:otherwise>
								全部
							</c:otherwise>
						</c:choose>
						</p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li onclick="isFree('');">全部</li>
							<li onclick="isFree('1');">收费</li>
							<li onclick="isFree('0');">免费</li>
						</ul>
					</div>
				</li>
				<li style="margin-left: 19px;" class="clearfix">
					<span>男女频道</span>
					<input type="hidden" name="CHANNEL_TYPE" id="CHANNEL_TYPE" value="${pd.CHANNEL_TYPE }">
					<div class="bookManagementDeClickupDiv clearfix">
						<p>
						<c:choose>
							<c:when test="${not empty pd.CHANNEL_TYPE}">
								<c:if test="${pd.CHANNEL_TYPE == 0}">
									男频
								</c:if>
								<c:if test="${pd.CHANNEL_TYPE == 1}">
									女频
								</c:if>
							</c:when>
							<c:otherwise>
								全部频道
							</c:otherwise>
						</c:choose>
						</p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li onclick="channelType('');">全部频道</li>
							<li onclick="channelType('0');">男频</li>
							<li onclick="channelType('1');">女频</li>
						</ul>
					</div>
				</li>
			</ul>
			<div class="bookManagementDeClickupKey clearfix">
				<a href="javascript:seachListchapter()" title="">查&nbsp;&nbsp;询</a>
				<span>重&nbsp;&nbsp;置</span>
			</div>
			<div class="bookManagementDeClickupBtn">
				<span>收起</span>
			</div>
		</div>
		<div class="chapterManagementTit clearfix">
				<span style="margin-left: 20px; width: 80px;">关联书籍</span>
				<span style="width: 225px;">章节名称</span>
				<span style="width: 87px;">类型</span>
				<span style="width: 87px;">章节状态</span>
				<span style="width: 95px;">男女频道</span>
				<span style="width: 149px;">章节价格</span>
				<span style="width: 141px;">添加时间</span>
				<span>管理操作</span>
			</div>
			<div class="chapterManagementTex">
				<ul>
					<c:choose>
					<c:when test="${not empty varList}">
					<c:if test="${QX.cha == 1 }">
					<c:forEach items="${varList}" var="var" varStatus="vs">
						<li class="clearfix">
							<div class="chapterManagementTexOn">${var.ARTICLE_NAME }</div>
							<div class="chapterManagementTexTw">${var.CHAPTER_NAME }</div>
							<div class="chapterManagementTexTh">
								${var.CATEGORY }
								
							</div>
							<div class="chapterManagementTexFo">
								<c:if test="${var.CHAPTER_STATE  == 0 }">
									显示
								</c:if>
								<c:if test="${var.CHAPTER_STATE  == 1 }">
									隐藏
								</c:if>
							</div>
							<div class="chapterManagementTexFi">
								<c:if test="${var.CHANNEL_TYPE  == 0 }">
									男
								</c:if>
								<c:if test="${var.CHANNEL_TYPE  == 1 }">
									女
								</c:if>
							</div>
							<div class="chapterManagementTexSi">
								<span>${var.CONSUMES }</span>
								<div class="chapterManagementTexSiInp clearfix">
									<input name="CONSUMES" id="CONSUMES" title="${var.ARTICLE_CHAPTER_ID }" value="${var.CONSUMES }"/>
								</div>
							</div>
							<div class="chapterManagementTexSe">${var.CREATE_TIME }</div>
							<div class="chapterManagementTexEi">
								<span>操作</span>
								<div>
									<a>预览</a>
									<a href="<%=basePath%>articlechapter/goEdit.do?ARTICLE_CHAPTER_ID=${var.ARTICLE_CHAPTER_ID }">编辑</a>
									<c:if test="${var.CHAPTER_STATE  == 0 }">
										<a  href="<%=basePath%>articlechapter/update.do?ARTICLE_CHAPTER_ID=${var.ARTICLE_CHAPTER_ID}&CHAPTER_STATE=1">隐藏</a>
									</c:if>
									<c:if test="${var.CHAPTER_STATE  == 1 }">
										<a  href="<%=basePath%>articlechapter/update.do?ARTICLE_CHAPTER_ID=${var.ARTICLE_CHAPTER_ID}&CHAPTER_STATE=0">显示</a>
									</c:if>
									<a href="<%=basePath%>articlechapter/update.do?ARTICLE_CHAPTER_ID=${var.ARTICLE_CHAPTER_ID}&CHAPTER_STATE=2">删除</a>
								</div>
							</div>
						</li>
					</c:forEach>
					</c:if>
					<c:if test="${QX.cha == 0 }">
						您无权查看
					</c:if>
					</c:when>
					<c:otherwise>
						没有相关数据
					</c:otherwise>
					</c:choose>
				</ul>
			</div>
		<div class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
	</div>
	</form>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
		//查询
		function seachListchapter(){
			$("#CHAPTER_STATE1").val(CHAPTER_STATE = $("#CHAPTER_STATE").text());
			$("#IS_FREE1").val(IS_FREE = $("#IS_FREE").text());
			$("#CHANNEL_TYPE1").val(CHANNEL_TYPE = $("#CHANNEL_TYPE").text());
			$("#chapterForm").action="<%=basePath%>articlechapter/list.do";
	   		$("#chapterForm").submit();
		}
		//查询所有书籍类型
		function seachcategory(){
			var cssdisplay = $("#CATEGORY_UL").css('display');
			if(cssdisplay=="none"){
				var alltype = "全部类型";
				$.ajax({
					type: "post",
					data:{},
					url: "<%=basePath%>articlecategory/listJson.do",
					dataType:'json', 
					success: function(data){
						$("#CATEGORY_UL li").remove();
						$("#CATEGORY_UL").append("<li onclick=categoryli('"+alltype+"');>" + alltype + "</li>");
						$.each(data, function(index, item){
				       		$("#CATEGORY_UL").append("<li onclick=categoryli('"+item.CATEGORY+"',"+item.ARTICLE_CATEGORY_ID+");>" + item.CATEGORY + "</li>");
				        });
					}
				});
			}
		}
		function categoryli(data){
			$("#CATEGORY").val(data);
			$("#CATEGORY1").text(data);
		}	
		function channelType(data){
			$("#CHANNEL_TYPE").val(data);
		}
		function isFree(data){
			$("#IS_FREE").val(data);
		}
		function chapterState(data){
			$("#CHAPTER_STATE").val(data);
		}
	</script>
	<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("Thunder"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		
		laydate.render({
		  elem: '#sendtime'
		  ,range: true
		});
		
		$('.labelManagementTopL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
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
		})
		
		$('.labelManagementTopR2').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		
		$(window).click(function(){
			$('.bookManagementDeClickupDiv').find('ul').hide();
			$('.labelManagementTopR2 ul').hide();
			$('.chapterManagementTexEi div').hide();
		})
		$('.bookManagementDeClickupDiv').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		
		$('.bookManagementDeClickupDiv ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent().siblings('p').text(val);
			e.stopPropagation();
		})
		
		$('.bookManagementDeClickdown span').click(function(){
			$('.bookManagementDeClickdown').hide();
			$('.bookManagementDeClickup').show();
		})
		$('.bookManagementDeClickupBtn span').click(function(){
			$('.bookManagementDeClickdown').show();
			$('.bookManagementDeClickup').hide();
		})
		
		$('.chapterManagementTexSi span').click(function(){
			var spanVal=$(this).text();
			$(this).siblings('.chapterManagementTexSiInp').show().find('input').val(spanVal);
			$(this).siblings('.chapterManagementTexSiInp').find('input').focus();
		})
		$('.chapterManagementTexSiInp input').blur(function(){
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
						}
					}
				});
			$(this).parent().siblings('span').text(inpVal);
			$(this).parent('.chapterManagementTexSiInp').hide();
		})
		
		$('.chapterManagementTexEi span').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			e.stopPropagation();
		})
		$('.chapterManagementTexEi div a').click(function(e){
			$(this).parent('div').hide();
			e.stopPropagation();
		})
		
		$('.flipTwoC1').click(function(){
			var spanVal=parseInt($('.flipTwoC span').text());
			spanVal++;
			$('.flipTwoC span').text(spanVal+'条/页');
		})
		$('.flipTwoC2').click(function(){
			var spanVal=parseInt($('.flipTwoC span').text());
			if(spanVal>0){
				spanVal--;
			}
			$('.flipTwoC span').text(spanVal+'条/页');
		})
		
		
	</script>
</body>
</html>