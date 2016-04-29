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
<input type="button" value="click" onclick="say()">
<hr/>

<div id="say">

</div>

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
                document.getElementById("show").innerHTML=xmlhttp.responseText;
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
