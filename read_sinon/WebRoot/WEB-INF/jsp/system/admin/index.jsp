﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	com.sinontech.entity.system.User sessionuser = (com.sinontech.entity.system.User)request.getSession().getAttribute(com.sinontech.util.Const.SESSION_USER);
	
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>数字阅读</title>
	<link rel="stylesheet" type="text/css" href="static/read/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/public.css"/>
	<link rel="stylesheet" type="text/css" href="static/read/css/resourceManagement.css"/>
	<script src="static/read/js/jquery-1.12.4.min.js"></script>
	   	<!--引入弹窗组件start-->
	<script type="text/javascript" src="static/read/js/zDrag.js"></script>
	<script type="text/javascript" src="static/read/js/zDialog.js"></script>
    <!--引入弹窗组件end-->
	
	<script>
	$(function(){
			$('#addpushmode').children('li').bind('click', function(){
				//alert($(this).attr('value'));
				var pmode = $(this).attr('value');
				if(1==pmode){
					 var nowDate = new Date();     
					 var year = nowDate.getFullYear();    
					 var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;    
					 var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();    
					$("#SEND_TIME").val(year + "-" + month + "-" + date);
				}
				$("#PUSHMODE").val(pmode);
				searchnotcie();
			});
		});
	</script>	
</head>
<body>
	<div class="publicTop">
		<div class="w1024 clearfix">
			<div class="publicTopL">
				欣网小说后台管理系统
			</div>
			<div class="clearfix publicTopR">
				<p>账号：<%=sessionuser.getNAME() %></p>
				<a href="logout">退出</a>
			</div>
		</div>
	</div>
	<div class="publicDe">
		<div class="w1200 clearfix">
			<!-- 左侧菜单 -->
			<div class="publicDeL">
				<ul>
				<c:forEach items="${menuList}" var="menu">
				<c:if test="${menu.hasMenu}">
				<li id="lm${menu.MENU_ID }">
				<p>
				<c:choose>
					<c:when test="${not empty menu.subMenu }">
					<a class="${menu.MENU_ICON == null ? 'icon-desktop' : menu.MENU_ICON}" href="javascript:void(0)" title="">${menu.MENU_NAME }</a>
				 	<img src="<%=basePath %>static/read/images/myPic12.png" alt="" />
					</c:when>
					<c:otherwise>
					<a class="${menu.MENU_ICON == null ? 'icon-desktop' : menu.MENU_ICON}" 
					id="z${menu.MENU_ID }" onclick="linkTo('z${menu.MENU_ID }')" name="${menu.MENU_URL }"
					href="javascript:void(0)" title="">${menu.MENU_NAME }</a>
				 
					</c:otherwise>
				</c:choose>
				
				
				</p>	   
				<div>
				<c:forEach items="${menu.subMenu}" var="sub">
					<c:if test="${sub.hasMenu}">
					<a id="z${sub.MENU_ID }" onclick="linkTo('z${sub.MENU_ID }')" name="${sub.MENU_URL }"  href="javascript:void(0)" title="">${sub.MENU_NAME }</a>
					</c:if>
				</c:forEach>
				 </div> 		 
				</li>
				</c:if>
			</c:forEach>
				</ul>
			</div>
			<div class="publicDeR">
				<iframe id="iframe" id="Thunder" src="login_default.do" width="1018" height="1000" frameborder="no" scrolling="no" allowtransparency="yes"></iframe>
			</div>
		</div>
	</div>
	
	<!--各种弹出框-->
	
	
	<!--资源管理编辑标签添加分类-->
	<div class="editTagPlusAlert">
		<div class="editTagPlus">
			<h1>添加分类</h1>
			<div class="editTagPlusDe clearfix">
				<span>选择分类</span>
				<div class="editTagPlusDeSel clearfix"  onclick="addseachCategory();">
					<p id="addtypep"   onclick="addseachCategory();">请选择分类</p>
					<img src="static/read/images/myPic15.png" alt="" />
					<ul id="addlabeltype"   onclick="addseachCategory();">
					</ul>
				</div>
				<div class="editTagPlusDeInp clearfix">
					<input type="text" name="CATEGORY_NAME" id="CATEGORY_NAME"/>
					<p>输入分类名称</p>
				</div>
				<a href="javascript:void(0)" title="" onclick="addCategory();"><i>新增分类</i></a>
			</div>
			<input type="hidden" name="labelid" id="labelid" value=""/>
			<h2 onclick="addCategory('完成');">完&nbsp;&nbsp;成</h2>
			<img src="static/read/images/myPic19.png" alt="" />
		</div>
	</div>
	
	<!--资源管理标签管理新增标签-->
	<div class="labelManagementPlusAlert">
		<div class="labelManagementPlus">
			<h1>新增标签</h1>
			<div style="padding-bottom: 30px; background: #ffffff;">
				<div style="padding-bottom: 0;" class="labelManagementPlusDe clearfix">
					<span>选择分类</span>
					<div class="labelManagementPlusDeSel clearfix"  onclick="seachCategory();" >
						<p id="typep" onclick="seachCategory();">请选择分类</p>
						<img src="static/read/images/myPic15.png" alt=""  ></img>
						<ul id="typeLabel" onclick="seachCategory();">
							
						</ul>
					</div>
					<div class="labelManagementPlusDeInp clearfix">
						<input type="text" name="LabelCategory" id="LabelCategory" value=""/>
						<p>输入分类名称</p>
					</div>
					<a href="javascript:void(0)" onclick="addLabelCategory();" title=""><i>新增分类</i></a>
				</div>
				<div class="labelManagementPlusLine"></div>
				<div class="labelManagementPlusTex clearfix">
					<span>标签名称</span>
					<div class="clearfix">
						<textarea name="LABEL_NAME" id="LABEL_NAME" placeholder="在此处输入需要添加的标签名称，可一次性输入多个标签名称进行添加，每个标签以中文逗号隔开。例：美女，神话。"></textarea>
					</div>
				</div>
			</div>
			<h2 onclick="addlabel();">完&nbsp;&nbsp;成</h2>
			<img src="static/read/images/myPic19.png" alt="" />
		</div>
	</div>
	<!--添加书籍（单本）标签编辑-->
	<div class="labelEditingAlert">
		<div class="labelEditing">
			<h1>添加标签</h1>
			<div style="background: #ffffff; padding: 20px 0;">
				<div class="labelEditingSeled clearfix">
					<span>已选标签</span>
					<p>最多选择<i>5</i>个标签</p>
				</div>
				<div class="labelEditingSeledDe clearfix">
					<p>请选择标签</p>
					<ul>
						
					</ul>
				</div>
				<div class="labelEditingSeling">
					<p>选择标签</p>
					<input type="hidden" value = "" id="selectaddlabel" name = "selectaddlabel" value=""/>
					<ul class="clearfix">
					
					</ul>
				</div>
			</div>
			<h2 onclick="abbbooklabel();">完&nbsp;&nbsp;成</h2>
			<img src="static/read/images/myPic19.png" alt="" />
		</div>
	</div>
	
	<!--资源管理书籍管理批量编辑（价格）-->
	<div class="batchEditingPrice">
		<div class="batchEditingPriceDe">
			<h1>价格设置</h1>
			<div style="background: #ffffff; padding: 20px 0;">
				<div class="batchEditingPriceDeSection">
					价格区间
				</div>
				<div class="batchEditingPriceDeUl">
					<ul>
						<li class="clearfix">
							<i>1</i>
							<span style="margin-left: 20px;">章节</span>
							<div style="margin-left: 9px;" class="clearfix">
								<input class="batchEditingPriceDeUl1" value="1" readonly="readonly" />
							</div>
							<span style="margin: 0 10px;">~</span>
							<div class="clearfix">
								<input class="batchEditingPriceDeUl2" value="最后一章" readonly="readonly" />
							</div>
							<span style="margin-left: 30px;">价格</span>
							<div style="margin-left: 10px;" class="clearfix">
								<input style="color: #666;" value="0" />
							</div>
							<span style="margin-left: 10px;">阅读币</span>
						</li>
					</ul>
				</div>
				<div class="batchEditingPriceDePlus clearfix">
					<a href="javascript:void(0)" title=""><span>新增区间</span></a>
				</div>
			</div>
			<h2 onclick="upMoneys('batchEditingPriceDeUl');">完&nbsp;&nbsp;成</h2>
			<img src="static/read/images/myPic19.png" alt="" />
		</div>
	</div>
	
	<!--添加书籍（批量）编辑-->
	<div class="addBookBatchAlert">
		<div class="addBookBatchDe">
			<h1>修改书籍信息</h1>
			<div style="padding-bottom: 20px; background: #ffffff;">
				<div class="addBookBatchDeInp clearfix">
					<span>书名：</span>
					<div class="clearfix">
						<input value="斗破苍穹" />
					</div>
				</div>
				<div class="addBookBatchDeInp clearfix">
					<span>作者：</span>
					<div class="clearfix">
						<input value="天蚕土豆" />
					</div>
				</div>
				<div class="addBookBatchDeInp clearfix">
					<span>付费类型：</span>
					<div class="clearfix">
						<input value="付费" />
					</div>
				</div>
				<div class="addBookBatchDeInp clearfix">
					<span>付费方式：</span>
					<div class="clearfix">
						<input value="阅读币购买章节，VIP免费" />
					</div>
				</div>
				<div class="addBookBatchDeRemind">此处可填写：<仅限阅读币购买>或者<阅读币购买章节，VIP免费></div>
				<div style="margin: 0;" class="addBookBatchDeInp clearfix">
					<span>书籍标签：</span>
					<div class="clearfix">
						<input value="都市爱情戏，都市爱情戏，都市爱情戏，都市爱情戏，都市爱情戏" />
					</div>
				</div>
				<div class="addBookBatchDeTex clearfix">
					<span>书籍简介：</span>
					<div class="clearfix">
						<textarea>案发撒设计反馈拉法基拉分类较少法拉盛；司法局控件库十点读书马快</textarea>
					</div>
				</div>
			</div>
			<h2>完&nbsp;&nbsp;成</h2>
			<img src="static/read/images/myPic19.png" alt="" />
		</div>
	</div>
	
	<!--添加章节（批量）插入-->
	<div class="chapterAlert">
		<div class="chapterAlertDe">
			<h1>插入单章</h1>
			<div class="chapterAlertDeInp clearfix">
				<span>序号：</span>
				<div class="clearfix">
					<input />
					<p>请输入序号</p>
				</div>
			</div>
			<div class="chapterAlertDeInp clearfix">
				<span>章节名称：</span>
				<div class="clearfix">
					<input />
					<p>请输入章节名称</p>
				</div>
			</div>
			<div class="chapterAlertDeTex clearfix">
				<span>章节内容：</span>
				<div class="clearfix">
					<textarea></textarea>
					<p>请输入章节内容</p>
				</div>
			</div>
			<div class="chapterAlertDeInp clearfix">
				<span>章节字数：</span>
				<div class="clearfix">
					<input />
					<p>请输入章节字数</p>
				</div>
			</div>
			<h2>完&nbsp;&nbsp;成</h2>
			<img src="static/read/images/myPic19.png" alt="" />
		</div>
	</div>
	
	<!--添加章节（新增章节）匹配书籍-->
	<div class="seMatchBooksAlert">
		<div class="seMatchBooks">
			<h1>章节内容</h1>
			<div class="seMatchBooksTex">
				<h3>第一章  总裁独占小天后</h3>
				<p>重生后，宫暮暮虐渣打狗</p>
			</div>
			<h2>完&nbsp;&nbsp;成</h2>
			<img src="static/read/images/myPic19.png" alt="" />
		</div>
	</div>
	
	<!--添加章节（新增章节）匹配书籍-->
	<div class="newMatchBooksAlert">
		<div class="newMatchBooks">
			<h1>匹配到的书籍</h1>
			<div class="newMatchBooksAll">
				<div class="newMatchBooksTit clearfix">
					<span>单选</span>
					<span style="margin-left: 21px; width: 64px;">封面</span>
					<span style="width: 129px;">书名</span>
					<span style="width: 88px;">作者</span>
					<span style="width: 109px;">书籍ID</span>
					<span>字数</span>
				</div>
				<div class="newMatchBooksTex">
					<ul>
					</ul>
				</div>
			</div>
			<h2 onclick="goAddArticleChapter();">完&nbsp;&nbsp;成</h2>
			<img src="static/read/images/myPic19.png" alt="" />
		</div>
	</div>
	<!--读者管理渠道信息-->
	<div class="RmciAlert">
		<div class="RmciDe">
			<div class="RmciDeTop clearfix">
				<span>渠道信息</span>
				<img src="static/read/images/myPic19.png" alt="" />
			</div>
			<div class="RmciDeAll">
				<div class="RmciDeTit clearfix">
					<span style=" margin-left: 21px; width: 97px;">所属渠道</span>
					<span style="width: 145px;">渠道ID</span>
					<span style="width: 117px;">关注状态</span>
					<span>关注时间</span>
				</div>
				<div class="RmciDeTex">
					<ul>
						<li class="clearfix">
							<span class="RmciDeTex1">巴士小说</span>
							<span class="RmciDeTex2">124587451221</span>
							<span class="RmciDeTex3">已关注</span>
							<span>2017-12-12 12:12</span>
						</li>
					</ul>
				</div>
				<div class="RmciDeFlip clearfix">
					<div class="RmciDeFlipL">共50条</div>
					<div class="RmciDeFlipR clearfix">
						<p><span>1</span>/<i>10</i></p>
						<div class="RmciDeFlipR1">
							<img src="static/read/images/myPic17.png" />
						</div>
						<div class="RmciDeFlipR2">
							<img src="static/read/images/myPic18.png" />
						</div>
					</div>
				</div>
			</div>
			<h2>完&nbsp;&nbsp;成</h2>
		</div>
	</div>
	
	<!--消费管理渠道消费信息-->
	<div class="consumptionInformationAlert">
		<div class="consumptionInformationDe">
			<div class="consumptionInformationDeT clearfix">
				<span>渠道消费信息</span>
				<img src="static/read/images/myPic19.png" alt="" />
			</div>
			<div class="consumptionInformationDeC">
				<div class="consumptionInformationDeCTit clearfix">
					<span style="width: 75px; margin-left: 20px;">所属渠道</span>
					<span style="width: 104px;">渠道ID</span>
					<span style="width: 88px;">剩余阅读币</span>
					<span style="width: 92px;">使用阅读币</span>
					<span>最近消费时间</span>
				</div>
				<div class="consumptionInformationDeCTex">
					<ul>
						<li class="clearfix">
							<span style="width: 75px; margin-left: 20px;">巴士小说</span>
							<span style="width: 104px;">124587451221</span>
							<span style="width: 88px;">12547864</span>
							<span style="width: 92px;">12547864</span>
							<span>2017-12-12 12:12</span>
						</li>
					</ul>
				</div>
				<div class="RmciDeFlip clearfix">
					<div class="RmciDeFlipL">共50条</div>
					<div class="RmciDeFlipR clearfix">
						<p><span>1</span>/<i>10</i></p>
						<div class="RmciDeFlipR1">
							<img src="static/read/images/myPic17.png" />
						</div>
						<div class="RmciDeFlipR2">
							<img src="images/myPic18.png" />
						</div>
					</div>
				</div>
			</div>
			<div class="consumptionInformationDeB">
				完&nbsp;&nbsp;成
			</div>
		</div>
	</div>
	
	<!--重置密码-->
	<div class="resetPasswordAlert">
		<div class="resetPasswordDe">
			<div class="resetPasswordDeT clearfix">
				<img src="static/read/images/myPic57.png" alt="" />
				<div>
					<h2>您确认要重置密码吗？</h2>
					<p>重置后密码默认为66666666！</p>
				</div>
			</div>
			<div class="resetPasswordDeB clearfix">
				<a href="javascript:void(0)" title="">确定</a>
				<span>取消</span>
			</div>
			<img src="static/read/images/myPic58.png" alt="" />
		</div>
	</div>
	
	<!--修改成功-->
	<div class="unavailableAlert">
		<div class="unavailableDe">
			<div class="unavailableDeT clearfix">
				<img src="static/read/images/myPic59.png" alt="" />
				<div>
					<h2>修改成功！</h2>
					<p>密码已经成功设置为66666666！</p>
				</div>
			</div>
			<div class="unavailableDeB clearfix">
				知道了
			</div>
			<img src="static/read/images/myPic58.png" alt="" />
		</div>
	</div>
	
	<!--无法使用-->
	<div class="unavailableAlert">
		<div class="unavailableDe">
			<div class="unavailableDeT clearfix">
				<img src="images/myPic60.png" alt="" />
				<div>
					<h2>无法使用价格模板！</h2>
					<p>要使用价格模板，请先添加章节！</p>
				</div>
			</div>
			<div class="unavailableDeB clearfix">
				确定
			</div>
			<img src="static/read/images/myPic58.png" alt="" />
		</div>
	</div>
	
	
	<!--充值记录vip-->
	<div class="rechargeRecordAlert">
		<div class="rechargeRecordDe">
			<div class="rechargeRecordDeTop clearfix">
				<span>VIP信息</span>
				<p>剩余19天</p>
				<img src="static/read/images/myPic19.png" alt="" />
			</div>
			<div class="rechargeRecordDeC">
				<ul>
					<li class="clearfix">
						<span>VIP名称</span>
						<p>月卡</p>
					</li>
					<li class="clearfix">
						<span>购买时间</span>
						<p>2017-12-12 09:14</p>
					</li>
					<li class="clearfix">
						<span>有效时段</span>
						<p>全天</p>
					</li>
					<li class="clearfix">
						<span>有效期</span>
						<p>30天</p>
					</li>
					<li class="clearfix">
						<span>类型</span>
						<p>单独包</p>
					</li>
				</ul>
			</div>
			<div class="rechargeRecordDeB clearfix">
				完&nbsp;&nbsp;成
			</div>
		</div>
	</div> 
	
	
	
	
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	
	<!-- 书籍管理，价批量编辑 -->
	<input name="BATCHARTICLE_ID" id="BATCHARTICLE_ID" value=""/>
	<script type="text/javascript">
		function upMoneys(){
			var id = $("#BATCHARTICLE_ID").val();
			var money = "";
			$(".batchEditingPriceDeUl ul li input").each(function(n,value){
				money = money + $(this).val()+",";
			});
			$.ajax({
				type: "post",
				data:{
					'money':money,
					'ARTICLE_ID':id
				},
				url: "<%=basePath%>articlechapter/bathEditmoneys.do",
				dataType:'text', 
				success: function(data){
					/* if(data!=""){
						alert('设置完成');
					}else{
						alert('设置失败');
					} */
					document.getElementById("iframe").contentWindow.readlocation();
				}
			});
			$('.batchEditingPrice').hide();
		}
		function invalbookid(data){
			$("#BATCHARTICLE_ID").val(data);
		}
	</script>
	<!--添加章节（新增章节）匹配书籍-->
	<script>
	
		$(function(){
			$('#TITLE_CATEGORY_IDdiv').children('li').bind('click', function(){
				//alert($(this).attr('value'));
				var state =$(this).attr('value');
				alert(state);
				 $("#TITLE_CATEGORY_ID").val($(this).attr('value'));
			});
		});
		
	
		function goAddArticleChapter(){
			if($('.newMatchBooksTexAct').length>0){
				var name = "";
				var num = "";
				var id = "";
				var ARTICLE_ID = $('.newMatchBooksTex ul li').attr("id");
				if(ARTICLE_ID!=null&&ARTICLE_ID!=''){
					var label = ARTICLE_ID.split(",");
					$.each(label,function(n,value){
						if(n==0){
							name = value;
						}
						if(n==1){
							num = value;
						}
						if(n==2){
							id = value;
						}
					});
					document.getElementById("iframe").contentWindow.chapterAddtxt(name,num,id);
					$('.newMatchBooksAlert').hide();
				}
			}else{
				alert("请选择一本书！！");
			}
		}
		function selectzdnAllBook(data){
			$(".newMatchBooksTex ul li").remove();
			$.ajax({
				type: "post",
				data:{
					"ARTICLE_NAME":data
				},
				url: "<%=basePath%>article/matchingBook.do",
				success: function(data){
						if(data!=null&&data!=""){
						$.each(data, function(index, item){
							$(".newMatchBooksTex ul").append(
								"<li class='clearfix' id='"+item.ARTICLE_NAME+","+item.COUNT_CHAPTERS+","+item.ARTICLE_ID+"'>" +
									"<a href='javascript:void(0)' title=''><i></i></a>"+
									"<span style='width: 64px; margin-left: 21px;''><img src='uploadFiles/bookImage/"+item.BOOK_COVER+"' alt='' /></span>"+
									"<span style='width: 129px;'>"+item.ARTICLE_NAME+"</span>"+
									"<span style='width: 88px;'>"+item.AUTHOR+"</span>"+
									"<span style='width: 109px;'>"+item.ARTICLE_ID+"</span>"+
									"<span>共"+(parseInt(item.COUNT_LETTER)/10000)+"字</span>"+
								"</li>"
							);
				        });
					}
				}
			});
		}
	</script>
	<!--单本书籍编辑/新增js  -->
	<script>
		function addlabelEditingSeledDe(data){
			var label = data.split(",");
			$(".labelEditingSeledDe ul li").remove();
			 $.each(label,function(n,value){
	           	$(".labelEditingSeledDe ul").append("<li class='labelEditingSelingAct'>"+value+"<img src='static/read/images/myPic34.png'/>"+"</li>");
	           	$('.labelEditingSeling').find('li').each(function() {
		           	if($(this).text()==$.trim(value)){
		           		$(this).addClass('labelEditingSelingAct');
		           	}
		    	});
	        });
		}
		//提取主页面弹框内标签，给书籍单本新增/编辑子页面标签框赋值
		function abbbooklabel(){
			var addlabeltxt = "";
			$('.labelEditingSeledDe').find('li').each(function() {
				addlabeltxt = addlabeltxt + $(this).text()+",";
		    });
		    addlabeltxt = addlabeltxt.substring(0, addlabeltxt.length-1);
			document.getElementById("iframe").contentWindow.assignmentlabel(addlabeltxt);
			$('.labelEditingSeledDe ul li').remove();
			$('.labelEditingAlert').hide();
		}
	</script>
	<script>
		$.ajax({
			type: "post",
			data:{},
			url: "<%=basePath%>label/listJson.do",
			dataType:'json', 
			success: function(data){
				//$(".labelEditingSeling ul li").remove();
				$.each(data, function(index, item){
			       	$(".labelEditingSeling ul").append("<li class=''>" + item.LABEL_NAME + "</li>");
			    });
			},
			error: function () {    
		    	alert("数据错误");
		    }
		});
		//查询所有标签类
		function addseachCategory(){
			$.ajax({
				type: "post",
				data:{},
				url: "<%=basePath%>labelcategory/listJson.do",
				dataType:'json', 
				success: function(data){
					$("#addlabeltype li").remove();
					$.each(data, function(index, item){
			       		$("#addlabeltype").append("<li onclick=Choicedel('"+item.CATEGORY_NAME+"');>" + item.CATEGORY_NAME + "</li>");
			        });
				},
				error: function () {    
		        alert("数据错误");
		        }
			});
		}
		//添加标签
		function addlabel(){
			var LABEL_NAME = $("#LABEL_NAME").val();
			var  CATEGORY_NAME = document.getElementById("typep");  
			if(''!=LABEL_NAME && null!=LABEL_NAME && '请选择分类'!=CATEGORY_NAME.innerHTML){
				$.post("<%=basePath %>label/save.do",
				  {
					LABEL_NAME : LABEL_NAME,
					CATEGORY_NAME : CATEGORY_NAME.innerHTML
				  },
				  function(data,status){
				    //alert("Data: " + data + "\nStatus: " + status);
				    $('.labelManagementPlusAlert').hide();
				    var leftiframeid = document.getElementById('iframe');//left为对应iframe的id
				    leftiframeid.src = "<%=basePath %>label/list.do";//ileft.html为frame的页面
				  });
			}else{
				alert("标签类名或标签名为空");
			}
		}
		
		function Choicedel(data){
			if(data!=""){
				$("#addtypep").remove();
				$(".editTagPlusDeSel").append("<p id='addtypep'>"+data+"</p>");
			}
		}
		
		function Choice(data){
			if(data!=""){
				$("#typep").remove();
				$(".labelManagementPlusDeSel").append("<p id='typep'>"+data+"</p>");
			}
		}
		//添加标签类
		function addLabelCategory(){
			var CATEGORY_NAME = $("#LabelCategory").val();
			if(''!=CATEGORY_NAME && null!=CATEGORY_NAME){
				$.post("<%=basePath %>labelcategory/save.do",
				  {
					CATEGORY_NAME:CATEGORY_NAME
				  },
				  function(data,status){})
				  $("#typep").remove();
				  $(".labelManagementPlusDeSel").append("<p id='typep'>"+CATEGORY_NAME+"</p>");
				  
			}else{
				alert("请填写类名");
			}
		}
		//编辑页面添加标签类
		function addCategory(data){
			var CATEGORY_NAME = $("#CATEGORY_NAME").val();
				
				if(data=='完成'){
					var labelid = $("#labelid").val();
					CATEGORY_NAME = document.getElementById("addtypep").innerHTML;
					$.post("<%=basePath %>categorylabel/save.do",
						{
							"CATEGORY_NAME":CATEGORY_NAME,
							"labelid":labelid
						},
						function(data,status){
							$('.editTagPlusAlert').hide();
						});
				}else{
					if(""!=CATEGORY_NAME && null!=CATEGORY_NAME){
						$.post("<%=basePath %>labelcategory/save.do",
						{
							CATEGORY_NAME:CATEGORY_NAME
						},
						function(data,status){});
						
						$("#addtypep").remove();
						$(".editTagPlusDeSel").append("<p id='addtypep'>"+CATEGORY_NAME+"</p>");
					}else{
						alert("请填写类名");
					}
				}
		}
		//动态查询标签类名
		function seachCategory(){
			 $("#typeLabel li").remove();
			$.ajax({
				type: "post",
				data:{"limtpage":0},
				url: "<%=basePath%>labelcategory/listJson.do",
				dataType:'json', 
				success: function(data){
					$.each(data, function(index, item){
					    $("#typeLabel").append("<li onclick=Choice('"+item.CATEGORY_NAME+"');>" + item.CATEGORY_NAME + "</li>");
					});
				},
				error: function () {    
				    alert("数据错误");
				}
			});
		}
		
		$('.publicDeL ul li p').click(function(){
			if($(this).siblings().size()==0){
				$(this).parent('li').siblings().find('div').hide();
				$(this).parent().siblings().find('img').attr('src','<%=basePath %>static/read/images/myPic12.png');
			}
			if($(this).siblings('div').css('display')=='none'){
				$(this).siblings('div').show();
				$(this).find('img').attr('src','<%=basePath %>static/read/images/myPic13.png');
			}else{
				$(this).siblings('div').hide();
				$(this).find('img').attr('src','<%=basePath %>static/read/images/myPic12.png');
			}
			$(this).addClass('publicDeLActp');
			$(this).parent().siblings().find('p').removeClass('publicDeLActp');
			
//			$(this).parent().siblings().find('img').attr('src','images/myPic12.png');
			
		})
		$('.publicDeL ul li div a').click(function(e){
			$(this).addClass('publicDeLa').siblings().removeClass('publicDeLa');
			$(this).parents('li').siblings().find('p').removeClass('publicDeLActp');
			$(this).parents('li').siblings().find('p img').attr('src','<%=basePath %>static/read/images/myPic12.png');
			$(this).parent().siblings('p').addClass('publicDeLActp');
			$(this).parent().siblings('p').find('img').attr('src','<%=basePath %>static/read/images/myPic13.png');
			$(this).parents('li').siblings().find('div').hide();
			$(this).parents('li').siblings().find('div a').removeClass('publicDeLa');
			var son=$(this).text();
			var part=$(this).parent().siblings().find('a').text();
			$("#iframe").contents().find(".announcementManagementTop1").text(part);
			$("#iframe").contents().find(".announcementManagementTop2").text(son);
			e.stopPropagation();
		})
		function linkTo(id){
			var src=$('#'+id).attr('name');
			$('.publicDeR iframe').attr('src',src);
		}
		function reinitIframe() {           
		     var iframe = document.getElementById("iframe");           
		     try {               
		            var bHeight =iframe.contentWindow.document.body.scrollHeight;               
		            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;              
		            var height = Math.max(bHeight, dHeight);            
		            iframe.height = height;
		        } catch (ex) { }       
		}
		window.setInterval("reinitIframe()", 100);
		
		laydate.render({
		  elem: '#SEND_TIME'
		});

