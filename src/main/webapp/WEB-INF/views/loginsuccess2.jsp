<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html >
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>
        欢迎使用信任管理系统
    </title>
    <style>

        body
        {
            text-align:center;
        }

        a img{boder:0px;}

        a{text-decoration:none;}

    </style>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>

    <script type="text/javascript" src="js/Chart.js"></script>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

    <script type="text/javascript" src="js/hi.js"></script>

    <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
    <script type="text/javascript">


        $(document).ready(function(){
            $('#truestChangeChart').click(function(){
              // queryTrustChange();
                $.get(
                        "/user/queryHistory.do",
                        function(data){
                          huatu(data);
                        }
                );
            });

        });
    </script>

</head>
<!--body部分背景色 -->
<body bgcolor="#FFFFFF" >
<!--表格部分 id=_01 -->
<table id="__01" width="1260" height="768" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="1260" height="80"  style="background-image: url('/images/sy_01.jpg')" align="right">

                <!--P标签显示登录信息 -->
                <p>
                    尊敬的 <span id="identify"> ${result.entityId}</span> 欢迎您！

                    身份：      <span id="id">${result.indentifyCode}</span>
                   <!-- <a onclick="loginOut()" href="javascript:">安全退出</a> -->
                </p>

        </td>

    </tr>

    <tr>
        <td width="1260" height="624">
            <table id="Table1" width="1260" height="624" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="300" height="624">
                        <table id="Table2" width="300" height="624" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <!--表格跨三列，即图片 sy_02_01_01.jpg跨三列显示-->
                                <td colspan="3">
                                    <img src="images/sy_02_01_01.jpg" width="300" height="47" alt=""></td>
                            </tr>
                            <tr>
                                <!--表格跨两行，即图片 sy_02_01_02.jpg跨两行显示-->
                                <td rowspan="2">
                                    <img src="images/sy_02_01_02.jpg" width="76" height="577" alt=""></td>
                                <td width="193" height="389">
                                    <table id="Table3" width="193" height="389" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td>
                                                <p id="personalCenter" ><img src="images/personalCenter.jpg" width="193" height="77" alt=""></p></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="http://www.baidu.com" target="_blank"><img src="images/setting.jpg" width="193" height="58" alt=""></a></td>
                                        </tr>
                                        <tr>
                                            <td width="193" height="54">
                                                <a href="http://www.baidu.com" target="_blank"></a></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p ><img src="images/trustRecord.jpg" width="193" height="83" alt=""></p></td>

                                        </tr>
                                        <tr>
                                            <td>
                                                <a id="truestChangeChart" href="javascript:void(0);" ><img src="images/trustChangeChart.jpg" width="193" height="65" alt=""></a></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="http://www.baidu.com" target="_blank"><img src="images/trustChangeTable.jpg" width="193" height="52" alt=""></a></td>
                                        </tr>
                                    </table>
                                </td>
                                <td rowspan="2">
                                    <img src="images/sy_02_01_04.jpg" width="31" height="577" alt=""></td>
                            </tr>
                            <tr>
                                <td>
                                    <img src="images/sy_02_01_05.jpg" width="193" height="188" alt=""></td>
                            </tr>
                        </table>
                    </td>
                    <td id="chart">
                        <img src="images/sy_02_02.jpg" width="960" height="624" alt="">
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <img src="images/sy_03.jpg" width="1260" height="64" alt=""></td>
    </tr>
</table>



<script type="text/javascript">

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
                name: '${result.entityId}',
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
        $('#chart').highcharts(json);
    }


</script>

</body>
</html>




