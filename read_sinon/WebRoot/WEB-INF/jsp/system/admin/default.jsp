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
	<title>数据统计平台概况</title>
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
	</script>	
</head>
<body onload="CrbtOrders()">
	<div class="announcementManagementTop clearfix">
		<a class="announcementManagementTop1" href="javascript:void(0)" title="">数据统计</a>
		<img src="static/read/images/myPic12.png" alt="" />
		<a class="announcementManagementTop2" style="color: #999999;" href="javascript:void(0)" title="">平台概况</a>
	</div>
	<form action="<%=basePath%>login_default.do" id="Form" name="Form" method="post">
	<div class="platformOverviewCumulative clearfix">
		<div class="platformOverviewCumulativeD clearfix">
			<h2>累计充值</h2>
			<h1>${pd.recharges }</h1>
			<h3>笔数：${pd.rechargeTimes }</h3>
			<div class="clearfix platformOverviewCumulativeDe">
				<div>
					<p>当月</p>
					<h1>${pd.rechargesMonth }</h1>
				</div>
				<div style="float: right; margin-right: 34px;">
					<p>今日</p>
					<h1>${pd.rechargesDay }</h1>
				</div>
			</div>
		</div>
		<div style="margin-left: 20px;" class="platformOverviewCumulativeD clearfix">
			<h2>累计读者</h2>
			<h1>${pd.usersAll }</h1>
			<div style="margin-top: 41px;" class="clearfix platformOverviewCumulativeDe">
				<div>
					<p>当月</p>
					<h1>${pd.usersMonth }</h1>
				</div>
				<div style="float: right; margin-right: 34px;">
					<p>今日</p>
					<h1>${pd.usersDay }</h1>
				</div>
			</div>
		</div>
		<div style="margin-left: 20px;" class="platformOverviewCumulativeD clearfix">
			<h2>累计渠道</h2>
			<h1>${pd.countSysUsers }</h1>
			<div style="margin-top: 41px;" class="clearfix platformOverviewCumulativeDe">
				<div>
					<p>渠道粉丝</p>
					<h1>${pd.SysUserFans }</h1>
				</div>
				<div style="float: right; margin-right: 34px;">
					<p>付费粉丝</p>
					<h1>${pd.SysUserFansFee }</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="platformOverviewRecharge">
		<h1>昨日充值</h1>
		<div class="platformOverviewRechargeD clearfix">
			<div class="platformOverviewRechargeDe">
				<p>充值金额</p>
				<h1>${pd.rechargesYesterDay }</h1>
			</div>
			<span></span>
			<div class="platformOverviewRechargeDe">
				<p>充值笔数</p>
				<h1>${pd.rechargeTimesYesterDay }</h1>
			</div>
			<span></span>
			<div class="platformOverviewRechargeDe">
				<p>充值人数</p>
				<h1>${pd.countUserid }</h1>
			</div>
			<span></span>
			<div class="platformOverviewRechargeDe">
				<p>复充人数</p>
				<h1>${pd.SumUserIds }</h1>
			</div>
		</div>
	</div>
	<div class="platformOverviewChart">
		<h1>充值图表</h1>
		<div class="platformOverviewChartSel clearfix">
			<span id="span1" class="platformOverviewChartSelAct">近一年</span>
			<span id="span2">近30天</span>
			<div class="clearfix">
				<input type="text" id="test6" name="date11" onchange="fun1();" readonly="readonly"/>
				<img src="static/read/images/myPic13.png" alt="" />
			</div>
		</div>
		<div class="platformOverviewChartDe">
			<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		</div>
	</div>
	<div class="platformOverviewDaily">
		<h1>充值日报</h1>
		<div class="platformOverviewDailySel clearfix">
			<div class="clearfix">
				<input id="sendtime" name="sendtime" readonly="readonly" value="${pd.sendtime }"/>
				<img src="static/read/images/myPic13.png" alt="" />
			</div>
			<a href="javascript:void(0)" title=""><span id="outExcel">导出表格</span></a>
		</div>
		<div class="platformOverviewDailyDeTop clearfix">
			<span style="width: 146px; margin-left: 29px;">时间</span>
			<span style="width: 166px;">充值人数</span>
			<span style="width: 160px;">充值笔数</span>
			<span style="width: 180px;">充值金额(分)</span>
			<span style="width: 166px;">月累计</span>
			<span>总累计</span>
		</div>

		<div class="platformOverviewDailyDeTit">
			<ul>
				<c:choose>
					<c:when test="${not empty pd.listDayData}">
						<c:forEach items="${pd.listDayData}" var="var" varStatus="vs">
							<li style="background: #fafafa;" class="clearfix">
								<div style="width: 146px; margin-left: 29px;">${var.RECHARGETIME }</div>
								<div style="width: 166px;">${var.RECHARGEUSERS }</div>
								<div style="width: 160px;">${var.RECHARGETIMES }</div>
								<div style="width: 180px;">${var.RECHARGEDAYALL }</div>
								<div style="width: 166px;">${var.RECHARGEMONTHALL }</div>
								<div>${var.RECHARGEALL}</div></li>
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
	<script src="static/read/layDate-v5.0.8/laydate/laydate.js"></script>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="static/read/js/brokenLine.js"></script>
	<script type="text/javascript">
		$('#outExcel').click(function(){
			var sentTime = $("#sendtime").val();
			window.location="<%=basePath%>countday/excelOut.do?sentTime="+sentTime;
		});
	</script>
	<script>
	
		laydate.render({
		  elem: '#test6'
		  ,range: true
		});
		laydate.render({
		  elem: '#sendtime'
		  ,range: true
		});
	
		function fun(){
		    obj = document.getElementsByTagName("span");
		    class_name = "name";
		    for(i in obj){
		        if(obj[i].className == class_name){
		            alert(obj[i].innerHTML);
		            return
		        }
		    }
		}		
		
		function fun1(){
			alert();
		}
			$(function(){
					$("#span1").click(function(){
					var date=new Array();
					var number = new Array();
				       $.ajax({
				            type: "post",
				            data:{},
				            url: "<%=basePath%>countmonth/date.do",
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
				            url: "<%=basePath%>countday/date.do",
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
			url: "<%=basePath%>countmonth/date.do",
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