//		各种弹出框
//		数据统计公告管理编辑公告
		$('.pushMode div').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		})
		$('.pushMode div ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$('.pushMode div p').text(val);
			e.stopPropagation();
		})
		$(window).click(function(){
			$('.pushMode div').find('ul').hide();
		})
		 
		
		//添加标签分类
		$(window).click(function(){
			$('.pushMode div').find('ul').hide();
			$('.editTagPlusDeSel').find('ul').hide();
			$('.labelManagementPlusDeSel').find('ul').hide();
		});
		$('.editTagPlusDeSel').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		});
		$('.editTagPlusDeSel ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$('.editTagPlusDeSel p').text(val);
			e.stopPropagation();
		});
		$('.editTagPlusDeInp p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		});
		$('.editTagPlusDeInp input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
        	}
		});
		$('.editTagPlusDeInp input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		});
		
		$('.editTagPlus>img').click(function(){
			$('.editTagPlusAlert').hide();
		});
		
		//添加标签
		$('.labelManagementPlusDeSel').click(function(e){
			if($(this).find('ul').css('display')=="none"){
				$(this).find('ul').show();
			}else{
				$(this).find('ul').hide();
			}
			e.stopPropagation();
		});
		$('.labelManagementPlusDeSel ul li').click(function(e){
			$(this).css('color','#f37427');
			$(this).siblings().css('color','#666666');
			$(this).parent().hide();
			var val=$(this).text();
			$('.labelManagementPlusDeSel p').text(val);
			e.stopPropagation();
		})
		$('.labelManagementPlusDeInp p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.labelManagementPlusDeInp input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
        	}
		});
		$('.editTagPlusDeInp input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		$('.labelManagementPlus>img').click(function(){
			$('.labelManagementPlusAlert').hide();
		})
		function ajaxAddlabel(){
			$.ajax({
				type: "post",
				data:{},
				url: "<%=basePath%>label/listJson.do",
				dataType:'json', 
				success: function(data){
					$(".labelEditingSeling ul li").remove();
					$.each(data, function(index, item){
				       	$(".labelEditingSeling ul").append("<li class=''>" + item.LABEL_NAME + "</li>");
				    });
				}
			});
		}
		//添加书籍（单本）标签编辑
		$(document).on('click','.labelEditingSeling ul li',function(){
		
			if($(this).hasClass('labelEditingSelingAct')){
				$(this).removeClass('labelEditingSelingAct');
				var tex=$(this).text();
				$('.labelEditingSeledDe ul li:contains('+tex+')').remove();	
				var len = $('.labelEditingSeledDe ul li').length;
				if(len==0){
					$('.labelEditingSeledDe p').show();
				}
			}else{
				$(this).addClass('labelEditingSelingAct');
				var tex=$(this).text();
				var html='<li>'+tex+'<img src="static/read/images/myPic34.png"/></li>';
				$('.labelEditingSeledDe ul').append(html);
				var len = $('.labelEditingSeledDe ul li').length;
				if(len>0){
					$('.labelEditingSeledDe p').hide();
				}
			}
		});
		$(document).on('click','.labelEditingSeledDe ul li img',function(){
			$(this).parent('li').remove();
			var tex=$(this).parent('li').text();
			$('.labelEditingSeling ul li:contains('+tex+')').removeClass('labelEditingSelingAct');
			var len = $('.labelEditingSeledDe ul li').length;
			if(len==0){
				$('.labelEditingSeledDe p').show();
			}
		});
		$('.labelEditing>img').click(function(){
			$('.labelEditingAlert').hide();
		});
		
