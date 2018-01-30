<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/views/include/taglib.jsp"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!--会诊室ID,用户ID-->
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html">
	<meta name="decorator" content="default"/>
	<title>电子病历</title>
		<script type="text/javascript" src="/static/js/jquery/jquery-1.9.1.js"></script>

		<link type="text/css" rel="stylesheet" href="/static/js/layui/laydate/theme/default/laydate.css">
		<script type="text/javascript" language="JavaScript" charset="UTF-8" src="/static/js/layui/laydate/laydate.js"></script>

		<link type="text/css" rel="stylesheet" href="/static/js/layui/layer/theme/default/layer.css">
		<script type="text/javascript" language="JavaScript" charset="UTF-8" src="/static/js/layui/layer/layer.js"></script>

	<style type="text/css">
		html{margin: 0px;padding: 0px;}
		body{margin: 0px;padding: 0px;}
		.container{margin:0px auto;padding: 0px;width:98%;background-color: white; border: 1px dotted white;}
		.first{text-align:center;margin-bottom: 20px;}

		.second{margin-bottom: 20px;}
		.second-sick-title{font-size: 14px;text-align: right;padding-right:5px;width:10%;}
		.second-sick-content{;}
		.second-sick-content input{border:none;outline-color:transparent;background-color: transparent;margin-bottom:1px;font-size:14px;width:100%;}
		.underline{border-bottom: 1px solid lightgray;width: 40%;}

		.third label{font-size: 16px;}
		textarea{resize:none;border-color:lightgray;outline-color:lightgray;font-size:14px;overflow-x:hidden;}
		.third-content{margin-bottom:10px;}

		.fourth a{font-size:16px;border:1px solid gray;cursor:pointer;margin-left:20px;}



	</style>

	<script type="text/javascript">
		var currentWindowWidth;

		window.onload = function init() {
			var windowWidth = 0;
			if(window.innerWidth){
				currentWindowWidth = window.innerWidth;
			}else if(document.body && document.body.clientWidth){
				currentWindowWidth = document.body.clientWidth;
			}

			var obj = $("#hisemr-hidden").val();
			var hisemr = JSON.parse(obj);
			$("#drugAllergyHistory").val(hisemr.drugAllergyHistory);//药物过敏史
			$("#symptom").val(hisemr.symptom);//主诉
			$("#presentIllnessHistory").val(hisemr.presentIllnessHistory);//现病史
			$("#priviousIllnessHistory").val(hisemr.priviousIllnessHistory);//既往史
			$("#healthCheck").val(hisemr.healthCheck);//体格检查
			switch (hisemr.operateType){
				case 1://挂号
                    $("#auxiliaryCheck").val("");
                    $("#diagnose").val("");
                    $("#treatmentOptions").val("");
                    $("#signature").val("");

                    $(".fourth").hide();
					$("#operatebtn").val('下一步');
//                    document.getElementById("operatebtn").addEventListener('click',next,true);
					break;
				case 2://卫生员会诊
                    $("#auxiliaryCheck").val(hisemr.auxiliaryCheckMedic);
                    $("#diagnose").val(hisemr.diagnoseMedic);
                    $("#treatmentOptions").val(hisemr.treatmentOptionsMedic);
                    $("#signature").val(hisemr.signatureMedic);

                    document.getElementById("gender").children[hisemr.gender == '男'?0:hisemr.gender == '女'?1:0].selected=true;
                    document.getElementById("marriage").children[hisemr.marriage == '未婚'?0:hisemr.marriage == '已婚'?1:hisemr.marriage == '离异'?2:0].selected=true;
//                    document.getElementById("operatebtn").addEventListener('click',emrSave,true);
                    break;
				case 3://专家会诊
                    $("#auxiliaryCheck").val(hisemr.auxiliaryCheckExpert);
                    $("#diagnose").val(hisemr.diagnoseExpert);
                    $("#treatmentOptions").val(hisemr.treatmentOptionsExpert);
                    $("#signature").val(hisemr.signatureExpert);

                    document.getElementById("gender").children[hisemr.gender == '男'?0:hisemr.gender == '女'?1:0].selected=true;
                    document.getElementById("marriage").children[hisemr.marriage == '未婚'?0:hisemr.marriage == '已婚'?1:hisemr.marriage == '离异'?2:0].selected=true;
//                    document.getElementById("operatebtn").addEventListener('click',emrSave,true);
                    break;
			}

			//页面加载完毕适配页面尺寸
			//document.getElementById("container").style.width = currentWindowWidth*0.8+'px';
			var sick_table = document.getElementById("sick-table");
			for(var i=0;i<4;i++){
			    for(var j=0;j<8;j++){
			        if((j+1)%2==0){
                        sick_table.rows[i].cells[j];
					}else{
                        sick_table.rows[i].cells[j];
					}
				}
			}

		}
	</script>


