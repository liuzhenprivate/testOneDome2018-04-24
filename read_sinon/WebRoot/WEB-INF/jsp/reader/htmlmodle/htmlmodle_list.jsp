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
<input type="hidden" id="curmodleinfoid" name="curmodleinfoid" />
<input type="hidden" id="maxmodleinfoid" name="maxmodleinfoid" value="${modleid }"/>
<input type="hidden" id="titlenum" name="titlenum" value="1"/>
<input type="hidden" id="hbnum" name="hbnum" value="1"/>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">模板配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop1" href="pageConfigurate.html" title="">页面配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">模块编辑</a>
	</div>
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
						<div class="bd"  id="modle1">
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
					<div class="<%=divclass %> myAllChi" id="divmodle<%=modle.getId() %>">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
						<h2 id="modlename<%=modle.getId() %>"><%=modle.getName() %></h2>
						<ul class="clearfix" id="modle<%=modle.getId() %>">
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
					<div class="<%=divclass %> myAllChi" id="divmodle<%=modle.getId() %>">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
						<h2 id="modlename<%=modle.getId() %>"><%=modle.getName() %></h2>
						<ul id="modle<%=modle.getId() %>">
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
					<div class="vipPotenTial myAllChi" id="divmodle<%=modle.getId() %>">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
						<h2 id="modlename<%=modle.getId() %>" ><%=modle.getName() %></h2>
						<ul class="clearfix" id="modle<%=modle.getId() %>">
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
					<div class="thematicTemplateSingleDe myAllChi" id="divmodle<%=modle.getId() %>">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
					
						<div class="boyStyleDe" id="modle<%=modle.getId() %>">
						<%
					if(null!=details&&details.size()>0){
						 HtmlModleDetail detail = details.get(0);
					%>
							<h2><%=detail.getTitle() %></h2>
							<p><%=detail.getSubhead() %></p>
							<img src="<%=detail.getImgUrl() %>" alt="" />
					<%} %>
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
					
					</div>
					
					<%				 
								}else if(2==nums){
					%>				
					<!--双图banner-->
					<div class="thematicTemplateDoubleDe myAllChi" id="divmodle<%=modle.getId() %>">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
						<div class="boyStyleAll clearfix" id="modle<%=modle.getId() %>">
							<ul >
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
					<div class="thematicTemplateFourDe myAllChi" id="divmodle<%=modle.getId() %>">
					<input type="hidden" id="modle" name="modle" value="<%=modle.getId() %>" />
						<div class="boyStyleAll clearfix" id="modle<%=modle.getId() %>">
							<ul  >
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
		<div class="essentialInformateR">
			<div class="essentialInformateRNone" style="display: none;">
				点击左侧菜单进行编辑操作
			</div>
			<div class="essentialInformateRBtn clearfix">
				<span onclick="finishhtml()" class="essentialInformateRBtn1">完成并更新</span>
				<span onclick="show()" class="essentialInformateRBtn2">预览页面</span>
				<a href="htmlmodle/lookhtml?HTML_ID=${HTML_ID }"><span class="essentialInformateRBtn3">取消配置</span></a>
			</div>
			<%
			
			if(html.getPlacType()==1){ 
			%>
			<!--顶部banner-->
			<div class="bannerConfigure">
				<h1>Banner配置</h1>
				<ul>
				<%
				boolean flag = true;
				if(null!=modles && modles.size()>0){ 
					HtmlModle modle = modles.get(0);
					List<HtmlModleDetail> details = modle.getHtmlModleDetaillist();
					if(null!=details && details.size()>0){
						flag = false;
						int index =0;
						for(HtmlModleDetail detail:details){
							index++;
				%>
						
					<li>
						<div class="bannerConfigureT clearfix">
							<p>海报<%=detail.getSort() %></p>
							<div value="<%=detail.getSort() %>">
								<img id="hbimgsrc<%=detail.getSort() %>" src="<%=detail.getImgUrl() %>" alt="" />
								<span>上传封面</span>
								<input onchange="gethbimg(<%=detail.getSort() %>)" type="file" id="hbimg<%=detail.getSort() %>" name="hbimg<%=detail.getSort() %>" />
							</div>
							<%if(index>1){ %>
							<i onclick="delmodledetailhb(<%=detail.getSort() %>)"></i>
							<%} %>
						</div>
						<div class="bannerConfigureB clearfix">
							<p>选择链接</p>
							<div class="clearfix">
								<input placeholder="请输入正确合法的跳转地址" onchange="gethburl(<%=detail.getSort() %>)" id="hburl<%=detail.getSort() %>" name="hburl<%=detail.getSort() %>" value="<%=detail.getHtmlUrl()%>"/>
							</div>
							 
						</div>
					</li>
					<%
						}}
						}
				if(flag){ %>
					<li>
						<div class="bannerConfigureT clearfix">
							<p>海报1</p>
							<div value="1">
								<img id="hbimgsrc1" src="static/read/images/myPic74.png" alt="" />
								<span>上传封面</span>
								<input type="file" id="hbimg1" name="hbimg1" onchange="gethbimg(1)"/>
							</div>
							<!--<i></i>-->
						</div>
						<div class="bannerConfigureB clearfix">
							<p>选择链接</p>
							<div class="clearfix" value="1">
								<input  placeholder="请输入正确合法的跳转地址" id="hburl1" name="hburl1" onchange="gethburl(1)"/>
							</div>
						</div>
					</li>
					
					<%} %>
				</ul>
				<div><span>添加海报</span></div>
			</div>
			<%} %>
			<!--书籍模块-->
			<div class="bookModule">
				<h1>书籍模块</h1>
				<div class="bookModuleInput clearfix">
					<p>模块标题</p>
					<div class="clearfix">
						<input value="" id="MODLENAME" name="MODLENAME" onchange="getname()"/>
					</div>
				</div>
				<div class="bookModuleSel clearfix">
					<p>选择模板</p>
					<div class="clearfix">
						<img class="bookModuleSel1 bookModuleSelAct" src="static/read/images/myPic84.png" alt="" />
						<img class="bookModuleSel2" src="static/read/images/myPic85.png" alt="" />
						<img class="bookModuleSel3" src="static/read/images/myPic86.png" alt="" />
					</div>
				</div>
				<h1 style="margin-top: 30px;">书籍内容</h1>
				<!--未编辑-->
				<div class="bookModuleIng">
					<div class="bookModuleCla clearfix">
						<div class="bookModuleClaL clearfix">
							 
						</div>
						<div class="bookModuleClaR clearfix">
						</div>
						<div class="bookModuleClaR clearfix" onclick="addbook()">
							<span><i>添加书籍</i></span>
						</div>
					</div>
					<div class="bookModuleTit clearfix">
						<span style="width: 48px; margin-left: 20px;">序号</span>
						<span style="width: 128px;">书籍名称</span>
						<span style="width: 93px;">书籍ID</span>
						<span style="width: 62px;">男女频</span>
						<span style="width: 76px;">付费方式</span>
						<span style="width: 75px;">书籍类型</span>
						<span>管理操作</span>
					</div>
					<div class="bookModuleTex">
						<ul>
							 
							 
						</ul>
					</div>
				</div>
				 
			</div>
			
			<!--基本信息-->
			<div class="basicInf clearfix">
				<h1>基本信息</h1>
				<div class="basicInfInput clearfix">
					<p>页面名称</p>
					<div class="clearfix">
						<input value="书城首页" />
					</div>
				</div>
				<div class="basicInfText clearfix">
					<p>页面备注</p>
					<div class="clearfix">
						<h2>请输入页面备注</h2>
						<textarea></textarea>
					</div>
					<span><i>0</i>/200</span>
				</div>
				<div class="basicInfSel clearfix">
					<p>页面类型</p>
					<div class="clearfix">
						<span>系统页面</span>
						<ul>
							<li>系统页面</li>
							<li>各种页面</li>
							<li>各种页面</li>
						</ul>
					</div>
				</div>
			</div>
		
			<!--选择模板-->
			<div class="selectTemplate">
				<h1>专题模块</h1>
				<div class="selectTemplateSel clearfix">
					<p>选择模板</p>
					<div class="clearfix">
						<img class="selectTemplateSel1 selectTemplateSelAct" src="static/read/images/myPic87.png" alt="" />
						<img class="selectTemplateSel2" src="static/read/images/myPic88.png" alt="" />
						<img class="selectTemplateSel3" src="static/read/images/myPic89.png" alt="" />
					</div>
				</div>
				<div class="selectTemplateSingle clearfix">
				
					<h2  id="title">单图banner</h2>
					<div class="selectTemplateFile clearfix">
					<span id="banner1">
						<p>封面图片1</p>
						<div  onclick="showtile(1)">
							<img id="img1" src="static/read/images/myPic51.png" alt="" />
							<span>上传封面</span>
							<input type="file" id="imgfile1" name="imgfile1"  />
						</div>
					</span>
					<span id="banner2">
						<p>封面图片2</p>
						<div  onclick="showtile(2)">
							<img id="img2" src="static/read/images/myPic51.png" alt="" />
							<span>上传封面</span>
							<input type="file" id="imgfile2" name="imgfile2"/>
						</div>
					</span>
					</div>
				 
				
					<div class="selectTemplateFile clearfix" id="bigbanner">
					<span id="banner3">
						<p>封面图片3</p>
						<div  onclick="showtile(3)">
							<img id="img3" src="static/read/images/myPic51.png" alt="" />
							<span>上传封面</span>
							<input type="file" id="imgfile3" name="imgfile3"/>
						</div>
					</span>
					<span id="banner4">
						<p>封面图片4</p>
						<div onclick="showtile(4)">
							<img id="img4" src="static/read/images/myPic51.png" alt="" />
							<span>上传封面</span>
							<input type="file" id="imgfile4" name="imgfile4"/>
						</div>
					</span>
					</div>
					
					<div class="selectTemplateInput clearfix" style="display:none">
						<p id="tid1">主标题</p>
						<div class="clearfix">
							<input value="" id="MODLETITLE" name="MODLETITLE" onchange="updatetile()"/>
						</div>
					</div>
					<div class="selectTemplateInput clearfix" style="display:none">
						<p id="tid2">副标题</p>
						<div class="clearfix">
							<input value="" id="SUBHEAD" name="SUBHEAD" onchange="updatesubhead()"/>
						</div>
					</div>
					<div class="selectTemplateInput clearfix" style="display:none">
						<p id="tid3">链接</p>
						<div class="clearfix">
							<input value="" id="htmlUrl" name="htmlUrl" onchange="updatehtmlUrl()"/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="essentialInformateDeLink">
			<span>
				<i>新增模板</i>
				<ul>
					<li><a class="essentialInformateDeLink1" href="javascript:void(0)" title="">书籍模板</a></li>
					<li><a class="essentialInformateDeLink2" href="javascript:void(0)" title="">专题模板</a></li>
				</ul>
			</span>
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
<%
if(html.getPlacType()==1){ 
%>
	jQuery(".picScroll-left").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:1,trigger:"click"});
	var wid=(318-$('.hd ul').get(0).offsetWidth)/2;
	$('.picScroll-left .hd ul').css('margin-left',wid+'px');
