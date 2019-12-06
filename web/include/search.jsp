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
        <h4 id="right-title-text">搜索代码</h4>
    </div>
    <div class="panel-body">
        <form action="${pageContext.request.contextPath}/searchCode" method="post" style="padding-top:-700px;">
            <p>输入错误代码：</p><input type="text" name="code" value="E01">
            <button type="reset" value="Reset" class="btn btn-primary">
                <span class="glyphicon glyphicon-remove"></span>
            </button>
            <button type="submit" value="Submit" class="btn btn-primary">
                <span class="glyphicon glyphicon-search"></span>
            </button>
        </form>

    </div>
</div>
</body>
</html>
