package com.jfeat.am.module.sku.services.domain.dao;

import com.jfeat.am.module.sku.services.domain.model.SkuSpecificationRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-18
 */
public interface QuerySkuSpecificationDao extends BaseMapper<SkuSpecificationRecord> {
    List<SkuSpecificationRecord> findSkuSpecificationPage(Page<SkuSpecificationRecord> page, @Param("record") SkuSpecificationRecord record, @Param("orderBy") String orderBy);
}