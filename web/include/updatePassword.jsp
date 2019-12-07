<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/6
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/include.css">
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">修改密码</h3>
    </div>
    <script type="text/javascript">
        function updateInfo(){
            const rightContent = $("#right-content");
            const oldp=$("#oldPassword").val();
            const newp=$("#newPassword").val();
            const id=$("#userid").text();
            //alert(id+oldp+newp);
            rightContent.empty();
            rightContent.load("${pageContext.request.contextPath}/updatePassword?id="+id+"&oldp="+oldp+"&newp="+newp);
        }
        function clearInput() {
            //alert("in clear");
            $("#oldPassword").val("");
            $("#newPassword").val("");
        }
    </script>
    <div class="panel-body">
        <div id="search-bar-updateUser">
            <form id="form" onsubmit="return false;" onkeydown="if(event.keyCode===13){return false;}">
                <input style="display:none"/>
                <p>输入旧密码：</p>
                <input class=".input" type="password" name="oldPassword" id="oldPassword">
                <p>输入新密码：</p>
                <input class=".input" type="text" name="newPassword" id="newPassword">
                <br>
                <br>
                <div onclick="clearInput()" class="btn btn-primary">
                    <span class="glyphicon glyphicon-remove"></span>清空
                </div>
                <div onclick="updateInfo()" class="btn btn-primary">
                    <span class="glyphicon glyphicon-send"></span>提交
                </div>
            </form>
            <p class="result-ok">${uResult}</p>
            <!--TODO ↑-->
        </div>
        <br>

    </div>
</div>

</body>
</html>
