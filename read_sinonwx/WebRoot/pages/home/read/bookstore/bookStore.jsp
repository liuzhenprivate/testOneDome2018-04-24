<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
	Html html = (Html)request.getAttribute("html");
	Integer placType = (Integer)request.getAttribute("placType");
	Long webchatId = (Long)request.getAttribute("webchatId");
	List<HtmlModleInfo> htmlModleInfolist = (List<HtmlModleInfo>)request.getAttribute("htmlModleInfolist");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>${html.name }</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/swiper.min.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
    <script src='pages/home/read/js/swiper.min.js'></script>
    
    <script>
    	function search(){
    		$("#FORM").submit();
    	}
    </script>
</head>
<body style="background: #f2f2f2;">
<c:if test="${placType==1 }">
<form id="FORM" name="FORM" action="<%=basePath%>read/search/${webchatId}" method="post">
	<div class="boyStyleT">
	<div class="swiper-container">
	        <div class="swiper-wrapper">
	
	<% if(null!=htmlModleInfolist && htmlModleInfolist.size()>0){
		HtmlModleInfo modle = htmlModleInfolist.get(0);
		List<HtmlModleDetailInfo> details = modle.getHtmlModleDetaillist();
		if(null!=details && details.size()>0){
			for(HtmlModleDetailInfo info:details){
	%>
			 <div class="swiper-slide">
			<a href="<%=info.getHtmlUrl()%>">
				<img src="http://kuwx.xinxinwx.cn/read/<%=info.getImgUrl() %>"   alt="" />
			</a>
			</div>
		<%}}} %>
		</div>
		 <div class="swiper-pagination"></div>
	</div>
		<div class="boyStyleTInp clearfix">
			<img src="pages/home/read/images/myPic31.png" alt="" onclick="search()"/>
			<input placeholder="输入书籍、作者名称或关键词搜索" id="key" name="key"/>
		</div>
	</div>
