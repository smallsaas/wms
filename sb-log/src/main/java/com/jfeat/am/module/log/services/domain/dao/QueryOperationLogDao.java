package com.jfeat.am.module.log.services.domain.dao;

import com.jfeat.am.module.log.services.crud.persistence.model.OperationLog;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryOperationLogDao  extends BaseMapper<OperationLog> {
    /**
     * 可查询属于员工客户
     * @return
     */
    List<OperationLog> findOperationLogs(Page<OperationLog> page,
                                         @Param("logType") String logType,
                                         @Param("logName") String logName,
                                         @Param("userId") String userId,
                                         @Param("className")String className,
                                         @Param("method") String method,
                                         @Param("createTime") String createTime);
}