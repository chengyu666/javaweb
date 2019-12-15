<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/7
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑用户信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/include.css">
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">编辑用户信息</h3>
    </div>
    <script type="text/javascript">
        function updateName() {
            const newName = $("#input-new-name").val();
            if(confirm("确认将用户名由：\n${userInfo.name}\n修改为：\n"+newName)){
                changePage("${pageContext.request.contextPath}/editUserName?id=${userInfo.id}&newName="+newName);
            }
        }
    </script>
    <div class="panel-body">
        <div id="search-result">
            <h4>用户ID：${userInfo.id}</h4>
            <small>不可更改</small>
            <h4>角色：${userInfo.role}</h4>
            <small>不可更改</small>
            <h4>用户名：${userInfo.name}</h4>
            输入新的用户名：<input type="text" name="newName" id="input-new-name">
            <button onclick="updateName()" class="btn btn-primary">
                <span class="glyphicon glyphicon-send"></span>
            </button>
            <p class="result-ok">${nameResultOk}</p>
            <p class="result-fail">${nameResultFail}</p>
            <h4>注册日期：${userInfo.signup}</h4>
            <small>不可修改</small>
        </div>
    </div>
</div>
</body>
</html>
