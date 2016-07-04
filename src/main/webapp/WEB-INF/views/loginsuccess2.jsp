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
</head>
<body>

<div class="containner">
    <div class="banner">

        <div class="page">

            <div class="topxx">
                <p>


                    亲爱的 <span id="identify"> ${result.entityId}</span> 欢迎您！
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
                document.getElementById("show").innerHTML=xmlhttp.responseText;
            }
        }
        xmlhttp.open("post","/user/queryHistory.do",true);
        xmlhttp.send();
    }
</script>
</body>
</html>
