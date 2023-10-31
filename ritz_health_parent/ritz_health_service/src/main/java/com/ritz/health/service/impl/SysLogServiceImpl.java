package com.ritz.health.service.impl;

import com.ritz.health.mapper.ISysLogMapper;
import com.ritz.health.pojo.SysLog;
import com.ritz.health.service.ISysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLogServiceImpl implements ISysLogService {

    @Resource
    private ISysLogMapper sysLogMapper;

    @Override
    public void insertSyslog(SysLog sysLog) {
        sysLogMapper.insertSyslog(sysLog);
    }
}