</head>
<body>
<div id="container" class="container">
	<input type="hidden" id="hisemr-hidden" value='${hisemrJson}'>
	<!-- 第一部分 标题 -->
	<div class="first">
		<label style="font-size: 38px;" id="hospital">${hisemr.hospital }</label><br>
		<label style="font-size: 30px;">门诊电子病历</label>
	</div>
	
	<!-- 第二部分 患者基本信息-->
	<div class="second">
		<table id="sick-table" style="width: 100%;">
			<tr>
				<td class="second-sick-title">病例号:</td><td class="second-sick-content" id="emrId">${hisemr.emrId }</td>
				<td class="second-sick-title">科别:</td><td class="second-sick-content"><div class="underline"><input id="specialist" type="text" value="${hisemr.specialist }"></div></td>
				<td colspan="2"></td>
				<td class="second-sick-title">时间:</td><td class="second-sick-content"><div class="underline" style="width: 70%;"><input id="therapyTimeFormat" type="text"></div></td>
			</tr>
			<tr>
				<td class="second-sick-title">证件号:</td><td class="second-sick-content"><div class="underline"><input id="identityId" type="text" value="${hisemr.identityId }"></div></td>
				<td class="second-sick-title">姓名:</td><td class="second-sick-content"><div class="underline"><input id="name" type="text" value="${hisemr.name}"></div></td>
				<td class="second-sick-title">性别:</td><td class="second-sick-content" style="width:18%;"><select id="gender">
									<option value="1" selected="selected">男</option>
									<option value="2">女</option>
								</select></td>
				<td class="second-sick-title">年龄:</td><td class="second-sick-content"><div class="underline" style="width: 25%;float:left;"><input id="age" type="text" value="${hisemr.age }"></div><label style="float:left;">岁</label></td>
			</tr>
			<tr>
				<td class="second-sick-title">职业:</td><td class="second-sick-content"><div class="underline"><input id="occup" type="text" value="${hisemr.occup }"></div></td>
				<td class="second-sick-title">民族:</td><td class="second-sick-content"><div class="underline"><input id="nation" type="text" value="${hisemr.nation }"></div></td>
				<td class="second-sick-title">婚否:</td><td class="second-sick-content" style="width:18%;"><select id="marriage">
									<option value="1" selected="selected">未婚</option>
									<option value="2">已婚</option>
									<option value="3">离异</option>
								</select></td>
				<td class="second-sick-title">出生日期:</td><td class="second-sick-content"><div class="underline" style="width: 70%;"><input id="birthFormat" type="text"></div></td>
			</tr>
			<tr>
				<td class="second-sick-title">单位住址:</td><td class="second-sick-content" colspan="3"><div class="underline" style="width: 78%;"><input id="address" type="text" value="${hisemr.address }"></div></td>
				<td colspan="4"></td>
			</tr>
		</table>
	</div>
	
	<!-- 第三部分 会诊信息、医师签名-->
	<div class="third">
		<div class="third-content">
			<label>药物过敏史:</label><br>
			<textarea id="drugAllergyHistory" style="width:100%; height: 60px;"></textarea>
		</div>
		<div class="third-content">
			<label>主诉:</label><br>
			<textarea id="symptom" style="width:100%; height: 90px;"></textarea>
		</div>
		<div class="third-content">
			<label>现病史(发病时间、主要症状、伴发症状、诊治经过等):</label><br>
			<textarea id="presentIllnessHistory" style="width:100%; height: 90px;"></textarea>
		</div>
		<div class="third-content">
			<label>既往史:</label><br>
			<textarea id="priviousIllnessHistory" style="width:100%; height: 90px;"></textarea>
		</div>
		<div class="third-content">
			<label>体格检查(阳性体征及必要的阴性体征):</label><br>
			<textarea id="healthCheck" style="width:100%; height: 90px;"></textarea>
		</div>
		<div class="third-content">
			<div style="float: left; width:58%;">
				<label>辅助检查:</label><br>
				<textarea id="auxiliaryCheck" style="width:100%; height: 90px;"></textarea>
			</div>
			<div style="float: left; width:38%; margin-left: 4%;">
				<label>诊断:</label><br>
				<textarea id="diagnose" style="width:100%; height: 90px;"></textarea>
			</div>
		</div>
		<div class="third-content" style="clear:both;display: inline-block;margin-top:10px;width:100%;">
			<label>治疗意见:</label><br>
			<textarea id="treatmentOptions" style="width:100%; height: 90px;"></textarea>
		</div>


		<div style="text-align: right;margin-top:50px;">
			<div class="underline second-sick-content" style="float:right;width:10%;"><input id="signature" type="text"></div>
			<label style="float:right;">医师签名:</label>
		</div>
	</div>
	
	<!-- 第四部分 附件 -->
	<div class="fourth" style="clear: both; margin-top:70px;">
		<label style="font-size:22px;display:block;margin-bottom:-10px;">附件:</label><br>
		<a onclick="emrAdjunctShow(1);">检查申请单</a><a onclick="emrAdjunctShow(2);">化验申请单</a><a onclick="emrAdjunctShow(3);">治疗单</a><a onclick="emrAdjunctShow(4);">处方单</a><a onclick="emrAdjunctShow(5);">影像资料</a>
		<div id="adjunctContainer"></div>
	</div>
	<div style="text-align:center;margin-top:90px;margin-bottom:50px;"><input id="operatebtn" type="button" value="保存" onclick="emrSave();" style="border-radius:4px;font-size:18px;height:40px;width:80px;border:1px solid blue;"></div>
