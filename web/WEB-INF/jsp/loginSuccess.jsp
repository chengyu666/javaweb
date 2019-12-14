<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/11/27
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginResult</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="title">
    <h2>3D打印机销售系统<small><p>Version ${version}</p></small></h2>
</div>
<div class="panel panel-default" id="login-box">
    <div class="panel-heading">
        <h4 class="panel-title">
            登陆成功！
        </h4>
    </div>
    <div class="panel-body">
        <div>
            <input type="button" formaction="${pageContext.request.contextPath}/commonUser" value="点此进入系统"/>
        </div>
    </div>
</div>
<div class="panel-footer">当前时间: <%= new java.util.Date() %></div>

</body>
</html>
