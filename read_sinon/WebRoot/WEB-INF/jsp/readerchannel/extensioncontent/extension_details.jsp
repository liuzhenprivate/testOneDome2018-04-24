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
	<title>文案</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/promotionManagement.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/file.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">推广管理</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" href="<%=basePath%>extensioncontent/list.do" title="">推广列表</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">新建推广</a>
	</div>
	<input id="CHAPTER_NO" name="CHAPTER_NO" type="hidden" value="${pd.CHAPTER_NO }"/>
	<input id="ARTICLE_ID" name="ARTICLE_ID" type="hidden" value="${pd.ARTICLE_ID }"/>
	<input id="ARTICLE_CHAPTER_ID" name="ARTICLE_CHAPTER_ID" value="${pd.ARTICLE_CHAPTER_ID }"/>
	<div class="selectedChapters clearfix">
		<div class="fileL">
			<div class="fileLDe">
				<div style=" height: 450px; overflow-y: scroll;">
				<h1 id="title">请选择标题</h1>
					
						<section class="fileLDeImg">
							<img id="cover" src="<%=basePath%>static/readchannel/images/myPic71.png" alt="" />
						</section>
						<section  id="">
							<c:choose>
							<c:when test="${not empty varList}">
							<c:forEach items="${varList}" var="var" varStatus="vs">
								<div class="fileLDeAll">
									<h2>${var.CHAPTER_NAME }</h2>
									<p>${var.txt}</p>
								</div>
							</c:forEach>
							</c:when>
							</c:choose>
						</section>
						<div class="fileLDeBtn" id="asdfgg">
							点击下方【阅读原文】，后续剧情高潮不断!
						</div>
					</div>
			</div>
			<div class="fileLBtn clearfix">
				<a href="javascript:void(0)" title="">全屏查看</a>
				<a href="javascript:void(0)" title="">复制标题</a>
				<!-- <a href="javascript:void(0)" class="copyBtn" data-clipboard-text="来呀~快活呀，反正有~大把头发~">复制内容</a> -->
				<a class="copy"  href="javascript:void(0)"  data-clipboard-action="copy"  data-clipboard-target="#asdfgg">复制内容</a>
			</div>
		</div>
		<div class="fileR">
			<div class="fileRT clearfix">
				<div class="clearfix">
					<span id="copywritingHeadlines" onclick="linkSrc('copywritingHeadlines')" name="<%=basePath%>extensioncontent/goExtensiontitle.do" style="border-top-left-radius: 4px; border-bottom-left-radius: 4px; border-right: 1px solid #cccccc;" class="fileRTAct">文案标题</span>
					<span id="copywriting" onclick="linkSrc('copywriting')" name="<%=basePath%>extensioncontent/goExtensioncover.do" style="border-right: 1px solid #cccccc;">文案封面</span>
					<span id="textTemplate" onclick="linkSrc('textTemplate')" name="<%=basePath%>extensioncontent/goExtensionoriginal.do" style="border-right: 1px solid #cccccc;">正文模板</span>
					<span id="bootTemplate" onclick="linkSrc('bootTemplate')" name="<%=basePath%>extensioncontent/goExtensiontemplate.do" style="border-top-right-radius: 4px; border-bottom-right-radius: 4px;">原文引导</span>
				</div>
				<a onClick="fun3()" href="javascript:void(0)" title=""><i>推广链接</i></a>
			</div>
			<div class="fileRDe">
				<iframe id="fileRDe" src="<%=basePath%>extensioncontent/goExtensiontitle.do" width="100%" height="100%" id="faker" frameborder="no" scrolling="no" allowtransparency="yes"></iframe>
			</div>
		</div>
	</div>
	<script type='text/javascript' src="https://cdn.bootcss.com/clipboard.js/1.7.1/clipboard.js"></script>
	<script type="text/javascript" src="http://cj.qirexiaoshuo.com/static/js/rasterizeHTML/rasterizeHTML.allinone.js"></script>
	<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
	<script type="text/javascript">
		function fun3(){
			var CHAPTER_NO = $("#CHAPTER_NO").val();
			var ARTICLE_ID = $("#ARTICLE_ID").val();
			var TITLE = $("#title").text();
			var COVER = $("#cover")[0].src;
			var CSSS = 'selectedChapters clearfix,fileL,fileLDe,height:450px;overflow-y:scroll;,fileLDeImg,fileLDeAll';
			var CLICK_TITLE_CSS = 'fileLDeBtn';
			var CLICK_TITLE = $(".fileLDeBtn").text();
			var diag = new top.Dialog();
			var ARTICLE_CHAPTER_ID = $('#ARTICLE_CHAPTER_ID').val();
			diag.URL = "<%=basePath%>extensioncontent/goExtensiongenerate.do?ARTICLE_ID="+ARTICLE_ID+"&CHAPTER_NO="+CHAPTER_NO+"&CSSS="+CSSS+"&TITLE="+TITLE+"&COVER="+COVER+"&CLICK_TITLE_CSS="+CLICK_TITLE_CSS+"&CLICK_TITLE="+CLICK_TITLE+"&ARTICLE_CHAPTER_ID="+ARTICLE_CHAPTER_ID;
			diag.Title = "推广链接";
			diag.Width = 560;
			diag.Height = 360;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 setTimeout("self.location=self.location",100);
					 }
				}
				diag.close();
			 };
			diag.show();
		}
		
		function addtitle(data){
			$("#title").text(data);
		}
		function pupcover(data){
			$("#cover").attr("src",data);
		}
	</script>
	<script>
		var clipboard = new Clipboard('.copy');
		    clipboard.on('success', function(e) {
		        e.clearSelection();
	    });
		$('.fileRT div span').click(function(){
			$(this).addClass('fileRTAct').siblings().removeClass('fileRTAct');
		});
		
		function linkSrc(id){
			var src=$('#'+id).attr('name');
			$('.fileRDe iframe').attr('src',src);
		}
		function reinitIframe() {           
		     var iframe = document.getElementById("fileRDe");           
		     try {               
		            var bHeight =iframe.contentWindow.document.body.scrollHeight;               
		            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;              
		            var height = Math.max(bHeight, dHeight);            
		            iframe.height = height;
		        } catch (ex) { }       
		}
		window.setInterval("reinitIframe()", 100);
		
	</script>
</body>
</html>