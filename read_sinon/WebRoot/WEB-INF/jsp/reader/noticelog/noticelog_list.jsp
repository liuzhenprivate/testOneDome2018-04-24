<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>数据统计公告管理</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/dataStatistics.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
		<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("iframe"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		$(function(){
			$('#pushmode').children('li').bind('click', function(){
				//alert($(this).attr('value'));
				var state =$(this).attr('value');
				if(state !='-1'){
					$("#STATE").val($(this).attr('value'));
				}
				if(state =='-1'){
					$("#STATE").val('');
				}
				searchnotcie();
			});
		});
		
		function searchnotcie(){
			//alert("searchnotcie");
			//alert($("#STATE").val());
			$("#Form").submit();
		}
	</script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">数据统计</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">公告管理</a>
	</div>
	<!-- 检索  -->
	<form action="noticelog/list.do" method="post" name="Form" id="Form">
	<input type="hidden" id="PUSHMODE" name="PUSHMODE" value="${pd.PUSHMODE }"/>
	<input type="hidden" id="STATE" name="STATE" value="${pd.STATE }"/>
	<div class="announcementManagementDe">
		<div class="announcementManagementDeTop clearfix">
			<div class="announcementManagementDeTopL clearfix">
				<input type="text" id="sendtime" name="sendtime" readonly="readonly" value="${pd.sendtime }" 
				onchange="searchnotcie()"/>
				<img src="static/read/images/myPic13.png" alt="" />
			</div>
			<div class="announcementManagementDeTopC clearfix">
				<p id="pushmodetitle">推送状态</p>
				<img src="static/read/images/myPic13.png" alt="" />
				<ul id="pushmode">
					<li value="-1"> 全部状态 </li>
					<li value="0"> 等待推送 </li>
					<li value="1"> 已删除 </li>
					<li value="2"> 取消推送 </li>
					<li value="3"> 已推送 </li>
				</ul>
				
			</div>
			<a href="javascript:add()" title="新建公告" class="announcementManagementDeBodyB1A"><span>新建公告</span></a>
		</div>
		<div class="announcementManagementDeBodyT clearfix">
			<span style="width: 140px; margin-left: 19px;">推送日期</span>
			<span style="width: 110px;">管理员类型</span>
			<span style="width: 96px;">管理员</span>
			<span style="width: 108px;">推送标题</span>
			<span style="width: 305px;">内容预览</span>
			<span style="width: 107px;">公告状态</span>
			<span>管理操作</span>
		</div>
		<div class="announcementManagementDeBodyB">
			<ul>
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<li class="clearfix">
								<div style="width: 140px; margin-left: 19px;">${var.SEND_TIME}</div>
								<div style="width: 110px;">公告管理</div>
								<div style="width: 96px;">${var.USERNAME }</div>
								<div style="width: 108px;">${var.TITLE }</div>
								<div style="width: 305px;" class="clearfix">
									<p>${var.CONTENT }</p>
									<c:if test="${(fn:length(var.CONTENT))>17}">
									<span onclick="lookcontent('${var.NOTICELOG_ID}')">展开</span>
									</c:if>
								</div>
								<div style="width: 107px;">
									<c:choose>
										<c:when test="${var.STATE=='0'}">
											等待推送
										</c:when>
										<c:when test="${var.STATE=='1'}">
											<font color="red">已删除</font>
										</c:when>
										<c:when test="${var.STATE=='2'}">
											取消推送
										</c:when>
										<c:when test="${var.STATE=='3'}">
											已推送
										</c:when>
										<c:otherwise>
											没有相关数据
										</c:otherwise>
									</c:choose>
								</div>
								<div class="clearfix">
								<c:choose>
									<c:when test="${var.STATE=='0'}">
										<i>操作</i>
										<div class="announcementManagementDeBodyB1">
											<a  onclick="editnotice(${var.NOTICELOG_ID })">编辑</a> 
											<a  onclick="editnoticestate(${var.NOTICELOG_ID },3)">立即推送</a>
											<a  onclick="editnoticestate(${var.NOTICELOG_ID },2)">取消推送</a> 
											<a title="删除" onclick="editnoticestate(${var.NOTICELOG_ID},1);">删除</a>
										</div>
									</c:when>
									<c:when test="${var.STATE=='1'}">
										<font color="red">已删除</font>
									</c:when>
									<c:when test="${var.STATE=='2'}">
										<i>操作</i>
										<div class="announcementManagementDeBodyB1">
											<a  onclick="editnotice(${var.NOTICELOG_ID })">编辑</a> 
											<a  onclick="editnoticestate(${var.NOTICELOG_ID },3)">立即推送</a>
											<a title="删除" onclick="editnoticestate(${var.NOTICELOG_ID},1);">删除</a>
										</div>
									</c:when>
									<c:otherwise>
									<i>操作</i>
										<div class="announcementManagementDeBodyB1">
											<a title="取消推送" onclick="editnoticestate(${var.NOTICELOG_ID },2)">取消推送</a> 
											<a title="删除" onclick="editnoticestate(${var.NOTICELOG_ID},1);">删除</a>
										</div>
									</c:otherwise>
								</c:choose>
									
								</div></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center">没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
	</div>
	</form>
	
	<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
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
		function lookcontent(id){
			var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="公告内容";
			 diag.URL =  '<%=basePath%>noticelog/goLook.do?NOTICELOG_ID='+id;
			 diag.Width = 560;
			 diag.Height = 356;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 diag.close();
				}
				diag.close();
			 };
			 diag.show();
		}
		
		$(function(){
			var pmode = '${pd.STATE}';
			//alert(STATE);
			/**
			if(''==pmode){
				$("#pushmode").prepend("<li value=\"-1\"> 全部状态 </li>");
				$("#pushmode").prepend("<li value=\"0\"> 等待推送 </li>");
				$("#pushmode").prepend("<li value=\"1\"> 已删除 </li>");
				$("#pushmode").prepend("<li value=\"2\"> 取消推送 </li>");
				$("#pushmode").prepend("<li value=\"3\"> 已推送 </li>");
			}else 
				**/
			if('0'==pmode){
				$("#pushmodetitle").html("等待推送");
			}else if('1'==pmode){
				$("#pushmodetitle").html("已删除");
			}else if('2'==pmode){
				$("#pushmodetitle").html("取消推送");
			}else if('3'==pmode){
				$("#pushmodetitle").html("已推送");
			}else if('-1'==pmode){
				$("#pushmodetitle").html("全部状态");
			}
			 
			
		});
		
		function editnoticestate(id,state){
			 
			$.get("<%=basePath%>noticelog/editstate.do?NOTICELOG_ID="+id+"&STATE="+state,function(data,status){
				location.reload();
			});
				 
		}
		//新增
		function add(){
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增公告";
			 diag.URL = '<%=basePath%>noticelog/goAdd.do';
			 diag.Width = 560;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					//alert(1);
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
		function editnotice(Id){
			var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑公告";
			 diag.URL = '<%=basePath%>noticelog/goEdit.do?NOTICELOG_ID='+Id;
			 diag.Width = 560;
			 diag.Height = 355;
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
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					$.get("<%=basePath%>noticelog/delete.do?NOTICELOG_ID="+Id,function(data,status){
						location.reload();
					});
				}
			});
		}
	
		
		laydate.render({
		  elem: '#sendtime'
		  ,range: true
		});
		
		
		$('.announcementManagementDeTopC').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.announcementManagementDeTopC ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$('.announcementManagementDeTopC p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.announcementManagementDeTopC').find('ul').hide();
			$('.announcementManagementDeBodyB ul li>div div').hide();
		})
		
		$('.announcementManagementDeBodyB ul li div i').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.announcementManagementDeBodyB1').hide();
			e.stopPropagation();
		})
		$('.announcementManagementDeBodyB ul li>div div a').click(function(e){
			e.stopPropagation();
		})
		$('.announcementManagementDeBodyB1 a').click(function(){
			$(this).parent().hide();
		})
		 
	</script>
</body>
</html>