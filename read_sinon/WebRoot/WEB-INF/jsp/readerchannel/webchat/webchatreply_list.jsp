<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<title>自动回复</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/basicSetting.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
		var replyid = $("#replyid").val();
		if($("#CONTENT").val()==""){
			$("#CONTENT").tips({
				side:3,
	            msg:'请输入回复内容',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTENT").focus();
			return false;
		}
		var CONTENT=$("#CONTENT").val();
	 	//alert(CONTENT);
		$.post("<%=basePath%>channelwebchatreply/savereply.do",
		  {
			WEBCHATREPLY_ID:replyid,
			CONTENT:CONTENT
		  },
		  function(data,status){
		    //alert("Data: " + data + "\nStatus: " + status);
		    setTimeout("self.location=self.location",10);
		  });
	}
	
</script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">基础设置</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">自动回复</a>
	</div>
	<div class="automaticReplyDe">
		<div class="necessarySettingDeTop clearfix">
			<p>关注回复</p>
			<a href="javascript:void(0)" title=""><span>查看教程</span></a>
		</div>
		<div class="automaticReplyDeTexed">
			<div class="automaticReplyDeTexed1 clearfix">
				<span>回复内容</span>
				<p id="pcontent">${reply.CONTENT }</p>
			</div>
			<div class="automaticReplyDeTexed2 clearfix">
				<a   href="javascript:void(0)" title="">设置回复语</a>
			</div>
		</div>
		<input type="hidden" id="replyid" name="replyid" value="${reply.WEBCHATREPLY_ID }"/>
		<div class="automaticReplyDeTex">
			<div class="automaticReplyDeTex1 clearfix">
				<span>回复内容</span>
				<div><textarea id="CONTENT" name="CONTENT">${reply.CONTENT }</textarea></div>
			</div>
			<div class="automaticReplyDeTex2 clearfix">
				<a href="javascript:save()" title="">完&nbsp;&nbsp;成</a>
				<span>取&nbsp;&nbsp;消</span>
			</div>
		</div>
		<div class="necessarySettingDeTop clearfix">
			<p style="margin-top: 30px;">关键词回复</p>
		</div>
		<form action="channelwebchatreply/list.do" method="post" name="Form" id="Form">
		<div class="automaticReplyDeTime clearfix">
			<div class="clearfix">
				<input name="ctime" id="ctime" value="${pd.ctime}" readonly="readonly" />
				<img src="static/readchannel/images/myPic13.png" alt="" />
			</div>
			<a   href="javascript:add()" title=""><span>新&nbsp;&nbsp;增</span></a>
		</div>
		<!-- 检索  -->
	
		<div class="automaticReplyDeTit clearfix">
			<span style="margin-left: 19px; width: 155px;">添加日期</span>
			<span style="width: 87px;">关键词</span>
			<span style="width: 314px;">回复内容</span>
			<span style="width: 179px;">失效时间</span>
			<span style="width: 129px;">有效时间</span>
			<span>管理操作</span>
		</div>
		<div class="automaticReplyDeAll">
			<ul>
			<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
						<li class="clearfix" <c:if test="${(vs.index+1)%2==0 }">style="background: #fafafa;"</c:if>>
							<div class="automaticReplyDeAll1">${var.CREATE_TIME }</div>
							<div class="automaticReplyDeAll2">${var.KEYWORDS }</div>
							<div class="automaticReplyDeAll3 clearfix">
								<p>${var.CONTENT }</p>
								<c:if test="${(fn:length(var.CONTENT))>17}">
								<a href="javascript:lookcontent('${var.WEBCHATREPLY_ID }')" title="">查看</a>
								</c:if>
							</div>
							<div class="automaticReplyDeAll4">
							<c:if test="${ empty var.LOSE_TIME }">
							--
							</c:if>
							<c:if test="${not empty var.LOSE_TIME }">
								${var.LOSE_TIME }
							</c:if>
							
							</div>
							<div class="automaticReplyDeAll5">${var.VALID_HOURS }</div>
							<div class="automaticReplyDeAll6">
								<i>操作</i>
								<div class="automaticReplyDeAllD">
									<a href="javascript:edit('${var.WEBCHATREPLY_ID }')" title="">编辑</a>
									<a href="javascript:del('${var.WEBCHATREPLY_ID }')" title="">删除</a>
								</div>
							</div>
						</li>
						</c:forEach>
					</c:when>
			</c:choose>
			</ul>
		</div>
		
		<div style="margin-left: 30px;" class="flipTwo clearfix">
			${page.pageStr}
		</div>
		</form>
	</div>
	<script src="static/readchannel/layDate-v5.0.8/laydate/laydate.js"></script>
	<script>
	function lookcontent(id){
		var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="回复内容";
		 diag.URL =  '<%=basePath%>channelwebchatreply/goLook.do?WEBCHATREPLY_ID='+id;
		 diag.Width = 560;
		 diag.Height = 373;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 diag.close();
			}
			diag.close();
		 };
		 diag.show();
	}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="新增";
		 diag.URL = '<%=basePath%>channelwebchatreply/goAdd.do';
		 diag.Width = 560;
		 diag.Height = 392;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 if('${page.currentPage}' == '0'){
					 setTimeout("self.location=self.location",100);
				 }else{
					 nextPage(${page.currentPage});
				 }
			}
			diag.close();
		 };
		 diag.show();
	}
	
	//删除
	function del(Id){
		//bootbox.confirm("确定要删除吗?", function(result) {
			//if(result) {
				var url = "<%=basePath%>channelwebchatreply/delete.do?WEBCHATREPLY_ID="+Id+"&tm="+new Date().getTime();
				$.get(url,function(data){
					nextPage(${page.currentPage});
				});
			//}
		//});
	}
	
	//修改
	function edit(Id){
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="编辑";
		 diag.URL = '<%=basePath%>channelwebchatreply/goEdit.do?WEBCHATREPLY_ID='+Id;
		 diag.Width = 560;
		 diag.Height = 392;
		 diag.CancelEvent = function(){ //关闭事件
			 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 nextPage(${page.currentPage});
			}
			diag.close();
		 };
		 diag.show();
	}
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("iframe"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		$('.automaticReplyDeTexed2 a').click(function(){
			$('.automaticReplyDeTexed').hide();
			$('.automaticReplyDeTex').show();
		})
		$('.automaticReplyDeTex2 span').click(function(){
			$('.automaticReplyDeTexed').show();
			$('.automaticReplyDeTex').hide();
		})
		laydate.render({
		  elem: '#ctime'
		  ,range: true
		});
		$(window).click(function(){
			$('.automaticReplyDeAll6 div').hide();
		})
		$('.automaticReplyDeAll6 i').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.automaticReplyDeAllD').hide();
			e.stopPropagation();
		})
		$('.automaticReplyDeAll6 div a').click(function(e){
			e.stopPropagation();
		})
		
		 
	</script>
</body>
</html>
