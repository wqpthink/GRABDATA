<%--
  Created by IntelliJ IDEA.
  User: WANGQINGPING
  Date: 2017/10/31
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/taglib.jsp"%>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>预测未来新号码</title>
    <script type="text/javascript" src="/static/js/jquery/jquery-1.9.1.js"></script>

<style>

</style>
</head>
<body>
    <table>
        <c:forEach var="item" items="${resultTag}">
            <tr>
                <c:forEach var="ite" items="${item}">
                    <td style="border: 1px solid #fb0fac;">${ite}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>

    <br><br>
    <table>
        <c:forEach var="item" items="${resultNum}">
            <tr>
                <c:forEach var="ite" items="${item}">
                    <td style="border: 1px solid #2bffa0;">${ite}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>


<script type="text/javascript">


</script>
</body>
</html>
