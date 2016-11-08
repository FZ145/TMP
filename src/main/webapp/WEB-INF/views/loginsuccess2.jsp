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
        body {
            text-align:center;
        }
        a img{
            boder:0px;}

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

                $.get(
                        "/user/queryHistory.do",
                        function(data){
                          huatu(data);
                        }
                );

            });
            $('#trustChangeTable').click(function(){
                $('#chart').load('index.html');
                $.get(
                        "/user/queryHistory.do",
                        function(data){
                           // drawTable(data);
                           if(data.length<=0){
                               alert("没有找到有效数据！");
                           }else{
                                //$('#chart').load('loginsuccess1.html');
                           }
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
        <td width="1366" height="80" style="background-image: url('/images/header.jpg')" align="right">
                <!--P标签显示登录信息 -->
                <p>
                    尊敬的 <span id="identify"> ${result.entityId}</span> 欢迎您！

                    身份: <span id="id">${result.indentifyCode}</span>
                   <!-- <a onclick="loginOut()" href="javascript:">安全退出</a> -->
                </p>
        </td>

    </tr>

    <tr>
        <td width="1260" height="624">
            <table id="Table_1" width="1260" height="624" border="0" cellpadding="0" cellspacing="0">
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
                                    <table id="Table_3" width="193" height="389" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td>
                                                <p id="personalCenter" ><img src="images/personalCenter.jpg" width="193" height="77" alt=""></p></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="http://www.baidu.com" target="_blank"><img src="images/setting.jpg" width="193" height="58" alt=""></a></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p ><img src="images/trustRecord.jpg" width="193" height="83" alt=""></p></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <!--点击此处，将会查询显示信任变化趋势图 -->
                                                <a id="truestChangeChart" href="javascript:void(0);" ><img src="images/trustChangeChart.jpg" width="193" height="65" alt=""></a></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <!--点击此处，将会查询显示交互的具体信息 -->
                                                <a  id="trustChangeTable" href="javascript:void(0);" ><img src="images/trustChangeTable.jpg" width="193" height="52" alt=""></a></td>
                                        </tr>

                                        <tr>
                                            <td>
                                                <a href="http://www.baidu.com" target="_blank"><img src="images/decision.jpg" width="186" height="83" alt=""></a></td>
                                        </tr>


                                        <tr>
                                            <td>
                                                <a href="http://www.baidu.com" target="_blank"><img src="images/service_quality.jpg" width="186" height="55" alt=""></a></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="http://www.baidu.com" target="_blank"><img src="images/trust_value.jpg" width="186" height="53" alt=""></a></td>
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
                        <img src="images/right.jpg" width="960" height="624" alt="">
                    </td>

                </tr>
            </table>
        </td>
    </tr>
  <!--
    <tr>
        <td>
            <img src="images/bottom.jpg" width="1260" height="64" alt=""> </td>
    </tr>
    -->
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
<script type="text/javascript">
    function drawTable(data){
        var i = 0;
        var j = 0;
        var obj='';
        var txt='';

        $(tbody).empty();
        //把data中的数据取出来，用for循环遍历
        for(i= 0;i<data.length;i++){
            obj = data[i];
            //把每一次交互的各项指标取出
            for(j in obj){
                //使用指标拼接td
                txt= txt +'<td>'+obj[j]+'</td>';
            }
            //把单个内容插入表格
            $(txt).appendTo('tbody').wrapAll('<tr></tr>');
            txt= '';
        }
        $('#chart').innerHTML;
    }


</script>
</body>
</html>




