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
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">书籍管理</a>
	</div>
	<form action="<%=basePath%>article/list.do" method="post" name="Form" id="Form">
	<div class="bookManagementDe">
		<div class="bookManagementDeTop clearfix">
			<div class="labelManagementTopL clearfix">
				<div class="labelManagementTopL1 clearfix">
					<input name="names" id="names" type="text" />
					<p>输入书籍名称和ID搜索</p>
					<img src="static/read/images/myPic26.png" onclick="seachsubmit();" alt="" />
				</div>
				<div class="bookManagementTopL2 clearfix">
					<input id="sendtime" name="sendtime" readonly="readonly" />
					<img src="static/read/images/myPic13.png" alt="" />
				</div>
			</div>
			<div class="labelManagementTopR clearfix">
				<a class="labelManagementTopR1"href="javascript:batchedit()" title=""><i>批量编辑</i></a>
				<span class="labelManagementTopR2">
					<i>添加书籍</i>
					<ul>
						<li><a href="<%=basePath%>article/goAdd.do" title="">新增书籍</a></li>
						<li><a href="<%=basePath%>article/batchAddarticle.do" title="">批量新增</a></li>
					</ul>
				</span>
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
				<li class="clearfix"  onclick="seachcategory();">
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
				<%-- <li class="clearfix">
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
				</li> --%>
			</ul>
			<div class="bookManagementDeClickupKey clearfix">
				<a href="javascript:void" onclick="seachsubmit();" title="">查&nbsp;&nbsp;询</a>
				<span>重&nbsp;&nbsp;置</span>
			</div>
			<div class="bookManagementDeClickupBtn">
				<span>收起</span>
			</div>
		</div>
		<div class="bookManagementDeTit clearfix">
			<span style="width: 80px; margin-left: 19px;">封面</span>
			<span style="width: 148px;">基本信息</span>
			<span style="width: 113px;">书籍ID</span>
			<span style="width: 86px;">书籍类型</span>
			<span style="width: 85px;">累计消费</span>
			<span style="width: 84px;">全本价格</span>
			<span style="width: 74px;">书籍状态</span>
			<span style="width: 47px;">热门</span>
			<span style="width: 60px;">男女频</span>
			<span style="width: 85px;">添加日期</span>
			<span>管理操作</span>
		</div>
		<div class="bookManagementDeCont">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<input type="hidden" name="ARTICLE_ID" id="ARTICLE_ID" value="${var.ARTICLE_ID}"/>
					<li class="clearfix">
						<span class="bookManagementDeContOn">
							<img src="${var.BOOK_COVER}" alt="" />
						</span>
						<span class="bookManagementDeContTw">
							<p><i>书名：</i>${var.ARTICLE_NAME }</p>
							<p><i>作者：</i>${var.AUTHOR }</p>
							<p><i>章节数：</i>${var.COUNT_CHAPTERS }</p>
							<p><i>总字数：</i>${var.COUNT_LETTER }</p>
						</span>
						<span class="bookManagementDeContTh">
							${var.ARTICLE_ID}
						</span>
						<span class="bookManagementDeContFo"><p>${var.CATEGORY}<br/><i>
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
						</i></p></span>
						<span class="bookManagementDeContFi">
							<p><i>人数：</i></br>${var.READERS }</p>
							<p><i>阅读币：</i></br>${var.PAY_CONSUMES }</p>
						</span>
						<span class="bookManagementDeContSi">
							<p>${var.COUNT_CONSUMES }</p>
						</span>
						<span class="bookManagementDeContSe">
						<c:choose>
							<c:when test="${not empty var.STATE}">
								<c:if test="${var.STATE == -1 }">
									已删除
								</c:if>
								<c:if test="${var.STATE == 0 }">
									未上架
								</c:if>
								<c:if test="${var.STATE == 1 }">
									已上架
								</c:if>
								<c:if test="${var.STATE == 2 }">
									已下架
								</c:if>
							</c:when>
							<c:otherwise>
								没有相关数据
							</c:otherwise>
						</c:choose>
						</span>
						<span class="bookManagementDeContEi">
						<c:choose>
							<c:when test="${not empty var.STATE}">
								<c:if test="${var.IS_HOT == 0 }">
									否
								</c:if>
								<c:if test="${var.IS_HOT == 1 }">
									是
								</c:if>
							</c:when>
							<c:otherwise>
								没有相关数据
							</c:otherwise>
						</c:choose>
						</span>
						<span class="bookManagementDeContNi">
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
						</span>
						<span class="bookManagementDeContTe">
							<p>${var.CREATE_TIME }</p>
						</span>
						<span class="bookManagementDeContEl">
							<i>操作</i>
							<div class="bookManagementDeContElDiv">
								<a>预览</a>
								<a href="<%=basePath%>article/goEdit.do?ARTICLE_ID=${var.ARTICLE_ID}">编辑</a>
								<c:if test="${var.STATE == 0 }">
									<a href="<%=basePath%>article/editState?STATE=1&ARTICLE_ID=${var.ARTICLE_ID}">上架</a>
								</c:if>
								<c:if test="${var.STATE == 2 }">
									<a href="<%=basePath%>article/editState?STATE=1&ARTICLE_ID=${var.ARTICLE_ID}">上架</a>
								</c:if>
								<c:if test="${var.STATE == 1 }">
									<a href="<%=basePath%>article/editState?STATE=2&ARTICLE_ID=${var.ARTICLE_ID}">下架</a>
								</c:if>
								<c:if test="${var.STATE != 1 }">
									<a href="<%=basePath%>article/editState?STATE=-1&ARTICLE_ID=${var.ARTICLE_ID}">删除</a>
								</c:if>
							</div>
						</span>
							<a href="<%=basePath%>article/findById.do?ARTICLE_ID=${var.ARTICLE_ID}" title=""></a>
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
			document.getElementById("Form").action="<%=basePath%>article/list.do?IS_HOT="+bookishot+"&STATE="+bookstateid+"&CHANNEL_TYPE="+bookchanneltypeid+"&SERIAL_STATE="+bookserialstate+"&CATEGORY="+bookcategoryid;
	   		document.getElementById("Form").submit();
		}
		function batchedit(){
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
			document.getElementById("Form").action="<%=basePath%>article/batctEdit.do?IS_HOT="+bookishot+"&STATE="+bookstateid+"&CHANNEL_TYPE="+bookchanneltypeid+"&SERIAL_STATE="+bookserialstate+"&CATEGORY="+bookcategoryid;
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
			$('.bookManagementDeContElDiv').hide();
			$('.labelManagementTopR2 ul').hide();
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
		
		
		$('.bookManagementDeContEl i').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.bookManagementDeContElDiv').hide();
			e.stopPropagation();
		})
		$('.bookManagementDeContElDiv a').click(function(e){
			e.stopPropagation();
		})
		$('.bookManagementDeContElDiv a').click(function(){
			$(this).parent().hide();
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