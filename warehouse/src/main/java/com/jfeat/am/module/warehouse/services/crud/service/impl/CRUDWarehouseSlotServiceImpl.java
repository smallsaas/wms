package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.WarehouseSlot;
import com.jfeat.am.module.warehouse.services.persistence.dao.WarehouseSlotMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.WarehouseSlotMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.WarehouseSlot;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDWarehouseSlotService;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDWarehouseSlotService
 * @author Code Generator
 * @since 2018-06-22
 */

@Service
public class CRUDWarehouseSlotServiceImpl  extends CRUDServiceSlaveImpl<WarehouseSlot> implements CRUDWarehouseSlotService {

    private static final String masterField = "warehouse_id";

    @Resource
    private WarehouseSlotMapper warehouseSlotMapper;

    @Override
    protected BaseMapper<WarehouseSlot> getSlaveItemMapper() {
        return warehouseSlotMapper;
    }

    @Override
    protected String masterFieldName() {
        if(true){
           throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }

}


