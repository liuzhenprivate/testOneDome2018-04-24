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
	<title>资源管理书籍管理批量编辑</title>
	    <base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">资源管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="<%=basePath%>article/list.do" title="">书籍管理</a>
	</div>
	<form action="<%=basePath%>article/batchEditState.do" id="editForm" name="editForm" method="post">
	<input type="hidden" name="bookstateid" id="bookstateid" value="${pd.STATE}"/>
	<input type="hidden" name="bookchanneltypeid" id="bookchanneltypeid" value="${pd.CHANNEL_TYPE}"/>
	<input type="hidden" name="bookcategoryid" id="bookcategoryid" value="${pd.CATEGORY}"/>
	<input type="hidden" name="bookishot" id="bookishot" value="${pd.IS_HOT}"/>
	<input type="hidden" name="bookserialstate" id="bookserialstate" value="${pd.SERIAL_STATE}"/>
	<input type="hidden" name="bookpayway" id="bookpayway" value="${pd.PAY_WAY}"/>
	<div class="batchEditingDe">
		<div class="batchEditingDeTop clearfix">
			<div class="clearfix">
				<span onclick="selecty(1);">上架</span>
				<span onclick="selecty(2);">下架</span>
				<span class="batchEditingDeTopTh">价格设置</span>
				<span onclick="selecty(3);">删除</span>
			</div>
			<a href="<%=basePath%>article/list.do" title=""><i>完成编辑</i></a>
		</div>
		<div class="batchEditingDeTit clearfix">
			<a href="javascript:void(0)" title=""><i></i></a>
			<span style="width: 80px; margin-left: 21px;">封面</span>
			<span style="width: 148px;">基本信息</span>
			<span style="width: 113px;">书籍ID</span>
			<span style="width: 100px;">书籍类型</span>
			<span style="width: 95px;">累计消费</span>
			<span style="width: 84px;">全本价格</span>
			<span style="width: 74px;">书籍状态</span>
			<span style="width: 56px;">热门</span>
			<span style="width: 70px;">男女频</span>
			<span style="width: 84px;">添加日期</span>
		</div>
		<div class="batchEditingDeCont">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<input type="hidden" name="ARTICLE_ID" id="ARTICLE_ID" value="${var.ARTICLE_ID}"/>
					<li class="clearfix">
						<a href="javascript:void(0)" title=""><i title="${var.ARTICLE_ID}"></i></a>
						<span class="batchEditingDeContOn">
							<img src="${var.BOOK_COVER}" alt="" />
						</span>
						<span class="batchEditingDeContTw">
							<p><i>书名：</i>${var.ARTICLE_NAME }</p>
							<p><i>作者：</i>${var.AUTHOR }</p>
							<p><i>章节数：</i>${var.COUNT_CHAPTERS }</p>
							<p><i>总字数：</i>${var.COUNT_LETTER }</p>
						</span>
						<span class="batchEditingDeContTh">
							${var.ARTICLE_ID}
						</span>
						<span class="batchEditingDeContFo"><p>${var.CATEGORY}<i>(
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
						)</i></p></span>
						<span class="batchEditingDeContFi">
							<p><i>人数：</i></br>${var.READERS }</p>
							<p><i>阅读币：</i></br>${var.PAY_CONSUMES }</p>
						</span>
						<span class="batchEditingDeContSi">
							<p>${var.COUNT_CONSUMES }</p>
							<p>阅读币</p>
							<h3>VIP免费</h3>
						</span>
						<span class="batchEditingDeContSe">
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
						<span class="batchEditingDeContEi">
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
						<span class="batchEditingDeContNi">
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
						<span class="batchEditingDeContTe">
							<p>${var.CREATE_TIME }</p>
						</span>
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
	</div>
	</form>
	<script>
		function selecty(data){
			var bookcategoryid =  $("#bookcategoryid").val();
			var bookstateid =  $("#bookstateid").val();
			var bookchanneltypeid =  $("#bookchanneltypeid").val();
			var bookishot =  $("#bookishot").val();
			var bookserialstate =  $("#bookserialstate").val();
			var bookpayway =  $("#bookpayway").val();
			var BATCHEDIT_ARTICLEID = "";
			$(".batchEditingDeContAct").each(function(n,value){
				BATCHEDIT_ARTICLEID = BATCHEDIT_ARTICLEID + value.title+",";
			});
			BATCHEDIT_ARTICLEID = BATCHEDIT_ARTICLEID.substring(0, BATCHEDIT_ARTICLEID.length-1);
			$("#editForm").attr("action","<%=basePath%>article/batchEditState.do?PAY_WAY="+bookpayway+"&IS_HOT="+bookishot+"&STATE="+bookstateid+"&CHANNEL_TYPE="+bookchanneltypeid+"&SERIAL_STATE="+bookserialstate+"&CATEGORY="+bookcategoryid+"&EDITARTICLE_ID="+BATCHEDIT_ARTICLEID+"&batchnum="+data);
			$("#editForm").submit();
		}
	</script>
	<script>
		$('.batchEditingDeTit a i').click(function(){
			if($(this).hasClass('batchEditingDeTitAct')){
				$(this).removeClass('batchEditingDeTitAct');
				$('.batchEditingDeCont ul li>a i').removeClass('batchEditingDeContAct');
			}else{
				$(this).addClass('batchEditingDeTitAct');
				$('.batchEditingDeCont ul li>a i').addClass('batchEditingDeContAct');
			}
		})
		$('.batchEditingDeCont ul li>a i').click(function(){
			if($(this).hasClass('batchEditingDeContAct')){
				$(this).removeClass('batchEditingDeContAct');
				$('.batchEditingDeTit a i').removeClass('batchEditingDeTitAct');
			}else{
				$(this).addClass('batchEditingDeContAct');
			}
		})
		
		$('.batchEditingDeTopTh').click(function(){
			var BATCHEDIT_ARTICLEID = "";
			$(".batchEditingDeContAct").each(function(n,value){
				BATCHEDIT_ARTICLEID = BATCHEDIT_ARTICLEID + value.title+",";
			});
			if(BATCHEDIT_ARTICLEID!=""){
				BATCHEDIT_ARTICLEID = BATCHEDIT_ARTICLEID.substring(0, BATCHEDIT_ARTICLEID.length-1);
				window.parent.invalbookid(BATCHEDIT_ARTICLEID);
				$('.batchEditingPrice', parent.document).show();
				(function(Thunder){
					$('.batchEditingPrice', parent.document).css('padding-top',Thunder);
				})((parent.document.documentElement.clientHeight-$('.batchEditingPriceDe', parent.document).get(0).offsetHeight)/2);
			}else{
				alert("请选择书籍");
			}
		});
	</script>
</body>
</html>
