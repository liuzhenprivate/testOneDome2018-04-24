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
	Webchat webchat = (Webchat)request.getAttribute("webchat");
	String shareurl =  (String)request.getAttribute("url");
	//String articleid =  (String)request.getAttribute("articleid");
	//String tid =  (String)request.getAttribute("tid");
	//Map<String, String> ret = com.alipay.api.share.ShareSign.sign(webchat.getJsapiTicket(), shareurl);
        if (request.getQueryString() != null){
        	String param = request.getQueryString();
        	String[] params = param.split("#");
        	shareurl += "?" + params[0];
        }

	//System.out.println("====================="+shareurl);
%>

<%
	String jsapi_ticket = com.alipay.api.share.ShareSign.getJsapiTicket(application,webchat.getAppid(),webchat.getAppsecret());
	//System.out.println(shareurl);

	Map<String, String> ret = com.alipay.api.share.ShareSign.sign(jsapi_ticket, shareurl);
	//System.out.println("ret="+ret.toString());	
%>
	
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>书籍详情</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
    <script src='http://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script> 
    <style>
		/*分享浮层*/
	.shareAlert{ width: 100%; height: 20rem; background: rgba(0,0,0,0.6); position: fixed; top: 0; left: 0;}
	.shareAlert1 img{ width: 3.69rem; position: absolute; top: 0; right: 1rem;}
	</style>
    <script>
    var url1 =  location.href.split('#')[0] ;
	//document.write(url1);
	//$("#cs").html(url1);
	var url2 = '${url}';
	//alert(url1);
	//alert(url2);
	if(url1==url2){
		//alert(true);
	}
	
	//alert('appid='+'<%=webchat.getAppid()%>');
	//alert('timestamp='+<%=ret.get("timestamp")%>);
	//alert('nonceStr='+'<%=ret.get("nonceStr")%>');
	//alert('signature='+'<%=ret.get("signature")%>');
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '<%=webchat.getAppid()%>'+'', // 必填，公众号的唯一标识
	    timestamp: '<%=ret.get("timestamp")%>', // 必填，生成签名的时间戳
	    nonceStr: '<%=ret.get("nonceStr")%>'+'', // 必填，生成签名的随机串
	    signature: '<%=ret.get("signature")%>'+'',// 必填，签名
	    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表
	});
	
	wx.ready(function(){
		//window.location.href=url2;
	    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		//alert("ShareAppMessage");
		wx.onMenuShareAppMessage({
			title: '分享书籍${article.articleName }', // 分享标题
			desc: '${article.summary }', // 分享描述
			link: '${url}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			imgUrl: '${article.httpUrl}${article.bookCover}', // 分享图标
			type: '', // 分享类型,music、video或link，不填默认为link
			dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			success: function () {
			// 用户确认分享后执行的回调函数
			//alert("success");
			},
			cancel: function () {
			// 用户取消分享后执行的回调函数
			//alert("cancel");
			}
		});
		wx.onMenuShareTimeline({
			title: '分享书籍${article.articleName }', // 分享标题
		    link: '${url}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		    imgUrl: '${article.httpUrl}${article.bookCover}', // 分享图标
		    success: function () {
		    // 用户确认分享后执行的回调函数
		    	//alert("success");
			},
			cancel: function () {
			// 用户取消分享后执行的回调函数
				//alert("cancel");
			}
		});
	});
    </script>