</div>

<script type="text/javascript">
	/*电子病历附件展示*/
	function emrAdjunctShow(category){
        var tmp = $("#identityId").val();
        if(tmp == null || tmp == ''){
            alert("请录入患者证件号后再操作");
            return;
        }
        var title = "";
        var content = "";
        var pms = {};
        pms.identityId = tmp;//证件号
        switch(category){
			case 1:
			    pms.category = 1;
                title = "检查申请单列表"
			    break;
			case 2:
                pms.category = 2;
                title = "化验申请单列表";
			    break;
			case 3:
                pms.category = 3;
                title = "治疗单列表";
			    break;
			case 4:
                pms.category = 4;
                title = "处方单列表";
			    break;
			case 5:
                pms.category = 5;
                title = "影像资料";
			    break;
		}

		var adjunctText = $("#adjunctContainer").text();
        if(adjunctText != null && adjunctText != ""){
            //展示内容列表,可以在列表上点击添加附件


		}else{
            $.post('selectBySickIdentityId',pms,function (data) {
//            if(data == null){
//                alert("展示失败");
//                return;
//			}

                switch(category){
                    case 1:
                        content = "检查申请单列表内容"
                        break;
                    case 2:
                        content = "化验申请单列表内容";
                        break;
                    case 3:
                        content = "治疗单列表内容";
                        break;
                    case 4:
                        content = "处方单列表内容";
                        break;
                    case 5:
                        content = "影像资料内容";
                        break;
                }

                layer.open({
                    type:1,
                    title:title,
                    area:['60%','300px'],
                    content:content,
                    btn:['添加','取消'],
                    yes:function(index, layero){
                        alert(index+","+layero);


                        layer.close(layer.index);//关闭弹框
                    },
                    btn2:function (index, layero) {
                        alert(title+index+","+layero);


                        return true;
                    }
                });
            });
		}
	}

    /*电子病历保存*/
    function emrSave() {
        var pms = {};
        var obj = $("#hisemr-hidden").val();
        var hisemr = JSON.parse(obj);
        switch (hisemr.operateType){
			case 1://挂号
                pms.hospital = $("#hospital").text();//医院
                pms.specialist = $("#specialist").val();//科别
                pms.therapyTimeFormat = $("#therapyTimeFormat").val();//会诊时间,字符串
                pms.name = $("#name").val();//姓名
                pms.gender = $("#gender option:selected").val();//性别,1,2
                pms.age = $("#age").val();//年龄
                pms.identityId = $("#identityId").val();//证件号
                pms.nation = $("#nation").val();//民族
                pms.marriage = $("#marriage option:selected").val();//婚否,1,2,3
                pms.birthFormat = $("#birthFormat").val();//出生日期,字符串
                pms.address = $("#address").val();//单位住址
                pms.occup = $("#occup").val();//职业
                pms.drugAllergyHistory = $("#drugAllergyHistory").val();//药物过敏史
                pms.symptom = $("#symptom").val();//主诉
                pms.presentIllnessHistory = $("#presentIllnessHistory").val();//现病史
                pms.priviousIllnessHistory = $("#priviousIllnessHistory").val();//既往史
                pms.healthCheck = $("#healthCheck").val();//体格检查
                pms.auxiliaryCheckMedic = $("#auxiliaryCheck").val();//卫生员辅助检查
                pms.diagnoseMedic = $("#diagnose").val();//卫生员诊断
                pms.treatmentOptionsMedic = $("#treatmentOptions").val();//卫生员治疗意见
                pms.signatureMedic = $("#signature").val();//卫生员签名
				pms.operateType = 1;
			    break;
			case 2://卫生员会诊
                pms.emrId = $("#emrId").text();//病历号
                pms.hospital = $("#hospital").text();//医院
                pms.specialist = $("#specialist").val();//科别
                pms.therapyTimeFormat = $("#therapyTimeFormat").val();//会诊时间,字符串
                pms.name = $("#name").val();//姓名
                pms.gender = $("#gender option:selected").val();//性别,1,2
                pms.age = $("#age").val();//年龄
                pms.identityId = $("#identityId").val();//证件号
                pms.nation = $("#nation").val();//民族
                pms.marriage = $("#marriage option:selected").val();//婚否,1,2,3
                pms.birthFormat = $("#birthFormat").val();//出生日期,字符串
                pms.address = $("#address").val();//单位住址
                pms.occup = $("#occup").val();//职业
                pms.drugAllergyHistory = $("#drugAllergyHistory").val();//药物过敏史
                pms.symptom = $("#symptom").val();//主诉
                pms.presentIllnessHistory = $("#presentIllnessHistory").val();//现病史
                pms.priviousIllnessHistory = $("#priviousIllnessHistory").val();//既往史
                pms.healthCheck = $("#healthCheck").val();//体格检查
                pms.auxiliaryCheckMedic = $("#auxiliaryCheck").val();//卫生员辅助检查
                pms.diagnoseMedic = $("#diagnose").val();//卫生员诊断
                pms.treatmentOptionsMedic = $("#treatmentOptions").val();//卫生员治疗意见
                pms.signatureMedic = $("#signature").val();//卫生员签名
                pms.operateType = 2;
			    break;
			case 3://专家会诊
                pms.emrId = $("#emrId").text();//病历号
                pms.auxiliaryCheckExpert = $("#auxiliaryCheck").val();//专家辅助检查
                pms.diagnoseExpert = $("#diagnose").val();//专家诊断
                pms.treatmentOptionsExpert = $("#treatmentOptions").val();//专家治疗意见
                pms.signatureExpert = $("#signature").val();//专家签名
                pms.operateType = 3;
			    break;
			default:
			    break;
		}

        $.post('addEmr',pms,function (data) {
            if(data == null){
                alert("保存失败");
			}else{
                alert("保存成功");
			}
		});
    }

</script>


<script>
    laydate.render({
        elem: '#therapyTimeFormat',
        type: 'datetime',
        value:'${hisemr.therapyTimeFormat}'
    });

    laydate.render({
        elem:'#birthFormat',
        type:'date',
        value:'${hisemr.birthFormat}'
    });

//	layui.use('laydate',function () {
//        var laydate = layui.laydate;
//
//	});

</script>

</body>
</html>