package com.wqp.grab.module.emr.dao;

import com.wqp.grab.module.common.annotation.MyBatisDao;
import com.wqp.grab.module.emr.entity.HisEmrAdjunct;

import java.util.List;

@MyBatisDao
public interface HisEmrAdjunctMapper {

    int insertSelective(HisEmrAdjunct record);

    List<HisEmrAdjunct> selectBySelective(HisEmrAdjunct hisEmrAdjunct);

    int updateBySelective(HisEmrAdjunct hisEmrAdjunct);
}