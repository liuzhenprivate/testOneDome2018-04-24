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
	<title>榜单配置（编辑书籍）批量</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/moduleConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<input name="BOARD_ID" id="BOARD_ID" value="${pd.BOARD_ID }" type="hidden" />
	<input name="BOARD_TYPE" id="BOARD_TYPE" value="${pd.BOARD_TYPE }" type="hidden" />
	<div class="batchEditeDe clearfix">
		<div class="batchEditeDeT clearfix">
			<div class="batchEditeDeTL clearfix">
				<span onclick="funedit('top');">置顶</span>
				<span onclick="funedit('dow');">置底</span>
				<span onclick="funedit('del');">删除</span>
			</div>
			<a href="<%=basePath%>boarddetail/list.do?BOARD_ID=${pd.BOARD_ID}&BOARD_TYPE=${pd.BOARD_TYPE}" title=""><span>完成编辑</span></a>
		</div>
		
		<div class="batchEditeDeTit clearfix">
			<span><i></i></span>
			<div style="width: 48px; margin-left: 19px;">排名</div>
			<div style="width: 70px;">封面</div>
			<div style="width: 148px;">基本信息</div>
			<div style="width: 112px;">书籍ID</div>
			<div style="width: 98px;">书籍类型</div>
			<div style="width: 86px;">付费类型</div>
			<div style="width: 99px;">当前阅读数</div>
			<div style="width: 99px;">显示阅读数</div>
			<div style="width: 73px;">男女频</div>
			<div>排序类型</div>
		</div>
		<div class="batchEditeDeTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<a><span title="${var.BOARD_DETAIL_ID}"></span></a>
						<div class="batchEditeDeTexOn">${vs.index+1+(10*(page.currentPage-1))}</div>
						<div class="batchEditeDeTexTw">
							<img src="${var.BOOK_COVER }" alt="" />
						</div>
						<div class="batchEditeDeTexTh">
							<p><i>书名：</i>${var.ARTICLE_NAME }</p>
							<p><i>作者：</i>${var.AUTHOR }</p>
							<p><i>章节数：</i>${var.COUNT_CHAPTERS }</p>
							<p><i>总字数：</i>${var.COUNT_LETTER }</p>
						</div>
						<div class="batchEditeDeTexFo">
							${var.ARTICLE_ID }
						</div>
						<div class="batchEditeDeTexFi">
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
						<div class="batchEditeDeTexSi">
							<p></p>
							<c:choose>
							<c:when test="${not empty var.FEE_TYPE}">
								<c:if test="${var.FEE_TYPE == 0 }">
									<p>免费</p>
								</c:if>
								<c:if test="${var.FEE_TYPE == 1 }">
									<p>收费</p>
								</c:if>
							</c:when>
							<c:otherwise>
								没有相关数据
							</c:otherwise>
							</c:choose>
							<h3></h3>
						</div>
						<div class="batchEditeDeTexSe">
							${var.READERS }
						</div>
						<div class="batchEditeDeTexEi">
							<span>${var.DISPLAY_READERS }人</span>
							<div class="batchEditeDeTexEiInp clearfix">
								<input value="" />
							</div>
						</div>
						<div class="batchEditeDeTexNi">
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
						<div onClick="fun2()" class="batchEditeDeTexTe">
							<c:choose>
							<c:when test="${not empty var.STATE}">
								<c:if test="${var.SORT_RULE == 0 }">
									自动匹配
								</c:if>
								<c:if test="${var.SORT_RULE == 1 }">
									手动添加
								</c:if>
								<c:if test="${var.SORT_RULE == 2 }">
									手动修改
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
		<div class="flipTwo clearfix">
			${page.pageStr}
		</div>
	</div>
	<script type="text/javascript">
		function funedit(data){
			var BOARD_DETAIL_ID = "";
			$('.batchEditeDeTexAct').each(function(n,v){
				BOARD_DETAIL_ID = BOARD_DETAIL_ID + v.title + ",";
			});
			if(""!=BOARD_DETAIL_ID){
				var BOARD_TYPE = $('#BOARD_TYPE').val();
				var BOARD_ID = $('#BOARD_ID').val();
				if(BOARD_DETAIL_ID!=""){
					$.get("<%=basePath%>boarddetail/funedit.do?BOARD_DETAIL_ID="+BOARD_DETAIL_ID+"&EDIT="+data+"&BOARD_TYPE="+BOARD_TYPE+"&BOARD_ID="+BOARD_ID,function(data,status){
						location.reload();
					});
				}
			}else{
				alert('请选择书籍');
			}
			
		}
	</script>
	<script>
		$('.batchEditeDeTexEi span').click(function(){
			var spanVal=$(this).text();
			$(this).siblings('.batchEditeDeTexEiInp').show().find('input').val(spanVal);
			$(this).siblings('.batchEditeDeTexEiInp').find('input').focus();
		});
		$('.batchEditeDeTexEiInp input').blur(function(){
			var inpVal=$(this).val();
			$(this).parent().siblings('span').text(inpVal);
			$(this).parent('.batchEditeDeTexEiInp').hide();
		});
		
		$('.batchEditeDeTit span i').click(function(){
			if($(this).hasClass('batchEditeDeTitAct')){
				$(this).removeClass('batchEditeDeTitAct');
				$('.batchEditeDeTex ul li a span').removeClass('batchEditeDeTexAct');
			}else{
				$(this).addClass('batchEditeDeTitAct');
				$('.batchEditeDeTex ul li a span').addClass('batchEditeDeTexAct');
			}
		});
		$('.batchEditeDeTex ul li a span').click(function(){
			if($(this).hasClass('batchEditeDeTexAct')){
				$(this).removeClass('batchEditeDeTexAct');
				$('.batchEditeDeTit span i').removeClass('batchEditeDeTitAct');
			}else{
				$(this).addClass('batchEditeDeTexAct');
			}
		});
		
		$('.flipTwoC1').click(function(){
			var spanVal=parseInt($('.flipTwoC span').text());
			spanVal++;
			$('.flipTwoC span').text(spanVal+'条/页');
		});
		$('.flipTwoC2').click(function(){
			var spanVal=parseInt($('.flipTwoC span').text());
			if(spanVal>0){
				spanVal--;
			}
			$('.flipTwoC span').text(spanVal+'条/页');
		});
		
		function fun2(){
			var diag = new top.Dialog();

			diag.Width = 560;

			diag.Height = 156;

			diag.Title = "手动排序";

			diag.URL = "http://127.0.0.1:8020/digitalReading/allAlert/sort.html?__hbt=1520236138570";

			diag.show();

		}
	</script>
</body>
</html>

