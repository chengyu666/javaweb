<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/15
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>充值余额</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">开业酬宾，免费充值！</h3>
    </div>
    <script type="text/javascript">
        function recharge(){
            const id=$("#userid").text();
            const money=$("#charge-money").val();
            if(confirm("确认充值"+money+"元？")){
                changePage("${pageContext.request.contextPath}/recharge?id="+id+"&money="+money);
            }
        }
    </script>
    <div class="panel-body">
        <div id="search-bar-updateUser">
            <form id="form" onsubmit="return false;" onkeydown="if(event.keyCode===13){return false;}">
                <input style="display:none"/>
                <p>输入要充值的金额（￥）：</p>
                <input class=".input" type="number" name="charge-money" id="charge-money">
                <br>
                <br>
                <div onclick="recharge()" class="btn btn-primary">
                    <span class="glyphicon glyphicon-fire"></span>充充充
                </div>
            </form>
            <p class="result-ok">${chargeOk}</p>
            <p class="result-fail">${chargeFail}</p>
        </div>
        <br>

    </div>
</div>
</body>
</html>
