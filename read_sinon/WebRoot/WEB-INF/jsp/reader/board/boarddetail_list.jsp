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
	<title>榜单配置（编辑书籍）</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/moduleConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body style="background: #fff;" onload="myTab()">
	<form action="<%=basePath%>boarddetail/list.do" name="Form" id="Form" method="post">
	<input name="BOARD_ID" id="BOARD_ID" value="${pd.BOARD_ID }" type="hidden" />
	<input name="BOARD_TYPE" id="BOARD_TYPE" value="${pd.BOARD_TYPE }" type="hidden" />
	
	<div class="editBooksOneAll">
		<div class="pageConfigurateDeTop clearfix">
			<div style="margin-top: 30px;" class="labelManagementTopL clearfix">
				<div class="labelManagementTopL1 clearfix">
					<input name="names" id="names" type="text" value="${pd.names}"/>
					<p>输入书籍名称或作者搜索</p>
					<img onclick="submit();" src="static/read/images/myPic26.png" alt="" />
				</div>
			</div>
			<div class="pageConfigurateDeTopR clearfix">
				<a class="pageConfigurateDeTopR1" href="<%=basePath%>boarddetail/batchEdit.do?BOARD_ID=${pd.BOARD_ID}&BOARD_TYPE=${pd.BOARD_TYPE }" title=""><i>批量编辑</i></a>
				<span class="pageConfigurateDeTopR2">
					<i onClick="fun2('${pd.BOARD_ID}','${pd.BOARD_TYPE }');">添加书籍</i>
				</span>
			</div>
		</div>
		<div class="bookManagementDeClickdown">
			<span>展开搜索条件</span>
		</div>
		<div class="bookManagementDeClickup">
			<ul class="clearfix">
				<li style="margin-left: 19px;" class="clearfix">
				<input type="hidden" name="FEE_TYPE" id="FEE_TYPE" value="${pd.FEE_TYPE}"/>
					<span>付费方式</span>
					<div class="bookManagementDeClickupDiv clearfix">
						<p id="PAY_WAY1"><c:choose>
								<c:when test="${not empty pd.FEE_TYPE}">
									<c:if test="${pd.FEE_TYPE == 0 }">
										免费
									</c:if>
									<c:if test="${pd.FEE_TYPE == 1 }">
										收费
									</c:if>
								</c:when>
								<c:otherwise>
									全部方式
								</c:otherwise>
							</c:choose></p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li onclick="updateWay('');">全部方式</li>
							<li onclick="updateWay('0');">免费</li>
							<li onclick="updateWay('1');">付费</li>
						</ul>
					</div>
				</li>
				<li class="clearfix">
					<input type="hidden" name="CHANNEL_TYPE" id="CHANNEL_TYPE" value="${pd.CHANNEL_TYPE}"/>
					<span>男女频道</span>
					<div class="bookManagementDeClickupDiv clearfix">
						<p id="CHANNEL_TYPE1"><c:choose>
								<c:when test="${not empty pd.CHANNEL_TYPE}">
									<c:if test="${pd.CHANNEL_TYPE == 0 }">
										男频
									</c:if>
									<c:if test="${pd.CHANNEL_TYPE == 1 }">
										女频
									</c:if>
								</c:when>
								<c:otherwise>
									全部频道
								</c:otherwise>
							</c:choose></p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul>
							<li onclick="updateChannel('');">全部频道</li>
							<li onclick="updateChannel('0');">男频</li>
							<li onclick="updateChannel('1');">女频</li>
						</ul>
					</div>
				</li>
				<li class="clearfix">
					<input type="hidden" name="ARTICLE_CATEGORY_ID" id="ARTICLE_CATEGORY_ID" value="${pd.ARTICLE_CATEGORY_ID}"/>
					<input type="hidden" name="CATEGORY" id="CATEGORY" value="${pd.CATEGORY}"/>
					<span>书籍类型</span>
					<div class="bookManagementDeClickupDiv clearfix">
						<p id="CATEGORY1"><c:choose><c:when test="${not empty pd.CATEGORY}">${pd.CATEGORY }</c:when><c:otherwise>全部类型</c:otherwise></c:choose></p>
						<img src="static/read/images/myPic13.png" alt="" />
						<ul id="bookcategoryidul">
						
						</ul>
					</div>
				</li>
			</ul>
			<div class="bookManagementDeClickupKey clearfix">
				<a href="javascript:submit()" title="">查&nbsp;&nbsp;询</a>
				<span onclick="funcReset();">重&nbsp;&nbsp;置</span>
			</div>
			<div class="bookManagementDeClickupBtn">
				<span>收起</span>
			</div>
		</div>
		<div class="editBooksOneTit clearfix">
			<span style=" margin-left: 20px; width: 38px;">排名</span>
			<span style="width: 70px;">封面</span>
			<span style="width: 139px;">基本信息</span>
			<span style="width: 103px;">书籍ID</span>
			<span style="width: 88px;">书籍类型</span>
			<span style="width: 77px;">付费方式</span>
			<span style="width: 89px;">当前阅读数</span>
			<span style="width: 119px;">显示阅读数</span>
			<span style="width: 64px;">男女频</span>
			<span style="width: 76px;">排序类型</span>
			<span>管理操作</span>
		</div>
		<div class="editBooksOneTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<li class="clearfix">
						<div class="editBooksOneTexOn">
							${vs.index+1+(10*(page.currentPage-1))}
						</div>
						<div class="editBooksOneTexTw">
							<img src="${var.BOOK_COVER }" alt="" />
						</div>
						<div class="editBooksOneTexTh">
							<p><i>书名：</i>${var.ARTICLE_NAME }</p>
							<p><i>作者：</i>${var.AUTHOR }</p>
							<p><i>章节数：</i>${var.COUNT_CHAPTERS }</p>
							<p><i>总字数：</i>${var.COUNT_LETTER }</p>
						</div>
						<div class="editBooksOneTexFo">
							${var.ARTICLE_ID }
						</div>
						<div class="editBooksOneTexFi">
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
						<div class="editBooksOneTexSi">
							<p></p>
							<c:choose>
							<c:when test="${not empty var.FEE_TYPE}">
								<c:if test="${var.FEE_TYPE == 0 }">
									<p>免费</p>
								</c:if>
								<c:if test="${var.FEE_TYPE == 1 }">
									<p>付费</p>
								</c:if>
							</c:when>
							<c:otherwise>
								没有相关数据
							</c:otherwise>
							</c:choose>
							<h3></h3>
						</div>
						<div class="editBooksOneTexSe">
							${var.READERS }
						</div>
						<div class="editBooksOneTexEi">
							<span>${var.DISPLAY_READERS }</span>
							<div class="editBooksOneTexEiInp clearfix">
								<input title="${var.ARTICLE_ID }" value="" />
							</div>
						</div>
						<div class="editBooksOneTexNi">
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
						<div class="editBooksOneTexTe">
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
						<div class="editBooksOneTexEl">
							<span>操作</span>
							<div class="editBooksOneTexElDiv">
								<a href="<%=basePath%>boarddetail/delete.do?BOARD_DETAIL_ID=${var.BOARD_DETAIL_ID}">删除</a>
								<c:if test="${var.SORT_RULE == 2 }">
									<a onclick="reduction(${var.BOARD_DETAIL_ID})">还原</a>
								</c:if>
							</div>
						</div>
					</li>
				</c:forEach>
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
	<script type="text/javascript">
		function reduction(BOARD_DETAIL_ID){
			$.get("<%=basePath%>boarddetail/reduction.do?BOARD_DETAIL_ID="+BOARD_DETAIL_ID,function(data,status){
				location.reload();
			});
		}
		
		function funcReset(){
			$('#CATEGORY1').text('全部类型');
			$('#CHANNEL_TYPE1').text('全部频道');
			$('#PAY_WAY1').text('全部方式');
			$('#ARTICLE_CATEGORY_ID').val('');
			$('#CHANNEL_TYPE').val('');
			$('#FEE_TYPE').val('');
			$('#names').val('');
		}
		function submit(){
			var CATEGORY = $('#CATEGORY1').text();
			$('#CATEGORY').val(CATEGORY);
			$('#Form').action='<%=basePath%>boarddetail/list.do';
			$('#Form').submit();
		}
		function updateType(data){
			$('#ARTICLE_CATEGORY_ID').val(data);
		}
		function updateChannel(data){
			$('#CHANNEL_TYPE').val(data);
		}
		function updateWay(data){
			$('#FEE_TYPE').val(data);
		}
	</script>
	<script>
		$.ajax({
				type: "post",
				data:{},
				url: "<%=basePath%>articlecategory/listJson.do",
				dataType:'json', 
				success: function(data){
					$("#bookcategoryidul").append("<li onclick='updateType('')'>全部类型</li>");
					$.each(data, function(index, item){
			       		$("#bookcategoryidul").append("<li onclick=updateType('"+item.ARTICLE_CATEGORY_ID+"');>" + item.CATEGORY + "</li>");
			        });
				},
				error: function () {    
		        alert("数据错误");
		        }
			});
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("faker"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
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
		$(window).click(function(){
			$('.pageConfigurateDeTopR2 ul').hide();
			$('.editBooksOneTexEl div').hide();
		});
		
		$('.pageConfigurateDeTopR2').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		});
		
		$('.bookManagementDeClickdown span').click(function(){
			$('.bookManagementDeClickdown').hide();
			$('.bookManagementDeClickup').show();
		});
		$('.bookManagementDeClickupBtn span').click(function(){
			$('.bookManagementDeClickdown').show();
			$('.bookManagementDeClickup').hide();
		});
		
		$(document).on('click','.bookManagementDeClickupDiv',function(){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
				$(this).parent('li').siblings('li').find('ul').hide();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		});
		$(document).on('click','.bookManagementDeClickupDiv ul li',function(){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent().siblings('p').text(val);
			e.stopPropagation();
		});
		
		$('.editBooksOneTexEi span').click(function(){
			var spanVal=$(this).text();
			$(this).siblings('.editBooksOneTexEiInp').show().find('input').val(spanVal);
			$(this).siblings('.editBooksOneTexEiInp').find('input').focus();
		});
		$('.editBooksOneTexEiInp input').blur(function(){
			var inpVal=$(this).val();
			var ARTICLE_ID = $(this).attr('title');
			var reg = /^[1-9]\d*$/ ;
			if (reg.test(inpVal) == true) {
				$.ajax({
					type: "post",
					data:{
						'ARTICLE_ID':ARTICLE_ID,
						'DISPLAY_READERS':inpVal
					},
					url: "<%=basePath%>article/updateReads.do",
				});
				$(this).parent().siblings('span').text(inpVal);
			}
			$(this).parent('.editBooksOneTexEiInp').hide();
		});
		
		
		$('.editBooksOneTexEl span').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.editBooksOneTexElDiv').hide();
			e.stopPropagation();
		});
		$('.editBooksOneTexEl div a').click(function(e){
			$(this).parent('div').hide();
			e.stopPropagation();
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
		
		function fun2(data,type){
			var diag = new top.Dialog();

			diag.Width = 670;

			diag.Height = 610;

			diag.Title = "添加书籍";

			diag.URL = "<%=basePath%>boarddetail/goAddBooks.do?BOARD_ID="+data+"&BOARD_TYPE="+type;

			diag.show();

		}
	</script>
</body>
</html>