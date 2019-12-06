<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/6
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询用户信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/include.css">
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">搜索用户</h3>
    </div>
    <script type="text/javascript">
        function getSearchResult() {
            const input = $("#input").val();
            //alert(input);
            const rightContent = $("#right-content");
            rightContent.empty();
            rightContent.load("${pageContext.request.contextPath}/searchUser?input=" + input);
        }
    </script>
    <div class="panel-body">
        <div id="search-bar">
            <p>输入用户ID：</p><input type="text" name="id" placeholder="例如：3" id="input">
            <button onclick="$('#input').val('')" class="btn btn-primary">
                <span class="glyphicon glyphicon-remove"></span>
            </button>
            <button onclick="getSearchResult()" class="btn btn-primary">
                <span class="glyphicon glyphicon-search"></span>
            </button>
        </div>
        <br>
        <div id="search-result">
            <c:if test="${userInfo eq null}" var="flag1">
            </c:if>
            <c:if test="${userInfo.id == -1}" var="flag2">
                无信息
            </c:if>
            <c:if test="${not flag1 and not flag2}">
                <h4>用户ID</h4>
                <p>${userInfo.id}</p>
                <h4>用户名</h4>
                <p>${userInfo.name}</p>
                <h4>角色</h4>
                <p>${userInfo.role}</p>
                <h4>使用期限</h4>
                <p>${userInfo.expire}</p>
                <h4>操作</h4>
                <c:if test="${userInfo.role eq 'a'}" var="infoIsAdmin">
                    无法修改管理员信息！
                </c:if>
                <c:if test="${not infoIsAdmin}">
                    <p>
                        <a href="${pageContext.request.contextPath }/editUser?id=${userInfo.id}">修改</a>
                        <a href="${pageContext.request.contextPath }/removeUser?id=${userInfo.id}">删除</a>
                    </p>
                </c:if>
            </c:if>

        </div>
    </div>
</div>
</body>
</html>
