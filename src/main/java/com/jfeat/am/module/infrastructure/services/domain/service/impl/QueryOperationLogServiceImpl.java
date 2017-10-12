package com.jfeat.am.module.infrastructure.services.domain.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.core.support.StrKit;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;
import com.jfeat.am.module.infrastructure.services.domain.dao.QueryOperationLogDao;
import com.jfeat.am.module.infrastructure.services.domain.service.QueryOperationLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Silent-Y on 2017/9/14.
 */
@Service
public class QueryOperationLogServiceImpl implements QueryOperationLogService {

    @Resource
    private QueryOperationLogDao queryOperationLogDao;

    @Override
    public List<OperationLog> findOperationLogs(Page<OperationLog> page, String logType, String logName, String userId, String className, String method, String startTime, String endTime, String succeed) {
        EntityWrapper entityWrapper = new EntityWrapper<OperationLog>();
        if (!StrKit.isBlank(logType)){
            entityWrapper.eq(OperationLog.LOG_TYPE,logType);
        }
        if (!StrKit.isBlank(logName)){
            entityWrapper.eq(OperationLog.LOG_NAME,logName);
        }
        if (!StrKit.isBlank(userId)){
            entityWrapper.eq(OperationLog.USER_ID,userId);
        }
        if (!StrKit.isBlank(className)){
            entityWrapper.eq(OperationLog.CLASS_NAME,className);
        }
        if (!StrKit.isBlank(method)){
            entityWrapper.eq(OperationLog.METHOD,method);
        }
        if (startTime != null && endTime != null){
            entityWrapper.between(OperationLog.CREATE_TIME, startTime, endTime);
        }
        if (!StrKit.isBlank(succeed)){
            entityWrapper.eq(OperationLog.SUCCEED,succeed);
        }
        return queryOperationLogDao.selectList(page,entityWrapper);
    }
}
