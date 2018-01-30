package com.wqp.grab.module.qxc.service;

import com.wqp.grab.module.qxc.dao.QiXingCaiMapper;
import com.wqp.grab.module.qxc.entity.QiXingCai;
import com.wqp.grab.module.qxc.service.QiXingCaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QiXingCaiService {
    @Autowired
    private QiXingCaiMapper qiXingCaiMapper;

    public int insert(QiXingCai record) {
        return qiXingCaiMapper.insert(record);
    }

    public int insertSelective(QiXingCai record) {
        return qiXingCaiMapper.insertSelective(record);
    }

    public List<QiXingCai> findAll() {
        return qiXingCaiMapper.findAll();
    }

    public int statisticsByrFrequency(QiXingCai record){
        return qiXingCaiMapper.statisticsByrFrequency(record);
    }
}