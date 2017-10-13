package com.jfeat.am.module.infrastructure.services.domain.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jfeat.am.module.infrastructure.services.crud.persistence.model.OperationLog;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryOperationLogDao  extends BaseMapper<OperationLog> {

    List<OperationLog> findOperationLogs(Page<OperationLog> page,
            @Param("status") String status);

    List<OperationLog> selectList(Page<OperationLog> page,@Param("ew") Wrapper<OperationLog> var1);


}