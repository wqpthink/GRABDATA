package com.wqp.grab.module.qxc.service;

import com.wqp.grab.module.qxc.dao.DynamicMapper;
import com.wqp.grab.module.qxc.entity.Dynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DynamicService {
    @Autowired
    private DynamicMapper dynamicMapper;

    public int insert(Dynamic record){
        return dynamicMapper.insert(record);
    }


    public int insertSelective(Dynamic record){
        return dynamicMapper.insertSelective(record);
    }
}