//		资源管理书籍管理批量编辑（价格）
		$('.batchEditingPriceDePlus a').click(function(){
			var iLen=$('.batchEditingPriceDeUl ul li').length;
			var iLenNext=iLen+1;
			var iVal=parseInt($('.batchEditingPriceDeUl ul li').eq(iLen-1).find('.batchEditingPriceDeUl1').val());
			var iValNext=iVal+1;
			$('.batchEditingPriceDeUl ul li').eq(iLen-1).find('.batchEditingPriceDeUl2').val(iVal);
			var html='<li class="clearfix"><i>'+iLenNext+'</i><span style="margin-left: 20px;">章节</span><div style="margin-left: 9px;" class="clearfix"><input class="batchEditingPriceDeUl1" value='+iValNext+' style="color: #666;" /></div><span style="margin: 0 10px;">~</span><div class="clearfix"><input class="batchEditingPriceDeUl2" value="最后" readonly="readonly" /></div><span style="margin-left: 30px;">价格</span><div style="margin-left: 10px;" class="clearfix"><input style="color: #666;" value="0" /></div><span style="margin-left: 10px;">阅读币</span><img src="static/read/images/myPic36.png" alt="" /></li>';
			$('.batchEditingPriceDeUl ul').append(html);
			
		});
		$(document).on('blur','.batchEditingPriceDeUl1',function(){
			var inputVal=parseInt($(this).val());
			if(inputVal!=1){
				var inputPrveVal=parseInt($(this).parents('li').prev('li').find('.batchEditingPriceDeUl1').val());
				var inputPrveTVal=parseInt($(this).parents('li').prev('li').find('.batchEditingPriceDeUl2').val())+1;
				var inputsibVal=$(this).parent('div').siblings('div').find('.batchEditingPriceDeUl2').val();
				if(inputsibVal=='最后'){
					inputsibVal=100000000;
				}else{
					inputsibVal=parseInt(inputsibVal);
				}
				if(/^[0-9]+$/.test(inputVal)&&(inputVal>inputPrveVal)&&(inputVal<=inputsibVal)){
					$(this).parents('li').prev('li').find('.batchEditingPriceDeUl2').val(inputVal-1);
				}else{
					$(this).val(inputPrveTVal);
				}
			}
		});
		$(document).on('click','.batchEditingPriceDeUl ul li img',function(){
			var iLen=$('.batchEditingPriceDeUl ul li').length;
			var liEq=$(this).parent('li').index();
			var thisVal=$(this).parent('li').find('.batchEditingPriceDeUl2').val();
			if(iLen==(liEq+1)){
				$(this).parent('li').prev().find('.batchEditingPriceDeUl2').val('最后');
				$(this).parent('li').remove();
			}else{
				$(this).parent('li').prev().find('.batchEditingPriceDeUl2').val(thisVal);
				$(this).parent('li').remove();
				for(var j=1;j<=iLen;j++){
					$('.batchEditingPriceDeUl ul li').eq(j-1).find('i').text(j);
				}
			}
			
		});
		$('.batchEditingPriceDe>img').click(function(){
			$('.batchEditingPrice').hide();
		});
		
		//		添加书籍（批量）编辑
		$('.addBookBatchDe>img').click(function(){
			$('.addBookBatchAlert').hide();
		})
		
		//		添加章节（批量）插入
		$('.chapterAlertDeInp div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.chapterAlertDeInp div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.chapterAlertDeInp div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		$('.chapterAlertDeTex div p').click(function(){
			$(this).hide();
			$(this).siblings('textarea').focus();
		})
		$('.chapterAlertDeTex div textarea').bind('textarea propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.chapterAlertDeTex div textarea').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		$('.chapterAlertDe>img').click(function(){
			$('.chapterAlert').hide();
		})
		
		//		添加章节（新增章节）匹配书籍
		$('.seMatchBooks>img').click(function(){
			$('.seMatchBooksAlert').hide();
		})
		
		//		添加章节（新增章节）匹配书籍
		$(document).on('click','.newMatchBooksTex ul li a i',function(){
			$('.newMatchBooksTexAct').length;
			if($('.newMatchBooksTexAct').length>=1){
				$('.newMatchBooksTex ul li a i').removeClass('newMatchBooksTexAct');
				$(this).addClass('newMatchBooksTexAct');
			}else{
				$(this).addClass('newMatchBooksTexAct');
			}
		});
		$('.newMatchBooks>img').click(function(){
			$('.newMatchBooksAlert').hide();
		});	
			
