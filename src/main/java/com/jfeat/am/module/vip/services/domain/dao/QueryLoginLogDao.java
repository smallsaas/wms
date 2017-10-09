package com.jfeat.am.module.vip.services.domain.dao;

import com.jfeat.am.module.${cfg.en}.services.crud.persistence.model.LoginLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryLoginLogDao  extends BaseMapper<LoginLog> {

    List<LoginLog> findLoginLogs(
            @Param("status") String status);

}