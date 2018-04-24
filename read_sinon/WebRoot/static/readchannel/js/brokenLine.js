Highcharts.chart('container', {
    chart: {
        type: 'line'
    },
    title: {
        text: ''
    },
//  subtitle: {
//      text: 'Source: WorldClimate.com'
//  },
    xAxis: {
        categories: ['2017-9','2017-10','2017-11','2017-12','2018-01','2018-02','2018-03','2018-04','2018-05','2018-06']
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
            enableMouseTracking: false
        }
    },
    series: [{
        name: '充值金额',
        data: [100000,120000,140000,80000,190000,200000,210000,180000,160000,130000]
    }]
});


























