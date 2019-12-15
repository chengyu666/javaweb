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
    <title>添加3D打印机信息</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">输入新的3D打印机信息</h3>
    </div>
    <script type="text/javascript">
        function addPrinter() {
            const code = $("#code").val();
            const message = $("#message").val();
            const price = $("#price").val();
            let str = message;
            str = str.replace(/%/g, "%25");
            str = str.replace(/#/g, "%23");
            str = str.replace(/&/g, "%26");
            str = str.replace(/\s+/g, "+");
            str = str.replace(/'/g, "’");
            str = str.replace(/"/g, "”");
            const info = "确认添加打印机信息：\n" +
                "型号：" + code + "\n" +
                "信息：" + message + "\n" +
                "价格：" + price;
            if (confirm(info)) {
                let path = "${pageContext.request.contextPath}/addPrinter";
                path = path + "?code=" + code +
                    "&information=" + str +
                    "&price=" + price;
                changePage(path);
            }
        }
    </script>
    <div class="panel-body">
        <div id="input-div">
            <p>输入型号</p>
            <input type="text" maxlength="5" id="code">
            <p>输入具体信息</p>
            <input type="text" id="message">
            <p>输入价格</p>
            <input type="number" id="price">
            <br><br>
            <div onclick="addPrinter()" class="btn btn-primary">
                <span class="glyphicon glyphicon-send"></span>提交
            </div>
        </div>
    </div>
</div>
</body>
</html>
