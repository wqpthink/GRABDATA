package com.wqp.grab.module.emr.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.asm.Type;
import com.mysql.cj.xdevapi.JsonArray;
import com.wqp.grab.module.emr.entity.HisEmr;
import com.wqp.grab.module.emr.entity.HisEmrAdjunct;
import com.wqp.grab.module.emr.service.HisEmrAdjunctService;
import com.wqp.grab.module.emr.service.HisEmrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.alibaba.fastjson.JSONObject.*;

@Controller
@RequestMapping(value="${ctx}/emr")
public class HisEmrController {
	@Autowired
	private HisEmrService hisEmrService;
	@Autowired
	private HisEmrAdjunctService hisEmrAdjunctService;

	private SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 *
	 * @param model 视图
	 * @param roomId 会诊室I
	 * @param userId 用户
	 * @param operateType 操作类型 1:挂号;2:卫生员会诊;3:专家会诊
	 * @param emrId 电子病历编号
	 * @return
	 */
	@RequestMapping(value="index")
	public String index(Model model,String roomId,String userId,String operateType,String emrId) {
		HisEmr hisemr = null;
		switch (operateType){
			case "1"://挂号
				hisemr = new HisEmr();
				hisemr.setHospital("武汉陆军总医院");
				hisemr.setUserId(userId);
				hisemr.setOperateType(1);
				break;
			case "2"://卫生员会诊
				hisemr = hisEmrService.selectByPrimaryKey(emrId);
				if(hisemr.getTherapyTime() != null) hisemr.setTherapyTimeFormat(sdfTime.format(hisemr.getTherapyTime()));
				if(hisemr.getBirth() != null) hisemr.setBirthFormat(sdfDate.format(hisemr.getBirth()));
				hisemr.setRoomId(roomId);
				hisemr.setUserId(userId);
				hisemr.setOperateType(2);
				break;
			case "3"://专家会诊
				hisemr = hisEmrService.selectByPrimaryKey(emrId);
				if(hisemr.getTherapyTime() != null) hisemr.setTherapyTimeFormat(sdfTime.format(hisemr.getTherapyTime()));
				if(hisemr.getBirth() != null) hisemr.setBirthFormat(sdfDate.format(hisemr.getBirth()));
				hisemr.setRoomId(roomId);
				hisemr.setUserId(userId);
				hisemr.setOperateType(3);
				break;
		}

		model.addAttribute("hisemr", hisemr);
		model.addAttribute("hisemrJson", toJSONString(hisemr));
		return "module/emr";
	}

