<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/7
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理所有错误代码</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">所有错误代码信息</h3>
    </div>
    <script type="text/javascript">
        function removeCode(code){
            if(confirm("确认删除错误代码'"+code+"'的信息？")){
                changePage("${pageContext.request.contextPath}/removeCode?code="+code);
            }
        }
    </script>
    <div class="panel-body">
        <button class="btn btn-primary"
                onclick="changePage('${pageContext.request.contextPath}/include/addCode.jsp')">
            <span class="glyphicon glyphicon-plus"></span>添加错误代码信息
        </button>
        <br><br>
        <p class="result-ok">${uResultOk}</p>
        <p class="result-fail">${uResultFail}</p>
        <table width="100%" class="table table-bordered table-striped">
            <tr>
                <td>错误代码</td>
                <td>描述</td>
                <td>更新日期</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${codeList}" var="item">
                <tr>
                    <td>${item.code}</td>
                    <td>${item.message}</td>
                    <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a onclick="changePage('${pageContext.request.contextPath}/gotoEditCode?code=${item.code}')">编辑</a>
                        <a onclick="removeCode('${item.code}')">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>TODO here</p>
    </div>
</div>
</body>
</html>
