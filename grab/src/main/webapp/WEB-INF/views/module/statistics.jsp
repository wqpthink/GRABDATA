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
    <title>Statistics</title>
    <script type="text/javascript" src="/static/js/jquery/jquery-1.9.1.js"></script>

<style>
    table td{
        border: 1px solid #000000;
    }

</style>
</head>
<body>

<input type="text" id="code_value" placeholder="请输入本期单个号码"><br>
<button onclick="getData(4);">获取预计下期千位统计</button>&nbsp&nbsp&nbsp&nbsp
<button onclick="getData(3);">获取预计下期百位统计</button>&nbsp&nbsp&nbsp&nbsp
<button onclick="getData(2);">获取预计下期十位统计</button>&nbsp&nbsp&nbsp&nbsp
<button onclick="getData(1);">获取预计下期个位统计</button><br>

    <table></table>
    <p></p>

<script type="text/javascript">
    function getData(i) {
        var value = document.getElementById("code_value").value;
        var params = {};
        params.code = i;
        params.value = value;
        $.getJSON('statisticsHistory',params,function (data) {
            var temp="";
            for(var i=0;i<data.statistics.length;i++){
                var v = data.statistics[i];
                temp+='<tr>'+
                    '<td>'+v.issue+'</td>'+
                    '<td>'+v.winNumber+'</td>';
                    if(data.code == 1){
                        temp+='<td>'+v.geWei+'</td>';
                    }else if(data.code == 2){
                        temp+='<td>'+v.shiWei+'</td>';
                    }else if(data.code == 3){
                        temp+='<td>'+v.baiWei+'</td>';
                    }else if(data.code == 4){
                        temp+='<td>'+v.qianWei+'</td>';
                    }
                temp+='</tr>';
            }

            var temp_str = "";
            if(data.code == 1){
                temp_str = '<td>个位</td>';
            }else if(data.code == 2){
                temp_str = '<td>十位</td>';
            }else if(data.code == 3){
                temp_str = '<td>百位</td>';
            }else if(data.code == 4){
                temp_str = '<td>千位</td>';
            }

            var str =   '<thead><tr><td>期号</td><td>中奖号码</td>'+temp_str+'</tr></thead><tbody>'+temp+'</tbody>';

            var str2 = '上一期开'+data.pre_value+'，则下期预计开的数字出现次数统计如下：<br>'+
            '0出现'+data.zero+'次；&nbsp&nbsp&nbsp&nbsp 1出现'+data.one+'次；&nbsp&nbsp&nbsp&nbsp 2出现'+data.two+'次；&nbsp&nbsp&nbsp&nbsp '+
            '3出现'+data.three+'次；&nbsp&nbsp&nbsp&nbsp 4出现'+data.four+'次；&nbsp&nbsp&nbsp&nbsp 5出现'+data.five+'次；<br>'+
            '6出现'+data.six+'次；&nbsp&nbsp&nbsp&nbsp 7出现'+data.seven+'次；&nbsp&nbsp&nbsp&nbsp 8出现'+data.eight+'次；&nbsp&nbsp&nbsp&nbsp '+
            '9出现'+data.nine+'次';



            $("table").html(str);
            $("p").html(str2);
        });
    }

</script>
</body>
</html>
