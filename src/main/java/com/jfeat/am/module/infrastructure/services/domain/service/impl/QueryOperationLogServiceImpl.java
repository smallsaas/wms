package com.jfeat.am.module.infrastructure.services.domain.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
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
    public List<OperationLog> findOperationLogs(Page<OperationLog> page, String logType, String logName, String userId, String className, String method, Date startTime, Date endTime, String succeed) {
        EntityWrapper entityWrapper = new EntityWrapper<OperationLog>();
        if (logType != null){
            entityWrapper.eq("log_type",logType);
        }
        if (logName != null){
            entityWrapper.eq("log_name",logName);
        }
        if (userId != null){
            entityWrapper.eq("user_id",userId);
        }
        if (className != null){
            entityWrapper.eq("class_name",className);
        }
        if (method != null){
            entityWrapper.eq("method",method);
        }
        if (startTime != null && endTime != null){
            entityWrapper.between("create_time", startTime, endTime);
        }
        if (succeed != null){
            entityWrapper.eq("succeed",succeed);
        }
        return queryOperationLogDao.selectList(page,entityWrapper);
    }
}
