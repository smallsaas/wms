package com.jfeat.am.module.log;

import com.jfeat.am.module.log.services.persistence.model.OperationLog;

import java.util.Date;

/**
 * Created by jackyhuang on 2017/11/16.
 */
public class LogFactory {

    public static OperationLog createOperationLog(String logType, Long userId, String userName, String businessName, String clazzName, String methodName, String msg) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogType(logType);
        operationLog.setLogName(businessName);
        operationLog.setUserId(userId);
        operationLog.setUserName(userName);
        operationLog.setClassName(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreateTime(new Date());
        operationLog.setMessage(msg);
        return operationLog;
    }

}

