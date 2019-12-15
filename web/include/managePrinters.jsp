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
    <title>管理所有型号</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">所有3D打印机型号信息</h3>
    </div>
    <script type="text/javascript">
        function removePrinter(code){
            if(confirm("确认删除型号'"+code+"'的信息？")){
                changePage("${pageContext.request.contextPath}/removePrinter?code="+code);
            }
        }
    </script>
    <div class="panel-body">
        <button class="btn btn-primary"
                onclick="changePage('${pageContext.request.contextPath}/include/addPrinter.jsp')">
            <span class="glyphicon glyphicon-plus"></span>添加型号信息
        </button>
        <br><br>
        <p class="result-ok">${uResultOk}</p>
        <p class="result-fail">${uResultFail}</p>
        <table width="100%" class="table table-bordered table-striped">
            <tr>
                <td>型号</td>
                <td>信息</td>
                <td>价格</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${printerList}" var="item">
                <tr>
                    <td>${item.code}</td>
                    <td>${item.information}</td>
                    <td>${item.price}</td>
                    <td style="min-width: 50px;">
                        <a onclick="changePage('${pageContext.request.contextPath}/gotoEditPrinter?code=${item.code}')">编辑</a>
                        <br>
                        <a onclick="removePrinter('${item.code}')">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>---我们是有底线的---</p>
    </div>
</div>
</body>
</html>
