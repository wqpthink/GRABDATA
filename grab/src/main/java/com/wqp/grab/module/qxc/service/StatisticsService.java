package com.wqp.grab.module.qxc.service;

import com.wqp.grab.module.qxc.dao.StatisticsMapper;
import com.wqp.grab.module.qxc.entity.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;

    public int insert(Statistics record){
        return statisticsMapper.insert(record);
    }

    public int insertSelective(Statistics record){
        return statisticsMapper.insertSelective(record);
    }

    public List<Statistics> findByExpress(Statistics record){
        return statisticsMapper.findByExpress(record);
    }

}
