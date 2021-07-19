package com.jfeat.am.module.warehouse.services.crud.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Warehouse;
import com.jfeat.am.module.warehouse.services.persistence.dao.WarehouseMapper;
import com.jfeat.am.module.warehouse.services.persistence.dao.WarehouseSlotMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.WarehouseSlot;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDWarehouseService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.*;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.jfeat.am.module.warehouse.services.domain.model.WarehouseModel;

import java.util.List;

/**
 * <p>
 * implementation
 * </p>
 * CRUDWarehouseService
 *
 * @author Code Generator
 * @since 2018-06-22
 */

@Service
public class CRUDWarehouseServiceImpl extends CRUDServiceOverModelImpl<Warehouse, WarehouseModel> implements CRUDWarehouseService {


    @Resource
    private WarehouseMapper warehouseMapper;


    @Override
    protected BaseMapper<Warehouse> getMasterMapper() {
        return warehouseMapper;
    }

    @Override
    protected Class<Warehouse> masterClassName() {
        return Warehouse.class;
    }

    @Override
    protected Class<WarehouseModel> modelClassName() {
        return WarehouseModel.class;
    }


    @Resource
    private WarehouseSlotMapper warehouseSlotMapper;

    @Deprecated
    private final static String warehouseSlotFieldName = "warehouse_id";

    private final static String warehouseSlotKeyName = "items";

    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                warehouseSlotKeyName

        };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {
        if (field.compareTo(warehouseSlotKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(warehouseSlotFieldName);
            _field.setItemClassName(WarehouseSlot.class);
            _field.setItemMapper(warehouseSlotMapper);
            return _field;
        }

        throw new BusinessException(BusinessCode.BadRequest);
    }


    @Override
    protected String[] childFieldNames() {
        return new String[]{
        };
    }

    @Override
    protected FIELD onChildFieldItem(String field) {

        throw new BusinessException(BusinessCode.BadRequest);
    }

}


