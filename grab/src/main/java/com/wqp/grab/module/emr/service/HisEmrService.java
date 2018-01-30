package com.wqp.grab.module.emr.service;

import com.wqp.grab.module.emr.dao.HisEmrMapper;
import com.wqp.grab.module.emr.entity.HisEmr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class HisEmrService {
    @Autowired
    private HisEmrMapper hiscEmrMapper;

    public int deleteByPrimaryKey(String emrId){
    	return hiscEmrMapper.deleteByPrimaryKey(emrId);
    }

    public String insertSelective(HisEmr record){
    	return hiscEmrMapper.insertSelective(record);
    }

    public HisEmr selectByPrimaryKey(String emrId){
    	return hiscEmrMapper.selectByPrimaryKey(emrId);
    }

    public int updateByPrimaryKeySelective(HisEmr record){
    	return hiscEmrMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(HisEmr record){
    	return hiscEmrMapper.updateByPrimaryKey(record);
    }
}