	/**
	 * 添加电子病历数据
	 * @param hospital 医院
	 * @param specialist 科别
	 * @param therapyTimeFormat 会诊时间,字符串
	 * @param emrId 病历号
	 * @param name 姓名
	 * @param gender 性别
	 * @param age 年龄
	 * @param identityId 证件号
	 * @param nation 民族
	 * @param marriage 婚否
	 * @param birthFormat 出生日期
	 * @param address 单位住址
	 * @param occup 职业
	 * @param drugAllergyHistory 药物过敏史
	 * @param symptom 主诉
	 * @param presentIllnessHistory 现病史
	 * @param priviousIllnessHistory 既往史
	 * @param healthCheck 体格检查
	 * @param auxiliaryCheckMedic 卫生员辅助检查
	 * @param diagnoseMedic 卫生员诊断
	 * @param treatmentOptionsMedic 卫生员治疗意见
	 * @param auxiliaryCheckExpert 专家辅助检查
	 * @param diagnoseExpert 专家诊断
	 * @param treatmentOptionsExpert 专家治疗意见
	 * @param signatureMedic 卫生员签名
	 * @param signatureExpert 专家签名
	 * @param accessory 电子病历附件数组集合; adjunct：检查申请单、化验申请单、治疗单、处方单存放编号ID；影像资料存放文件路径
	 *                  			category：1:检查申请单;2:化验申请单;3:治疗单;4:处方单;5:影像资料
	 * @param operateType 操作类型 1:挂号;2:卫生员会诊;3:专家会诊
	 * @return 操作状态,true表示成功,false表示失败
	 */
	@ResponseBody
	@RequestMapping(value = "addEmr")
	public String addEmr(String hospital,String specialist,String therapyTimeFormat,String emrId,String name,String gender,
						 String age,String identityId,String nation,String marriage,String birthFormat,String address,
						 String occup,String drugAllergyHistory,String symptom,String presentIllnessHistory,String priviousIllnessHistory,
						 String healthCheck,String auxiliaryCheckMedic,String diagnoseMedic,String treatmentOptionsMedic,
						 String auxiliaryCheckExpert,String diagnoseExpert,String treatmentOptionsExpert,String signatureMedic,
						 String signatureExpert,String accessory,String operateType) throws ParseException {
		JSONObject jsonObject = new JSONObject();

		if("1".equals(operateType)){//挂号
			if(isEmpty(identityId) || isEmpty(name)){
				jsonObject.put("state",false);
				return jsonObject.toJSONString();
			}
		}else if("2".equals(operateType)){//卫生员会诊
			if(isEmpty(emrId)){
				jsonObject.put("state",false);
				return jsonObject.toJSONString();
			}
		}else if("3".equals(operateType)){//专家会诊
			if(isEmpty(emrId)){
				jsonObject.put("state",false);
				return jsonObject.toJSONString();
			}
		}else {//其它
			jsonObject.put("state",false);
			return jsonObject.toJSONString();
		}

		HisEmrAdjunct hisEmrAdjunct = null;
		HisEmr hisEmr = new HisEmr();
		hisEmr.setEmrId(isEmpty(emrId)?null:emrId);//病历号
		hisEmr.setHospital(isEmpty(hospital)?null:hospital);//医院
		hisEmr.setSpecialistId("12");//科室ID
		hisEmr.setTherapyTime(isEmpty(therapyTimeFormat)?null:sdfTime.parse(therapyTimeFormat));//会诊时间
		hisEmr.setName(isEmpty(name)?null:name);//姓名
		hisEmr.setGender(convertGenderText(gender));//性别
		hisEmr.setAge(isEmpty(age)?null:Integer.parseInt(age));//年龄
		hisEmr.setIdentityId(isEmpty(identityId)?null:identityId);//证件号
		hisEmr.setNation(isEmpty(nation)?null:nation);//民族
		hisEmr.setMarriage(convertMarriageText(marriage));//婚否
		hisEmr.setBirth(isEmpty(birthFormat)?null:sdfDate.parse(birthFormat));//出生日期
		hisEmr.setAddress(isEmpty(address)?null:address);//单位地址
		hisEmr.setOccup(isEmpty(occup)?null:occup);//职业
		hisEmr.setDrugAllergyHistory(isEmpty(drugAllergyHistory)?null:drugAllergyHistory);//药物过敏史
		hisEmr.setSymptom(isEmpty(symptom)?null:symptom);//主诉
		hisEmr.setPresentIllnessHistory(isEmpty(presentIllnessHistory)?null:presentIllnessHistory);//现病史
		hisEmr.setPriviousIllnessHistory(isEmpty(priviousIllnessHistory)?null:priviousIllnessHistory);//既往史
		hisEmr.setHealthCheck(isEmpty(healthCheck)?null:healthCheck);//体格检查
		hisEmr.setAuxiliaryCheckMedic(isEmpty(auxiliaryCheckMedic)?null:auxiliaryCheckMedic);//卫生员辅助检查
		hisEmr.setDiagnoseMedic(isEmpty(diagnoseMedic)?null:diagnoseMedic);//卫生员诊断
		hisEmr.setTreatmentOptionsMedic(isEmpty(treatmentOptionsMedic)?null:treatmentOptionsMedic);//卫生员治疗意见
		hisEmr.setAuxiliaryCheckExpert(isEmpty(auxiliaryCheckExpert)?null:auxiliaryCheckExpert);//专家辅助检查
		hisEmr.setDiagnoseExpert(isEmpty(diagnoseExpert)?null:diagnoseExpert);//专家诊断
		hisEmr.setTreatmentOptionsExpert(isEmpty(treatmentOptionsExpert)?null:treatmentOptionsExpert);//专家治疗意见
		hisEmr.setSignatureMedic(isEmpty(signatureMedic)?null:signatureMedic);//卫生员签名
		hisEmr.setSignatureExpert(isEmpty(signatureExpert)?null:signatureExpert);//专家签名

		if("1".equals(operateType)){//挂号
			hisEmr.setValidity(1);//新建时设置为有效
			//插入操作
			String emr = hisEmrService.insertSelective(hisEmr);
			System.out.println("新增电子病历号:"+emr);
		}else {//卫生员会诊、专家会诊
			//更新操作
			hisEmrService.updateByPrimaryKeySelective(hisEmr);
			List<HisEmrAdjunct> hisEmrAdjuncts = parseAccessory(accessory);
			for (HisEmrAdjunct a:hisEmrAdjuncts) { hisEmrAdjunctService.insertSelective(a); }
		}
		return jsonObject.toJSONString();
	}


