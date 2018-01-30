package com.wqp.grab.module.qxc.service;

import com.wqp.grab.module.qxc.dao.RecommendMapper;
import com.wqp.grab.module.qxc.entity.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecommendService {
    @Autowired
    private RecommendMapper recommendMapper;

    public int insert(Recommend record){
        return recommendMapper.insert(record);
    }

    public int insertSelective(Recommend record){
        return recommendMapper.insertSelective(record);
    }
}
