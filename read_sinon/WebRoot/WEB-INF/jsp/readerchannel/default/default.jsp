﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<title>数据统计</title>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/ThunderBase.css"/>
	<link rel="stylesheet" type="text/css" href="static/readchannel/css/otherPages.css"/>
	<script src="static/readchannel/js/jquery-1.12.4.min.js"></script>
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">数据统计</a>
	</div>
	<div class="platformOverviewCumulative clearfix">
		<div class="platformOverviewCumulativeD clearfix">
			<h2>累计收益</h2>
			<h1>${Profitpd.RECHARGES }</h1>
			<div style="margin-top: 41px;" class="clearfix platformOverviewCumulativeDe">
				<div>
					<p>当月</p>
					<h1>${ProfitMonth.RECHARGES }</h1>
				</div>
				<div style="float: right; margin-right: 34px;">
					<p>今日</p>
					<h1>${ProfitDay.RECHARGES }</h1>
				</div>
			</div>
		</div>
		<div style="margin-left: 20px;" class="platformOverviewCumulativeD clearfix">
			<h2>累计读者</h2>
			<h1>${userpdsum.USERID }</h1>
			<h3>付费数：${userpdsumfee.UsersSumFee }
				<c:if test="${pd.Percentage !=null && pd.Percentage != ''}">
					（${pd.Percentage }）
				</c:if>
			</h3>
			<div class="clearfix platformOverviewCumulativeDe">
				<div>
					<p>当月</p>
					<h1>${userpdsummonth.USERID }</h1>
				</div>
				<div style="float: right; margin-right: 34px;">
					<p>今日</p>
					<h1>${userpdsumday.USERID }</h1>
				</div>
			</div>
		</div>
		<div style="margin-left: 20px;" class="platformOverviewCumulativeD clearfix">
			<h2>累计充值</h2>
			<h1>${rechargesum.recharges }</h1>
			<h3>笔数：${rechargesum.rechargeTimes }</h3>
			<div class="clearfix platformOverviewCumulativeDe">
				<div>
					<p>当月</p>
					<h1>${rechargesummonth.recharges }</h1>
				</div>
				<div style="float: right; margin-right: 34px;">
					<p>今日</p>
					<h1>${rechargesumday.recharges }</h1>
				</div>
			</div>
			<a href="<%=basePath%>channelrechargelog/goStatisticsDay.do" title="">查看详情</a>
		</div>
	</div>
	<div class="platformOverviewChart">
		<h1>充值图表</h1>
		<div class="platformOverviewChartSel clearfix">
			<span id="span1" class="platformOverviewChartSelAct">近一年</span>
			<span id="span2">近30天</span>
			<div class="clearfix">
				<input type="text" id="sendTime" name="date11" onchange="fun1();" readonly="readonly"/>
				<img src="static/read/images/myPic13.png" alt="" />
			</div>
		</div>
		<div class="platformOverviewChartDe">
			<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		</div>
	</div>
	<script src="static/readchannel/layDate-v5.0.8/laydate/laydate.js"></script>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="static/readchannel/js/brokenLine.js"></script>
	<script>
		function CrbtOrders(){
			//alert(this.document.body.scrollHeight); //弹出当前页面的高度
			var obj = parent.document.getElementById("Thunder"); //取得父页面IFrame对象
			//alert(obj.height); //弹出父页面中IFrame中设置的高度
			obj.height = this.document.body.scrollHeight; //调整父页面中IFrame的高度为此页面的高度
		}
		laydate.render({
		  elem: '#sendTime'
		  ,range: true
		});
		$(function(){
					$("#span1").click(function(){
					var date=new Array();
					var number = new Array();
				       $.ajax({
				            type: "post",
				            data:{},
				            url: "<%=basePath%>countmonth/sysuserdate.do",
				            dataType:'json', 
				            success: function(data){
				           		 $.each(data, function(index, item){
                            		number[index] = item.RECHARGES;
                            		date[index] = item.COUNT_MONTH.substr(0,7);
                        		});
				           		Highcharts.chart('container', {
								    chart: {
								        type: 'line'
								    },
								    title: {
								        text: ''
								    },
								    xAxis: {
								        categories: date
								    },
								    yAxis: {
								        title: {
								            text: ''
								        }
								    },
								    plotOptions: {
								        line: {
								            dataLabels: {
								                enabled: true
								            },
								            enableMouseTracking: true
								        }
								    },
								    series: [{
								        name: '充值金额',
								        data: number
								    }]
								}); 
				            }
				        });
			    	});

					$("#span2").click(function(){
					var date=new Array();
					var number = new Array();
				       $.ajax({
				            type: "post",
				            data:{},
				            url: "<%=basePath%>countday/sysuserdate.do",
				            dataType:'json', 
				            success: function(data){
				           		 $.each(data, function(index, item){
                            		number[index] = item.RECHARGES;
                            		date[index] = item.COUNT_DAY.substr(5,9);
                        		});
				           		Highcharts.chart('container', {
								    chart: {
								        type: 'line'
								    },
								    title: {
								        text: ''
								    },
								    xAxis: {
								        categories: date
								    },
								    yAxis: {
								        title: {
								            text: ''
								        }
								    },
								    plotOptions: {
								        line: {
								            dataLabels: {
								                enabled: true
								            },
								            enableMouseTracking: true
								        }
								    },
								    series: [{
								        name: '充值金额',
								        data: number
								    }]
								    
								}); 
				            }
				        });
			    	});
		laydate.render({
		  elem: '#test6'
		  ,range: true
		});
		$('.highcharts-credits').hide();
		
		$('.platformOverviewChartSel span').click(function(){
			$(this).addClass('platformOverviewChartSelAct').siblings().removeClass('platformOverviewChartSelAct');
		});
		var date=new Array();
		var number = new Array();
		$.ajax({
			type: "post",
			url: "<%=basePath%>countmonth/sysuserdate.do",
			dataType:'json', 
			success: function(data){
				$.each(data, function(index, item){
	                number[index] = item.RECHARGES;
	                date[index] = item.COUNT_MONTH.substr(0,7);
                });
				Highcharts.chart('container', {
					chart: {
						type: 'line'
					},
					title: {
						text: ''
					},
					xAxis: {
						categories: date
					},
					yAxis: {
						title: {
							text: ''
						}
					},
					plotOptions: {
						line: {
							dataLabels: {
								enabled: true
							},
							enableMouseTracking: true
						}
					},
					series: [{
						name: '充值金额',
						data: number
					}]
				}); 
			},
			error: function () {    
            	alert("数据错误");
           	}
		});
	});
	</script>
</body>
</html>
