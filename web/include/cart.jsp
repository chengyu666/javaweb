<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/15
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">购物车</h3>
        <small>商品总价：${sum}, 您的余额：${user.money}</small>
        <c:if test="${sum < user.money}" var="enoughMoney"><small>
            余额充足，买买买！
        </small></c:if>
        <c:if test="${not enoughMoney}"><small>
            余额不足，请充值！
        </small></c:if>
    </div>
    <script type="text/javascript">
        function buy() {
            const mail = prompt("请输入您的邮箱：");
            let emreg = /^\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}$/;
            let path;
            if (mail == null || mail === "") {
                alert("必须输入邮箱以完成购买！");
            } else if(!emreg.test(mail)) {
                alert("请输入正确的邮箱地址");
            }else{
                path = "${pageContext.request.contextPath}/buyAll?" +
                    "id=${user.id}" +
                    "&mail=" + mail;
                changePage(path);
            }
        }

        function clearCart() {
            let path;
            if (confirm("确认清空购物车？")) {
                path = "${pageContext.request.contextPath}/clearCart?id=${user.id}";
                changePage(path);
            }
        }
    </script>
    <div class="panel-body">
        <button onclick="buy()" class="btn btn-primary">
            <span class="glyphicon glyphicon-check"></span> 全部下单
        </button>
        <button onclick="clearCart()" class="btn btn-primary">
            <span class="glyphicon glyphicon-remove-circle"></span>清空购物车
        </button>
        <br>
        <small class="result-ok">${cartResult}</small>
        <br>
        <table width="100%" class="table table-bordered table-striped">
            <tr>
                <td>型号</td>
                <td>价格</td>
                <td>数量</td>
            </tr>
            <c:if test="${empty cartList}">
                <tr>
                    <td>暂无商品，快去加购吧！</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
            </c:if>
            <c:forEach items="${cartList}" var="item">
                <tr>
                    <td>${item.code}</td>
                    <td>${item.price}￥</td>
                    <td>${item.number}</td>
                </tr>
            </c:forEach>
        </table>
        <p>---到底啦---</p>
    </div>
</div>
</body>
</html>
