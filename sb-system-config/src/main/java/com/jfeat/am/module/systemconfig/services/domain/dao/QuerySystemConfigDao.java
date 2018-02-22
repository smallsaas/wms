package com.jfeat.am.module.systemconfig.services.domain.dao;

import com.jfeat.am.module.systemconfig.services.persistence.model.SystemConfig;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-02-22
 */
public interface QuerySystemConfigDao extends BaseMapper<SystemConfig> {
    List<SystemConfig> findSystemConfigPage(Page<SystemConfig> page,@Param("systemconfig") SystemConfig systemconfig,@Param("orderBy") String orderBy);
}