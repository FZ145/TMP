<%--
  Created by IntelliJ IDEA.
  User: yuanyao
  Date: 2016/4/16
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form id="register-from" action="/register.do" method="post">
    <input id="registerName" type="text" >
    <input id="registerPassword" type="text">
    <select name="indentifyCode" type="text"  class="select identifyCode">
        <option value="renter">用户</option>
        <option value="component">云组件</option>
        <option value="provider">云</option>
    </select>
</form>
</body>
</html>
