<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.sinontech.modle.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Integer signflag = (Integer)request.getAttribute("signflag");//0未开启签到功能 1 已签到2未签到
UserInfo user = (UserInfo)request.getAttribute("user");
%>

<!DOCTYPE html>
<html>
  <head>
  	<meta charset="utf-8">
    <base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
      <title>书架编辑</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="bookshelfEdit clearfix">
		<span>我的书架</span>
		<a href="<%=basePath%>bookshelf/userlistBookShelf/<%=user.getWebchatId()%>.do?userid=<%=user.getId()%>" title="完成编辑">完成</a>
	</div>
	<div class="bookshelfDe clearfix">
		<ul>
			<c:if test="${varList != null}">
			<c:forEach items="${varList}" var="var" varStatus="vs">
				<li>
					<div class="delbookshelf">
						<img class="bookshelfDe1" src="${var.article.httpUrl}${var.article.bookCover}" alt="" />
						<span>更新：第${var.article.countChapters }章</span>
						<i></i>
						<img class="bookshelfDe2" src="pages/home/read/images/myPic74.png" title="${var.id}" onclick="delBook();"/>
					</div>
					<h2>${var.article.articleName}</h2>
					<p>${var.articleChapter.chapterName }</p>
				</li>
			</c:forEach>
			</c:if>
		</ul>
	</div>
	<div style="height: 1.2rem;"></div>
	<div class="bookshelfEditBtn">
		<a id="shelfdel" href="<%=basePath%>bookshelf/delShelf/<%=user.getWebchatId()%>/<%=user.getId()%>.do" title="删除书籍"><span>删除</span></a>
	</div>
	<script>
		$('.RankingBot span').click(function(){
			$(this).addClass('RankingBotAct').siblings().removeClass('RankingBotAct');
		});
		
		$('.bookshelfDe2').click(function(){
			if($(this).attr('src')=='pages/home/read/images/myPic74.png'){
				$(this).attr('src','pages/home/read/images/myPic75.png');
				$(this).addClass('Thunder');
				var bookShelfId = "";
			    var imgs = $(".delbookshelf").find('img');
			    for(var i=0;i<imgs.length;i++){
			    	if($(imgs[i]).attr('src')=='pages/home/read/images/myPic75.png'){
				      bookShelfId = bookShelfId + $(imgs[i]).attr('title') +",";
			    	}
			    }
			    var  path = '<%=basePath%>bookshelf/delShelf/<%=user.getWebchatId()%>/<%=user.getId()%>.do';
			   	if(bookShelfId!=''){
			   		$("#shelfdel").attr('href',path+'?bookShelfId='+bookShelfId);
			    }
			}else{
				$(this).attr('src','pages/home/read/images/myPic74.png');
				$("#shelfdel").attr('href','<%=basePath%>bookshelf/delShelf/<%=user.getWebchatId()%>/<%=user.getId()%>.do');
				$(this).removeClass('Thunder');
			}
			var len=$('.Thunder').length;
			if(len>0){
				$('.bookshelfEditBtn span').css('color','#f14530').text('删除('+len+')');	
			}else{
				$('.bookshelfEditBtn span').css('color','#666666').text('删除');
			}
		});
	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>