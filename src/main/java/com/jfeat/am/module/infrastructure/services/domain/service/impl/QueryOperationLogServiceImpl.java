package com.jfeat.am.module.infrastructure.services.domain.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;
import com.jfeat.am.module.infrastructure.services.domain.service.QueryOperationLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Silent-Y on 2017/9/14.
 */
@Service
public class QueryOperationLogServiceImpl implements QueryOperationLogService {

    @Override
    public List<OperationLog> findOperationLogs(Page<OperationLog> page, String logType, String logName, String userId, String className, String method, Date startTime, Date endTime, String succeed) {
        return null;
    }
}
