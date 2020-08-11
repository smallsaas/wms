package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDSuppliersService;
import com.jfeat.am.module.warehouse.services.domain.model.SuppliersModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface SuppliersService extends CRUDSuppliersService{

    /**
     *  1. 修改 供应商 状态
     *  2. 为正常状态的时候，调用次 服务 变为禁止状态
     *  3. 为禁止状态的时候，调用次 服务 变为正常状态
     * */
    @Transactional
    //Integer changeSupplierStatus(long id, User user);
    Integer changeSupplierStatus(long id);


    /**
     * 供应商所供应的所有的货物
     * */

    SuppliersModel allProductsSuppliersProvide(long id);
}