</head>
<body style="background: #f2f2f2;">
	<div class="bookDetailT clearfix">
		<div class="bookDetailTL">
			<img src="${article.httpUrl}${article.bookCover}" alt="" />
			<span>
			<c:if test="${article.feeType==0 }">
			免费
			</c:if>
			<c:if test="${article.feeType==1 }">
			VIP
			</c:if>
			</span>
		</div>
		<div class="bookDetailTR">
			<div class="bookDetailTR1 clearfix">
				<p>${article.articleName }</p>
				<span><font size="1">${article.articleCategory.gategory }</font></span>
			</div>
			<div class="bookDetailTR2 clearfix">
				<span>作者：<font style="color:black">${article.author }</font></span>
			</div>
			<div class="bookDetailTR2 clearfix">
				<span>字数：<font style="color:black">${article.countLetter }字</font></span>
			</div>
			<div class="bookDetailTR2 clearfix">
				<span>标签：<font style="color:black">
				<c:forEach items="${labellist}" var="var" varStatus="vs">
					${var.label.labelName }<i>|</i>
				</c:forEach>
				${article.articleCategory.gategory }</font></span>
			</div>
		</div>
	</div>
	<div class="bookDetailNum clearfix">
		<div>
			<h2 id="collections">${article.collection }+</h2>
			<p>收藏</p>
		</div>
		<span></span>
		<div>
			<h2>${article.displayReaders }+</h2>
			<p>阅读</p>
		</div>
		<span></span>
		<div>
			<h2>${article.countConsumes }+</h2>
			<p>付费</p>
		</div>
	</div>
	<div class="bookDetailTex" id="cs">
		<p>${article.summary }</p>
	</div>
	<div class="bookDetailLink clearfix">
		<a href="article/lookbookCatalogues/<%=user.getWebchatId()%>/${article.id }.do" title="">查看目录</a>
		<a href="article/lookbookCatalogues/<%=user.getWebchatId()%>/${article.id }.do" title="">连载至${article.countChapters }章</a>
	</div>
	<div style="margin-top: 0.2rem;" class="bookReviewAllT clearfix">
		<span>书评</span>
		<i>${discusssum }人评论</i>
		<p><a href="discuss/todiscuss/${article.id }">撰写评论</a></p>
	</div>
	<div class="bookReviewAllDe">
		<ul>
		<c:forEach items="${discusslist}" var="var" varStatus="vs">
			<li>
				<div class="bookReviewAllDeT clearfix">
					<img src="${var.user.headimgurl }" alt="" />
					<p>${var.user.nickname }</p>
					<c:if test="${var.user.isvip==1 }">
					<span>VIP</span>
					</c:if>
					<div class="clearfix">
					<c:forEach var="s"  begin="1" end="${var.levels}">
					 <img src="pages/home/read/images/myPic42.png" alt="" />
					</c:forEach>
					 
					<c:forEach var="s"  begin="1" end="${5-var.levels}">
					 <img src="pages/home/read/images/myPic41.png" alt="" />
					</c:forEach>
						 
					</div>
				</div>
				<div class="bookReviewAllDeC">
					<p>${var.content }</p>
				</div>
				<div class="bookReviewAllDeB clearfix">
					<p>${var.createTime }</p>
					<span id="zans${var.id}" onclick="zan(${var.id})">${var.zans }</span>
				</div>
			</li>
		</c:forEach>
			 
		</ul>
	</div>
	 <div class="bookDetailMore">
		<a href="discuss/listDiscuss/${article.id }">查看更多</a>
	</div>
	 
	<div style="height: 1.3rem;"></div>
	<div class="bookDetailBot clearfix">
		<a href="javascript:void(0)" title=""><span>分享书籍</span></a>
		<c:if test="${articleChapterId != 0}">
			<a class="bookDetailBotAct" href="<%=basePath%>articlechapter/articlechapterGetById/${user.webchatId}/${articleChapterId}/${user.id }/0.do" title=""><span>免费试读</span></a>
		</c:if>
		<c:if test="${articleChapterId == 0}">
			<a class="bookDetailBotAct" href="javascript:alertChapterNo('该书籍暂没有章节')" title=""><span>免费试读</span></a>
		</c:if>
		
		<c:if test="${isBookShelf==0 }">
		<a href="javascript:collect()" title=""><span>收藏书籍</span></a>
		</c:if>
		<c:if test="${isBookShelf==1 }">
		 <a href="javascript:void(0)" title=""  ><span  >已收藏书籍</span> </a>
		</c:if>
	</div>
	
	<!--书籍详情分享-->
	 <div class="shareAlert" style="display:none" onclick="hidepic()">
		<div class="shareAlert1" >
			<img src="pages/home/read/images/myPicSh.png" alt="" />
		</div>
	</div>
	 
	<script>
		function alertChapterNo(data){
			alert(data);
		}
	/**
		$('.bookDetailMore').click(function(){
			var html='<li><div class="bookReviewAllDeT clearfix"><img src="pages/home/read/images/myPic45.png" alt="" /><p>静静的相识仙人</p><div class="clearfix"><img src="pages/home/read/images/myPic41.png" alt="" /><img src="pages/home/read/images/myPic41.png" alt="" /><img src="pages/home/read/images/myPic41.png" alt="" /><img src="pages/home/read/images/myPic41.png" alt="" /><img src="pages/home/read/images/myPic41.png" alt="" /></div></div><div class="bookReviewAllDeC"><p>蜜蜜的话题圈和兴趣部落，已经建了，只要每天发一篇关于大大书的精致帖子一百字左右就可以不用一直在线，只要我每天可以看见你的</p></div><div class="bookReviewAllDeB clearfix"><p>2017-12-12 12:12</p><span class="bookReviewAllDeBAct">7764</span></div></li>'
			$('.bookReviewAllDe ul').append(html);
		})
		**/
		/* $('.bookDetailBot a').click(function(){
			$(this).addClass('bookDetailBotAct').siblings().removeClass('bookDetailBotAct');
		}) */
		
		$('body').on('touchstart', '.bookDetailShareCla', function(e) {
		    var touch = e.originalEvent,
		       	startX = touch.changedTouches[0].pageX;
		    $(this).on('touchmove', function(e) {
		        e.preventDefault();
		        touch = e.originalEvent.touches[0] ||
		            e.originalEvent.changedTouches[0];
		        if (touch.pageX - startX > 20) {//向左
		            $('.bookDetailShareClaDe').animate({
		            	right:'0'
		            });
		        } else if (touch.pageX - startX < -20) {//向右
		            $('.bookDetailShareClaDe').animate({
		            	left:'0'
		            });
		        }
		    });
		})
		
		$('.bookDetailBot a:first-child').click(function(){
			$('.shareAlert').show();
		});
		$('.bookDetailShareBot').click(function(){
			$('.bookDetailShareA').hide();
		});
		function hidepic(){
			$('.shareAlert').hide();
		}
		function zan(id){
			var url = "<%=basePath%>article/zan/${article.id }/"+id;
			$("#zans"+id).addClass("bookReviewAllDeBAct");
			$.get(url,function(data){
				if(data>0){
					$("#zans"+id).html(data);
				}else{
					alert("已经赞过了");
				}
			});
		}
		
		function collect(){
			var url = "<%=basePath%>article/collect/${article.id }";
			//alert(url);
			$.get(url,function(data){
				//alert(data);
				if(data>0){
				$("#collections").html(data+"+");
				}
			});
		}
		
		 
		//获取“分享给朋友”按钮点击状态及自定义分享内容接口
		function ShareAppMessage(){
			alert("ShareAppMessage");
			wx.onMenuShareAppMessage({
				title: '分享书籍', // 分享标题
				desc: '${article.summary }', // 分享描述
				link: '${url}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
				imgUrl: 'http://kuwx.xinxinwx.cn/wx/pages/home/read/images/myPic101.png', // 分享图标
				type: '', // 分享类型,music、video或link，不填默认为link
				dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				success: function () {
				// 用户确认分享后执行的回调函数
				alert("success");
				},
				cancel: function () {
				// 用户取消分享后执行的回调函数
				alert("cancel");
				}
			});
		}
		
		//获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
		function ShareTimeline(){
			alert("ShareTimeline");
			wx.onMenuShareTimeline({
			    title: '${article.summary }', // 分享标题
			    link: '${url}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
			    imgUrl: 'http://kuwx.xinxinwx.cn/wx/pages/home/read/images/myPic101.png', // 分享图标
			    success: function () {
			    // 用户确认分享后执行的回调函数
			    	//alert("success");
				},
				cancel: function () {
				// 用户取消分享后执行的回调函数
					//alert("cancel");
				}
			});
		}
		//获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
		function shareQZone(){
			wx.onMenuShareQZone({
				title: '', // 分享标题
				desc: '', // 分享描述
				link: '', // 分享链接
				imgUrl: '', // 分享图标
				success: function () {
				// 用户确认分享后执行的回调函数
				},
				cancel: function () {
				// 用户取消分享后执行的回调函数
				}
			});
		}
		//获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
		function shareWeibo(){
			wx.onMenuShareWeibo({
				title: '', // 分享标题
				desc: '', // 分享描述
				link: '', // 分享链接
				imgUrl: '', // 分享图标
				success: function () {
				// 用户确认分享后执行的回调函数
				},
				cancel: function () {
				// 用户取消分享后执行的回调函数
				}
			});
		}
		//获取“分享到QQ”按钮点击状态及自定义分享内容接口
		function shareQQ(){
			wx.onMenuShareQQ({
				title: '', // 分享标题
				desc: '', // 分享描述
				link: '', // 分享链接
				imgUrl: '', // 分享图标
				success: function () {
				// 用户确认分享后执行的回调函数
					//alert("success");
				},
				cancel: function () {
				// 用户取消分享后执行的回调函数
					//alert("cancel");
				}
			});
		}
		 
		
	</script>

	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>

