<%--
  Created by IntelliJ IDEA.
  User: WANGQINGPING
  Date: 2017/10/25
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/taglib.jsp"%>
<html>
<head>
    <title>QXC</title>
    <script type="text/javascript" src="${ctxStatic}/js/jquery/jquery-1.9.1.js"></script>
</head>
<body>
    <p>《*************************动态添加预测数据*************************》</p>
    <label>&nbsp;&nbsp;&nbsp;期号：</label><input id="dynamic-issue" type="text" name="issue" value=""><br>
    <label>获奖号：</label><input id="dynamic-winNumber" type="text" name="winNumber" value=""><br>
    <label>&nbsp;类型：</label>
        <select id="dynamic-classify" name="classify">
        <option value="定头">定头</option>
        <option value="定十">定十</option>
        <option value="定百">定百</option>
        <option value="定尾">定尾</option>
        <option value="杀头">杀头</option>
        <option value="杀十">杀十</option>
        <option value="杀百">杀百</option>
        <option value="杀尾">杀尾</option>
        </select><br>
    <label>&nbsp;来源：</label><input id="dynamic-origin" type="text" name="origin" value=""><br>
    <label>&nbsp;0位：</label><input id="dynamic-zero" type="text" name="zero" value=""><br>
    <label>&nbsp;1位：</label><input id="dynamic-one" type="text" name="one" value=""><br>
    <label>&nbsp;2位：</label><input id="dynamic-two" type="text" name="two" value=""><br>
    <label>&nbsp;3位：</label><input id="dynamic-three" type="text" name="three" value=""><br>
    <label>&nbsp;4位：</label><input id="dynamic-four" type="text" name="four" value=""><br>
    <label>&nbsp;5位：</label><input id="dynamic-five" type="text" name="five" value=""><br>
    <label>&nbsp;6位：</label><input id="dynamic-six" type="text" name="six" value=""><br>
    <label>&nbsp;7位：</label><input id="dynamic-seven" type="text" name="seven" value=""><br>
    <label>&nbsp;8位：</label><input id="dynamic-eight" type="text" name="eight" value=""><br>
    <label>&nbsp;9位：</label><input id="dynamic-nine" type="text" name="nine" value=""><br>

    <label>正确率：</label><input id="dynamic-frequencyValidity" type="text" name="frequencyValidity" value="0"><br>
    <label>&nbsp;&nbsp;&nbsp;基数：</label><input id="dynamic-radix" type="text" name="radix" value="10"><br>
    <br>
    <button onclick="clickSave();">提交保存</button>

<script type="text/javascript">
    
    function clickSave() {
//        document.location.href='operateByDynamicTable';

        var params = {};
        params.issue = $("#dynamic-issue").val();
        params.winNumber = $("#dynamic-winNumber").val();
        params.classify = $('#dynamic-classify option:selected').val();
        params.origin = $("#dynamic-origin").val();
        params.zero = $("#dynamic-zero").val();
        params.one = $("#dynamic-one").val();
        params.two = $("#dynamic-two").val();
        params.three = $("#dynamic-three").val();
        params.four = $("#dynamic-four").val();
        params.five = $("#dynamic-five").val();
        params.six = $("#dynamic-six").val();
        params.seven = $("#dynamic-seven").val();
        params.eight = $("#dynamic-eight").val();
        params.nine = $("#dynamic-nine").val();
        params.frequencyValidity = $("#dynamic-frequencyValidity").val();
        params.radix = $("#dynamic-radix").val();


        $.ajax({
            url:'operateByDynamicTable',
            type:'POST',
            data:params,
            dataType:'json',
            success:function(data){
                if(data.state){
                    alert("添加成功");
                    $("#dynamic-origin").val("");
                }else{
                    window.confirm('添加失败,请重新操作');
                }
            }
        });

    }
    
</script>
</body>
</html>
