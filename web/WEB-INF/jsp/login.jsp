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
    <title>登录系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="title">
    <h2>3D打印机错误代码查询系统<small><p>Version ${version}</p></small></h2>
</div>
<div class="panel panel-default" id="login-box">
    <div class="panel-heading">
        <h4 class="panel-title">
            登陆
        </h4>
    </div>
    <p class="result">${loginResult}</p>
    <!--TODO-->
    <div class="panel-body">
        <form action="${pageContext.request.contextPath}/login/try" method="post" style="padding-top:-700px;">
            <p>用户名</p><input type="text" name="name" value=""><br>
            <p>密码</p><input type="password" name="password" value=""><br><br>

            <div>
                <button type="submit" value="Submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> 登陆</button>
                ........
                <button type="reset" value="Reset" class="btn btn-primary"><span class="glyphicon glyphicon-repeat"></span> 重置</button>
            </div>
        </form>
    </div>
</div>
<div class="panel-footer">当前时间: <%= new java.util.Date() %>
</div>

</body>
</html>
