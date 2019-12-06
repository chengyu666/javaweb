<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/11/28
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>3D打印机错误代码查询系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
    </script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>

<div class="title">
    <table>
        <td class="td-title">
            <h2>
                3D打印机错误代码查询系统
                <small><p>Version ${version}</p></small>
            </h2>
        </td>
        <td class="td-info">
            <small>
                <span class="glyphicon glyphicon-user"></span>${user.name}
                ID:<i id="userid">${user.id}</i>
                <c:if test="${user.role eq 'a'}" var="isAdmin">
                    <p>角色：管理员</p>
                </c:if>
                <c:if test="${not isAdmin}">
                    <p>角色：普通用户</p>
                    <p>使用期限：<fmt:formatDate value="${user.expire}" pattern="yyyy年MM月dd日"/></p>
                </c:if>
            </small>
        </td>
    </table>
</div>
<table border="2px" class="two-panels">
    <tr>
        <td width="20%">
            <div class="left-panel panel panel-default">
            <div class="left-title panel-heading">
                <h4 style="margin: 0px"><span class="glyphicon glyphicon-cog"></span>选择功能</h4>
            </div>
            <script type="text/javascript">
                function changePage(pagePath) {
                    const rightContent = $("#right-content");
                    rightContent.empty();
                    rightContent.load(pagePath);
                }
            </script>
            <div class="panel-body">
                <ul class="list-group">
                    <li onclick="changePage('${pageContext.request.contextPath}/include/introduce.jsp')"
                        class="list-group-item btn btn-default btn-lg btn-block text-left">
                        <span class="glyphicon glyphicon-star"></span>查询系统介绍
                    </li>
                    <li onclick="changePage('${pageContext.request.contextPath}/include/searchCode.jsp')"
                        class="list-group-item btn btn-default btn-lg btn-block text-left">
                        <span class="glyphicon glyphicon-search"></span>查询错误代码
                    </li>
                    <li onclick="changePage('${pageContext.request.contextPath}/allCodes')"
                        class="list-group-item btn btn-default btn-lg btn-block text-left">
                        <span class="glyphicon glyphicon-th-list"></span>查看所有代码
                    </li>
                    <li onclick="changePage('${pageContext.request.contextPath}/include/updateUser.jsp')"
                        class="list-group-item btn btn-default btn-lg btn-block text-left">
                        <span class="glyphicon glyphicon-th-list"></span>修改账户信息
                    </li>
                    <c:if test="${isAdmin}">
                        管理员功能：
                        <li onclick="changePage('${pageContext.request.contextPath}/allUsers')"
                            class="list-group-item btn btn-default btn-lg btn-block text-left">
                            <span class="glyphicon glyphicon-list-alt"></span>查看所有用户
                        </li>
                        <li onclick="changePage('${pageContext.request.contextPath}/include/searchUser.jsp')"
                            class="list-group-item btn btn-default btn-lg btn-block text-left">
                            <span class="glyphicon glyphicon-list-alt"></span>查询特定用户
                        </li>
                    </c:if>
                </ul>
            </div>
        </div></td>
        <td width="80%" style="vertical-align: top;"><div class="right-panel">
            <div id="right-content">
                <c:if test="${page eq 'introduce'}">
                    <jsp:include page="../../include/introduce.jsp"/>
                </c:if>
                <c:if test="${page eq 'search'}">
                    <jsp:include page="../../include/searchCode.jsp"/>
                </c:if>
            </div>
        </div></td>
    </tr>
</table>


<div class="panel-footer">
    当前时间: <%= new java.util.Date() %>
    所在IP:<%out.println(request.getRemoteAddr());%>
</div>
</body>
</html>
