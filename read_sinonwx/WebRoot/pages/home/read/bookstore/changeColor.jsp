<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
Integer signflag = (Integer)request.getAttribute("signflag");//0未开启签到功能 1 已签到2未签到
UserInfo user = (UserInfo)request.getAttribute("user");
//	System.out.println(user.toString());
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>正文阅读</title>
     <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f7f7f7;">
	<div class="textReadingT">
		<h2><sapn>${articleChapter.chapterName }</sapn></h2>
		<p><font id="font" size="2">${chapterTxt }</font></p>
	</div>
	<div class="textReadingBtn">
		<div class="changeColorT">
			<span id="A-">A-</span>
			<font id="middle">2</font>
			<i onclick="AUP();">A+</i>
		</div>
		<div class="changeColorSel">
			<div class="changeColorSelAct"><span></span></div>
			<div><span></span></div>
			<div><span></span></div>
			<div><span></span></div>
		</div>
		<div class="textReadingBtnT clearfix">
		<c:if test="${not empty articleChapterup }">
			<span onclick="articleChapterfee('${articleChapterup.id}','${articleChapterup.consumes}')">上一章</span>
		</c:if>
			<div>
				<span></span>
				<i></i>
			</div>
			<c:if test="${not empty articleChapternext }">
			<i onclick="articleChapterfee('${articleChapternext.id}','${articleChapternext.consumes }')">下一章</i>
			</c:if>
			 
		</div>
		<div class="textReadingBtnB clearfix">
			<a href="article/lookbookCatalogues/${user.webchatId }/${articleChapter.article.id}.do"><span>目录</span></a>
			<span id="conf" class="h">设置</span>
		</div>
	</div>
	
	<div class="textReadingBuy" id="textReadingBuy1" >
		<div class="textReadingBuy1 clearfix">
			<p>我的余额：<i>（余额不足）</i></p>
			<span id="userCurrency">剩余：${userCurrency}阅读币</span>
		</div>
		<div class="textReadingBuy3 clearfix">
			<p>需支付：<i id="Currency1"></i></p>
			<a id="hrefCurrency1" ><span>充&nbsp;&nbsp;&nbsp;值</span></a>
		</div>
	</div>
	<div class="textReadingBuy" id="textReadingBuy2" >
		<div class="textReadingBuy2 clearfix">
			<p>是否自动扣费：</p>
			<%if(user.getAutoOrder()==1){ %>
			<img src="pages/home/read/images/myPic67.png" alt="" />
			<%}else{ %>
			<img src="pages/home/read/images/myPic66.png" alt="" />
			<%} %>
		</div>
		<div class="textReadingBuy3 clearfix">
			<p>需支付：<i id="Currency2"></i></p>
			<span><a id="hrefCurrency2" href="<%=basePath%>articlechapter/articlechapterGetById/<%=user.getWebchatId()%>/${articleChapter.id}/<%=user.getId()%>/${forceChapter}.do?id=1">购&nbsp;&nbsp;&nbsp;买</a></span>
		</div>
	</div>
	<script src="pages/home/read/js/rem.js"></script>
	<script>
	$('#textReadingBuy1').hide();
	$('#textReadingBuy2').hide();
	function articleChapterfee(articleChapterId,consumes){
		//alert(articleChapterId+"=="+consumes);
		var url = "<%=basePath%>articlechapter/articleChapterfee/${user.webchatId }/"+articleChapterId;
		$.get(url,function(data){
			//alert(data);
			var arr = data.split('==');
			if(arr.length>1){
				var isread = arr[0];
				var bc = arr[1];
				$("#userCurrency").html("剩余："+bc+"阅读币");
				if(isread=='1'||isread=='6'){
					//未开启费用不够
					show(1,articleChapterId,consumes);
				}else if(isread=='2'){
					//已开启费用不够
					show(2,articleChapterId,consumes);
				}else{
					window.location.href='<%=basePath%>articlechapter/articlechapterGetById/${user.webchatId }/'+articleChapterId+'/${user.id }/${forceChapter}.do';
				}
			}
		});
	}	
	
	    //是否可阅读 0可免费阅读 1未开启自动扣费且阅读币不够  
	    //2未开启自动扣费且费用够 3未开启自动扣费用户直接购买 
	    //4用户已购买过 5开启自动扣费且费用够6开启自动扣费且费用不够
		var isread = '${isread}';
		//alert('isread='+isread);
		var isreadflag = '${isreadflag}';
		//if('false'==isreadflag){
			if('1'==isread || '6'==isread){
				//alert('阅读币不够');
				//阅读币不够
				$('.textReadingBtn').hide();
				$('#textReadingBuy2').hide();
				$('#textReadingBuy1').show();
				$('#Currency1').text(articleChapter.consumes+'阅读币');
				$("#hrefCurrency1").attr("href",'<%=basePath%>recharge/list/${user.webchatId }?rurl=articlechapter/articlechapterGetById/${user.webchatId }/${articleChapter.id}/${user.id }/${forceChapter}');
			}else if('2'==isread){
				//alert('未开启自动扣费');
				//alert(2);
				//未开启自动扣费
				$('.textReadingBtn').hide();
				$('#textReadingBuy1').hide();
				$('#textReadingBuy2').show();
				$('#Currency2').text(articleChapter.consumes+'阅读币');
				$('#hrefCurrency2').attr('href','<%=basePath%>articlechapter/articlechapterGetById/${user.webchatId }/${articleChapter.id}/${user.id }/${forceChapter}.do');
				
			}
		//}
		$('.textReadingBuy2 img').click(function(){
			if($(this).attr('src')=='pages/home/read/images/myPic66.png'){
				$(this).attr('src','pages/home/read/images/myPic67.png');
				auto(1);
			}else{
				$(this).attr('src','pages/home/read/images/myPic66.png');
				auto(0);
			}
		});
		function lookchapter(){
			
		}
		function show(type,cid, consumes){
			//阅读币不足
			if(type==1){
				$('#textReadingBuy2').hide();
				$('#textReadingBuy1').show();
				$('#Currency1').text(consumes+'阅读币');
				$("#hrefCurrency1").attr("href",'<%=basePath%>recharge/list/${user.webchatId }?rurl=articlechapter/articlechapterGetById/${user.webchatId }/'+cid+'/${user.id }/${forceChapter}');
			}else{
				//未开启自动扣费
				$('#textReadingBuy1').hide();
				$('#textReadingBuy2').show();
				$('#Currency2').text(consumes+'阅读币');
				$('#hrefCurrency2').attr('href','<%=basePath%>articlechapter/articlechapterGetById/${user.webchatId }/'+cid+'/${user.id }/${forceChapter}.do?id=1');
				
			}
		}
		//开启关闭自动购买付费章节功能
		function auto(autoOrder){
			var url = "<%=basePath%>recharge/autoOrder/<%=user.getWebchatId() %>/"+autoOrder;
			//alert(url);
			$.get(url,function(data){
				//alert(data);
			});
		}
		
		
		$(window).on("touchstart", function(e) {
			startX = e.originalEvent.changedTouches[0].pageX,
			startY = e.originalEvent.changedTouches[0].pageY;
			e.stopPropagation();
		});
		$(window).on("touchend", function(e) {
			moveEndX = e.originalEvent.changedTouches[0].pageX;
			moveEndY = e.originalEvent.changedTouches[0].pageY;
			X = moveEndX - startX;
			Y = moveEndY - startY;
			var total=document.documentElement.clientWidth;
			if(Y<=5&&Y>=-5){
				if(startX<total/3){
			    	if(getScrollTop()<=0){
			    		//上一张
			    		var articleChapterup = '${articleChapterup}';
			    		//alert('articleChapterup='+articleChapterup);
			    		if(null!=articleChapterup && 'null'!=articleChapterup){
			    			articleChapterfee('${articleChapterup.id}','${articleChapterup.consumes}')
			    		}
			    		 
			    	}else{
			    		window.scrollBy(0,-getClientHeight());
			    	}
				}else if(startX>total*2/3){
			    	if(getScrollTop()+getClientHeight()==getScrollHeight()){
			    		//下一章
			    		var articleChapternext = '${articleChapternext}';
			    		if(null!=articleChapternext && 'null'!=articleChapternext){
			    			articleChapterfee('${articleChapternext.id}','${articleChapternext.consumes}')
			    		}
			    	}else if(getScrollHeight()>=getClientHeight()){
			    		//var articleId = $('#ARTICLE_ID').val();
			    		//var articleChpterId = $('#ARTICLE_CHAPTER_ID').val();
			    		//var extensionContentId = $('#EXTENSION_CONTENT_ID').val();
			    		var articleChapternext = '${articleChapternext}';
			    		//alert('articleChapternext='+articleChapternext);
			    		if(null!=articleChapternext && 'null'!=articleChapternext){
			    			articleChapterfee('${articleChapternext.id}','${articleChapternext.consumes}')
			    		}
			    	}else{
			    		window.scrollBy(0,getClientHeight());
			    	}
			    }else{
					if($('.textReadingBtn').css('display')=='none'){
						$('.textReadingBtn').css('display','block');
					}else{
						$('.textReadingBtn').css('display','none');
						
					}
				}
			}
			
		});
