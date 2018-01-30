package com.wqp.grab.module.emr.dao;

import com.wqp.grab.module.common.annotation.MyBatisDao;
import com.wqp.grab.module.emr.entity.HisEmr;

@MyBatisDao
public interface HisEmrMapper {

	int deleteByPrimaryKey(String emrId);

	String insertSelective(HisEmr record);

	HisEmr selectByPrimaryKey(String emrId);

	int updateByPrimaryKeySelective(HisEmr record);

	int updateByPrimaryKey(HisEmr record);
}