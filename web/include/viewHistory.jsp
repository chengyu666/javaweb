<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/16
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>浏览历史</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">全站浏览日志</h3>
    </div>
    <div class="panel-body">
        <table width="100%" class="table table-bordered table-striped">
            <tr>
                <td>用户</td>
                <td>型号</td>
                <td>时间</td>
            </tr>
            <c:forEach items="${historyList}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.code}</td>
                    <td>${item.time} </td>
                </tr>
            </c:forEach>
        </table>
        <p>---到底啦---</p>
    </div>
</div>
</body>
</html>
