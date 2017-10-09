package com.jfeat.am.module.vip.services.domain.dao;

import com.jfeat.am.module.${cfg.en}.services.crud.persistence.model.OperationLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryOperationLogDao  extends BaseMapper<OperationLog> {

    List<OperationLog> findOperationLogs(
            @Param("status") String status);

}