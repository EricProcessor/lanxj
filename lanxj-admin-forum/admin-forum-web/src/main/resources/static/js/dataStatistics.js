    // 活跃度统计图
    var dataStatistics = {
        Echart1: function () {
            var dom = document.getElementById("echartOne");
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            option = {
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17',
                        '18', '19', '20', '21', '22', '23', '24'
                    ]
                },
                yAxis: {
                    type: 'value',
                    name: "(百人)"
                },
                tooltip: {
                    trigger: 'axis',
                    formatter: function (params, ticket, callback) {
                        // $.get('detail?name=' + params.name, function (content) {
                        //     callback(ticket, toHTML(content));
                        // });
                        var times = params[0].axisValue;
                        var num = params[0].data * 100;
                        times = times < 10 ? "0" + times + ":00" : times + ":00";
                        return times + '<br />峰值' + num + '人';

                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                series: [{
                    data: [2.34, 4.26, 1.88, 3.33, 6.66, 7.23, 9.55, 5.13, 8.44, 4.56, 5.67, 5.66, 6.66, 2.34, 0.44,
                        4.43, 4.55, 6.60, 5.54, 7.77, 5.66, 7.33, 0, 0
                    ],
                    type: 'line',
                    areaStyle: {},
                    itemStyle: {
                        normal: {
                            color: '#0076ff',
                            lineStyle: {
                                color: '#0076ff',
                            }
                        }
                    }
                }]
            };
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        },
        Echart2: function () {
            // 用户行为统计图
            var dom = document.getElementById("echartTow");
            var myChart2 = echarts.init(dom);
            var app = {};
            option = null;
            option = {
                tooltip: {
                    trigger: 'axis',
                    formatter: function (params, ticket, callback) {
                        // $.get('detail?name=' + params.name, function (content) { 
                        // callback(ticket,toHTML(content)); 
                        // });
                        var times = params[0].axisValue;
                        var num = params[0].data * 100;
                        times = times < 10 ? "0" + times :
                            times;
                        return times + '<br />峰值' + num + '人';
                    },
                    axisPointer: {
                        type: 'cross',
                        label: {
                            backgroundColor: '#6a7985'
                        }
                    }
                },
                legend: {
                    type: 'scroll',
                    data: ['PV', 'UV'],
                    right: '4%'
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data: ['22日', '23日', '24日', '25日', '26日', '27日', '28日']
                }],
                yAxis: [{
                    type: 'value',
                    name: '(百人)'
                }],
                series: [{
                        name: 'PV',
                        type: 'line',
                        smooth: true,
                        lineStyle: {
                            color: '#ff993f'
                        },
                        areaStyle: {

                            color: '#ff993f'
                        },
                        data: [1.50, 2.32, 2.01, 1.54, 1.90, 3.30, 4.10]
                    },
                    {
                        name: 'UV',
                        type: 'line',
                        smooth: true,
                        lineStyle: {
                            color: '#0076ff'
                        },
                        areaStyle: {
                            // normal: {}
                            color: '#0076ff'
                        },
                        data: [3.20, 3.2, 3.01, 3.34, 3.90, 3.30, 3.20]
                    }
                ]
            };;
            if (option && typeof option === "object") {
                myChart2.setOption(option, true);
            }
        },
    }
    dataStatistics.Echart1();
    dataStatistics.Echart2();