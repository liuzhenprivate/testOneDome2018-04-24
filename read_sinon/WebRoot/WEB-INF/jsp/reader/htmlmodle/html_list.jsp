<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
	<title>模块配置（页面配置）</title>
	<base href="<%=basePath%>">
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/moduleConfiguration.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">模板配置</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">页面配置</a>
	</div>
	<div class="pageConfigurateDe">
		<div class="pageConfigurateDeTop clearfix">
		<!-- 检索  -->
			<form action="html/list.do" method="post" name="Form" id="Form">
			<div class="pageConfigurateDeTopL clearfix">
				<input id="sendtime" name="sendtime" readonly="readonly" value="${pd.sendtime }"/>
				<img src="static/read/images/myPic13.png" alt="" />
			</div>
			<div class="pageConfigurateDeTopC clearfix">
			<input type="hidden" name="HTML_TYPE" id="HTML_TYPE" value="${pd.HTML_TYPE }">
				<p>
				<c:choose>
					<c:when test="${not empty pd.HTML_TYPE}">
						<c:if test="${pd.HTML_TYPE == 1}">
							系统页面
						</c:if>
						<c:if test="${pd.HTML_TYPE == 2}">
							其他页面
						</c:if>
					</c:when>
					<c:otherwise>
						页面类型
					</c:otherwise>
				</c:choose>
				</p>
				<img src="static/read/images/myPic13.png" alt="" />
				<ul>
					<li onclick="htmlType('');">全部页面</li>
					<li onclick="htmlType('1');">系统页面</li>
					<li onclick="htmlType('2');">其他页面</li>
				</ul>
			</div>
			<div class="pageConfigurateDeTopC clearfix">
				<input type="hidden" name="STATE" id="STATE" value="${pd.STATE }">
				<p>
				<c:choose>
					<c:when test="${not empty pd.STATE}">
						<c:if test="${pd.STATE == 1}">
							已上线
						</c:if>
						<c:if test="${pd.STATE == 2}">
							未上线
						</c:if>
					</c:when>
					<c:otherwise>
						页面状态
					</c:otherwise>
				</c:choose>
				</p>
				<img src="static/read/images/myPic13.png" alt="" />
				<ul>
					<li onclick="htmlState('');">全部状态</li>
					<li onclick="htmlState('1');">已上线</li>
					<li onclick="htmlState('2');">未上线</li>
				</ul>
			</div>
			</form>
			<div class="pageConfigurateDeTopR clearfix"  >
				<span class="pageConfigurateDeTopR2" onclick="add()">
					<i>新增页面</i>
				</span>
			</div>
		</div>
		<div class="pageConfigurateTit clearfix">
			<span style="width: 137px; margin-left: 19px;">名称</span>
			<span style="width: 183px;">创建时间</span>
			<span style="width: 172px;">总访问量</span>
			<span style="width: 157px;">页面类型</span>
			<span style="width: 165px;">页面状态</span>
			<span>管理操作</span>
		</div>
		<div class="pageConfigurateTex">
			<ul>
			<c:forEach items="${varList}" var="var" varStatus="vs">
				<li class="clearfix">
					<div class="pageConfigurateTex1">${var.NAME}</div>
					<div class="pageConfigurateTex2">${var.CREATE_TIME}</div>
					<div class="pageConfigurateTex3">${var.PVS}</div>
					<div class="pageConfigurateTex4">
					<c:if test="${var.HTML_TYPE==1}">
					系统页面
					</c:if>
					<c:if test="${var.HTML_TYPE==2}">
					其他页面
					</c:if>
					</div>
					<div class="pageConfigurateTex5">
					<c:if test="${var.STATE==1}">
					已上线
					</c:if>
					<c:if test="${var.STATE==2}">
					未上线
					</c:if>
					</div>
					 
					<div class="pageConfigurateTex7 clearfix">
						<i>操作</i>
						<div class="pageConfigurateTex7Div">
							<a class="pageConfigurateTex7DivA" href="htmlmodle/lookhtml?HTML_ID=${var.HTML_ID }">编辑</a>
							<c:if test="${var.HTML_TYPE!=1}">
							<a href="javascript:editname('${var.HTML_ID }')">修改名称</a>
								<c:if test="${var.STATE==1}">
									<a href="javascript:etidstate('${var.HTML_ID }','2')">下线</a>
								</c:if>
								<c:if test="${var.STATE==2}">
									<a href="javascript:etidstate('${var.HTML_ID }','1')">上线</a>
									<a>删除</a>
								</c:if>
							</c:if>
						</div>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
		 
		<div class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
	</div>
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript">
		function etidstate(id,state){
			$.get("<%=basePath%>html/editstate.do?HTML_ID="+id+"&STATE="+state,function(data,status){
			});
			location.reload();
		}
		function htmlType(data){
			$('#HTML_TYPE').val(data);
			//alert($('#HTML_TYPE').val());
			$('#Form').submit();
		}
		function htmlState(data){
			$('#STATE').val(data);
			//alert($('#STATE').val());
			$('#Form').submit();
		}
	</script>
	<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("Thunder"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		
		laydate.render({
		  elem: '#sendtime'
		  ,range: true
		});
		
		
		$('.pageConfigurateDeTopC').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.pageConfigurateDeTopC ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$(this).parent('ul').siblings('p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.pageConfigurateDeTopC').find('ul').hide();
			$('.pageConfigurateDeTopR2 ul').hide();
			$('.pageConfigurateTex7 div').hide();
		})
		
		$('.pageConfigurateDeTopR2').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		
		$('.pageConfigurateTex7 i').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.pageConfigurateTex7Div').hide();
			e.stopPropagation();
		})
		$('.pageConfigurateTex7 div a').click(function(e){
			e.stopPropagation();
			$(this).parent().hide();
		})
		
		function add(){
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增页面";
			 diag.URL = '<%=basePath%>html/goAdd.do';
			 diag.Width = 560;
			 diag.Height = 150;
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
		function editname(id){
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="修改名称";
			 diag.URL = '<%=basePath%>html/goEdit.do?HTML_ID='+id;
			 diag.Width = 560;
			 diag.Height = 150;
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
	</script>

</body>
</html>