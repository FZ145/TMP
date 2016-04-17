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


</body>
</html>