	/**
	 * 根据用户证件号[查询]:检查申请单、化验申请单、治疗单、处方单、影像资料数据
	 * @param identityId 患者证件号
	 * @param category 查询方式 1:检查申请单;2:化验申请单;3:治疗单;4:处方单;5:影像资料
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "selectBySickIdentityId")
	public List<Object> selectBySickIdentityId(String identityId,String category){
		List<Object> results = null;
		switch (category){
			case "1"://检查申请单

				break;
			case "2"://化验申请单

				break;
			case "3"://治疗单

				break;
			case "4"://处方单

				break;
			case "5"://影像资料

				break;
			default:
				break;
		}




		return results;
	}


	/**
	 * 解析传入的电子病历附件数据
	 * @param accessory json数组格式的电子病历附件,数组内每个对象为一条独立附件
	 * @return 解析后封装的数据集合
	 */
	private List<HisEmrAdjunct> parseAccessory(String accessory){
		List<HisEmrAdjunct> results = null;
		HisEmrAdjunct had = null;
		if(isEmpty(accessory)) return results;

		results = new ArrayList<HisEmrAdjunct>();
		JSONArray jsonArray = JSONArray.parseArray(accessory);
		for(int i=0;i<jsonArray.size();i++){
			Object o = jsonArray.get(i);
			had = JSONObject.parseObject(o.toString(),HisEmrAdjunct.class);
			had.setAdjunctId(generateAdjunctId());//新创建设置电子病历附件编号
			results.add(had);
		}
		return results;
	}

	/**
	 * 判断是否为空
	 * @param t 待判断数据
	 * @param <T> String ,object
	 * @return true表示为空,否则为false
	 */
	private <T> boolean  isEmpty(T t){
		boolean flag = true;
		if(t instanceof String){
			if(t != null && !"".equals(t) && ((String)t).length() > 0) flag = false;
		}else if(t instanceof Object){
			if(t != null) flag = false;
		}else{
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据结婚类别编号转换成文字
	 * @param marriage 类别;1:未婚;2:已婚;3:离异
	 * @return 转换成功则返回汉字,否则null
	 */
	private String convertMarriageText(String marriage){
		if("1".equals(marriage)){
			marriage = "未婚";
		}else if("2".equals(marriage)){
			marriage = "已婚";
		}else if("3".equals(marriage)){
			marriage = "离异";
		}else {
			marriage = null;
		}
		return marriage;
	}

	/**
	 * 根据性别类别编号转换成文字
	 * @param gender 类别;1:男;2:女
	 * @return 转换成功则返回汉字,否则null
	 */
	private String convertGenderText(String gender){
		if("1".equals(gender)){
			gender = "男";
		}else if("2".equals(gender)){
			gender = "女";
		}else {
			gender = null;
		}
		return gender;
	}

	private String generateAdjunctId() {
		return UUID.randomUUID().toString().replace("-","");
	}
}
