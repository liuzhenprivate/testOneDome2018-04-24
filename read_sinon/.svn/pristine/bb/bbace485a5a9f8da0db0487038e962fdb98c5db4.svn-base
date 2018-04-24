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
	<title>榜单配置（编辑书籍）添加书籍</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/alert.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<form action="<%=basePath%>boarddetail/booksAdd.do" name="addForm" id="addForm" method="post">
	<div class="addBooks">
		<div style="background: #fff; padding-bottom: 20px;">
			<div class="addBooksTop clearfix">
				<span>已选书籍</span>
				<p id="selectBook">已选择 0 本书籍</p>
			</div>
			<div class="addBooksSel clearfix">
				<div class="addBooksSelL clearfix">
					<input id="SEARCHKEY" name="SEARCHKEY"  type="text" value="${pd.SEARCHKEY }" />
					<p>输入书籍名称或作者搜索</p>
					<img onclick="seachList();" src="static/read/images/myPic26.png" alt="" />
				</div>
				<div class="addBooksSelC clearfix">
					<input type="hidden" name="ARTICLE_CATEGORY_ID" id="ARTICLE_CATEGORY_ID" value="${pd.ARTICLE_CATEGORY_ID }">
					<p id="bookType">
						<c:choose>
							<c:when test="${not empty pd.CATEGORY}">
								${pd.CATEGORY}
							</c:when>
							<c:otherwise>
								书籍类型
							</c:otherwise>
						</c:choose>
					</p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul id="bookTypeUl">
					</ul>
				</div>
				<div class="addBooksSelC clearfix">
					<input type="hidden" id="FEE_TYPE" name="FEE_TYPE" value="${pd.FEE_TYPE }">
					<p id="payType">
					<c:choose>
							<c:when test="${not empty pd.payType}">
								${pd.payType}
							</c:when>
							<c:otherwise>
								付费类型
							</c:otherwise>
						</c:choose>
					</p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul>
						<li onclick="payType('','全部类型');">全部类型</li>
						<li onclick="payType('0','免费');">免费</li>
						<li onclick="payType('1','付费');">付费</li>
					</ul>  
				</div>
				<div class="addBooksSelC clearfix">
					<input type="hidden" id="CHANNEL_TYPE" name="CHANNEL_TYPE" value="${pd.CHANNEL_TYPE }">
					<p id="pindao">
					<c:choose>
							<c:when test="${not empty pd.pindao}">
								${pd.pindao}
							</c:when>
							<c:otherwise>
								书籍频道
							</c:otherwise>
						</c:choose>
					</p>
					<img src="static/read/images/myPic13.png" alt="" />
					<ul>
						<li onclick="pindao('','全部频道');">全部频道</li>
						<li onclick="pindao('0','男频');">男频</li>
						<li onclick="pindao('1','女频');">女频</li>
					</ul>
				</div>
			</div>
			<input name="BOARD_ID" id="BOARD_ID" value="${pd.BOARD_ID }" type="hidden" />
			<input name="BOARD_TYPE" id="BOARD_TYPE" value="${pd.BOARD_TYPE }" type="hidden" />
			<div class="addBooksSelTit clearfix">
				<span><i></i></span>
				<div style="width: 70px; margin-left: 21px;">封面</div>
				<div style="width: 146px;">基本信息</div>
				<div style="width: 103px;">书籍ID</div>
				<div style="width: 89px;">书籍类型</div>
				<div style="width: 75px;">付费类型</div>
				<div>男女频</div>
			</div>
			<div class="addBooksSelTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<a><span title="${var.ARTICLE_ID}"></span></a>
						<div class="addBooksSelTexTw">
							<img src="${var.BOOK_COVER }" alt="" />
						</div>
						<div class="addBooksSelTexTh">
							<p><i>书名：</i>${var.ARTICLE_NAME }</p>
							<p><i>作者：</i>${var.AUTHOR }</p>
							<p><i>章节数：</i>${var.COUNT_CHAPTERS }</p>
							<p><i>总字数：</i>${var.COUNT_LETTER }</p>
						</div>
						<div class="addBooksSelTexFo">
							${var.ARTICLE_ID }
						</div>
						<div class="addBooksSelTexFi">
							<p>${var.CATEGORY}<i>
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
							</i></p>
						</div>
						<div class="addBooksSelTexSi">
							<c:choose>
							<c:when test="${not empty var.FEE_TYPE}">
								<c:if test="${var.FEE_TYPE == 0 }">
									免费
								</c:if>
								<c:if test="${var.FEE_TYPE == 1 }">
									付费
								</c:if>
							</c:when>
							<c:otherwise>
								没有相关数据
							</c:otherwise>
							</c:choose>
						</div>
						<div class="addBooksSelTexSe">
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
		</div>
		<input type="hidden" name="articleId" id="articleId" value="">
		<div class="sortBtn">
			完&nbsp;&nbsp;成
		</div>
	</div>
	</form>
	<script type="text/javascript">
		function payType(data,txt){
			$('#FEE_TYPE').val(data);
			$('#payType').text(txt);
			seachList();
		}
		function pindao(data,txt){
			$('#CHANNEL_TYPE').val(data);
			$('#pindao').text(txt);
			seachList();
		}
		function seachList(){
			var CATEGORY = $('#bookType').text();
			var payType = $('#payType').text();
			var pindao = $('#pindao').text();
			document.getElementById("addForm").action="<%=basePath%>boarddetail/goAddBooks.do?CATEGORY="+CATEGORY+"&payType="+payType+"&pindao="+pindao;
	   		document.getElementById("addForm").submit();
		};
	
		$('.sortBtn').click(function(e){
			var articleId = "";
			$('.addBooksSelTexAct').each(function(n,value){
				articleId = articleId + value.title+",";
			});
			$('#articleId').val(articleId);
			if($('#articleId').val()!=null&&$('#articleId').val()!=""){
				$('#addForm').action="<%=basePath%>boarddetail/booksAdd.do?articleId="+articleId;
				$('#addForm').submit();
				e.stopPropagation();
			}
		});
			var alltype = "全部类型";
			$.ajax({
				type: "post",
				data:{},
				url: "<%=basePath%>articlecategory/listJson.do",
				dataType:'json', 
				success: function(data){
					$("#bookTypeUl li").remove();
					$("#bookTypeUl").append("<li onclick=categoryli('"+alltype+"','');>" + alltype + "</li>");
					$.each(data, function(index, item){
			       		$("#bookTypeUl").append("<li onclick=categoryli('"+item.CATEGORY+"','"+item.ARTICLE_CATEGORY_ID+"');>" + item.CATEGORY + "</li>");
			        });
				}
			});
		function categoryli(data,id){
			$('#bookType').text(data);
			$('#ARTICLE_CATEGORY_ID').val(id);
			seachList();
		}
	</script>
	<script>
		$('.addBooksSelL p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		});
		$('.addBooksSelL input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.addBooksSelL input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		});
		
		$('.addBooksSelC').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		});
		
		$('.addBooksSelC ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent('ul').siblings('p').text(val);
			e.stopPropagation();
		});
		$(window).click(function(){
			$('.addBooksSelC').find('ul').hide();
		});
		
		$('.addBooksSelTit span i').click(function(){
			if($(this).hasClass('addBooksSelTitAct')){
				$(this).removeClass('addBooksSelTitAct');
				$('.addBooksSelTex ul li a span').removeClass('addBooksSelTexAct');
				var len = $('.addBooksSelTexAct').length;
				$('#selectBook').text('已选择 '+len+' 本书籍');
			}else{
				$(this).addClass('addBooksSelTitAct');
				$('.addBooksSelTex ul li a span').addClass('addBooksSelTexAct');
				var len = $('.addBooksSelTexAct').length;
				$('#selectBook').text('已选择 '+len+' 本书籍');
			}
		});
		$('.addBooksSelTex ul li a span').click(function(){
			if($(this).hasClass('addBooksSelTexAct')){
				$(this).removeClass('addBooksSelTexAct');
				$('.addBooksSelTit span i').removeClass('addBooksSelTitAct');
				var len = $('.addBooksSelTexAct').length;
				$('#selectBook').text('已选择 '+len+' 本书籍');
			}else{
				$(this).addClass('addBooksSelTexAct');
				var len = $('.addBooksSelTexAct').length;
				$('#selectBook').text('已选择 '+len+' 本书籍');
			}
		});
	</script>
</body>
</html>
