<%--
  Created by IntelliJ IDEA.
  User: fengc
  Date: 2019/12/7
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑错误代码信息</title>
</head>
<body>
<div class="panel panel-default">
    <div class="right-title panel-heading">
        <h3 id="right-title-text">编辑错误代码信息</h3>
    </div>
    <script type="text/javascript">
        function updateDesc() {
            const newDesc = $("#input-new-desc").val();
            let str=newDesc;
            str=str.replace(/%/g,"%25");
            str=str.replace(/#/g,"%23");
            str=str.replace(/&/g,"%26");
            str=str.replace(/\s+/g,"+");
            str=str.replace(/'/g,"’");
            str=str.replace(/"/g,"”");
            if(confirm("确认将描述由：\n${codeInfo.message}\n修改为：\n"+str)){
                changePage("${pageContext.request.contextPath}/editCodeMessage?code=${codeInfo.code}&newMessage="+str);
            }
        }
    </script>
    <div class="panel-body">
        <div id="search-result">
            <h4>错误代码：${codeInfo.code}</h4>
            <small>不可更改</small>
            <h4>描述：${codeInfo.message}</h4>
            输入新的描述：<input type="text" size="35" value="${codeInfo.message}" name="newDescription" id="input-new-desc">
            <button onclick="updateDesc()" class="btn btn-primary">
                <span class="glyphicon glyphicon-send"></span>
            </button>
            <p class="result-ok">${descResultOk}</p>
            <p class="result-fail">${descResultFail}</p>
            <h4>更新时间：${codeInfo.time}</h4>
            <small>不可自行更改</small>
        </div>
    </div>
</div>
</body>
</html>
