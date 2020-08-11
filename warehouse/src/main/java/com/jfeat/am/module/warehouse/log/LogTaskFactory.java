package com.jfeat.am.module.warehouse.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class LogTaskFactory {
    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
   // private static OperationLogService operationLogService = (OperationLogService)LogSpringContextHolder.getBean(OperationLogService.class);

    public LogTaskFactory() {
    }

    public static TimerTask businessLog(Long userId, String userName, String businessName, String className, String methodName, String msg, String result) {
        return businessLog(userId, userName, businessName, className, methodName, msg, result, (Long)null, (String)null);
    }

    public static TimerTask businessLog(final Long userId, final String userName, final String businessName, final String className, final String methodName, final String msg, final String result, final Long targetId, final String targetType) {
        return new TimerTask() {
            public void run() {
                //OperationLog operationLog = LogFactory.createOperationLog(LogType.BUSINESS_LOG.toString(), userId, userName, businessName, className, methodName, msg, result, targetId, targetType);

                try {
                    //LogTaskFactory.operationLogService.createMaster(operationLog);
                } catch (Exception var3) {
                    LogTaskFactory.logger.error("创建业务日志异常!", var3);
                }

            }
        };
    }
}
