package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.jfeat.am.common.crud.CRUDObject;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryStorageInDao;
import com.jfeat.am.module.warehouse.services.domain.dao.QueryStorageOutDao;
import com.jfeat.am.module.warehouse.services.domain.service.WarehouseService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDWarehouseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("warehouseService")
public class WarehouseServiceImpl extends CRUDWarehouseServiceImpl implements WarehouseService {

    @Resource
    QueryStorageInDao queryStorageInDao;
    @Resource
    QueryStorageOutDao queryStorageOutDao;

    public List<CRUDObject> inAndOutList(){

        // TODO needs more details
        return null;
    }
}
