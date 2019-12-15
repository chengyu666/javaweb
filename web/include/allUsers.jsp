<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/5
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>所有用户信息</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">所有用户信息</h3>
    </div>
    <script type="text/javascript">
        function removeUser(id) {
            if (confirm("确认删除ID为" + id + "的用户？")) {
                changePage("${pageContext.request.contextPath}/removeUser?id=" + id);
            }
        }
    </script>
    <div class="panel-body">
        <button class="btn btn-primary"
                onclick="changePage('${pageContext.request.contextPath}/include/addUser.jsp')">
            <span class="glyphicon glyphicon-plus"></span>添加用户
        </button>
        <br><br>
        <p class="result-ok">${uResultOk}</p>
        <p class="result-fail">${uResultFail}</p>
        <table width="100%" class="table table-bordered table-striped">
            <tr>
                <td>用户ID</td>
                <td>用户名称</td>
                <td>角色</td>
                <td>注册日期</td>
                <td>操作</td>
            </tr>
            <%--@elvariable id="userList" type="java.util.List"--%>
            <c:forEach items="${userList}" var="item" varStatus="status">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>
                        <c:if test="${item.role eq 'u'}" var="isCommonUser">
                            <p>普通用户</p>
                        </c:if>
                        <c:if test="${not isCommonUser}">
                            <p>管理员</p>
                        </c:if>
                    </td>
                    <td><fmt:formatDate value="${item.signup}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <c:if test="${isCommonUser}">
                            <a onclick="changePage('${pageContext.request.contextPath}/gotoEditUser?id=${item.id}')">编辑</a>
                            <a onclick="removeUser(${item.id})">删除</a>
                        </c:if>
                        <c:if test="${not isCommonUser}">
                            无法修改
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>---我们是有底线的---</p>
    </div>
</div>
</body>
</html>
