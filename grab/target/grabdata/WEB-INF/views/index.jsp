<%--
  Created by IntelliJ IDEA.
  User: WANGQINGPING
  Date: 2017/10/25
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/taglib.jsp"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/static/js/jquery/jquery-1.9.1.js"></script>
</head>
<body>


    <%--<button onclick="getData();">进入统计页面</button><br>--%>
    <%--<button onclick="getData();">进入预测未来新号码页面</button><br>--%>
    <%--<button onclick="calculator();">预测计算</button>--%>
    <%--<button onclick="validitys();">进入号码预测页面</button><br>--%>
    <button onclick="emr(1);">挂号-电子病历</button><br>
    <button onclick="emr(2);">卫生员-电子病历</button><br>
    <button onclick="emr(3);">专家-电子病历</button><br>

    <script type="text/javascript">

        function getData() {
            <%--document.location.href='${ctx}/qxc/index';--%>
            <%--document.location.href='${ctx}/qxc/statisticsByrFrequency';--%>
            <%--document.location.href='${ctx}/qxc/statisticsByAbove';--%>
            <%--document.location.href='${ctx}/qxc/statisticsBySum';--%>
            <%--document.location.href='${ctx}/qxc/statistics';--%>

            <%--document.location.href='${ctx}/qxc/jump';--%>
            <%--document.location.href='${ctx}/qxc/statistics';--%>
            document.location.href='${ctx}/qxc/analystFeature';

        }


        function calculator() {
            document.location.href='${ctx}/qxc/calculator';
        }

        function winHistory() {
            document.location.href='${ctx}/qxc/winHistory';
        }

        function validitys() {
            document.location.href='${ctx}/qxc/validityPage';
        }

        function emr(i){
            switch (i){
                case 1://挂号
                    document.location.href='${ctx}/emr/index?roomId=272025760&userId=ganjun&operateType='+i;
                    break;
                case 2://卫生员会诊
                    document.location.href='${ctx}/emr/index?roomId=272025760&userId=ganjun&emrId=20171212041213001&operateType='+i;
                    break;
                case 3://专家会诊
                    document.location.href='${ctx}/emr/index?roomId=272025760&userId=ganjun&emrId=20171212041213001&operateType='+i;
                    break;
            }
        }

    </script>

</body>
</html>
