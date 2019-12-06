<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/11/28
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>3D打印机错误代码查询系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
    </script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>

<div class="title">
    <table>
        <td class="td-title">
            <h2>
                3D打印机错误代码查询系统
                <small><p>Version ${version}</p></small>
            </h2>
        </td>
        <td class="td-info">
            <small>
                <p>用户名：${name}</p>
                <p>用户ID：${id}</p>
            </small>
        </td>
    </table>
</div>
<div class="left-panel panel panel-default">
    <div class="left-title panel-heading">
        <h4 style="margin: 0px"><span class="glyphicon glyphicon-cog"></span>选择功能</h4>
    </div>
    <script type="text/javascript">
        function changePage(pagePath) {
            const rightContent = $("#right-content");
            rightContent.empty();
            rightContent.load(pagePath);
        }
    </script>
    <div class="panel-body">
        <ul class="list-group">
            <li onclick="changePage('${pageContext.request.contextPath}/include/introduce.jsp')"
                class="list-group-item btn btn-default btn-lg btn-block text-left">
                <span class="glyphicon glyphicon-star"></span>系统介绍
            </li>
            <li onclick="changePage('${pageContext.request.contextPath}/include/search.jsp')"
                class="list-group-item btn btn-default btn-lg btn-block text-left">
                <span class="glyphicon glyphicon-search"></span>查询代码
            </li>
            <li onclick="changePage('${pageContext.request.contextPath}/allCodes')"
                class="list-group-item btn btn-default btn-lg btn-block text-left">
                <span class="glyphicon glyphicon-th-list"></span>查看所有
            </li>
        </ul>
    </div>
</div>
<div class="right-panel">

    <div id="right-content">
        <jsp:include page="../../include/introduce.jsp"/>
    </div>
</div>
<div class="panel-footer">
    当前时间: <%= new java.util.Date() %>
    所在IP:<%out.println(request.getRemoteAddr());%>
</div>
</body>
</html>
