<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/7
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">输入新用户信息</h3>
    </div>
    <script type="text/javascript">
        function addUser() {
            const name = $("#userName").val();
            const password = $("#password").val();
            const expire = $("#expire").val();
            const str = "确认添加普通用户：\n" +
                "用户名：" + name + "\n" +
                "密码：" + password + "\n" +
                "使用期限：" + expire;
            if (confirm(str)) {
                let path = "${pageContext.request.contextPath}/addUser";
                path = path + "?name=" + name + "&password=" + password + "&expire=" + expire;
                changePage(path);
            }
        }
    </script>
    <div class="panel-body">
        <div id="input-div">
            <p>输入用户名</p>
            <input type="text" id="userName">
            <p>输入密码</p>
            <input type="text" id="password">
            <p>选择使用期限</p>
            <input type="date" id="expire">
            <br><br>
            <div onclick="addUser()" class="btn btn-primary">
                <span class="glyphicon glyphicon-send"></span>提交
            </div>
        </div>
    </div>
</div>
</body>
</html>