</form>
	<div class="bookStoreCla clearfix">
		<a href="<%=basePath%>read/articleCategory/${webchatId }" title="">分类</a>
		<a href="<%=basePath%>read/bookstore/${webchatId }/2" title="">男生</a>
		<a href="<%=basePath%>read/bookstore/${webchatId }/3" title="">女生</a>
		<a href="<%=basePath%>read/bookstore/${webchatId }/4" title="">免费</a>
		<%-- <a href="<%=basePath%>read/bookstore/${webchatId }/5" title="">VIP</a> --%>
		<a href="javascript:alertVip()" title="">VIP</a>
	</div>
	<c:if test="${not empty articleChapterLog }">
	<div class="boyStyleLink">
		<a href="articlechapter/articlechapterGetById/${webchatId }/${articleChapterLog.articleChapter.id}/${user.id}/0.do" title="" class="clearfix">
			<span>最近阅读</span>
			<div>
				<h2>${articleChapterLog.article.articleName }</h2>
				<p>${articleChapterLog.articleChapter.chapterName }</p>
			</div>
			<img src="pages/home/read/images/myPic23.png" alt="" />
		</a>
	</div>
	</c:if>
	</c:if>
	<%
	if(null!=htmlModleInfolist && htmlModleInfolist.size()>0){
		int k=0;
		if(1==placType){
			k =1;
		}
		for(int i=k;i<htmlModleInfolist.size();i++){
			HtmlModleInfo htmlModleInfo = htmlModleInfolist.get(i);
			//1书籍2专题
			int modletype = htmlModleInfo.getModleType();
			String divclass = htmlModleInfo.getDivClass();
			int nums = htmlModleInfo.getNums();
			List<HtmlModleDetailInfo> htmlModleDetaillist = htmlModleInfo.getHtmlModleDetaillist();
			if(1==modletype){
				if(3==nums && "freeAdmission".equals(divclass)){
		%>
		<div class="vipPotenTial">
		<h2><%=htmlModleInfo.getName() %></h2>
		<ul class="clearfix">
		<% 
				if(null!=htmlModleDetaillist && htmlModleDetaillist.size()>0){ 
					for(HtmlModleDetailInfo info:htmlModleDetaillist){
						Article article = info.getArticle();
						if(null!=article){
			%>
			<li>
			<a href="<%=basePath%>article/findArticleId/${webchatId }/<%=article.getId()%>/${user.id}.do">
				<div class="vipPotenTialDe">
					<img src="<%=article.getHttpUrl() %><%=article.getBookCover() %>" alt="" />
					<%if(article.getFeeType()==1){ %>
							<span class="bookStoreDeRRE">VIP</span>
						<%} %>
				</div>
				<h3><%=article.getArticleName() %></h3>
				<p><%=article.getAuthor() %></p>
			</a>
			</li>
		<%}}} %>
			 
		</ul>
	</div>
		 
	<%
				}else if(3==nums && "vipPopularity".equals(divclass)){
					//三本竖排
					%>
					<div class="bookStoreDe">
					<h2><%=htmlModleInfo.getName() %></h2>
					<ul>
						<% 
							if(null!=htmlModleDetaillist && htmlModleDetaillist.size()>0){ 
								for(HtmlModleDetailInfo info:htmlModleDetaillist){
									Article article = info.getArticle();
									if(null!=article){
						%>
						<li class="clearfix">
							<a href="<%=basePath%>article/findArticleId/${webchatId }/<%=article.getId()%>/${user.id}.do">
							<div class="bookStoreDeL">
								<img src="<%=article.getHttpUrl() %><%=article.getBookCover() %>" alt="" />
							</div>
							<div class="bookStoreDeR">
								<h3><%=article.getArticleName() %></h3>
								<p><%=article.getSummary() %></p>
								<div class="clearfix bookStoreDeRR clearfix">
									<h4><%=article.getAuthor() %></h4>
									<div class="clearfix">
									<%if(article.getFeeType()==1){ %>
										<span class="bookStoreDeRRE">VIP</span>
									<%} %>
										<span><%=article.getArticleCategory().getGategory() %></span>
										<span><%=article.getDisplayReaders() %>人在看</span>
									</div>
								</div>
							</div>
							</a>
						</li>
						<%} }}%>
					</ul>
					</div>
				<%				
					
				}else{
					//6本
					%>
				<div class="vipPotenTial">
		<h2><%=htmlModleInfo.getName() %></h2>
		<ul class="clearfix">
		<% 
				if(null!=htmlModleDetaillist && htmlModleDetaillist.size()>0){ 
					for(HtmlModleDetailInfo info:htmlModleDetaillist){
						Article article = info.getArticle();
						if(null!=article){
			%>
			<li>
			<a href="<%=basePath%>article/findArticleId/${webchatId }/<%=article.getId()%>/${user.id}.do">
				<div class="vipPotenTialDe">
					<img src="<%=article.getHttpUrl() %><%=article.getBookCover() %>" alt="" />
					<%if(article.getFeeType()==1){ %>
							<span class="bookStoreDeRRE">VIP</span>
						<%} %>
				</div>
				<h3><%=article.getArticleName() %></h3>
				<p><%=article.getAuthor() %></p>
			</a>
			</li>
		<%}}} %>
			 
		</ul>
	</div>	 
				<%		
					
					
				}
				
			}else{
				if(null!=htmlModleDetaillist && htmlModleDetaillist.size()>0){ 
					int size = htmlModleDetaillist.size();
					if(size==1){
						HtmlModleDetailInfo info = htmlModleDetaillist.get(0);
	 %>
	 					<div class="boyStyleDe">
	 						<a href="<%=info.getHtmlUrl()  %>">
							<h2><%=info.getTitle() %></h2>
							<p><%=info.getSubhead() %></p>
							<img src="http://kuwx.xinxinwx.cn/read/<%=info.getImgUrl() %>" alt="" />
							</a>
						</div>
	 
	 <%
					}else if(size==2){
	 %>
	 				<div class="boyStyleAll clearfix">
							<ul>
								 
								<li class="clearfix">
								<a href="<%=htmlModleDetaillist.get(0).getHtmlUrl()  %>">
									<div>
										<h2><%=htmlModleDetaillist.get(0).getTitle() %></h2>
										<p><%=htmlModleDetaillist.get(0).getSubhead() %></p>
										<img src="http://kuwx.xinxinwx.cn/read/<%=htmlModleDetaillist.get(0).getImgUrl()%>" alt="" />
									</div>
								</a>
								<a href="<%=htmlModleDetaillist.get(1).getHtmlUrl()  %>">
									<div>
										<h2><%=htmlModleDetaillist.get(1).getTitle() %></h2>
										<p><%=htmlModleDetaillist.get(1).getSubhead() %></p>
										<img src="http://kuwx.xinxinwx.cn/read/<%=htmlModleDetaillist.get(1).getImgUrl()%>" alt="" />
									</div>
									</a>
								</li>
							</ul>
						</div>
	 <%					
					}else if(size==4){
	 %>
						<div class="boyStyleAll clearfix">
							<ul>
								 
								<li class="clearfix">
								<a href="<%=htmlModleDetaillist.get(0).getHtmlUrl()  %>">
									<div>
										<h2><%=htmlModleDetaillist.get(0).getTitle() %></h2>
										<p><%=htmlModleDetaillist.get(0).getSubhead() %></p>
										<img src="http://kuwx.xinxinwx.cn/read/<%=htmlModleDetaillist.get(0).getImgUrl()%>" alt="" />
									</div>
								</a>
								<a href="<%=htmlModleDetaillist.get(1).getHtmlUrl()  %>">
									<div>
										<h2><%=htmlModleDetaillist.get(1).getTitle() %></h2>
										<p><%=htmlModleDetaillist.get(1).getSubhead() %></p>
										<img src="http://kuwx.xinxinwx.cn/read/<%=htmlModleDetaillist.get(1).getImgUrl()%>" alt="" />
									</div>
								</a>
								</li>
								<li class="clearfix">
								<a href="<%=htmlModleDetaillist.get(2).getHtmlUrl()  %>">
									<div>
										<h2><%=htmlModleDetaillist.get(2).getTitle() %></h2>
										<p><%=htmlModleDetaillist.get(2).getSubhead() %></p>
										<img src="http://kuwx.xinxinwx.cn/read/<%=htmlModleDetaillist.get(2).getImgUrl()%>" alt="" />
									</div>
								</a>
								<a href="<%=htmlModleDetaillist.get(3).getHtmlUrl()  %>">
									<div>
										<h2><%=htmlModleDetaillist.get(3).getTitle() %></h2>
										<p><%=htmlModleDetaillist.get(3).getSubhead() %></p>
										<img src="http://kuwx.xinxinwx.cn/read/<%=htmlModleDetaillist.get(3).getImgUrl()%>" alt="" />
									</div>
								</a>
								</li>
							</ul>
						</div>
	<%
					}
				}
			}
		}
	%>
	
	<%} %>
	  
	
	
	<div class="vipBottom clearfix">
		<span style="margin-left: 0.3rem;"></span>
		<p>我是有底线的</p>
		<span></span>
	</div>
	<div style="height: 1.3rem;"></div>
	<div class="RankingBot clearfix">
		<a class="RankingBotAct" href="<%=basePath%>read/bookstore/${webchatId}/1">书城</a>
		<a href="<%=basePath%>board/listBoard/${webchatId}.do">排行</a>
		<a href="<%=basePath%>bookshelf/userlistBookShelf/${webchatId}">书架</a>
		<a href="<%=basePath%>my/index/${webchatId}">我的</a>
	</div>
	<script type="text/javascript">
		function alertVip(){
			alert("暂时没有VIP功能");		
		}
	</script>
	<script>
		$(document).on('click','.vipPopularityMore',function(){
			var html='<li class="clearfix"><div class="vipPopularityL"><img src="pages/home/read/images/myPic1.png" /></div><div class="vipPopularityR"><h3>绝剑江湖之浪剑传奇</h3><p>全民偶像“之称的可爱明星莫依，在华夏最具盛名京都花台举办个人演唱会，正是多年来梦寐以求多年来梦寐以求</p><div class="clearfix vipPopularityRR clearfix"><h4>特立独行的猫</h4><div class="clearfix"><span class="vipPopularityRRE">VIP</span><span>玄幻</span><span>354万人在看</span></div></div></div></li>';
			$('.vipPopularity ul').append(html);
		});
		
		$(document).on('click','.bookStoreMore',function(){
			var html='<li class="clearfix"><div class="bookStoreDeL"><img src="pages/home/read/images/myPic1.png" /></div><div class="bookStoreDeR"><h3>绝剑江湖之浪剑传奇</h3><p>全民偶像“之称的可爱明星莫依，在华夏最具盛名京都花台举办个人演唱会，正是多年来梦寐以求多年来梦寐以求</p><div class="clearfix bookStoreDeRR clearfix"><h4>特立独行的猫</h4><div class="clearfix"><span class="bookStoreDeRRE">VIP</span><span>玄幻</span><span>354万人在看</span></div></div></div></li>';
			$('.bookStoreDe ul').append(html);
		});
		$('.RankingBot span').click(function(){
			$(this).addClass('RankingBotAct').siblings().removeClass('RankingBotAct');
		});
		
		var swiper = new Swiper('.swiper-container', {
	        pagination: '.swiper-pagination',
	        nextButton: '.swiper-button-next',
	        prevButton: '.swiper-button-prev',
	        paginationClickable: true,
	        spaceBetween: 30,
	        centeredSlides: true,
	        autoplay: 2500,
	        autoplayDisableOnInteraction: false
	    });

	</script>
	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>

