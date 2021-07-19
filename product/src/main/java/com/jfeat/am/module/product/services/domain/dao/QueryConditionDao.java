package com.jfeat.am.module.product.services.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.product.services.domain.model.ConditionRecord;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-19
 */
public interface QueryConditionDao extends BaseMapper<ConditionRecord> {
    List<ConditionRecord> findConditionPage(Page<ConditionRecord> page, @Param("record") ConditionRecord record, @Param("orderBy") String orderBy);
}