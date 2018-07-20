package com.jfeat.am.module.sku.services.domain.dao;

import com.jfeat.am.module.sku.services.domain.model.SkuSpecificationGroupRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-18
 */
public interface QuerySkuSpecificationGroupDao extends BaseMapper<SkuSpecificationGroupRecord> {
    List<SkuSpecificationGroupRecord> findSpecificationGroupPage(Page<SkuSpecificationGroupRecord> page, @Param("record") SkuSpecificationGroupRecord record, @Param("orderBy") String orderBy);
}