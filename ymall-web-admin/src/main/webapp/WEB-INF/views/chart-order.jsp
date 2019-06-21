<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../includes/header.jsp"/>
    <title>订单销量统计</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 统计管理 <span
        class="c-gray en">&gt;</span> 订单销量统计 <a class="btn btn-success radius r"
                                                style="line-height:1.6em;margin-top:3px"
                                                href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" onclick="getChartData(0)" class="btn btn-secondary-outline radius">本周</a>
			<a href="javascript:;" onclick="getChartData(1)" class="btn btn-secondary-outline radius">本月</a>
			<a href="javascript:;" onclick="getChartData(2)" class="btn btn-secondary-outline radius">上个月</a>
			<select class="select-box" style="width: 100px" id="year" onchange="getYearData()">

			</select>
			&nbsp;&nbsp;指定日期范围：
            <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'startDate\')||\'%y-%M-%d\'}' })"
                   id="startDate" name="startDate" class="input-text Wdate" style="width:120px;">
            -
            <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'endDate\')}',maxDate:'%y-%M-%d' })"
                   id="endDate" name="endDate" class="input-text Wdate" style="width:120px;">
			&nbsp;&nbsp;<a href="javascript:;" onclick="getCustomData()" class="btn btn-success">确定</a>
		</span>
        <span class="r">总销售额：<strong id="countAll">0.00</strong> ￥</span>
    </div>
    <div id="container" style="min-width:700px;height:450px;margin-top: 10px;"></div>
</div>

<jsp:include page="../includes/footer.jsp"/>


<!-- WdatePicker -->
<script type="text/javascript" src="/static/assets/lib/My97DatePicker/4.8/WdatePicker.js"></script>

<!-- echarts -->
<script type="text/javascript" src="/static/assets/lib/echarts.common.min.js"></script>
<script type="text/javascript">

    /**
     * 初始化年份信息
     */
    $(document).ready(function () {
        $("#year").append("<option selected value='-1'>按年统计</option>");
        var startYear = 2019, nowYear = new Date().getFullYear();
        for (var i = 0; i <= nowYear - startYear + 1; i++) {
            $("#year").append("<option value='" + startYear + "'>" + startYear + "年</option>");
            startYear += 1;
        }
    });

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('container'), 'light');
    var xDatas = [], yDatas = [], startDate, endDate, year = 0;
    getChartData(0, null, null);


    /**
     * 获取图表数据
     * @param type
     */
    function getChartData(type) {
        var index = layer.load(3);
        $.ajax({
            type: 'GET',
            url: '/count/order',
            dataType: 'json',
            data: {
                type: type,
                startTime: startDate,
                endTime: endDate,
                year: year
            },
            success: function (data) {
                layer.close(index);
                if (data.status == 500) {
                    layer.alert(data.message, {title: '错误信息', icon: 2});
                    return;
                }
                console.log(data)
                xDatas = data.result.xDatas;
                yDatas = data.result.yDatas;
                $("#countAll").html(data.result.countAll);
                drawChart();
            },
            error: function (XMLHttpRequest) {
                layer.close(index);
                layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
            }
        });
    }

    function getCustomData() {
        startDate = $('#startDate').val();
        if (startDate == "" || startDate == null) {
            $('#startDate').focus();
            return;
        }
        endDate = $('#endDate').val();
        if (endDate == "" || endDate == null) {
            $('#endDate').focus();
            return;
        }
        getChartData(-1);
    }

    function getYearData() {
        console.log(1)
        year = $('#year').val();
        if (year == "" || year == "-1" || year == null) {
            return;
        }
        getChartData(-2);
    }

    function drawChart() {
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '订单销量统计',
                subtext: 'YMall'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['销售金额']
            },
            toolbox: {
                show: true,
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    dataView: {readOnly: false},
                    magicType: {type: ['line', 'bar']},
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
                boundaryGap: false,
                data: xDatas
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    formatter: '{value} ￥'
                }
            },
            series: [{
                name: '销售金额',
                type: 'line',
                data: yDatas
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
</script>
</body>
</html>