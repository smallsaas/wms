package com.jfeat.am.module.warehouse.services.persistence.dao;

import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Code Generator
 * @since 2018-08-29 @Param("record") SkuConditionRecord record, @Param("orderBy") String orderBy
 */
public interface InventoryMapper extends BaseMapper<Inventory> {

    Integer getInventoryCount(@Param("warehouseId") Long warehouseId, @Param("skuId") Long skuId);
}