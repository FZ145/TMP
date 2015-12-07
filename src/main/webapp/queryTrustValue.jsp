<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shining.cui
  Date: 2015/12/7
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信任评估信息查询</title>
</head>
<body>
<div align="center">
    <table style="border-style: dotted; border-width: 1px">
        <tr>
            <td width="800" height="100"
                style="font-family: 华文行楷; font-size: 70px; border-style: ridge; border-width: 1px; color:#332590; font-style:italic"
                bgcolor="#C0C0C0" align="center">
                信任评估数据查询
            </td>
        </tr>
    </table>
</div>
<div align="center">
    <form action="/query/queryTrustValue.do" >
        <label>
            <select name="entityType">
                <option value="1">组件</option>
                <option value="2">租户</option>
            </select>
        </label>
        <input name="trustorUid" type="text" placeholder="请输入评估主体UID"/>
        <input name="trusteeUid" type="text" placeholder="请输入评估客体UID"/>
        <label>
            <select name="actionType">
                <option value="1">层内</option>
                <option value="2">层间</option>
            </select>
        </label>

        <input type="submit" value="查询"/><br/>
    </form>
</div>
<br/>
<table border="1" id="table1" align="center">
    <tr>
        <td>id</td>
        <td>实体Uid</td>
        <td>评估主体Uid</td>
        <td>评估客体Uid</td>
        <td>行为信任值</td>
        <td>信任评估时间</td>
        <td>交互类型</td>
    </tr>
    <c:forEach items="${trustValues}" var="trustValue">
        <tr>
            <td>
                <c:out value="${trustValue.id}"/>
            </td>
            <td>
                <c:out value="${trustValue.uid}"/>
            </td>
            <td>
                <c:out value="${trustValue.trustorUid}"/>
            </td>
            <td>
                <c:out value="${trustValue.trusteeUid}"/>
            </td>
            <td>
                <c:out value="${trustValue.trustValue}"/>
            </td>
            <td>
                <c:out value="${trustValue.createTime}"/>
            </td>
            <td>
                <c:if test="${trustValue.actionType == '1'}">层内交互</c:if>
                <c:if test="${trustValue.actionType == '2'}">层间交互</c:if>
            </td>
        </tr>
    </c:forEach>
</table>



</body>
</html>
