package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Inventory;
import com.jfeat.am.module.warehouse.services.persistence.dao.InventoryMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDInventoryService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDInventoryService
 * @author Code Generator
 * @since 2018-06-23
 */

@Service
public class CRUDInventoryServiceImpl  extends CRUDServiceOnlyImpl<Inventory> implements CRUDInventoryService {





        @Resource
        private InventoryMapper inventoryMapper;

        @Override
        protected BaseMapper<Inventory> getMasterMapper() {
                return inventoryMapper;
        }







}


