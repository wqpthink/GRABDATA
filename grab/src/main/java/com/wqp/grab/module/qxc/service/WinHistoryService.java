package com.wqp.grab.module.qxc.service;

import com.wqp.grab.module.qxc.dao.WinHistoryMapper;
import com.wqp.grab.module.qxc.entity.WinHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WinHistoryService {
    @Autowired
    private WinHistoryMapper winHistoryMapper;


    public int insert(WinHistory record){
        return winHistoryMapper.insert(record);
    }

    public int insertSelective(WinHistory record){
        return winHistoryMapper.insertSelective(record);
    }

    public List<WinHistory> findByExpress(WinHistory record){
        return winHistoryMapper.findByExpress(record);
    }

}