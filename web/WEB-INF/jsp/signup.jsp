<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/16
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册账号</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
    </script>
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
            输入新的账户信息
        </h4>
    </div>
    <script type="text/javascript">
        function signup() {
            let name = $("#name").val();
            let password = $("#password").val();
            let password2 = $("#password2").val();
            if (name == null || name === "" || password == null || password === "") {
                alert("必须输入用户名和密码！")
            } else if (password !== password2) {
                alert("两次输入的密码不一致！")
            } else {
                const str = "请确认注册信息：\n" +
                    "用户名：" + name + "\n" +
                    "密码：" + password;
                if (confirm(str)) {
                    let path = "${pageContext.request.contextPath}/signup";
                    path = path + "?name=" + name + "&password=" + password;
                    window.location.href=path;
                }
            }
        }
        function clearInput() {
            $("#name").val("");
            $("#password").val("");
            $("#password2").val("");
        }
    </script>
    <p class="result">${loginResult}</p>
    <div class="panel-body" style="">
        <p>用户名</p><input type="text" id="name" value=""><br>
        <p>密码</p><input type="password" id="password" value=""><br>
        <p>再次确认密码</p><input type="password" id="password2" value=""><br><br>
        <div class="btn-group">
            <button onclick="signup()" class="btn btn-primary">
                <span class="glyphicon glyphicon-ok"></span> 提交
            </button>
            <button onclick="clearInput()" class="btn btn-primary">
                <span class="glyphicon glyphicon-repeat"></span> 重置
            </button>
            <button onclick="window.location.href='${pageContext.request.contextPath}/login'"
                    class="btn btn-primary">
                <span class="glyphicon glyphicon-log-in"></span> 返回
            </button>
        </div>
    </div>
</div>
<div class="panel-footer">
    当前时间: <%= new java.util.Date() %>
    所在IP:<%out.println(request.getRemoteAddr());%>
</div>
</body>
</html>
