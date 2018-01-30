package com.wqp.grab.module.emr.entity;
import java.io.Serializable;
import java.util.Date;


public class HisEmr implements Serializable{
	/**
	 * 电子病历id
	 */
	private String emrId;
	/**
	 * 证件id
	 */
	private String identityId = null;
	/**
	 * 科室id
	 */
	private String specialistId = null;
	/**
	 * 科室名称
	 */
	private String specialist = null;
	/**
	 * 医院id
	 */
	private String hospitalId;
	/**
	 * 医院
	 */
	private String hospital = null;
	/**
	 * 治疗时间
	 */
	private Date therapyTime = null;

	/**
	 * 治疗时间格式化
	 */
	private String therapyTimeFormat = null;
	/**
	 * 名称
	 */
	private String name = null;
	/**
	 * 性别
	 */
	private String gender = null;
	/**
	 * 年龄
	 */
	private Integer age = null;
	/**
	 * 民族
	 */
	private String nation = null;
	/**
	 * 婚否
	 */
	private String marriage = null;

	/**
	 * 出生日期
	 */

	private Date birth = null;
	/**
	 * 出生日期格式化
	 */
	private String birthFormat = null;
	/**
	 * 单位地址
	 */
	private String address = null;
	/**
	 * 职业
	 */
	private String occup = null;
	/**
	 * 药物过敏史
	 */
	private String drugAllergyHistory;
	/**
	 * 主诉
	 */
	private String symptom;
	/**
	 * 现病史
	 */
	private String presentIllnessHistory;
	/**
	 * 既往史
	 */
	private String priviousIllnessHistory;
	/**
	 * 体格检查
	 */
	private String healthCheck;
	/**
	 * 卫生员辅助治疗
	 */
	private String auxiliaryCheckMedic;
	/**
	 * 卫生员诊断
	 */
	private String diagnoseMedic;
	/**
	 * 卫生员治疗意见
	 */
	private String treatmentOptionsMedic;

	/**
	 * 专家辅助治疗
	 */
	private String auxiliaryCheckExpert;
	/**
	 * 专家诊断
	 */
	private String diagnoseExpert;
	/**
	 * 专家治疗意见
	 */
	private String treatmentOptionsExpert;

	/**
	 * 卫生员签名
	 */
	private String signatureMedic;
	/**
	 * 专家签名
	 */
	private String signatureExpert;
	/**
	 * 房间ID
	 */
	private String roomId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户类型
	 * 1:挂号;2:卫生员会诊;3:专家会诊
	 */
	private int operateType;

	/**
	 * 电子病历有效性
	 * @return 1:有效；2:无效
	 */
	private Integer validity;

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public String getEmrId() {
		return emrId;
	}

	public void setEmrId(String emrId) {
		this.emrId = emrId;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getSpecialistId() {
		return specialistId;
	}

	public void setSpecialistId(String specialistId) {
		this.specialistId = specialistId;
	}

	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public Date getTherapyTime() {
		return therapyTime;
	}

	public void setTherapyTime(Date therapyTime) {
		this.therapyTime = therapyTime;
	}

	public String getTherapyTimeFormat() {
		return therapyTimeFormat;
	}

	public void setTherapyTimeFormat(String therapyTimeFormat) {
		this.therapyTimeFormat = therapyTimeFormat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getBirthFormat() {
		return birthFormat;
	}

	public void setBirthFormat(String birthFormat) {
		this.birthFormat = birthFormat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOccup() {
		return occup;
	}

	public void setOccup(String occup) {
		this.occup = occup;
	}

	public String getDrugAllergyHistory() {
		return drugAllergyHistory;
	}

	public void setDrugAllergyHistory(String drugAllergyHistory) {
		this.drugAllergyHistory = drugAllergyHistory;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getPresentIllnessHistory() {
		return presentIllnessHistory;
	}

	public void setPresentIllnessHistory(String presentIllnessHistory) {
		this.presentIllnessHistory = presentIllnessHistory;
	}

	public String getPriviousIllnessHistory() {
		return priviousIllnessHistory;
	}

	public void setPriviousIllnessHistory(String priviousIllnessHistory) {
		this.priviousIllnessHistory = priviousIllnessHistory;
	}

	public String getHealthCheck() {
		return healthCheck;
	}

	public void setHealthCheck(String healthCheck) {
		this.healthCheck = healthCheck;
	}

	public String getAuxiliaryCheckMedic() {
		return auxiliaryCheckMedic;
	}

	public void setAuxiliaryCheckMedic(String auxiliaryCheckMedic) {
		this.auxiliaryCheckMedic = auxiliaryCheckMedic;
	}

	public String getDiagnoseMedic() {
		return diagnoseMedic;
	}

	public void setDiagnoseMedic(String diagnoseMedic) {
		this.diagnoseMedic = diagnoseMedic;
	}

	public String getTreatmentOptionsMedic() {
		return treatmentOptionsMedic;
	}

	public void setTreatmentOptionsMedic(String treatmentOptionsMedic) {
		this.treatmentOptionsMedic = treatmentOptionsMedic;
	}

	public String getAuxiliaryCheckExpert() {
		return auxiliaryCheckExpert;
	}

	public void setAuxiliaryCheckExpert(String auxiliaryCheckExpert) {
		this.auxiliaryCheckExpert = auxiliaryCheckExpert;
	}

	public String getDiagnoseExpert() {
		return diagnoseExpert;
	}

	public void setDiagnoseExpert(String diagnoseExpert) {
		this.diagnoseExpert = diagnoseExpert;
	}

	public String getTreatmentOptionsExpert() {
		return treatmentOptionsExpert;
	}

	public void setTreatmentOptionsExpert(String treatmentOptionsExpert) {
		this.treatmentOptionsExpert = treatmentOptionsExpert;
	}

	public String getSignatureMedic() {
		return signatureMedic;
	}

	public void setSignatureMedic(String signatureMedic) {
		this.signatureMedic = signatureMedic;
	}

	public String getSignatureExpert() {
		return signatureExpert;
	}

	public void setSignatureExpert(String signatureExpert) {
		this.signatureExpert = signatureExpert;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
}