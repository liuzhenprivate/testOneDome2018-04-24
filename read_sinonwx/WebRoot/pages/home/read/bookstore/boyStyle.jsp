<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinontech.modle.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
//	Integer signflag = (Integer)request.getAttribute("signflag");//0未开启签到功能 1 已签到2未签到
//	UserInfo user = (UserInfo)request.getAttribute("user");
//	System.out.println(user.toString());
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <title>男生</title>
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/base.css">
    <link rel="stylesheet" type="text/css" href="pages/home/read/css/index.css">
    <script src='pages/home/read/js/jquery-1.12.4.min.js'></script>
</head>
<body style="background: #f2f2f2;">
	<div class="boyStyleT">
		<img src="pages/home/read/images/myPic30.png" alt="" />
		<div class="clearfix">
			<img src="pages/home/read/images/myPic31.png" alt="" />
			<input placeholder="输入书籍、作者名称或关键词搜索" />
		</div>
	</div>
	<div class="boyStyleCla clearfix">
		<a href="javascript:void(0)" title="">排行</a>
		<a href="javascript:void(0)" title="">玄幻</a>
		<a href="javascript:void(0)" title="">都市</a>
		<a href="javascript:void(0)" title="">校园</a>
		<a href="javascript:void(0)" title="">VIP</a>
	</div>
	<div class="boyStyleLink">
		<a href="javascript:void(0)" title="" class="clearfix">
			<span>最近阅读</span>
			<div>
				<h2>我家客人你惹不起</h2>
				<p>第三章 心包腔内发黑的血液</p>
			</div>
			<img src="pages/home/read/images/myPic23.png" alt="" />
		</a>
	</div>
	<div class="freeAdmission">
		<h2>主编推荐</h2>
		<ul class="clearfix">
			<li>
				<div class="freeAdmissionDe">
					<img src="pages/home/read/images/myPic26.png" alt="" />
				</div>
				<h3>我家客人你惹不起我家客人你惹不起</h3>
				<p>烽火成林</p>
			</li>
			<li>
				<div class="freeAdmissionDe">
					<img src="pages/home/read/images/myPic26.png" alt="" />
					<span style="background: #29d5a6;">免费</span>
				</div>
				<h3>我家客人你惹不起我家客人你惹不起</h3>
				<p>烽火成林</p>
			</li>
			<li>
				<div class="freeAdmissionDe">
					<img src="pages/home/read/images/myPic26.png" alt="" />
					<span style="background: #fe925a;">VIP</span>
				</div>
				<h3>我家客人你惹不起我家客人你惹不起</h3>
				<p>烽火成林</p>
			</li>
		</ul>
	</div>
	<div class="vipPopularity">
		<h2>精选好书</h2>
		<ul>
			<li class="clearfix">
				<div class="vipPopularityL">
					<img src="pages/home/read/images/myPic1.png" alt="" />
				</div>
				<div class="vipPopularityR">
					<h3>绝剑江湖之浪剑传奇</h3>
					<p>全民偶像“之称的可爱明星莫依，在华夏最具盛名京都花台举办个人演唱会，正是多年来梦寐以求多年来梦寐以求</p>
					<div class="clearfix vipPopularityRR clearfix">
						<h4>特立独行的猫</h4>
						<div class="clearfix">
							<span class="vipPopularityRRE">VIP</span>
							<span>玄幻</span>
							<span>354万人在看</span>
						</div>
					</div>
				</div>
			</li>
			<li class="clearfix">
				<div class="vipPopularityL">
					<img src="pages/home/read/images/myPic1.png" alt="" />
				</div>
				<div class="vipPopularityR">
					<h3>绝剑江湖之浪剑传奇</h3>
					<p>全民偶像“之称的可爱明星莫依，在华夏最具盛名京都花台举办个人演唱会，正是多年来梦寐以求多年来梦寐以求</p>
					<div class="clearfix vipPopularityRR clearfix">
						<h4>特立独行的猫</h4>
						<div class="clearfix">
							<span class="vipPopularityRRE">VIP</span>
							<span>玄幻</span>
							<span>354万人在看</span>
						</div>
					</div>
				</div>
			</li>
			<li class="clearfix">
				<div class="vipPopularityL">
					<img src="pages/home/read/images/myPic1.png" alt="" />
				</div>
				<div class="vipPopularityR">
					<h3>绝剑江湖之浪剑传奇</h3>
					<p>全民偶像“之称的可爱明星莫依，在华夏最具盛名京都花台举办个人演唱会，正是多年来梦寐以求多年来梦寐以求</p>
					<div class="clearfix vipPopularityRR clearfix">
						<h4>特立独行的猫</h4>
						<div class="clearfix">
							<span class="vipPopularityRRE">VIP</span>
							<span>玄幻</span>
							<span>354万人在看</span>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
	<div class="vipPopularityMore">
		查看更多
	</div>
	<div class="boyStyleDe">
		<h2>斩妖妃虐暴君：皇后保卫战</h2>
		<p>女主表示：势必打赢这场勾搭与反勾搭，算计与被算计！</p>
		<img src="pages/home/read/images/myPic30.png" alt="" />
	</div>
	<div class="boyStyleAll clearfix">
		<ul>
			<li class="clearfix">
				<div>
					<h2>最美电子书</h2>
					<p>不只是精美的插图，精巧的是精美的插图</p>
					<img src="pages/home/read/images/myPic30.png" alt="" />
				</div>
				<div>
					<h2>最美电子书</h2>
					<p>不只是精美的插图，精巧的是精美的插图</p>
					<img src="pages/home/read/images/myPic30.png" alt="" />
				</div>
			</li>
			<li class="clearfix">
				<div>
					<h2>最美电子书</h2>
					<p>不只是精美的插图，精巧的是精美的插图</p>
					<img src="pages/home/read/images/myPic30.png" alt="" />
				</div>
				<div>
					<h2>最美电子书</h2>
					<p>不只是精美的插图，精巧的是精美的插图</p>
					<img src="pages/home/read/images/myPic30.png" alt="" />
				</div>
			</li>
		</ul>
	</div>
	<div class="vipBottom clearfix">
		<span style="margin-left: 0.3rem;"></span>
		<p>我是有底线的</p>
		<span></span>
	</div>
	<script>
		$(document).on('click','.vipPopularityMore',function(){
			var html='<li class="clearfix"><div class="vipPopularityL"><img src="pages/home/read/images/myPic1.png" /></div><div class="vipPopularityR"><h3>绝剑江湖之浪剑传奇</h3><p>全民偶像“之称的可爱明星莫依，在华夏最具盛名京都花台举办个人演唱会，正是多年来梦寐以求多年来梦寐以求</p><div class="clearfix vipPopularityRR clearfix"><h4>特立独行的猫</h4><div class="clearfix"><span class="vipPopularityRRE">VIP</span><span>玄幻</span><span>354万人在看</span></div></div></div></li>'
			$('.vipPopularity ul').append(html);
		})
	</script>

	<script src="pages/home/read/js/rem.js"></script>
</body>
</html>


