<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/5
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询代码信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/include.css">
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">搜索代码</h3>
    </div>
    <script type="text/javascript">
        function getSearchResult() {
            const input = $("#input").val();
            //alert(input);
            const rightContent = $("#right-content");
            rightContent.empty();
            rightContent.load("${pageContext.request.contextPath}/searchCode?input=" + input);
        }
    </script>
    <div class="panel-body">
        <div id="search-bar">
            <p>输入错误代码：</p><input type="text" name="code" placeholder="例如：E0001" id="input">
            <button onclick="$('#input').val('')" class="btn btn-primary">
                <span class="glyphicon glyphicon-remove"></span>
            </button>
            <button onclick="getSearchResult()" class="btn btn-primary">
                <span class="glyphicon glyphicon-search"></span>
            </button>
        </div>
        <br>
        <div id="search-result">
            <c:if test="${code eq null}" var="flag1">
            </c:if>
            <c:if test="${code.code eq 'E0000'}" var="flag2">
                无信息
            </c:if>
            <c:if test="${not flag1 and not flag2}">
                <h4>错误代码</h4>
                <p>${code.code}</p>
                <h4>描述</h4>
                <p>${code.message}</p>
                <h4>更新日期</h4>
                <p>${code.time}</p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
