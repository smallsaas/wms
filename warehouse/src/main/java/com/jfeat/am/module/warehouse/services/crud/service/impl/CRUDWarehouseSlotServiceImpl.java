package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.common.crud.impl.CRUDServiceSlaveImpl;
import com.jfeat.am.module.warehouse.services.persistence.model.WarehouseSlot;
import com.jfeat.am.module.warehouse.services.persistence.dao.WarehouseSlotMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.WarehouseSlotMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.WarehouseSlot;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDWarehouseSlotService;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.am.common.crud.impl.CRUDServiceOnlyImpl;

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


