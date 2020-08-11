package com.jfeat.am.module.product.services.domain.dao;

import com.jfeat.am.module.product.services.domain.model.SpecificationRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-19
 */
public interface QuerySpecificationDao extends BaseMapper<SpecificationRecord> {
    List<SpecificationRecord> findSpecificationPage(Page<SpecificationRecord> page, @Param("record") SpecificationRecord record, @Param("orderBy") String orderBy);
}