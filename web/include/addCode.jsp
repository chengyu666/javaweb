<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/7
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加错误代码信息</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">输入新的错误代码信息</h3>
    </div>
    <script type="text/javascript">
        function addCode() {
            const code = $("#code").val();
            const message = $("#message").val();
            let str=message;
            str=str.replace(/%/g,"%25");
            str=str.replace(/#/g,"%23");
            str=str.replace(/&/g,"%26");
            str=str.replace(/\s+/g,"+");
            str=str.replace(/'/g,"’");
            str=str.replace(/"/g,"”");
            const info = "确认添加错误代码信息：\n" +
                "错误代码：" + code + "\n" +
                "描述：" + message;
            if (confirm(info)) {
                let path = "${pageContext.request.contextPath}/addCode";
                path = path + "?code=" + code + "&message=" + str;
                changePage(path);
            }
        }
    </script>
    <div class="panel-body">
        <div id="input-div">
            <p>输入错误代码</p>
            <input type="text" id="code">
            <p>输入描述</p>
            <input type="text" id="message">
            <br><br>
            <div onclick="addCode()" class="btn btn-primary">
                <span class="glyphicon glyphicon-send"></span>提交
            </div>
        </div>
    </div>
</div>
</body>
</html>
