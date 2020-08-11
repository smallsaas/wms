package com.jfeat.am.module.product.services.domain.dao;

import com.jfeat.am.module.product.services.domain.model.SpecificationGroupRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-19
 */
public interface QuerySpecificationGroupDao extends BaseMapper<SpecificationGroupRecord> {
    List<SpecificationGroupRecord> findSpecificationGroupPage(Page<SpecificationGroupRecord> page, @Param("record") SpecificationGroupRecord record, @Param("orderBy") String orderBy);
}