<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/5
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>所有3D打印机信息</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">所有3D打印机型号信息</h3>
    </div>
    <script type="text/javascript">
        function addToCart(code){
            const id=$("#userid").text();
            var path="${pageContext.request.contextPath}/cart?id="+id+"&addCode="+code;
            changePage(path);
        }
    </script>
    <div class="panel-body">
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
                    <td>${item.price}￥</td>
                    <td style="min-width: 50px">
                        <a onclick="addToCart('${item.code}')">加购</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>---我们是有底线的---</p>
    </div>
</div>
</body>
</html>
