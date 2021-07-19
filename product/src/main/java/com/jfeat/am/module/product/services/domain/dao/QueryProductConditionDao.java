package com.jfeat.am.module.product.services.domain.dao;

import com.jfeat.am.module.product.services.domain.model.ProductConditionRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-19
 */
public interface QueryProductConditionDao extends BaseMapper<ProductConditionRecord> {
    List<ProductConditionRecord> findProductConditionPage(Page<ProductConditionRecord> page, @Param("record") ProductConditionRecord record, @Param("orderBy") String orderBy);
}