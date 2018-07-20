package com.jfeat.am.module.sku.services.domain.dao;

import com.jfeat.am.module.sku.services.domain.model.SkuConditionRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-18
 */
public interface QuerySkuConditionDao extends BaseMapper<SkuConditionRecord> {
    List<SkuConditionRecord> findSkuConditionPage(Page<SkuConditionRecord> page, @Param("record") SkuConditionRecord record, @Param("orderBy") String orderBy);
}