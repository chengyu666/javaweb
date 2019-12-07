<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/5
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>所有错误代码信息</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">所有错误代码信息</h3>
    </div>
    <div class="panel-body">
        <table width="100%" class="table table-bordered table-striped">
            <tr>
                <td>错误代码</td>
                <td>描述</td>
                <td>更新日期</td>
            </tr>
            <c:forEach items="${codeList}" var="item">
                <tr>
                    <td>${item.code}</td>
                    <td>${item.message}</td>
                    <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
        </table>
        <p>TODO here</p>
    </div>
</div>
</body>
</html>
