<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>添加书籍（单本）</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">资源管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" href="bookManagement.html" title="">书籍管理</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">新增书籍</a>
	</div>
	<form id="userForm" name="userForm" action="<%=basePath%>article/adduploadfile.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="HTTPURL" id="HTTPURL" value="<%=basePath%>"/>
	<div class="addBooksDe">
		<h1>新增书籍</h1>
		<div class="bookName clearfix">
			<span>书籍名称</span>
			<div class="bookName1 clearfix">
				<input id="BOOKARTICLE_NAME" name="BOOKARTICLE_NAME" type="text" value="${pd.ARTICLE_NAME}" placeholder="请输入书籍名称"/>
			</div>
		</div>
		<div class="bookCover clearfix">
			<span>书籍封面</span>
				<input type="hidden" name="BOOK_COVERSRC" id="BOOK_COVERSRC" value="${pd.BOOK_COVER}"/>
			<div class="bookCoverC">
				<c:if test="${not empty pd.BOOK_COVER}">
					<img src="${pd.BOOK_COVER}" alt="" />
				</c:if>
				<c:if test="${empty pd.BOOK_COVER}">
					<img src="static/read/images/myPic33.png" alt="" />
				</c:if>
				<i>上传封面</i>
				<input type="file" id="editFile" name="file" type="file" />
			</div>
			<div class="bookCoverR">
				<p>推荐尺寸：210×280 px</p>
				<p>支持jpj、jpeg、gif、png，大小不超过5MB</p>
			</div>
		</div>
		<div class="bookName clearfix">
			<span>书籍作者</span>
			<div class="bookName1 clearfix">
				<input id="BOOKAUTHOR" name="BOOKAUTHOR" type="text" value="${pd.AUTHOR}" placeholder="请输入作者"/>
			</div>
		</div>
		<div class="paymentType clearfix">
			<span>付费类型</span>
			<div class="paymentTypeDiv clearfix">
				<p id="BOOKFEE_TYPE">
				<c:choose>
					<c:when test="${not empty pd.FEE_TYPE}">
						<c:if test="${pd.FEE_TYPE == 0 }">
							免费
						</c:if>
						<c:if test="${pd.FEE_TYPE == 1 }">
							付费
						</c:if>
					</c:when>
					<c:otherwise>
						付费
					</c:otherwise>
				</c:choose>
				</p>
				<img src="static/read/images/myPic13.png" alt="" />
				<ul>
					<li onclick="funyc('1');">付费</li>
					<li onclick="funyc('2');">免费</li>
				</ul>
			</div>
		</div>
		<%-- <div id="paymentType" class="paymentType clearfix">
			<span>付费方式</span>
			<div class="paymentTypeDiv clearfix">
				<p id="BOOKPAY_WAY">
						<c:if test="${pd.PAY_WAY == 0 }">
							阅读币
						</c:if>
						<c:if test="${pd.PAY_WAY == 1 }">
							VIP免费阅读
						</c:if>
						<c:if test="${pd.PAY_WAY != 1 } && ${pd.PAY_WAY != 0 }">
							请选择付费方式
						</c:if>
				</p>
				<img src="static/read/images/myPic13.png" alt="" />
				<ul>
					<li>阅读币</li>
					<li>VIP免费阅读</li>
				</ul>
			</div>
		</div> --%>
		<div class="paymentType clearfix">
			<span>是否热门</span>
			<div class="paymentTypeDiv clearfix">
				<p id="BOOKIS_HOT">
						<c:if test="${pd.IS_HOT == 0 }">
							否
						</c:if>
						<c:if test="${pd.IS_HOT == 1 }">
							是
						</c:if>
						<c:if test="${pd.IS_HOT != 1 } & ${pd.IS_HOT != 0 }">
							请选择是否热门
						</c:if>
				</p>
				<img src="static/read/images/myPic13.png" alt="" />
				<ul>
					<li>否</li>
					<li>是</li>
				</ul>
			</div>
		</div>
		<div class="paymentType clearfix">
			<input type="hidden" name="ARTICLE_CATEGORY_ID" id="ARTICLE_CATEGORY_ID" value="${pd.ARTICLE_CATEGORY_ID }"/>
			<span>书籍类型</span>
			<div class="paymentTypeDiv clearfix">
				<p id="book_typeid">${pd.CATEGORY }</p>
				<img src="static/read/images/myPic13.png" alt="" />
				<ul id="categroy_id">
				</ul>
			</div>
		</div>
		<div class="bookLabel clearfix">
			<span>书籍标签</span>
			<div class="clearfix">
				<p id="editlabel">
						<c:choose>
							<c:when test="${not empty pd.EDITLABEL_NAME}">
								${pd.EDITLABEL_NAME}
							</c:when>
							<c:otherwise>
								请添加书籍标签
							</c:otherwise>
						</c:choose>
				</p>
				<i>添加标签</i>
			</div>
		</div>
		<div class="bookName clearfix">
			<span>推荐指数</span>
			<div class="bookName1 clearfix">
				<input type="text" id="BOOKRECOMMEND" name="BOOKRECOMMEND" value="${pd.RECOMMEND}" placeholder="请输入1-100数值"/>
			</div>
		</div>
		<div class="bookIntroduction clearfix">
			<span>书籍简介</span>
			<div class="clearfix">
				<textarea id="BOOKSUMMARY" placeholder="请输入书籍简介">${pd.SUMMARY}</textarea>
			</div>
		</div>
		<input type="hidden" id="ycTypeae" name="ycTypeae" value="${pd.ycTypeae}"/>
		<input type="hidden" id="EDITARTICLE_ID" name="EDITARTICLE_ID" value="${pd.ARTICLE_ID}"/>
		<div class="addBooksDeBtn clearfix">
			<a class="AddBatchSectionBtn1" href="javascript:blackbat()" title="">完成</a>
			<span  onclick="javascript:history.back(-1);">取&nbsp;&nbsp;消</span>
		</div>
	</div>
	</form>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script>
		function categoryli(data,name){
			$('#book_typeid').text(name);
			$('#ARTICLE_CATEGORY_ID').val(data);
		}
		function funyc(data){
			if(data=="2"){
				$("#paymentType").css("display","none");
			}else if(data=="1"){
				$("#paymentType").css("display","block");
			}
		}
	
		function assignmentlabel(data){
			$("#editlabel").text(data);
		}
		function blackbat(){
			if($("#BOOKARTICLE_NAME").val()==""){
				$("#BOOKARTICLE_NAME").tips({
					side:3,
		            msg:'请输入书籍名称',
		            bg:'#FF6600',
		            time:2
		        });
				$("#BOOKARTICLE_NAME").focus();
				return false;
			}
			if($("#BOOKAUTHOR").val()==""){
				$("#BOOKAUTHOR").tips({
					side:3,
		            msg:'请输入作者',
		            bg:'#FF6600',
		            time:2
		        });
				$("#BOOKAUTHOR").focus();
				return false;
			}
			var ycTypeae = $("#ycTypeae").val();
			if(ycTypeae=="0"){
				var BOOK_COVERSRC = $("#BOOK_COVERSRC").val();
				var imagesrc = $(".bookCoverC img")[0].src;
				imagesrc = imagesrc.substring(33,imagesrc.length);
				if(BOOK_COVERSRC==imagesrc){
						var biaoshi = 0;
				}else{
						var biaoshi = 1;
				}
				var ARTICLE_NAME = $("#BOOKARTICLE_NAME").val();
				var AUTHOR = $("#BOOKAUTHOR").val();
				var FEE_TYPE = $("#BOOKFEE_TYPE").text();
				//var PAY_WAY = $("#BOOKPAY_WAY").text();
				var IS_HOT = $("#BOOKIS_HOT").text();
				var RECOMMEND = $("#BOOKRECOMMEND").val();
				var SUMMARY = $("#BOOKSUMMARY").val();
				var ARTICLE_ID = $("#EDITARTICLE_ID").val();
				var ARTICLE_CATEGORY_ID = $("#ARTICLE_CATEGORY_ID").val();
				var LABEL = $("#editlabel").text();
				var a = $('.AddBatchSectionBtn1').text();
				if(a=="完成"){
					$('.AddBatchSectionBtn1').text('提交中...');
					$("#userForm").attr("action","<%=basePath%>article/edituploadfile.do?ARTICLE_NAME="+ARTICLE_NAME+"&BOOK_COVER="+BOOK_COVERSRC+"&biaoshi="+biaoshi+"&LABEL="+LABEL+"&ARTICLE_ID="+ARTICLE_ID+"&AUTHOR="+AUTHOR+"&FEE_TYPE="+FEE_TYPE+"&IS_HOT="+IS_HOT+"&RECOMMEND="+RECOMMEND+"&SUMMARY="+SUMMARY+"&ARTICLE_CATEGORY_ID="+ARTICLE_CATEGORY_ID);
					$("#userForm").submit();
				}
			}else{
				var ARTICLE_NAME = $("#BOOKARTICLE_NAME").val();
				var AUTHOR = $("#BOOKAUTHOR").val();
				var FEE_TYPE = $("#BOOKFEE_TYPE").text();
				//var PAY_WAY = $("#BOOKPAY_WAY").text();
				var IS_HOT = $("#BOOKIS_HOT").text();
				var RECOMMEND = $("#BOOKRECOMMEND").val();
				var SUMMARY = $("#BOOKSUMMARY").val();
				var LABEL = $("#editlabel").text();
				var ARTICLE_CATEGORY_ID = $("#ARTICLE_CATEGORY_ID").val();
				var a = $('.AddBatchSectionBtn1').text();
				if(a=="完成"){
					$('.AddBatchSectionBtn1').text('提交中...');
					$("#userForm").attr("action","<%=basePath%>article/adduploadfile.do?ARTICLE_NAME="+ARTICLE_NAME+"&LABEL="+LABEL+"&AUTHOR="+AUTHOR+"&FEE_TYPE="+FEE_TYPE+"&IS_HOT="+IS_HOT+"&RECOMMEND="+RECOMMEND+"&SUMMARY="+SUMMARY+"&ARTICLE_CATEGORY_ID="+ARTICLE_CATEGORY_ID);
					$("#userForm").submit();
				}
			}
	 	}
	</script>
	<script>
		$.ajax({
			type: "post",
			data:{},
			url: "<%=basePath%>articlecategory/listJson.do",
			dataType:'json', 
			success: function(data){
				$.each(data, function(index, item){
		       		$("#categroy_id").append("<li onclick=categoryli('"+item.ARTICLE_CATEGORY_ID+"','"+item.CATEGORY+"');>" + item.CATEGORY + "</li>");
		        });
			},
		});
		
		$('.bookName1 p').click(function() {
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.bookName1 input').bind('input propertychange', function() {
			if ($(this).val() == '') {
				$(this).siblings('p').show();
			}
		});
		$('.bookName1 input').blur(function() {
			if ($(this).val() == '') {
				$(this).siblings('p').show();
			}
		})

		$(".bookCoverC input").change(
			function() {
				var $file = $(this);
				var fileObj = $file[0];
				var windowURL = window.URL || window.webkitURL;
				var dataURL;
				var $img = $(this).siblings('img');
				if (fileObj && fileObj.files && fileObj.files[0]) {
					dataURL = windowURL.createObjectURL(fileObj.files[0]);
					$img.attr('src', dataURL);
				} else {
					dataURL = $file.val();
					var imgObj = $img.get(0);
					imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
				}
		});

		$(window).click(function() {
			$('.paymentTypeDiv').find('ul').hide();
		})
		$('.paymentTypeDiv').click(function(e) {
			if ($(this).find('ul').css('display') == "none") {
				$(this).find('ul').show();
			} else {
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.paymentTypeDiv ul li').click(function(e) {
			$(this).css('color', '#f37427');
			$(this).siblings().css('color', '#666666');
			$(this).parent().hide();
			var val = $(this).text();
			$(this).parent().siblings('p').text(val);
			e.stopPropagation();
		})

		$('.bookIntroduction div p').click(function() {
			$(this).hide();
			$(this).siblings('textarea').focus();
		})
		$('.bookIntroduction div textarea').bind('input propertychange',function() {
			if ($(this).val() == '') {
				$(this).siblings('p').show();
			}
		});
		$('.bookIntroduction div textarea').blur(function() {
			if ($(this).val() == '') {
				$(this).siblings('p').show();
			}
		})

		$('.bookLabel div i').click(function() {
			//var LABEL = $("#editlabel").text();
			window.parent.ajaxAddlabel();
			$('.labelEditingAlert', parent.document).show();
			(function(Thunder) {$('.labelEditingAlert', parent.document).css('padding-top', Thunder);})
			((parent.document.documentElement.clientHeight - $('.labelEditing', parent.document).get(0).offsetHeight) / 2);
		})
	</script>
</body>
</html>