//		滚动条高度
		function getScrollTop(){  
		    var scrollTop=0;  
		    if(document.documentElement&&document.documentElement.scrollTop){  
		        scrollTop=document.documentElement.scrollTop;  
		    }else if(document.body){  
		        scrollTop=document.body.scrollTop;  
		    }  
		    return scrollTop;  
		};
//		文本实际高度
		function getScrollHeight(){  
		    return Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);  
		};
//		手机屏幕高度
		function getClientHeight(){  
		    var clientHeight=0;  
		    if(document.body.clientHeight&&document.documentElement.clientHeight){  
		        var clientHeight=(document.body.clientHeight<document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
		    }else{  
		        var clientHeight=(document.body.clientHeight>document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
		    }
		    return clientHeight;  
		}
		
		$('.textReadingBtn').on("touchstart", function(e) {
			e.stopPropagation();
		});
		
		var fontsize = $('#fontsize').text();
		$('#A-').on("touchstart", function(e) {
			var num = Number($('#font').attr('size'))-1;
			if(num>0){
				$('#font').attr('size',num);
				$('#middle').text(num);
			}
			e.stopPropagation();
		});
		function AUP(e){
			var num = Number($('#font').attr('size'))+1;
			if(num<=5){
				$('#font').attr('size',num);
				$('#middle').text(num);
			}
			e.stopPropagation();
		}
		
		$('.changeColorSel div').on("touchstart", function(e) {
			$(this).addClass('changeColorSelAct').siblings().removeClass('changeColorSelAct');
		});
		
		$('.changeColorSel div:first-child').on("touchstart", function(e) {
			$('body').css('background','#f7f7f7');
			$('.textReadingT h2').css('color','#7b5a40');
			$('.textReadingT p').css('color','#474747');
		});
		$('.changeColorSel div:nth-child(2)').on("touchstart", function(e) {
			$('body').css('background','#dfefd5');
			$('.textReadingT h2').css('color','#7b5a40');
			$('.textReadingT p').css('color','#474747');
		});
		$('.changeColorSel div:nth-child(3)').on("touchstart", function(e) {
			$('body').css('background','#f3e3bd');
			$('.textReadingT h2').css('color','#7b5a40');
			$('.textReadingT p').css('color','#474747');
		});
		$('.changeColorSel div:last-child').on("touchstart", function(e) {
			$('body').css('background','#373531');
			$('.textReadingT h2').css('color','#a08168');
			$('.textReadingT p').css('color','#92908c');
		});
	</script>
	
</body>
</html>

