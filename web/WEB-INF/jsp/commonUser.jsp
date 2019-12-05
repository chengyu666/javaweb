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
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
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
    <div class="left-title panel-heading"><h4><span class="glyphicon glyphicon-cog"></span>选择功能</h4></div>
    <div class="panel-body">
        <ul class="list-group">
            <li class="list-group-item btn btn-default btn-lg btn-block"><span class="glyphicon glyphicon-search"></span>查询代码</li>
        </ul>
    </div>
</div>
<div class="right-panel panel panel-default">

    <div class="right-content">
        <jsp:include page="introduce.jsp"></jsp:include>
    </div>
</div>
<div>

</div>
</body>
</html>
