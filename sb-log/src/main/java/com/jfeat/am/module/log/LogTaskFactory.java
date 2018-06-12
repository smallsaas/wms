package com.jfeat.am.module.log;

import com.jfeat.am.core.util.SpringContextHolder;
import com.jfeat.am.module.log.services.crud.service.OperationLogService;
import com.jfeat.am.module.log.services.persistence.model.OperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * Created by jackyhuang on 2017/11/16.
 */
public class LogTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(LogManager.class);

    private static OperationLogService operationLogService = SpringContextHolder.getBean(OperationLogService.class);


    public static TimerTask businessLog(Long userId,
                                        final String userName,
                                        final String businessName,
                                        final String className,
                                        final String methodName,
                                        final String msg,
                                        final String result) {
        return new TimerTask() {
            @Override
            public void run() {
                OperationLog operationLog = LogFactory.createOperationLog(LogType.BUSINESS_LOG.toString(),
                        userId, userName, businessName, className, methodName, msg, result);
                try {
                    operationLogService.createMaster(operationLog);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }

}
