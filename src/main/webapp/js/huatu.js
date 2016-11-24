

    function huatu(data){
        var title = {
            text: '信任变化趋势图'
        };
        var subtitle = {
            text: ''
        };
        var xAxis = {
            categories: [yaxis]
        };
        var yAxis = {
            title: {
                text: '信任值'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            min: 0,
            max:1
        };
        var tooltip = {
            valueSuffix: ''
        }
        var legend = {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        };

        <!--解析json串 -->

        var txt=data;
        var obj  = eval ("(" + txt + ")");
        var data = new Array();
        var data = obj.result;
        var xaxis = new Array();
        var yaxis = new Array();
        for(var i=0;i<data.length;i++){
            xaxis[i]=data[i].actionTime;
            yaxis[i]=data[i].trustValue;
        }
        var series =  [
            {
                name: '<%= ${result.entityId}%>',
                data: yaxis,
            },
        ];
        var json = {};
        json.title = title;
        json.subtitle = subtitle;
        json.xAxis = xAxis;
        json.yAxis = yAxis;
        json.tooltip = tooltip;
        json.legend = legend;
        json.series = series;
        $('#show').highcharts(json);
    }


