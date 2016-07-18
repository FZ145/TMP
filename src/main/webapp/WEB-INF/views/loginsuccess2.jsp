<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html >
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>
        欢迎使用信任管理系统
    </title>
    <link href="../css/StudentStyle.css" rel="stylesheet" type="text/css" />

    <link href="../css/stu.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/highcharts.js"></script>
</head>
<body>

<div class="containner">
    <div class="banner">

        <div class="page">

            <div class="topxx">
                <p>


                    尊敬的 <span id="identify"> ${result.entityId}</span> 欢迎您！
                    身份：      <span id="id">${result.indentifyCode}</span>
                    <a onclick="loginOut()" href="javascript:">安全退出</a>
                </p>

            </div>

        </div>

    </div>
    <div class="page">
        <div class="box mtop">
            <div class="leftbox">
                <div class="l_nav2">
                    <div class="ta1">
                        <strong>个人中心</strong>
                        <div class="leftbgbt">
                        </div>
                    </div>
                    <div class="cdlist">
                        <div>
                            <a href="Index.aspx.html">设置</a></div>
                        <div>
                            <a href="ClassInfo.aspx.html"> </a>
                        </div>


                    </div>

                    <div class="ta1">
                        <strong>信任记录</strong>

                    </div>
                    <div class="cdlist">
                        <div align="center">

                           <a name = trustChange,  value="信任值变化"  onclick="queryTrustChange()">信任值变化</a></div>
                        <div>
                            <a href="../MyAccount/IndexJH.aspx.html">交互记录</a></div>

                    </div>
                    <div class="ta1">
                        <strong></strong>
                    </div>
                    <div class="cdlist">
                        <div>
                            <a href=""></a></div>
                        <div>
                            <a href=""></a></div>
                    </div>

                    <div class="ta1">
                        <strong></strong>
                    </div>
                    <div class="cdlist">
                        <div>
                            <a href=""></a></div>
                        <div>
                            <a href=""></a></div>
                    </div>

                </div>
            </div>
            <div class="rightbox" id = "show">

                <div class="xixi" align="center">
                        <p>欢&nbsp;迎&nbsp;使&nbsp;用</p>
                </div>

            </div>
        </div>
        <div class="footer">
            <p>
                copyright  </p>
        </div>
    </div>
</div>
<script type="text/javascript">
    function queryTrustChange()
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
                var txt=xmlhttp.responseText;
               document.getElementById("show").innerHTML=txt;
               // document.getElementById("show").innerHTML=xmlhttp.responseText;
                var obj = eval ("("+txt+")");
                var myobj= new Array();
                var myobj=obj.result;

                var arr= new Array();
                var arrs= new Array();
                for(var i=0;i<myobj.length;i++){
                    arr[i]=myobj[i].actionTime;
                    arrs[i]=myobj[i].trustValue;

                }

                $(document).ready(function() {
                    var title = {       //设置标题
                        text: '信任值走势'
                    };
                    var subtitle = {   //设置副标题
                        text: 'trust value variation'
                    };
                    var xAxis = {   //设置X轴的数据来源
                        categories:arr
                    };
                    var yAxis = {  //Y轴
                        title: {   //设置Y轴标题
                            text: 'Value'
                        },
                        plotLines: [{    //折线
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    };

                    var tooltip = {  //设置鼠标悬停时的数据提示，后缀为空
                        valueSuffix: ''
                    }

                    var legend = {   //图例
                        layout: 'vertical',   //垂直
                        align: 'right',       //靠右
                        verticalAlign: 'middle',  //垂直居中
                        borderWidth: 0             //边线宽度
                    };



                    var json = {};

                    json.title = title;
                    json.subtitle = subtitle;
                    json.xAxis = xAxis;
                    json.yAxis = yAxis;
                    json.tooltip = tooltip;
                    json.legend = legend;
                    json.series = series;

                    $('#show').highcharts(json);
                });
            }
        }
        xmlhttp.open("get","/user/queryHistory.do",true);
        xmlhttp.send();
    }
</script>
</body>
</html>
