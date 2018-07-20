package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.CheckRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-23
 */
public interface QueryCheckDao extends BaseMapper<CheckRecord> {
    List<CheckRecord> findCheckPage(Page<CheckRecord> page, @Param("record") CheckRecord record, @Param("orderBy") String orderBy);
}