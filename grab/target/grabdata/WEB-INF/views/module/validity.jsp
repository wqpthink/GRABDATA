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
    <title>验证历史是否出现过</title>
    <script type="text/javascript" src="/static/js/jquery/jquery-1.9.1.js"></script>

<style>
    table td{
        border: 1px solid #000000;
    }

</style>
</head>
<body>

<input type="text" id="qian_value" placeholder="请输入预测千位号码"><br>
<input type="text" id="bai_value" placeholder="请输入预测百位号码"><br>
<input type="text" id="shi_value" placeholder="请输入预测十位号码"><br>
<input type="text" id="ge_value" placeholder="请输入预测个位号码"><br>
<button onclick="validitys();">预测</button><br><br>

    <table></table>

<script type="text/javascript">
    function validitys() {
        var params = {};
        params.qian = document.getElementById("qian_value").value;
        params.bai = document.getElementById("bai_value").value;
        params.shi = document.getElementById("shi_value").value;
        params.ge = document.getElementById("ge_value").value;
        $.getJSON('validity',params,function (data) {
            var temp="<tr>";
            for(var i=0;i<data.length;i++){
                var v = data[i];
                if(i % 10 == 0){
                    temp += '</tr><tr>';
                }
                temp += '<td>'+v+'</td>';
            }
            temp+='</tr>';
            $("table").html(temp);
        });
    }

</script>
</body>
</html>
