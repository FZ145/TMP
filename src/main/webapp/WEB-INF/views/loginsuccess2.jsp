<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html >
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>
        欢迎使用信任管理系统
    </title>
    <link href="../css/LoginSuccess.css" rel="stylesheet" type="text/css" />

    <link href="../css/stu.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/highcharts.js"></script>
    <script type="text/javascript" src="js/Chart.js"></script>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script type="text/javascript" src="js/hi.js"></script>
</head>
<body>



<!--容器 -->
<div class="containner">

    <!--这一部分代码是用来控制显示右上角用户名及用户身份 -->
    <!-- 横幅 标语-->
    <div class="banner">
        <!-- 页面 -->
        <div class="page">
            <!--右上角显示用户名与身份的地方，如果不用这个div，则显示不靠右 -->
            <div class="topxx">
                <p>
                    尊敬的 <span id="identify"> ${result.entityId}</span> 欢迎您！

                    身份：      <span id="id">${result.indentifyCode}</span>
                    <a onclick="loginOut()" href="javascript:">安全退出</a>
                </p>
            </div>
        </div>
    </div>

    <!-- 这部分代码用来控制左边抽屉显示设置、个人中心、信任值变化趋势及变化细节-->
    <div class="page">

        <div class="box mtop">
            <div class="leftbox">
                <div class="l_nav2">
                    <div class="ta1">
                        <!--stong标签为加粗 -->
                        <strong>个人中心</strong>
                        <div class="leftbgbt">
                        </div>
                    </div>
                    <!--左边抽屉栏第一部分，即个人中心及设置等 -->
                    <div class="cdlist">
                        <div>
                            <a href="Index.aspx.html">设置</a></div>
                        <div>
                            <a href="ClassInfo.aspx.html"> 我就是空白</a>
                        </div>
                    </div>

                    <!-- 左边抽屉第二部分，即信任值变化-->
                    <div class="ta1">
                        <strong>信任记录</strong>
                    </div>
                    <div class="cdlist">
                        <div align="center">
                            <a href="../MyAccount/IndexJH.aspx.html">信任值变化趋势</a>
                            <button name=trustChange, value="信任值变化" onclick=queryTrustChange()>信任值变化</button>
                            <div>
                                <a href="../MyAccount/IndexJH.aspx.html">交互记录</a>
                                <button name = trustChangeDetail, value="交互记录" onclick="">交互记录</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--这部分为主要显示部分，即右边部分 -->
            <div class="rightbox" id = "show" >
                <div class="welcome" align="center" >
                    <p align="center"><span style="font: 600 72px Simsun"> 欢&nbsp;迎&nbsp;使&nbsp;用 </span></p>
                </div>
            </div>
        </div>

        <!-- 页面底部版权部分-->
        <div class="footer">
            <p>copyright 没版权，我在这儿瞎扯呢 </p>
        </div>
        <!--js函数queryTrustChange，用来生成信任趋势走向图 -->
        <script type="text/javascript">
            function queryTrustChange(){}
                    var xmlhttp;
                 if (window.XMLHttpRequest) {
                     // code for IE7+, Firefox, Chrome, Opera, Safari
                     xmlhttp = new XMLHttpRequest();
                 }
                    else{
                     xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                 }
            xmlhttp.onreadystatechange=function()
            {
                if (xmlhttp.readyState==4 && xmlhttp.status==200)
                {
                    //txt为异步刷新获取的信任数据
                    // txt=xmlhttp.responseText;
                   // document.getElementById("show").innerHTML=txt;
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
                            name: 'renter1',
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


        </script>

     </div>
    </div>
       </body>
</html>




