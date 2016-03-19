<%--
  Created by IntelliJ IDEA.
  User: yuanyao
  Date: 2016/3/5
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请注册</title>
</head>
<body>
<form action="RegisterController.do" method="post">

    用户名：<input type="text" name="uid"  ><br/>
    密  码：<input type="text" name="password"  ><br/>
    <select name="indentifyCode" type="text" class="select indentifyCode" style="width:240px">
        <option value="renter">用户</option>

        <option value="provider">云</option>
    </select><br/>
   <input type="submit" value="注册">

</form>
</body>
</html>