//		读者管理渠道信息
		$('.RmciDeFlipR1').click(function(){
			var val=parseInt($('.RmciDeFlipR p span').text());
			if(val<2){
				$(this).css('background','#e8e8e8');
			}else if(val==2){
				$('.RmciDeFlipR p span').text(val-1);
				$('.RmciDeFlipR2').css('background','#f5f5f5');
				$(this).css('background','#e8e8e8');
			}else{
				$('.RmciDeFlipR p span').text(val-1);
				$('.RmciDeFlipR2').css('background','#f5f5f5');
			}
		})
		$('.RmciDeFlipR2').click(function(){
			var val=parseInt($('.RmciDeFlipR p span').text());
			var all=$('.RmciDeFlipR p i').text()-1;
			if(val<all){
				$('.RmciDeFlipR p span').text(val+1);
				$('.RmciDeFlipR1').css('background','#f5f5f5');
			}else if(val==all){
				$('.RmciDeFlipR p span').text(val+1);
				$(this).css('background','#e8e8e8');
			}
		})
		
		$('.RmciDeTop img,.RmciDe>h2').click(function(){
			$('.RmciAlert').hide();
		})
			
//		消费管理渠道消费信息
		$('.consumptionInformationDeT img').click(function(){
			$('.consumptionInformationAlert').hide();
		})
		
//		重置密码
		$('.resetPasswordDe>img').click(function(){
			$('.resetPasswordAlert').hide();
		})
		
//		新增渠道
		$('.newChannelsDeInp div p').click(function(){
			$(this).hide();
			$(this).siblings('input').focus();
		})
		$('.newChannelsDeInp div input').bind('input propertychange', function(){
        	if($(this).val()==''){
        		$(this).siblings('p').show();
//      		$(this).blur();
        	}
		});
		$('.newChannelsDeInp div input').blur(function(){
			if($(this).val()==''){
				$(this).siblings('p').show();
			}
		})
		$('.newChannelsDeTop img').click(function(){
			$('.newChannelsAlert').hide();
		})
		
//		充值记录vip
		$('.rechargeRecordDeTop img').click(function(){
			$('.rechargeRecordAlert').hide();
		})
		
	</script>
</body>
</html>
		