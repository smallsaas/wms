package com.jfeat.am.module.warehouse.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.domain.model.TraderRecord;
import com.jfeat.am.module.warehouse.services.persistence.model.Trader;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2018-11-14
 */
public interface QueryTraderDao extends BaseMapper<TraderRecord> {
    List<TraderRecord> findTraderPage(Page<TraderRecord> page, @Param("record") TraderRecord record, @Param("orderBy") String orderBy);

    TraderRecord traderDetails(@Param("traderId")Long traderId);

}