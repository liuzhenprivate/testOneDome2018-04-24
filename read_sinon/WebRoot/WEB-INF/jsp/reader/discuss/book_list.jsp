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
	<title>书评管理</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/moduleConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">模板配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">页面配置</a>
	</div>
	<form action="<%=basePath%>discuss/list.do" id="Form" name="Form" method="post">
	<div class="managementBookDe">
		<h1>书评管理</h1>
		<div class="bookManagementDeTop clearfix">
			<div class="labelManagementTopL clearfix">
				<div class="labelManagementTopL1 clearfix">
					<input type="text" name = "names" id="names" value="${pd.names }"/>
					<p>输入书籍或作者名称</p>
					<img src="static/read/images/myPic26.png" alt="" onclick="seachsubmit();"/>
				</div>
				<div class="bookManagementTopL2 clearfix">
					<input id="sendtime" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
			</div>
			 
		</div>
		<div class="bookManagementDeClickdown">
			<span>展开搜索条件</span>
		</div>
		<div class="bookManagementDeClickup">
			<ul class="clearfix">
				<li style="margin-left: 19px;" class="clearfix">
					<span>书籍状态</span>
					<div class="bookManagementDeClickupDiv clearfix">
						<p id="bookstateid">
						<c:choose>
							<c:when test="${not empty pd.STATE}">
								${pd.STATE}
							</c:when>
							<c:otherwise>
								全部状态
							</c:otherwise>
						</c:choose>
						</p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li>全部状态</li>
							<li>未上架</li>
							<li>已上架</li>
							<li>已下架</li>
							<li>已删除</li>
						</ul>
					</div>
				</li>
				<li class="clearfix">
					<span>男女频道</span>
					<div class="bookManagementDeClickupDiv clearfix">
						<p id="bookchanneltypeid">
						<c:choose>
							<c:when test="${not empty pd.CHANNEL_TYPE}">
								${pd.CHANNEL_TYPE}
							</c:when>
							<c:otherwise>
								全部频道
							</c:otherwise>
						</c:choose>
						</p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li>全部频道</li>
							<li>男</li>
							<li>女</li>
						</ul>
					</div>
				</li>
				<li class="clearfix">
					<span>书籍类型</span>
					<div class="bookManagementDeClickupDiv clearfix" onclick="seachcategory();">
						<p id="bookcategoryid" onclick="seachcategory();">
							<c:choose>
							<c:when test="${not empty pd.CATEGORY}">
								${pd.CATEGORY}
							</c:when>
							<c:otherwise>
								全部类型
							</c:otherwise>
						</c:choose>
						</p>
						<img src="static/read/images/myPic13.png" onclick="seachcategory();" alt="" />
						<ul id="bookcategoryidul">
						</ul>
					</div>
				</li>
				<li style="margin-left: 19px;" class="clearfix">
					<span>是否热门</span>
					<div class="bookManagementDeClickupDiv clearfix">
						<p id="bookishot">
						<c:choose>
							<c:when test="${not empty pd.IS_HOT}">
								${pd.IS_HOT}
							</c:when>
							<c:otherwise>
								全部
							</c:otherwise>
						</c:choose>
						</p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li>全部</li>
							<li>否</li>
							<li>是</li>
						</ul>
					</div>
				</li>
				<li class="clearfix">
					<span>连载状态</span>
					<div class="bookManagementDeClickupDiv clearfix">
						<p id="bookserialstate">
						<c:choose>
							<c:when test="${not empty pd.SERIAL_STATE}">
								${pd.SERIAL_STATE}
							</c:when>
							<c:otherwise>
								全部状态
							</c:otherwise>
						</c:choose>
						</p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li>全部状态</li>
							<li>连载中</li>
							<li>已完结</li>
						</ul>
					</div>
				</li>
				<li class="clearfix">
					<span>付费方式</span>
					<div class="bookManagementDeClickupDiv clearfix">
						<p id="bookpayway">
						<c:choose>
							<c:when test="${not empty pd.PAY_WAY}">
								${pd.PAY_WAY}
							</c:when>
							<c:otherwise>
								全部方式
							</c:otherwise>
						</c:choose>
						</p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li>全部方式</li>
							<li>阅读币购买阅读</li>
							<li>免费阅读</li>
						</ul>
					</div>
				</li>
			</ul>
			<div class="bookManagementDeClickupKey clearfix">
				<a href="javascript:void" onclick="seachsubmit();" title="">查&nbsp;&nbsp;询</a>
				<span>重&nbsp;&nbsp;置</span>
			</div>
			<div class="bookManagementDeClickupBtn">
				<span>收起</span>
			</div>
		</div>
		<div class="managementBookTit clearfix">
			<span style="margin-left: 20px; width: 80px;">封面</span>
			<span style="width: 147px;">基本信息</span>
			<span style="width: 103px;">书籍ID</span>
			<span style="width: 98px;">书籍类型</span>
			<span style="width: 77px;">付费方式</span>
			<span style="width: 109px;">当前阅读数</span>
			<span style="width: 142px;">显示阅读数</span>
			<span style="width: 72px;">男女频</span>
			<span>评论数</span>
		</div>
		<div class="managementBookTex">
			<ul>
			<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
				<li class="clearfix">
					<div class="managementBookTexOn">
						<img src="${var.BOOK_COVER}" alt="" />
					</div>
					<div class="managementBookTexTw">
						<p><i>书名：</i>${var.ARTICLE_NAME }</p>
						<p><i>作者：</i>${var.AUTHOR }</p>
						<p><i>章节数：</i>${var.COUNT_CHAPTERS }</p>
						<p><i>总字数：</i>${var.COUNT_LETTER }</p>
					</div>
					<div class="managementBookTexTh">
						${var.ARTICLE_ID}
					</div>
					<div class="managementBookTexFo">
						<p>${var.CATEGORY}<i>（
							<c:choose>
								<c:when test="${not empty var.SERIAL_STATE}">
									<c:if test="${var.SERIAL_STATE == 0 }">
										连载中
									</c:if>
									<c:if test="${var.SERIAL_STATE == 1 }">
										已完结
									</c:if>
								</c:when>
								<c:otherwise>
									没有相关数据
								</c:otherwise>
							</c:choose>
						）</i></p>
					</div>
					<div class="managementBookTexFi">
						<p>${var.COUNT_CONSUMES }</p>
						<p>阅读币</p>
						<h3>VIP免费</h3>
					</div>
					<div class="managementBookTexSi">
						${var.READERS }人
					</div>
					<div class="managementBookTexSe">
						<span>${var.DISPLAY_READERS }</span>
						<div class="managementBookTexSeInp clearfix">
							<input title="${var.ARTICLE_ID }" value="" />
						</div>
					</div>
					<div class="managementBookTexEi">
						<c:choose>
							<c:when test="${not empty var.STATE}">
								<c:if test="${var.CHANNEL_TYPE == 0 }">
									男频
								</c:if>
								<c:if test="${var.CHANNEL_TYPE == 1 }">
									女频
								</c:if>
							</c:when>
							<c:otherwise>
								没有相关数据
							</c:otherwise>
						</c:choose>
					</div>
					<div class="managementBookTexNi">
						${var.Countdiscuss }
					</div>
					<div class="managementBookTexTe">
						<a href="<%=basePath%>discuss/goDiscuss.do?ARTICLE_ID=${var.ARTICLE_ID}" title="书评管理"><img src="static/read/images/myPic66.png" alt="" /></a>
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
	<script type="text/javascript">
		function seachsubmit(){
			var bookcategoryid =  document.getElementById("bookcategoryid");
				bookcategoryid = bookcategoryid.innerHTML;
			var bookstateid =  document.getElementById("bookstateid");
				bookstateid = bookstateid.innerHTML;
			var bookchanneltypeid =  document.getElementById("bookchanneltypeid");
				bookchanneltypeid = bookchanneltypeid.innerHTML;
			var bookishot =  document.getElementById("bookishot");
				bookishot = bookishot.innerHTML;
			var bookserialstate =  document.getElementById("bookserialstate");
				bookserialstate = bookserialstate.innerHTML;
			var bookpayway =  document.getElementById("bookpayway");
				bookpayway = bookpayway.innerHTML;
			document.getElementById("Form").action="<%=basePath%>discuss/list.do?PAY_WAY="+bookpayway+"&IS_HOT="+bookishot+"&STATE="+bookstateid+"&CHANNEL_TYPE="+bookchanneltypeid+"&SERIAL_STATE="+bookserialstate+"&CATEGORY="+bookcategoryid;
	   		document.getElementById("Form").submit();
		}
		
		function seachcategory(){
			var alltype = "全部类型";
			$.ajax({
				type: "post",
				data:{},
				url: "<%=basePath%>articlecategory/listJson.do",
				dataType:'json', 
				success: function(data){
					$("#bookcategoryidul li").remove();
					$("#bookcategoryidul").append("<li onclick=categoryli('"+alltype+"');>" + alltype + "</li>");
					$.each(data, function(index, item){
			       		$("#bookcategoryidul").append("<li onclick=categoryli('"+item.CATEGORY+"');>" + item.CATEGORY + "</li>");
			        });
				},
				error: function () {    
		        alert("数据错误");
		        }
			});
		}
		
		function categoryli(data){
			var bookcategoryid =  document.getElementById("bookcategoryid");
			bookcategoryid.innerHTML=data;
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
		
		$('.labelManagementTopR2').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		});
		
		$(window).click(function(){
			$('.labelManagementTopR2 ul').hide();
		});
		
		$('.bookManagementDeClickdown span').click(function(){
			$('.bookManagementDeClickdown').hide();
			$('.bookManagementDeClickup').show();
		});
		$('.bookManagementDeClickupBtn span').click(function(){
			$('.bookManagementDeClickdown').show();
			$('.bookManagementDeClickup').hide();
		});
		
		$('.managementBookTexSe span').click(function(){
			var spanVal=$(this).text();
			$(this).siblings('.managementBookTexSeInp').show().find('input').val(spanVal);
			$(this).siblings('.managementBookTexSeInp').find('input').focus();
		});
		$('.managementBookTexSeInp input').blur(function(){
			var inpVal=$(this).val();
			var reg = /^[1-9]\d*$/  ;
			var articleId = $(this).attr('title');
			if (reg.test(inpVal) == true) {
				$.ajax({
					type: "post",
					data:{
						'ARTICLE_ID':articleId,
						'DISPLAY_READERS':inpVal
					},
					url: "<%=basePath%>discuss/updateReads.do",
				});
				$(this).parent().siblings('span').text(inpVal);
			}
			$(this).parent('.managementBookTexSeInp').hide();
		});
		
		$(window).click(function(){
			$('.labelManagementTopR2 ul').hide();
			$('.bookManagementDeClickupDiv').find('ul').hide();
		});
		$('.bookManagementDeClickupDiv').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		});
		$('.bookManagementDeClickupDiv ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent().siblings('p').text(val);
			e.stopPropagation();
		});
		
		
	</script>
</body>
</html>
