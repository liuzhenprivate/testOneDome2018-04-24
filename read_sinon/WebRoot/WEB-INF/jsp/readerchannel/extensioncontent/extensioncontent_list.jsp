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
	<title>推广管理</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/promotionManagement.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<style type="text/css">
   .wrapper {position: relative;}
   .textarea {position: absolute;top: 0;left: 0;opacity: 0;z-index: -10;}
</style>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">推广管理</a>
		<img src="static/readchannel/images/myPic12.png" alt="" />
		<a class="announcementManagementTop3" style="color: #999999;" href="javascript:void(0)" title="">推广列表</a>
	</div>
	<div class="promotionManagementDe">
		<form action="<%=basePath%>extensioncontent/list.do" method="post" id="eForm" name="eForm">
		<div class="rechargeManagementDeSel clearfix">
			<div class="rechargeManagementDeSelL clearfix">
				<div class="rechargeManagementDeSelL1 clearfix">
					<input id="names" name="names" value="${pd.names }" />
					<p>输入书籍或推广标题搜索</p>
					<img src="static/readchannel/images/myPic26.png" alt="查询" onclick="seachSubmint();"/>
				</div>
				<div class="rechargeManagementDeSelL2 clearfix">
					<input id="sendtime" name="sendtime" value="${pd.sendtime }" readonly="readonly" />
					<img src="static/readchannel/images/myPic13.png" alt="" />
				</div>
			</div>
			<c:if test="${pd.Webchat ==1 }">
				<a href="javascript:allertFun()" title=""><span style=" background: url(static/readchannel/images/myPic14.png) right center no-repeat;">新建推广</span></a>
			</c:if>
			<c:if test="${pd.Webchat !=1 }">
				<a href="<%=basePath%>extensioncontent/goExtensionbook.do" title=""><span style=" background: url(static/readchannel/images/myPic14.png) right center no-repeat;">新建推广</span></a>
			</c:if>
		</div>
		<div class="promotionManagementDeTit">
			<span style="margin-left: 19px; width: 231px;">渠道信息</span>
			<span style="width: 100px;">封面</span>
			<span style="width: 167px;">基本信息</span>
			<span style="width: 123px;">用户</span>
			<span style="width: 132px;">充值</span>
			<span style="width: 111px;">收益</span>
			<span>管理操作</span>
		</div>
		<div class="promotionManagementDeTex">
			<ul>
				<c:choose>
				<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
				<li class="clearfix">
					<div class="promotionManagementDeTex1">
						<h2>${var.NAME }-${var.TITLE }</h2>
						<div  class="clearfix">
							<a title="${var.CONTENT_URL }" href="javascript:void(0)">${var.CONTENT_URL }</a>
							<textarea class="textarea">${var.CONTENT_URL }</textarea>
							<img class="copyUrl" src="static/readchannel/images/myPic68.png"/>
						</div>
						<p>创建日期：<span>${var.CREATE_TIME }</span></p>
					</div>
					<div class="promotionManagementDeTex2">
						<img src="${var.COVER }" alt="" />
					</div>
					<div class="promotionManagementDeTex3">
						<p>书名：<span>${var.ARTICLE_NAME }</span></p>
						<p>章节：<span>${var.ARTICLE_CHAPTERS }</span></p>
						<p>强制关注：<span>${var.FORCE_CHAPTER }</span></p>
					</div>
					<div class="promotionManagementDeTex4">
						<p>关注：<span>${var.FOLLOW }</span></p>
						<p>引导：<span>${var.GUIDE }</span></p>
						<p>关注率：<span>${var.FOLLOWGUIDE }%</span></p>
					</div>
					<div class="promotionManagementDeTex5">
						<p>充值金额：<span>${var.RECHARGE }</span></p>
						<p>笔数：<span>${var.RECHARGE_TIMES }</span></p>
						<p>充值率：<span>${var.RECHARGEFOLLOW }%</span></p>
					</div>
					<div class="promotionManagementDeTex6">
						<p>收益：<span>${var.PROFIT }</span></p>
					</div>
					<div class="promotionManagementDeTex7">
						<i>操作</i>
						<div class="promotionManagementDeTexD">
							<a href="javascript:editextension('${var.EXTENSION_CONTENT_ID }')" title="编辑">编辑</a>
							<a href="javascript:del('${var.EXTENSION_CONTENT_ID }')" title="删除<">删除</a>
						</div>
					</div>
				</li>
				</c:forEach>
				</c:when>
				<c:otherwise>
					没有相关数据
				</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="flipTwo clearfix">
			 ${page.pageStr}
		</div>
		</form>
	</div>
	<script src="static/readchannel/layDate-v5.0.8/laydate/laydate.js"></script>
	<script type="text/javascript">
		function allertFun(){
			alert("公众号未配置完成，请先配置公众号！");
		}
		$(document).on('click','.copyUrl',function(){
			var input = $(this).siblings('textarea');
			input.select(); // 选中文本
			document.execCommand("copy"); // 执行浏览器复制命令
			alert('成功复制推广链接');
		});
	</script>
	<script type="text/javascript">
	
		function del(data){
			if(window.confirm('你确定要删除这条推广吗？')){
                 $.get("<%=basePath%>extensioncontent/del.do?EXTENSION_CONTENT_ID="+data+"&STATE="+1,function(data,status){
					location.reload();
				});
                 return true;
              }else{
                 return false;
             }
			
		}
		function seachSubmint(){
	   		$("#eForm").attr("action","<%=basePath%>extensioncontent/list.do");
			$("#eForm").submit();
		}
	</script>
	<script>
		function editextension(Id){
			var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑推广";
			 diag.URL = '<%=basePath%>extensioncontent/goEdit.do?EXTENSION_CONTENT_ID='+Id;
			 diag.Width = 561;
			 diag.Height = 304;
			 diag.CancelEvent = function(){ //关闭事件
				 diag.close();
			 };
			 diag.show();
		}
	
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
		$('.rechargeManagementDeSelL1 p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.rechargeManagementDeSelL1 input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.rechargeManagementDeSelL1 input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		
		$(window).click(function(){
			$('.promotionManagementDeTex7 div').hide();
		})
		$('.promotionManagementDeTex7 i').click(function(e){
			if($(this).siblings('div').css('display')=="none"){
				$(this).siblings('div').show();
			}else{
				$(this).siblings('div').hide();
			}
			$(this).parents('li').siblings().find('.promotionManagementDeTexD').hide();
			e.stopPropagation();
		})
		$('.promotionManagementDeTex7 div a').click(function(e){
			e.stopPropagation();
		})
		
		$('.flipTwoC1').click(function(){
			var spanVal=parseInt($('.flipTwoC span').text());
			spanVal++;
			$('.flipTwoC span').text(spanVal+'条/页');
		})
		$('.flipTwoC2').click(function(){
			var spanVal=parseInt($('.flipTwoC span').text());
			if(spanVal>0){
				spanVal--;
			}
			$('.flipTwoC span').text(spanVal+'条/页');
		})
	</script>
</body>
</html>
