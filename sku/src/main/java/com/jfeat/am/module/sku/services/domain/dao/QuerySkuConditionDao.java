package com.jfeat.am.module.sku.services.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.jfeat.am.module.sku.services.domain.model.SkuConditionRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-18
 */
public interface QuerySkuConditionDao extends BaseMapper<SkuConditionRecord> {
    List<SkuConditionRecord> findSkuConditionPage(Page<SkuConditionRecord> page, @Param("record") SkuConditionRecord record, @Param("orderBy") String orderBy);
}