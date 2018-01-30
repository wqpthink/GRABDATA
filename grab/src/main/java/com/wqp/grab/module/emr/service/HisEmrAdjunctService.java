package com.wqp.grab.module.emr.service;

import com.wqp.grab.module.emr.dao.HisEmrAdjunctMapper;
import com.wqp.grab.module.emr.entity.HisEmrAdjunct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HisEmrAdjunctService {
    @Autowired
    private HisEmrAdjunctMapper hisEmrAdjunctMapper;

    public int insertSelective(HisEmrAdjunct record){
        return hisEmrAdjunctMapper.insertSelective(record);
    }

    List<HisEmrAdjunct> selectBySelective(HisEmrAdjunct hisEmrAdjunct){
        return hisEmrAdjunctMapper.selectBySelective(hisEmrAdjunct);
    }

    int updateBySelective(HisEmrAdjunct hisEmrAdjunct){
        return hisEmrAdjunctMapper.updateBySelective(hisEmrAdjunct);
    }
}