<%}%>	
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
	
	$(document).on('click','.bannerConfigureB div p',function(){
		$(this).hide();
		$(this).siblings('input').focus();
	});
	
	$('.bannerConfigureB div input').bind('input propertychange', function(){
        if($(this).val()==''){
        	$(this).siblings('p').show();
//      	$(this).blur();
        }
	});
	$(document).on('blur','.bannerConfigureB div input',function(){
		if($(this).val()==''){
			$(this).siblings('p').show();
		}
	});
	
	$(document).on('click','.bannerConfigureT i',function(){
		$(this).parents('li').remove();
	});
	
	$('.bannerConfigure>div').click(function(){
		var hbnum = parseInt($("#hbnum").val())+1;
		$("#hbnum").val(hbnum)
		//var len=parseInt($('.bannerConfigure ul li').length);
		//var lenPlus=len+1;
		var html='<li><div class="bannerConfigureT clearfix"><p>海报'+hbnum+'</p><div><img id="hbimgsrc'+hbnum+'" src="static/read/images/myPic74.png" alt="" /><span>上传封面</span><input type="file" onchange="gethbimg('+hbnum+')" type="file" id="hbimg'+hbnum+'" name="hbimg'+hbnum+'"/></div><i onclick="delmodledetailhb('+hbnum+')"></i></div><div class="bannerConfigureB clearfix"><p>选择链接</p><div class="clearfix"><input placeholder="请输入正确合法的跳转地址" type="text" onchange="gethburl('+hbnum+')" id="hburl'+hbnum+'" name="hburl'+hbnum+'"/></div> </div></li>';

		$('.bannerConfigure ul').append(html);
	});
	
	$('.essentialInformateRBtn1').click(function(){
		$('.picList li').remove();
		$('.bannerConfigureT div img').each(function(){
			var src=$(this).attr('src');
			var html='<li><img src="'+src+'" alt="" /></li>';
			$('.picList').append(html);
		})
		var allHtml=$('.essentialInformateLT').html();
		$('.essentialInformateLT').html(allHtml);
		jQuery(".picScroll-left").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:1,trigger:"click"});
		var wid=(318-$('.hd ul').get(0).offsetWidth)/2;
		$('.picScroll-left .hd ul').css('margin-left',wid+'px');
	});
	
	
	$(document).on('mouseenter','.freeAdmission',function(){
		$(this).find('.freeAdmissionA').show();
	});
	$(document).on('mouseleave','.freeAdmission',function(){
		$(this).find('.freeAdmissionA').hide();
	});
	
	$(document).on('mouseenter','.vipPotenTial',function(){
		$(this).find('.freeAdmissionS').show();
	});
	$(document).on('mouseleave','.vipPotenTial',function(){
		$(this).find('.freeAdmissionS').hide();
	});
	
	$(document).on('mouseenter','.vipPopularity',function(){
		$(this).find('.freeAdmissionP').show();
	});
	$(document).on('mouseleave','.vipPopularity',function(){
		$(this).find('.freeAdmissionP').hide();
	});
	
	$('.bookModuleSel div img').click(function(){
		$(this).addClass('bookModuleSelAct').siblings().removeClass('bookModuleSelAct');
	});
	
	$('.bookModuleClaL p').click(function(){
		$(this).hide();
		$(this).siblings('input').focus();
	});
	$('.bookModuleClaL input').bind('input propertychange', function(){
        if($(this).val()==''){
        	$(this).siblings('p').show();
//      	$(this).blur();
        }
	});
	$('.bookModuleClaL input').blur(function(){
		if($(this).val()==''){
			$(this).siblings('p').show();
		}
	});
	
	$(window).click(function(){
		$('.bookModuleTexSe div').hide();
		$('.basicInfSel div').find('ul').hide();
		$('.essentialInformateDeLink span ul').hide();
	});
	$('.bookModuleTexSe span').click(function(e){
		if($(this).siblings('div').css('display')=="none"){
			$(this).siblings('div').show();
		}else{
			$(this).siblings('div').hide();
		}
		$(this).parents('li').siblings().find('.bookModuleTexSeDiv').hide();
		e.stopPropagation();
	});
	$('.bookModuleTexSe div a').click(function(e){
		$(this).parent('div').hide();
		e.stopPropagation();
	});
	
	$('.bookModuleSel1').click(function(){
		//alert(1);
		updatearticlemodle('',3,'freeAdmission');
		$('.essentialInformateH').show();
		$('.essentialInformateS').hide();
		$('.essentialInformateP').hide();
	});
	$('.bookModuleSel2').click(function(){
		//alert(2);
		updatearticlemodle('',6,'vipPotenTial');
		$('.essentialInformateH').hide();
		$('.essentialInformateS').show();
		$('.essentialInformateP').hide();
	});
	$('.bookModuleSel3').click(function(){
		//alert(3);
		updatearticlemodle('',3,'vipPopularity');
		$('.essentialInformateH').hide();
		$('.essentialInformateS').hide();
		$('.essentialInformateP').show();
	});
	
	$('.essentialInformateL>h1').click(function(){
		$('.basicInf').show();
		$('.bannerConfigure').hide();
		$('.bookModule').hide();
	});
	$('.essentialInformateLT').click(function(){
		$("#curmodleinfoid").val(1);
		$('.basicInf').hide();
		$('.bannerConfigure').show();
		$('.bookModule').hide();
		$('.selectTemplate').hide();
	});
	
	$(document).on('click','.freeAdmission',function(){
		$(".bookModuleSel1").addClass('bookModuleSelAct').siblings().removeClass('bookModuleSelAct');
		
		//三本横排
		var modleid= $(this).children('#modle').val() ;
		//alert(modleid);
		getmodle(modleid);
		//alert("freeAdmission="+modleid);
		$('.basicInf').hide();
		$('.bannerConfigure').hide();
		$('.bookModule').show();
		$('.selectTemplate').hide();
	});
	$(document).on('click','.vipPotenTial',function(){
		$(".bookModuleSel2").addClass('bookModuleSelAct').siblings().removeClass('bookModuleSelAct');
		//六本横排
		var modleid= $(this).children('#modle').val() ;
		//alert("vipPotenTial="+modleid);
		getmodle(modleid);
		$('.basicInf').hide();
		$('.bannerConfigure').hide();
		$('.bookModule').show();
		$('.selectTemplate').hide();
	});
	$(document).on('click','.vipPopularity',function(){
		$(".bookModuleSel3").addClass('bookModuleSelAct').siblings().removeClass('bookModuleSelAct');
		//三本竖排
		var modleid= $(this).children('#modle').val() ;
		//alert("vipPopularity="+modleid);
		getmodle(modleid);
		$('.basicInf').hide();
		$('.bannerConfigure').hide();
		$('.bookModule').show();
		$('.selectTemplate').hide();
	});
	$(document).on('click','.thematicTemplateSingleDe',function(){
		$(".selectTemplateSel1").addClass('selectTemplateSelAct').siblings().removeClass('selectTemplateSelAct');
		//单图banner
		$("#title").html("单图banner1");
		showbanner(1);
		var modleid= $(this).children('#modle').val() ;
		//alert("thematicTemplateSingleDe="+modleid);
		getmodle(modleid);
		$('.selectTemplate').show();
		$('.bannerConfigure').hide();
		$('.bookModule').hide();
	});
	$(document).on('click','.thematicTemplateDoubleDe',function(){
		$(".selectTemplateSel2").addClass('selectTemplateSelAct').siblings().removeClass('selectTemplateSelAct');
		//双图banner
		$("#title").html("双图banner");
		showbanner(2);
		var modleid= $(this).children('#modle').val() ;
		//alert("thematicTemplateDoubleDe="+modleid);
		getmodle(modleid);
		$('.selectTemplate').show();
		$('.bannerConfigure').hide();
		$('.bookModule').hide();
	});
	$(document).on('click','.thematicTemplateFourDe',function(){
		
		$(".selectTemplateSel3").addClass('selectTemplateSelAct').siblings().removeClass('selectTemplateSelAct');
		//四图banner
		$("#title").html("四图banner");
		showbanner(4);
		
		var modleid= $(this).children('#modle').val() ;
		//alert("thematicTemplateFourDe="+modleid);
		getmodle(modleid);
		$('.selectTemplate').show();
		$('.bannerConfigure').hide();
		$('.bookModule').hide();
		 
	});
	$('.basicInfSel div').click(function(e){
		if($(this).find('ul').css('display')=="none"){
			$(this).find('ul').show();
		}else{
			$(this).find('ul').hide();
		}
		e.stopPropagation();
	});
	$('.basicInfSel div ul li').click(function(e){
		$(this).css('color','#f37427');
		$(this).siblings().css('color','#666666');
		$(this).parent().hide();
		var val=$(this).text();
		$('.basicInfSel div span').text(val);
		e.stopPropagation();
	});
	
	$('.basicInfText div h2').click(function(){
		$(this).hide();
		$(this).siblings('textarea').focus();
	});
	$('.basicInfText div textarea').bind('input propertychange', function(){
        if($(this).val()==''){
        	$(this).siblings('h2').show();
//      	$(this).blur();
        }
	});
	$('.basicInfText div textarea').blur(function(){
		if($(this).val()==''){
			$(this).siblings('h2').show();
		}
	});
	
	$('.bookModuleEdTit span i').click(function(){
		if($(this).hasClass('bookModuleEdTitAct')){
			$(this).removeClass('bookModuleEdTitAct');
			$('.bookModuleEdTex ul li a span').removeClass('bookModuleEdTexAct');
		}else{
			$(this).addClass('bookModuleEdTitAct');
			$('.bookModuleEdTex ul li a span').addClass('bookModuleEdTexAct');
		}
	});
	$('.bookModuleEdTex ul li a span').click(function(){
		if($(this).hasClass('bookModuleEdTexAct')){
			$(this).removeClass('bookModuleEdTexAct');
			$('.bookModuleEdTit span i').removeClass('bookModuleEdTitAct');
		}else{
			$(this).addClass('bookModuleEdTexAct');
		}
	});
	
	$('.bookModuleClaR a').click(function(){
		$('.bookModuleIng').hide();
		$('.bookModuleEd').show();
	});
	$('.bookModuleEdTop a').click(function(){
		$('.bookModuleIng').show();
		$('.bookModuleEd').hide();
	});
	
	$('.essentialInformateDeLink span').click(function(e){
		if($(this).find('ul').css('display')=="none"){
			$(this).find('ul').show();
		}else{
			$(this).find('ul').hide();
		}
		e.stopPropagation();
	});
	
	
	$('.essentialInformateDeLink1').click(function(){
		$('.basicInf').hide();
		$('.bannerConfigure').hide();
		$('.bookModule').hide();
		$('.selectTemplate').hide();
		$('.essentialInformateRNone').hide();
		//选中的模板
		var curmodleinfoid = $('#curmodleinfoid').val();
		var maxmodleinfoid =parseInt($('#maxmodleinfoid').val())+1; 
		$('#maxmodleinfoid').val(maxmodleinfoid);
		addmodle(maxmodleinfoid,curmodleinfoid,1);
		var cur = '<input type=\"hidden\" id=\"modle\" name=\"modle\" value=\"'+maxmodleinfoid+'\" />';
		if($('.myAllChi').hasClass('myAllChiAct')){
			var eq=parseInt($('.myAllChiAct').index());
			var len=parseInt($('.myAllChi').length)-1;
			
			if(eq==0&&len==0){
				var html='<div class="freeAdmission myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<h2 id="modlename'+maxmodleinfoid+'">书籍模板</h2><ul class="clearfix" id="modle'+maxmodleinfoid+'"><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #29d5a6;">免费</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #fe925a;">VIP</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li></ul><div class="freeAdmissionA myAllHov clearfix"><div class="clearfix"><img class="myAllPic1" src="static/read/images/myPic79.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				$('.myAllChi').eq(eq).removeClass('myAllChiAct');
				$('.myAll').append(html);
			}else if(eq==len&&eq!=0&&len!=0){
				var html='<div class="freeAdmission myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<h2 id="modlename'+maxmodleinfoid+'">书籍模板</h2><ul class="clearfix" id="modle'+maxmodleinfoid+'"><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #29d5a6;">免费</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #fe925a;">VIP</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li></ul><div class="freeAdmissionA myAllHov clearfix"><div class="clearfix"><img class="myAllPic1" src="static/read/images/myPic79.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				var html1='<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />';
				$('.myAllChi').eq(eq).removeClass('myAllChiAct');
				$('.myAllChi').eq(eq).find('.myAllPic1').after(html1);
				$('.myAll').append(html);
			}else{
				var html='<div class="freeAdmission myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<h2 id="modlename'+maxmodleinfoid+'">书籍模板</h2><ul class="clearfix" id="modle'+maxmodleinfoid+'"><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #29d5a6;">免费</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #fe925a;">VIP</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li></ul><div class="freeAdmissionA myAllHov clearfix"><div class="clearfix"><img class="myAllPic1" src="static/read/images/myPic79.png" alt="" /><img class="myAllPic2" src="static/read/images/myPic80.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				$('.myAllChi').eq(eq).removeClass('myAllChiAct');
				$('.myAllChi').eq(eq).after(html);
			}
		}else{
			var len=parseInt($('.myAllChi').length);
			if(len==0){
				var html='<div class="freeAdmission myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<h2 id="modlename'+maxmodleinfoid+'">书籍模板1</h2><ul class="clearfix" id="modle'+maxmodleinfoid+'"><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #29d5a6;">免费</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #fe925a;">VIP</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li></ul><div class="freeAdmissionA myAllHov clearfix"><div class="clearfix"><img class="myAllPic2" src="static/read/images/myPic80.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				$('.myAll').append(html);
			}else if(len==1){
				var html='<div class="freeAdmission myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<h2 id="modlename'+maxmodleinfoid+'">书籍模板1</h2><ul class="clearfix" id="modle'+maxmodleinfoid+'"><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #29d5a6;">免费</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #fe925a;">VIP</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li></ul><div class="freeAdmissionA myAllHov clearfix"><div class="clearfix"><img class="myAllPic1" src="static/read/images/myPic79.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				$('.myAllChi').eq(len-1).removeClass('myAllChiAct');
				$('.myAll').append(html);
			}else{
				var html='<div class="freeAdmission myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<h2 id="modlename'+maxmodleinfoid+'">书籍模板1</h2><ul class="clearfix" id="modle'+maxmodleinfoid+'"><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #29d5a6;">免费</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li><li><div class="freeAdmissionDe"><img src="static/read/images/myPic73.png" alt="" /><span style="background: #fe925a;">VIP</span></div><h3>我家客人你惹不起我家客人你惹不起</h3><p>烽火成林</p></li></ul><div class="freeAdmissionA myAllHov clearfix"><div class="clearfix"><img class="myAllPic1" src="static/read/images/myPic79.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				var html1='<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />';
				$('.myAllChi').eq(len-1).find('.myAllPic1').after(html1);
				$('.myAllChi').eq(len-1).removeClass('myAllChiAct');
				$('.myAll').append(html);
			}
		}
	});
	$('.essentialInformateDeLink2').click(function(){
		$('.basicInf').hide();
		$('.bannerConfigure').hide();
		$('.bookModule').hide();
		$('.selectTemplate').hide();
		$('.essentialInformateRNone').hide();
		//选中的模板
		var curmodleinfoid = $('#curmodleinfoid').val();
		var maxmodleinfoid =parseInt($('#maxmodleinfoid').val())+1; 
		$('#maxmodleinfoid').val(maxmodleinfoid);
		addmodle(maxmodleinfoid,curmodleinfoid,2);
		var cur = '<input type=\"hidden\" id=\"modle\" name=\"modle\" value=\"'+maxmodleinfoid+'\" />';
		if($('.myAllChi').hasClass('myAllChiAct')){
			var eq=parseInt($('.myAllChiAct').index());
			var len=parseInt($('.myAllChi').length)-1;
			if(eq==0&&len==0){
				var html='<div class="thematicTemplateSingleDe myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<div class="boyStyleDe" id="modle'+maxmodleinfoid+'"><h2>斩妖妃虐暴君：皇后保卫战</h2><p>女主表示：势必打赢这场勾搭与反勾搭，算计与被算计！</p><img src="static/read/images/myPic54.png" alt="" /></div><div class="thematicTemplateSingleA myAllHov clearfix"><div class="clearfix"><img class="myAllPic1" src="static/read/images/myPic79.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				$('.myAllChi').eq(eq).removeClass('myAllChiAct');
				$('.myAll').append(html);
			}else if(eq==len&&eq!=0&&len!=0){
				var html='<div class="thematicTemplateSingleDe myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<div class="boyStyleDe" id="modle'+maxmodleinfoid+'"><h2>斩妖妃虐暴君：皇后保卫战</h2><p>女主表示：势必打赢这场勾搭与反勾搭，算计与被算计！</p><img src="static/read/images/myPic54.png" alt="" /></div><div class="thematicTemplateSingleA myAllHov clearfix"><div class="clearfix"><img class="myAllPic1" src="static/read/images/myPic79.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				var html1='<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />';
				$('.myAllChi').eq(eq).removeClass('myAllChiAct');
				$('.myAllChi').eq(eq).find('.myAllPic1').after(html1);
				$('.myAll').append(html);
			}else{
				var html='<div class="thematicTemplateSingleDe myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<div class="boyStyleDe" id="modle'+maxmodleinfoid+'"><h2>斩妖妃虐暴君：皇后保卫战</h2><p>女主表示：势必打赢这场勾搭与反勾搭，算计与被算计！</p><img src="static/read/images/myPic54.png" alt="" /></div><div class="thematicTemplateSingleA myAllHov clearfix"><div class="clearfix"><img class="myAllPic1" src="static/read/images/myPic79.png" alt="" /><img class="myAllPic2" src="static/read/images/myPic80.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				$('.myAllChi').eq(eq).removeClass('myAllChiAct');
				$('.myAllChi').eq(eq).after(html);
			}
		}else{
			var len=parseInt($('.myAllChi').length);
			if(len==0){
				var html='<div class="thematicTemplateSingleDe myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<div class="boyStyleDe" id="modle'+maxmodleinfoid+'"><h2>斩妖妃虐暴君：皇后保卫战</h2><p>女主表示：势必打赢这场勾搭与反勾搭，算计与被算计！</p><img src="static/read/images/myPic54.png" alt="" /></div><div class="thematicTemplateSingleA myAllHov clearfix"><div class="clearfix"><img class="myAllPic2" src="static/read/images/myPic80.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				$('.myAll').append(html);
			}else if(len==1){
				var html='<div class="thematicTemplateSingleDe myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<div class="boyStyleDe" id="modle'+maxmodleinfoid+'"><h2>斩妖妃虐暴君：皇后保卫战</h2><p>女主表示：势必打赢这场勾搭与反勾搭，算计与被算计！</p><img src="static/read/images/myPic54.png" alt="" /></div><div class="thematicTemplateSingleA myAllHov clearfix"><div class="clearfix"><img class="myAllPic1" src="static/read/images/myPic79.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				$('.myAllChi').eq(len-1).removeClass('myAllChiAct');
				$('.myAll').append(html);
			}else{
				var html='<div class="thematicTemplateSingleDe myAllChi myAllChiAct" id="divmodle'+maxmodleinfoid+'\">'+cur+'<div class="boyStyleDe" id="modle'+maxmodleinfoid+'"><h2>斩妖妃虐暴君：皇后保卫战</h2><p>女主表示：势必打赢这场勾搭与反勾搭，算计与被算计！</p><img src="static/read/images/myPic54.png" alt="" /></div><div class="thematicTemplateSingleA myAllHov clearfix"><div class="clearfix"><img class="myAllPic1" src="static/read/images/myPic79.png" alt="" /><img class="myAllDel" src="static/read/images/myPic82.png" alt="" /></div></div></div>';
				var html1='<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />';
				$('.myAllChi').eq(len-1).find('.myAllPic1').after(html1);
				$('.myAllChi').eq(len-1).removeClass('myAllChiAct');
				$('.myAll').append(html);
			}
		}
	});
	
	
