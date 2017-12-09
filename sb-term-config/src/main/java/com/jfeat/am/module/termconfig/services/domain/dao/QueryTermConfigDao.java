package com.jfeat.am.module.termconfig.services.domain.dao;

import com.jfeat.am.module.termconfig.services.persistence.model.TermConfig;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2017-12-09
 */
public interface QueryTermConfigDao extends BaseMapper<TermConfig> {
    List<TermConfig> findTermConfigPage(Page<TermConfig> page,@Param("termconfig") TermConfig termconfig);
}