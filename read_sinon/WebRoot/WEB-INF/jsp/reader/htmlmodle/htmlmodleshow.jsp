<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.entity.read.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	String htmlid = request.getParameter("HTML_ID");
	System.out.println(htmlid);
	Html html = (Html)request.getSession().getAttribute("HTML"+htmlid);
	List<HtmlModle> modles = html.getHtmlmodles();
	int hbnum =1;
%>
<html>
<head>
	<title>模块配置（页面配置）</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/moduleConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	<script src="static/read/js/jquery.SuperSlide.2.1.1.js"></script>
	
</head>
<body>
	 
	<div class="essentialInformateDe clearfix">
		<div class="essentialInformateL">
			<h1><%=html.getName() %></h1>
			<div class="essentialInformateLDe">
			<%
			
			if(html.getPlacType()==1){ 
			%>
				<div class="essentialInformateLT">
					<img src="static/read/images/myPic68.png" alt="" />
					<div class="picScroll-left">
						<div class="hd">
							<ul></ul>
						</div>
						<div class="bd">
							<ul class="picList">
							<%if(null!=modles && modles.size()>0){ 
								HtmlModle modle = modles.get(0);
								List<HtmlModleDetail> details = modle.getHtmlModleDetaillist();
								if(null!=details && details.size()>0){
									for(HtmlModleDetail detail:details){
										hbnum = detail.getSort();
							%>
								<li value="<%=detail.getSort()%>">
								<img src="<%=detail.getImgUrl() %>" alt="" />
								</li>
								
							<%} }
							}%>
							</ul>
						</div>
					</div>
				</div>
				<div class="essentialInformateSel"><img src="static/read/images/myPic69.png" alt="" /></div>
				<div class="essentialInformateNow"><img src="static/read/images/myPic70.png" alt="" /></div>
				<%} %>
				<div class="myAll">
					<%
					
					if(null!=modles && modles.size()>0){
						int j =0;
						if(html.getPlacType()==1){
							j=1;
						}
						for(int i=j;i<modles.size();i++){
							HtmlModle modle = modles.get(i);
							String modlename = modle.getName();
							int nums = modle.getNums();
							//1是书籍2是专题
							int modletype = modle.getModleType();
							String divclass = modle.getDivClass();
							List<HtmlModleDetail> details = modle.getHtmlModleDetaillist();
							if(1==modletype){
								//三本横排
								if(3==nums && "freeAdmission".equals(divclass)){
					%>
								<!--三本横排-->
					<div class="<%=divclass %> myAllChi">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
						<h2><%=modle.getName() %></h2>
						<ul class="clearfix">
						<%if(null!=details&&details.size()>0){
							for(int k=0;k<details.size();k++){
								HtmlModleDetail detail = details.get(k);
								Article article = detail.getArticle();
								 
						%>
							<li>
								<div class="freeAdmissionDe">
									<img src="<%=article.getBookCover() %>" alt="" />
								</div>
								<h3><%=article.getArticleName() %></h3>
								<p><%=article.getAuthor() %></p>
							</li>
						<% }}%>
						</ul>
						<div class="freeAdmissionA myAllHov clearfix">
							<div class="clearfix">
							<%if(i!=0){ %>
								<img class="myAllPic1" src="static/read/images/myPic79.png" alt="" />
							<%} %>
								<%if((i+1) != modles.size()){ %>
								<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />
								<%} %>
								<img class="myAllDel" src="static/read/images/myPic82.png" alt="" />
							</div>
						</div>
					</div>
					
					
					<%      	//三本竖排
								}else if(3==nums && "vipPopularity".equals(divclass)){
					%>			
									<!--三本竖排-->
					<div class="<%=divclass %> myAllChi">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
						<h2><%=modle.getName() %></h2>
						<ul>
						<%if(null!=details&&details.size()>0){
							for(int k=0;k<details.size();k++){
								HtmlModleDetail detail = details.get(k);
								Article article = detail.getArticle();
								 
						%>
							<li class="clearfix">
								<div class="vipPopularityL">
									<img src="<%=article.getBookCover() %>" alt="" />
								</div>
								<div class="vipPopularityR">
									<h3><%=article.getArticleName() %></h3>
									<p><%=article.getSummary() %></p>
									<div class="clearfix vipPopularityRR clearfix">
										<h4><%=article.getAuthor() %></h4>
										<div class="clearfix">
										<%if(article.getFeeType()==1){ %>
											<span class="vipPopularityRRE">VIP</span>
										<%} %>
											<span>玄幻</span>
											<span>354万人在看</span>
										</div>
									</div>
								</div>
							</li>
							<%}} %>
							 
						</ul>
						<div class="freeAdmissionP myAllHov clearfix">
							<div class="clearfix">
								<%if(i!=0){ %>
								<img class="myAllPic1" src="static/read/images/myPic79.png" alt="" />
								<%} %>
								<%if((i+1) != modles.size()){ %>
								<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />
								<%} %>
								<img class="myAllDel" src="static/read/images/myPic82.png" alt="" />
							</div>
						</div>
					</div>
									
					<%				
								}else{
									//vipPotenTial
					%>				
								<!--六本横排-->
					<div class="vipPotenTial myAllChi">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
						<h2><%=modle.getName() %></h2>
						<ul class="clearfix">
						<%if(null!=details&&details.size()>0){
							for(int k=0;k<details.size();k++){
								HtmlModleDetail detail = details.get(k);
								Article article = detail.getArticle();
								 
						%>
							<li>
								<div class="vipPotenTialDe">
									<img src="<%=article.getBookCover() %>" alt="" />
									<%if(article.getFeeType()==1){ %>
									<span style="background: #29d5a6;">VIP</span>
									<%}else{ %>
									<span style="background: #29d5a6;">免费</span>
									<%} %>
									
								</div>
								<h3><%=article.getArticleName() %></h3>
								<p><%=article.getAuthor() %></p>
							</li>
						<%}} %>
							 
						</ul>
						<div class="freeAdmissionS myAllHov clearfix">
							<div class="clearfix">
								<%if(i!=0){ %>
								<img class="myAllPic1" src="static/read/images/myPic79.png" alt="" />
								<%} %>
								<%if((i+1) != modles.size()){ %>
								<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />
								<%} %>
								<img class="myAllDel" src="static/read/images/myPic82.png" alt="" />
							</div>
						</div>
					</div>	
					<%				
								}
							}else{
								if(1==nums){
									 
					%>
					<!--专题模板-->
					<!--单图banner-->
					<div class="thematicTemplateSingleDe myAllChi">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
					<%
					if(null!=details&&details.size()>0){
						 HtmlModleDetail detail = details.get(0);
					%>
						<div class="boyStyleDe">
							<h2><%=detail.getTitle() %></h2>
							<p><%=detail.getSubhead() %></p>
							<img src="<%=detail.getImgUrl() %>" alt="" />
						</div>
						<div class="thematicTemplateSingleA myAllHov clearfix">
							<div class="clearfix">
								<%if(i!=0){ %>
								<img class="myAllPic1" src="static/read/images/myPic79.png" alt="" />
								<%} %>
								<%if((i+1) != modles.size()){ %>
								<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />
								<%} %>
								<img class="myAllDel" src="static/read/images/myPic82.png" alt="" />
							</div>
						</div>
					<%} %>
					</div>
					
					<%				 
								}else if(2==nums){
					%>				
					<!--双图banner-->
					<div class="thematicTemplateDoubleDe myAllChi">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
						<div class="boyStyleAll clearfix">
							<ul>
								<li class="clearfix">
								<%
								if(null!=details&&details.size()>0){
									 for(HtmlModleDetail detail:details){
								%>
									<div>
										<h2><%=detail.getTitle() %></h2>
										<p><%=detail.getSubhead() %></p>
										<img src="<%=detail.getImgUrl() %>" alt="" />
									</div>
							<%}} %>		
								</li>
						
							</ul>
						</div>
						<div class="thematicTemplateSingleS myAllHov clearfix">
							<div class="clearfix">
								<%if(i!=0){ %>
								<img class="myAllPic1" src="static/read/images/myPic79.png" alt="" />
								<%} %>
								<%if((i+1) != modles.size()){ %>
								<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />
								<%} %>
								<img class="myAllDel" src="static/read/images/myPic82.png" alt="" />
							</div>
						</div>
					</div>
					
					<%				
								}else{
					%>				
					<!--四图banner-->
					<div class="thematicTemplateFourDe myAllChi">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
						<div class="boyStyleAll clearfix">
							<ul>
							<%
								if(null!=details&&details.size()>0){
									HtmlModleDetail detail1 = details.get(0);
									HtmlModleDetail detail2 = new HtmlModleDetail();
									HtmlModleDetail detail3 = new HtmlModleDetail();
									HtmlModleDetail detail4 = new HtmlModleDetail();
									if(details.size()>1){
										detail2 = details.get(1);
									}
									if(details.size()>2){
										detail3 = details.get(2);
									}
									if(details.size()>3){
										detail4 = details.get(3);
									}
								%>
								<li class="clearfix">
									<div>
										<h2><%=detail1.getTitle() %></h2>
										<p><%=detail1.getSubhead() %></p>
										<img src="<%=detail1.getImgUrl() %>" alt="" />
									</div>
									<div>
										<h2><%=detail2.getTitle() %></h2>
										<p><%=detail2.getSubhead() %></p>
										<img src="<%=detail2.getImgUrl() %>" alt="" />
									</div>
								</li>
								
								<li class="clearfix">
									<div>
										<h2><%=detail3.getTitle() %></h2>
										<p><%=detail3.getSubhead() %></p>
										<img src="<%=detail3.getImgUrl() %>" alt="" />
									</div>
									<div>
										<h2><%=detail4.getTitle() %></h2>
										<p><%=detail4.getSubhead() %></p>
										<img src="<%=detail4.getImgUrl() %>" alt="" />
									</div>
								</li>
								<%} %>
							</ul>
						</div>
						<div class="thematicTemplateSingleF myAllHov clearfix">
							<div class="clearfix">
								<%if(i!=0){ %>
								<img class="myAllPic1" value="1" src="static/read/images/myPic79.png" alt="" />
								<%} %>
								<%if((i+1) != modles.size()){ %>
								<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />
								<%} %>
								<img class="myAllDel" src="static/read/images/myPic82.png" alt="" />
							</div>
						</div>
					</div>				
					<%				
								}
								
							}
					 }}
					 
					 %>
				</div>
			</div>
		</div>
		 
		 
	</div>
</body>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>

<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->



<!-- 引入 -->
<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		
<script>
	jQuery(".picScroll-left").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:1,trigger:"click"});
	var wid=(318-$('.hd ul').get(0).offsetWidth)/2;
	$('.picScroll-left .hd ul').css('margin-left',wid+'px');
	
	$('.essentialInformateDe>a').click(function(){
		$('.essentialInformateRNone').hide();
	});
	$(document).on('change','.bannerConfigureT div input',function(){
		var $file = $(this);
		var fileObj = $file[0];
		var windowURL = window.URL || window.webkitURL;
		var dataURL;
		var $img = $(this).siblings('img');
		if(fileObj && fileObj.files && fileObj.files[0]){
			dataURL = windowURL.createObjectURL(fileObj.files[0]);
			$img.attr('src',dataURL);
		}else{
			dataURL = $file.val();
			var imgObj = $img.get(0);
			imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
		}
	});
	
	 
	 
</script>



</body>
</html>
