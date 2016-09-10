<%--
  Created by IntelliJ IDEA.
  User: yuanyao
  Date: 2016/4/11
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="js/hi.js"></script>
    <script type="text/javascript" src="js/chart.js"></script>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <title>登录成功</title>
</head>
<body>
<div id="user-name-label" align="right">
    <h1>${result.entityId}</h1>
</div>

<br/>
<div id="indentifyCode--label" align="right">
    ${result.indentifyCode}
</div>


<hr/>
<input type="button" value="click" onclick="huatu()">
<hr/>

<div id="say" align="right">

</div>

<div id="style="width: 800px; height: 400px; margin: 0 auto></div>
<hr/>
<input type="button" value="通过js中函数查询交互数据" onclick="show()">
<div id="show" ></div>

<script type="text/javascript">
    function loadXMLDoc()
    {
        var xmlhttp;
        if (window.XMLHttpRequest)
        {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
        }
        else
        {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                //document.getElementById("show").innerHTML=xmlhttp.responseText;
            var txt='xmlhttp.responseText';
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
                    }]
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

                var txt='{"result":[{"id":104,"uid":"1457072937063component1","trustorUid":"component1","trusteeUid":"renter3","trustValue":0.7970,"actionTime":1457072937000,"actionType":2},{"id":105,"uid":"1457072937074component1","trustorUid":"component1","trusteeUid":"renter4","trustValue":0.7110,"actionTime":1457072937000,"actionType":2},{"id":117,"uid":"1457072937238component1","trustorUid":"component1","trusteeUid":"renter2","trustValue":0.7390,"actionTime":1457072937000,"actionType":2},{"id":126,"uid":"1457072937355component1","trustorUid":"component1","trusteeUid":"renter4","trustValue":0.6850,"actionTime":1457072937000,"actionType":2},{"id":128,"uid":"1457072937381component1","trustorUid":"component1","trusteeUid":"renter3","trustValue":0.6710,"actionTime":1457072937000,"actionType":2},{"id":137,"uid":"1457072937500component1","trustorUid":"component1","trusteeUid":"renter3","trustValue":0.8860,"actionTime":1457072938000,"actionType":2},{"id":140,"uid":"1457072937541component1","trustorUid":"component1","trusteeUid":"renter1","trustValue":0.7740,"actionTime":1457072938000,"actionType":2},{"id":164,"uid":"1457072937855component1","trustorUid":"component1","trusteeUid":"renter3","trustValue":0.8960,"actionTime":1457072938000,"actionType":2},{"id":168,"uid":"1457072937908component1","trustorUid":"component1","trusteeUid":"renter3","trustValue":0.6930,"actionTime":1457072938000,"actionType":2},{"id":171,"uid":"1457072937946component1","trustorUid":"component1","trusteeUid":"renter4","trustValue":0.7970,"actionTime":1457072938000,"actionType":2},{"id":179,"uid":"1457072938085component1","trustorUid":"component1","trusteeUid":"renter2","trustValue":0.6960,"actionTime":1457072938000,"actionType":2},{"id":181,"uid":"1457072938116component1","trustorUid":"component1","trusteeUid":"renter2","trustValue":0.8680,"actionTime":1457072938000,"actionType":2},{"id":184,"uid":"1457072938151component1","trustorUid":"component1","trusteeUid":"renter2","trustValue":0.6320,"actionTime":1457072938000,"actionType":2},{"id":188,"uid":"1457072938208component1","trustorUid":"component1","trusteeUid":"renter4","trustValue":0.8590,"actionTime":1457072938000,"actionType":2},{"id":194,"uid":"1457072938283component1","trustorUid":"component1","trusteeUid":"renter3","trustValue":0.9840,"actionTime":1457072938000,"actionType":2}]}';

                <!--解析json串 -->
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

                $('#show').highcharts(json);
            }
        }
        xmlhttp.open("post","/user/queryHistory.do",true);
        xmlhttp.send();
    }
</script>
<hr/>
<input type="button" value="通过ajax查询交互数据" onclick=loadXMLDoc()>
<hr/>
<input type="button" value="直接查询交互数据" onclick="window.location.href='/user/queryHistory.do'">
</body>
</html>
