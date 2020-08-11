package com.jfeat.am.module.product.services.domain.dao;

import com.jfeat.am.module.product.services.domain.model.ConditionRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-19
 */
public interface QueryConditionDao extends BaseMapper<ConditionRecord> {
    List<ConditionRecord> findConditionPage(Page<ConditionRecord> page, @Param("record") ConditionRecord record, @Param("orderBy") String orderBy);
}