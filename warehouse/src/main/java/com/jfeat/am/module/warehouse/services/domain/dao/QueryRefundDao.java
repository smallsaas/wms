package com.jfeat.am.module.warehouse.services.domain.dao;

import com.jfeat.am.module.warehouse.services.domain.model.RefundRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-06-22
 */
public interface QueryRefundDao extends BaseMapper<RefundRecord> {
    List<RefundRecord> findRefundPage(Page<RefundRecord> page, @Param("record") RefundRecord record, @Param("orderBy") String orderBy);
}