//	专题模块
	$(document).on('click','.myAllChi',function(){
		var modleid= $(this).children('#modle').val() ;
		//alert(modleid);
		$("#curmodleinfoid").val(modleid);
		$(this).parents('.myAll').find('.myAllChi').removeClass('myAllChiAct');
		$(this).addClass('myAllChiAct');
	});
	$(document).on('mouseenter','.thematicTemplateSingleDe',function(){
		$(this).find('.thematicTemplateSingleA').show();
	});
	$(document).on('mouseleave','.thematicTemplateSingleDe',function(){
		$(this).find('.thematicTemplateSingleA').hide();
	});
	
	$(document).on('mouseenter','.thematicTemplateDoubleDe',function(){
		$(this).find('.thematicTemplateSingleS').show();
	});
	$(document).on('mouseleave','.thematicTemplateDoubleDe',function(){
		$(this).find('.thematicTemplateSingleS').hide();
	});
	
	$(document).on('mouseenter','.thematicTemplateFourDe',function(){
		$(this).find('.thematicTemplateSingleF').show();
	});
	$(document).on('mouseleave','.thematicTemplateFourDe',function(){
		$(this).find('.thematicTemplateSingleF').hide();
	});
	
	$('.selectTemplateSel div img').click(function(){
		$(this).addClass('selectTemplateSelAct').siblings().removeClass('selectTemplateSelAct');
	});
	
	$('.selectTemplateSel1').click(function(){
		$("#title").html("单图banner");
		updatearticlemodle('',1,'thematicTemplateSingleA');
		showbanner(1);
		$('.thematicTemplateSingle').show();
		$('.thematicTemplateDouble').hide();
		$('.thematicTemplateFour').hide();
	});
	$('.selectTemplateSel2').click(function(){
		$("#title").html("双图banner");
		updatearticlemodle('',2,'thematicTemplateDoubleDe');
		showbanner(2);
		$('.thematicTemplateSingle').hide();
		$('.thematicTemplateDouble').show();
		$('.thematicTemplateFour').hide();
	});
	$('.selectTemplateSel3').click(function(){
		updatearticlemodle('',4,'thematicTemplateSingleS');
		$("#title").html("四图banner");
		showbanner(4);
		$('.thematicTemplateSingle').hide();
		$('.thematicTemplateDouble').hide();
		$('.thematicTemplateFour').show();
	});
	
	
	
	$(document).on('click','.myAllDel',function(e){
		var eq=parseInt($(this).parents('.myAllChi').index());
		var len=parseInt($('.myAllChi').length);
		var lenDown=len-1;
		var modleid= $(this).parents('.myAllChi').children('#modle').val() ;
		delmodle(modleid);
		if(eq==0){
			$(this).parents('.myAllChi').next('.myAllChi').find('.myAllPic1').remove();
			$(this).parents('.myAllChi').remove();
		}else if(eq==lenDown){
			$(this).parents('.myAllChi').prev('.myAllChi').find('.myAllPic2').remove();
			$(this).parents('.myAllChi').remove();
		}else{
			$(this).parents('.myAllChi').remove();
		}
		e.stopPropagation();
		$('.selectTemplate').hide();
		$('.bookModule').hide();
	});
	
	$(document).on('click','.myAllPic1',function(e){
		var len=parseInt($('.myAllChi').length);
		var eq=parseInt($(this).parents('.myAllChi').index());
		var modleid= $(this).parents('.myAllChi').children('#modle').val() ;
		//alert("modleid="+modleid);
		move(modleid,1);
		//alert("eq="+eq);
		var eqPlus=eq+1;
		if(len==eqPlus){
			var html='<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />';
			$(this).after(html);
			$(this).parents('.myAllChi').prev('.myAllChi').find('.myAllPic2').remove();
			$(this).parents('.myAllChi').prev('.myAllChi').before($(this).parents('.myAllChi'));
		}else if(eq==1){
			var html1='<img class="myAllPic1" src="static/read/images/myPic79.png" alt="" />';
			$(this).parents('.myAllChi').prev('.myAllChi').find('.myAllPic2').before(html1);
			$(this).parents('.myAllChi').prev('.myAllChi').before($(this).parents('.myAllChi'));
			$(this).remove();
		}else{
			$(this).parents('.myAllChi').prev('.myAllChi').before($(this).parents('.myAllChi'));
		}
		e.stopPropagation();
	});
	$(document).on('click','.myAllPic2',function(e){
		var eq=parseInt($(this).parents('.myAllChi').index());
		var len=parseInt($('.myAllChi').length)-2;
		var modleid= $(this).parents('.myAllChi').children('#modle').val() ;
		move(modleid,2);
		if(eq==0){
			var html='<img class="myAllPic1" src="static/read/images/myPic79.png" alt="" />';
			$(this).before(html);
			$(this).parents('.myAllChi').next('.myAllChi').find('.myAllPic1').remove();
			$(this).parents('.myAllChi').next('.myAllChi').after($(this).parents('.myAllChi'));
		}else if(eq==len){
			var html1='<img class="myAllPic2" src="static/read/images/myPic80.png" alt="" />';
			$(this).parents('.myAllChi').next('.myAllChi').find('.myAllPic1').after(html1);
			$(this).parents('.myAllChi').next('.myAllChi').after($(this).parents('.myAllChi'));
			$(this).remove();
		}else{
			$(this).parents('.myAllChi').next('.myAllChi').after($(this).parents('.myAllChi'));
		}
		e.stopPropagation();
	});
	//移动模板
	function move(modleid,type){
		$.get("<%=basePath%>htmlmodle/move.do?htmlid=${HTML_ID}&type="+type+"&modleid="+modleid,function(data,status){
			//alert(data);
		});
	}
	//删除模板
	function delmodle(modleid){
		$.get("<%=basePath%>htmlmodle/delmodle.do?htmlid=${HTML_ID}&modleid="+modleid,function(data,status){
			//alert(data);
		});
	}
	//新增模板
	function addmodle(modleid,curmodleid,type){
		//alert(modleid+"="+curmodleid+"="+type);
		$.get("<%=basePath%>htmlmodle/addmodle.do?htmlid=${HTML_ID}&type="+type+"&curmodleid="+curmodleid+"&modleid="+modleid,function(data,status){
			//alert(data);
		});
	}
	function showmodle(data){
		var jsondata =data;// $.parseJSON(data);
		var modleType = jsondata[0].modleType;
		var id = jsondata[0].id;
		var name = jsondata[0].name;
		var nums = jsondata[0].nums;
		var place = jsondata[0].place;
		var divClass = jsondata[0].divClass;
		var nums = jsondata[0].nums;
		var title = jsondata[0].title;
		var subhead = jsondata[0].subhead;
		//alert(modleType);
		var details = jsondata[0].htmlModleDetaillist;
		//alert(details);
		if(1==id){
			var html =	'<ul class="picList">';
			$.each(details, function(i, list){
				html = html+
				+'<li value="'+this.sort+'">'
				+'<img src="'+this.imgUrl+'" alt="" />'
				+'</li>';
			});	
			html = html +'</ul>';
			$("#modle1").html(html);
		}else{
		
		var html ="";
		if(1==modleType){
			$("#modlename"+id).html(name);
			$.each(details, function(i, list){
				var feetype = this.article.feeType+'';
				var fee = "<span class=\"vipPopularityRRE\">VIP</span>";
				if('0'==feetype){
					fee = "<span class=\"vipPopularityRRE\">免费</span>";
				}
			if(nums=='3' && 'freeAdmission'==divClass){
				$("#divmodle"+id).attr("class","freeAdmission myAllChi"); 
				$("#divmodle"+id).addClass('myAllChiAct');
				
					html= html
					+"<li>"
					+"<div class=\"freeAdmissionDe\">"
					+"	<img src=\""+this.article.bookCover+''+"\" alt=\"\" />"
					+ fee
					+"</div>"
					+"<h3>"+this.article.articleName+''+"</h3>"
					+"<p>"+this.article.author+''+"</p>"
					+"</li>";
				
			}else if(nums=='3' && 'vipPopularity'==divClass){
				$("#divmodle"+id).attr("class","vipPopularity myAllChi"); 
				$("#divmodle"+id).addClass('myAllChiAct');
				html= html
				+'<li class="clearfix">'
				+'<div class="vipPopularityL">'
				+'	<img src="'+this.article.bookCover+''+'" alt="" />'
				+'</div>'
				+'<div class="vipPopularityR">'
				+'	<h3>'+this.article.articleName+''+'</h3>'
				+'	<p>'+this.article.summary+''+'</p>'
				+'	<div class="clearfix vipPopularityRR clearfix">'
				+'		<h4>'+this.article.author+'</h4>'
				+'		<div class="clearfix">'
				+				fee
				+'			<span></span>'
				+'			<span>'+this.article.displayReaders+'人在看</span>'
				+'		</div>'
				+'	</div>'
				+'</div>'
				+'</li>';
			}else{
				$("#divmodle"+id).attr("class","vipPotenTial myAllChi"); 
				$("#divmodle"+id).addClass('myAllChiAct');
				html = html
				+"<li>"
				+"<div class=\"vipPotenTialDe\">"
				+"	<img src=\""+this.article.bookCover+"\" alt=\"\" />"
				+   fee
				+"</div>"
				+"<h3>"+this.article.articleName+"</h3>"
				+"<p>"+this.article.author+"</p>"
				+"</li>";
			}
			});
		}else{
			html = html +"<ul>";
			var detail1 ="";
			var detail2 = "";
			var detail3 = "";
			var detail4 = "";
			$.each(details, function(i, list){
				if(0==i){
					detail1 = list;
				}else if(1==i){
					detail2 = list;
				}else if(2==i){
					detail3 = list;
				}else if(3==i){
					detail4 = list;
				}
			});
				if('1'==nums){
					$("#divmodle"+id).attr("class","thematicTemplateSingleDe myAllChi"); 
					$("#modle"+id).attr("class","boyStyleDe"); 
					$("#divmodle"+id).addClass('myAllChiAct');
					if(''!=detail1){
					html = html 
					+'<h2>'+detail1.title+'</h2>'
					+'<p>'+detail1.subhead+'</p>'
					+'<img src="'+detail1.imgUrl+'" alt="" />';
					}
				}else if('2'==nums){
					$("#divmodle"+id).attr("class","thematicTemplateDoubleDe myAllChi"); 
					$("#modle"+id).attr("class","boyStyleAll clearfix"); 
					$("#divmodle"+id).addClass('myAllChiAct');
					html = html 
					+'<li class="clearfix">';
					if(''!=detail1){
						html = html 
					+'	<div>'
					+'		<h2>'+detail1.title+'</h2>'
					+'		<p>'+detail1.subhead+'</p>'
					+'		<img src="'+detail1.imgUrl+'" alt="" />'
					+'	</div>';
					}
					if(''!=detail2){
						html = html 
					+'	<div>'
					+'		<h2>'+detail2.title+'</h2>'
					+'		<p>'+detail2.subhead+'</p>'
					+'		<img src="'+detail2.imgUrl+'" alt="" />'
					+'	</div>';
					}
					html = html +'</li>';
				}else {
					$("#divmodle"+id).attr("class","thematicTemplateFourDe myAllChi"); 
					$("#modle"+id).attr("class","boyStyleAll clearfix"); 
					$("#divmodle"+id).addClass('myAllChiAct');
					html = html 
					+'<li class="clearfix">';
					if(''!=detail1){
						html = html 
					+'<div>'
					+'	<h2>'+detail1.title+'</h2>'
					+'	<p>'+ detail1.subhead+'</p>'
					+'	<img src="'+detail1.imgUrl+'" alt="" />'
					+'</div>';
					}
					if(''!=detail2){
						html = html 
					+'<div>'
					+'	<h2>'+detail2.title+'</h2>'
					+'	<p>'+ detail2.subhead+'</p>'
					+'	<img src="'+detail2.imgUrl+'" alt="" />'
					+'</div>';
					}
					html = html +'</li>'				
				+'<li class="clearfix">';
					if(''!=detail3){
						html = html 
					+'<div>'
					+'	<h2>'+detail3.title+'</h2>'
					+'	<p>'+ detail3.subhead+'</p>'
					+'	<img src="'+detail3.imgUrl+'" alt="" />'
					+'</div>';
					}
					if(''!=detail4){
						html = html 
					+'<div>'
					+'	<h2>'+detail4.title+'</h2>'
					+'	<p>'+ detail4.subhead+'</p>'
					+'	<img src="'+detail4.imgUrl+'" alt="" />'
					+'</div>';
					}
				html = html +'</li>';
				}
				html = html +'</ul>';
		}
		//alert(html);
		$("#modle"+id).html(html);
		}
	}
	//获取模板信息
	function getmodle(modleid){
		//alert("getmodle="+modleid);
		$.get("<%=basePath%>htmlmodle/getmodle.do?htmlid=${HTML_ID}&modleid="+modleid,function(data,status){
			//alert(data);
			if('0'==data){
				
			}else{
				var jsondata = $.parseJSON(data);
				showmodle(jsondata);
				// alert(jsondata);
				var modleType = jsondata[0].modleType;
				var name = jsondata[0].name;
				var nums = jsondata[0].nums;
				var place = jsondata[0].place;
				var divClass = jsondata[0].divClass;
				var nums = jsondata[0].nums;
				//var title = jsondata[0].title;
				//var subhead = jsondata[0].subhead;
				//alert(modleType);
				var details = jsondata[0].htmlModleDetaillist;
				//alert(details);
				if(1==modleType){
					$(".bookModuleTex ul").html("");
					$("#MODLENAME").val(name);
					
					$.each(details, function(i, list){
						//alert(i);
						var background='';
						if((i+1)%2==0){
							background='background: #fafafa;';
						}
						var fee = this.article.feeType;
						var fees = "免费";
						if(1==fee){
							fees="VIP";
						}
						var norn = "";
						if(this.article.channelType==1||this.article.channelType=='1'){
							norn = '女频';
						}else{
							norn = '男频';
						}
						var ar='<li style='+background+' class=\"clearfix\">'
							+'<div class="bookModuleTexOn">'+this.sort+'</div>'
							+'<div class="bookModuleTexTw">'+this.article.articleName+'</div>'
							+'<div class="bookModuleTexTh">'+this.article.id+'</div>'
							+'<div class="bookModuleTexFo">'+norn+'</div>'
							+'<div class="bookModuleTexFi">'+fees+'</div>'
							+'<div class="bookModuleTexSi">'+this.article.category+'</div>'
							+'<div class="bookModuleTexSe">'
							+'	 <a onclick=\"delarticle('+this.id+')\">删除</a> '
							+'</div>'
							+'</li>';
						$(".bookModuleTex ul").append(ar);
				 	});
				}else{
					$(".selectTemplateInput").show();
					var num = $("#titlenum").val();
					$("#tid1").html("图"+num+"主标题");
					$("#tid2").html("图"+num+"副标题");
					$("#tid3").html("图"+num+"链接");
					$("#titlenum").val(num);
					var titlenum = parseInt($("#titlenum").val())-1;
					var len = parseInt(details.length)-1;
					//alert("length="+details.length);
					//alert("titlenum="+titlenum);
					//alert("title="+details[titlenum].title);
					if(titlenum>len){
						$("#MODLETITLE").val("");
						$("#SUBHEAD").val("");
						$("#htmlUrl").val("");
						$("#img"+parseInt($("#titlenum").val())).attr('src',"static/read/images/myPic51.png"); 
					}else{
						$("#MODLETITLE").val(details[titlenum].title);
						$("#SUBHEAD").val(details[titlenum].subhead);
						$("#htmlUrl").val(details[titlenum].htmlUrl);
						$("#img"+parseInt($("#titlenum").val())).attr('src',details[titlenum].imgUrl); 
					}
					
				}
				
			}
		});
	}
	function searcharticle(){
		var searchkey = $("#searchkey").val();
		//alert(searchkey);
	}
	//进入查询书籍页面
	function addbook( ){
		var Id = $("#curmodleinfoid").val();
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="添加书籍";
		 diag.URL = "<%=basePath%>htmlmodle/getbook.do?htmlid=${HTML_ID}&modleid="+Id+"&tm="+new Date().getTime();
		 diag.Width = 670;
		 diag.Height = 610;
		 diag.CancelEvent = function(){ //关闭事件
			// if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 var aid = diag.innerFrame.contentWindow.document.getElementById('aid').value;
				 var htmlid = diag.innerFrame.contentWindow.document.getElementById('htmlid').value;
				 var modleid = diag.innerFrame.contentWindow.document.getElementById('modleid').value;
				 //alert(aid+"=="+modleid);
				 if(''!=aid){
				 	addarticle(modleid,aid);
				 }
				 diag.close();
			// }
			//diag.close();
		 };
		 diag.show();
	}
	//删除书籍
	function delarticle(detailid){
		var modleid = $("#curmodleinfoid").val();
		//alert(modleid+"=="+detailid);
	   $.get("<%=basePath%>htmlmodle/delmodledetail.do?htmlid=${HTML_ID}&modleid="+modleid+"&detailid="+detailid,function(data,status){
		//alert(data);
		if('0'==data){
			
		}else{
			var jsondata = $.parseJSON(data);
			// alert(jsondata);
			var modleType = jsondata[0].modleType;
			var name = jsondata[0].name;
			var nums = jsondata[0].nums;
			var place = jsondata[0].place;
			var divClass = jsondata[0].divClass;
			var nums = jsondata[0].nums;
			//alert(modleType);
			var details = jsondata[0].htmlModleDetaillist;
			//alert(details);
			if(1==modleType){
				$(".bookModuleTex ul").html("");
				$("#MODLENAME").val(name);
				
				$.each(details, function(i, list){
					//alert(i);
					var background='';
					if((i+1)%2==0){
						background='background: #fafafa;';
					}
					var fee = this.article.feeType;
					var fees = "免费";
					if(1==fee){
						fees="VIP";
					}
					
					var ar='<li style='+background+' class=\"clearfix\">'
						+'<div class="bookModuleTexOn">'+this.sort+'</div>'
						+'<div class="bookModuleTexTw">'+this.article.articleName+'</div>'
						+'<div class="bookModuleTexTh">'+this.article.id+'</div>'
						+'<div class="bookModuleTexFo"> </div>'
						+'<div class="bookModuleTexFi">'+fees+'</div>'
						+'<div class="bookModuleTexSi">'+'科幻'+'</div>'
						+'<div class="bookModuleTexSe">'
						+'	 <a onclick=\"delarticle( '+this.id+')\">删除</a> '
						+'</div>'
						+'</li>';
						//alert(ar);
					$(".bookModuleTex ul").append(ar);
			 	});
			}else{
				
				
			}
		}
	});
	}
	//添加书籍
	function addarticle(modleid,articleid){
		//alert(modleid+"===articleid="+articleid);
	   $.get("<%=basePath%>htmlmodle/addmodledetail.do?htmlid=${HTML_ID}&modleid="+modleid+"&articleid="+articleid,function(data,status){
		//alert(data);
		if('0'==data){
			
		}else{
			var jsondata = $.parseJSON(data);
			// alert(jsondata);
			var modleType = jsondata[0].modleType;
			var name = jsondata[0].name;
			var nums = jsondata[0].nums;
			var place = jsondata[0].place;
			var divClass = jsondata[0].divClass;
			var nums = jsondata[0].nums;
			//alert(modleType);
			var details = jsondata[0].htmlModleDetaillist;
			//alert(details);
			if(1==modleType){
				$(".bookModuleTex ul").html("");
				$("#MODLENAME").val(name);
				
				$.each(details, function(i, list){
					//alert(i);
					var background='';
					if((i+1)%2==0){
						background='background: #fafafa;';
					}
					var feetype = this.article.feeType+'';
					var fee = "VIP";
					if('0'==feetype){
						fee = "免费";
					}
					var ar='<li style='+background+' class=\"clearfix\">'
						+'<div class="bookModuleTexOn">'+this.sort+'</div>'
						+'<div class="bookModuleTexTw">'+this.article.articleName+'</div>'
						+'<div class="bookModuleTexTh">'+this.article.id+'</div>'
						+'<div class="bookModuleTexFo"> </div>'
						+'<div class="bookModuleTexFi">'+fee+'</div>'
						+'<div class="bookModuleTexSi">'+'科幻'+'</div>'
						+'<div class="bookModuleTexSe">'
						+'	 <a onclick=\"delarticle( '+this.id+')\">删除</a> '
						+'</div>'
						+'</li>';
					$(".bookModuleTex ul").append(ar);
			 	});
				getmodle(modleid);
			}else{
				
				
			}
		}
	});
	}
	//更新书籍模块信息
	function  updatearticlemodle(name,nums,divclass){
		//alert(name+"="+nums+"="+divclass);
		var modleid = $("#curmodleinfoid").val();
		$.get("<%=basePath%>htmlmodle/updatearticlemodle.do?htmlid=${HTML_ID}&modleid="+modleid+"&nums="+nums+"&divclass="+divclass+"&name="+name,function(data,status){
			//alert(data);
			getmodle(modleid);
			if('0'==data){
				
			}else{
				
			}
		});
	}
	function getname(){
		var MODLENAME = $("#MODLENAME").val();
		//alert(MODLENAME);
		updatearticlemodle(MODLENAME,'','');
	}
	function showbanner(num){
		if(num==1){
			$("#banner2").hide();
			$("#bigbanner").hide();
		}else if(num==2){
			$("#banner2").show();
			$("#bigbanner").hide();
		}else if(num==4){
			$("#banner2").show();
			$("#bigbanner").show();
		}
		//showtile(1);
	}
	function showtile(num){
		$(".selectTemplateInput").show();
		$("#tid1").html("图"+num+"主标题");
		$("#tid2").html("图"+num+"副标题");
		$("#tid3").html("图"+num+"链接");
		$("#titlenum").val(num);
		var modleid = $("#curmodleinfoid").val();
		getmodle(modleid);
	}
	
	$('#imgfile1').change(function(){
		uploadPic(1);
	});
	$('#imgfile2').change(function(){
		uploadPic(2);
	});
	$('#imgfile3').change(function(){
		uploadPic(3);
	});
	$('#imgfile4').change(function(){
		uploadPic(4);
	});
	function uploadPic(num){
        var pic = $('#imgfile'+num)[0].files[0];
        //alert("pic="+pic);
        var fd = new FormData();
        fd.append('file', pic);
        var modleid = $("#curmodleinfoid").val();
        $.ajax({
            url:"<%=basePath%>htmlmodle/uploadfile?htmlid=${HTML_ID}&modleid="+modleid+"&num="+num,
            type:"post",
            // Form数据
            data: fd,
            cache: false,
            contentType: false,
            processData: false,
            success:function(data){
            	$("#img"+num).attr('src',data); 
            	//alert(data);
                alert("成功");
                getmodle(modleid);
            }
        });
                        
    }
	function updatetile(){
		var title = $("#MODLETITLE").val();
		updatemodledetail(title,'','');
	}
	function updatesubhead(){
		var title = $("#SUBHEAD").val();
		updatemodledetail('',title,'');
	}
	function updatehtmlUrl(){
		var htmlUrl = $("#htmlUrl").val();
		updatemodledetail('','',htmlUrl);
	}
	//更新专题模块信息
	function  updatemodledetail(title,subhead,htmlUrl){
		var num = $("#titlenum").val();
		htmlUrl = encodeURI(htmlUrl);
		var modleid = $("#curmodleinfoid").val();
		//alert("curmodleinfoid="+modleid+"=="+title+"="+num +"="+subhead+"="+htmlUrl);
		$.get("<%=basePath%>htmlmodle/updatemodledetail.do?htmlid=${HTML_ID}&modleid="+modleid+"&num="+num+"&title="+title+"&subhead="+subhead+"&htmlUrl="+htmlUrl,function(data,status){
			//alert(data);
			getmodle(modleid);
			if('0'==data){
				
			}else{
				
			}
		});
	}
	
	function gethbimg(num){
		//alert("hbimg="+num);
		var pic = $('#hbimg'+num)[0].files[0];
        //alert("pic="+pic);
        var fd = new FormData();
        fd.append('file', pic);
        var modleid = 1;
        $.ajax({
            url:"<%=basePath%>htmlmodle/uploadfile?htmlid=${HTML_ID}&modleid="+modleid+"&num="+num,
            type:"post",
            // Form数据
            data: fd,
            cache: false,
            contentType: false,
            processData: false,
            success:function(data){
            	$("#img"+num).attr('src',data); 
            	//alert(data);
                alert("成功");
                getmodle(1);
            }
        });
	}
	function gethburl(num){
		var hburl = $("#hburl"+num).val();
		var modleid = $("#curmodleinfoid").val();
		$.get("<%=basePath%>htmlmodle/updatemodledetail.do?htmlid=${HTML_ID}&modleid="+modleid+"&num="+num+"&title=&subhead=&htmlUrl="+hburl,function(data,status){
			//alert(data);
			if('0'==data){
				
			}else{
				
			}
		});
	}
	$("#hbnum").val(<%=hbnum%>);
	 
	function delmodledetailhb(num){
		var modleid = $("#curmodleinfoid").val();
		$.get("<%=basePath%>htmlmodle/delmodledetailhb.do?htmlid=${HTML_ID}&modleid="+modleid+"&num="+num,function(data,status){
			//alert(data);
			if('0'==data){
				
			}else{
				
			}
		});
	}
	function finishhtml(){
		$.get("<%=basePath%>htmlmodle/finishhtml?htmlid=${HTML_ID }",function(data,status){
			//alert(data);
			window.location.href='<%=basePath%>htmlmodle/lookhtml?HTML_ID=${HTML_ID }';
		});
	}
	
	function show( ){
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="浏览页面";
		 diag.URL = "<%=basePath%>htmlmodle/showhtml?HTML_ID=${HTML_ID }&tm="+new Date().getTime();
		 diag.Width = 400;
		 diag.Height = 710;
		 diag.CancelEvent = function(){ //关闭事件
				 diag.close();
		 };
		 diag.show();
	}
</script>



</body>
</html>