package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.jfeat.am.common.exception.BusinessCode;
import com.jfeat.am.common.exception.BusinessException;
import com.jfeat.am.common.persistence.model.User;
import com.jfeat.am.core.shiro.ShiroKit;
import com.jfeat.am.module.warehouse.api.permission.SuppliersPermission;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDSuppliersService;
import com.jfeat.am.module.warehouse.services.definition.SupplierStatus;
import com.jfeat.am.module.warehouse.services.domain.dao.QuerySuppliersDao;
import com.jfeat.am.module.warehouse.services.domain.model.SuppliersModel;
import com.jfeat.am.module.warehouse.services.domain.service.SuppliersService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDSuppliersServiceImpl;
import com.jfeat.am.module.warehouse.services.persistence.model.Suppliers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("suppliersService")
public class SuppliersServiceImpl extends CRUDSuppliersServiceImpl implements SuppliersService {



    @Resource
    CRUDSuppliersService crudSuppliersService;
    @Resource
    QuerySuppliersDao querySuppliersDao;
    /**
     *  1. 修改 供应商 状态
     *  2. 为正常状态的时候，调用次 服务 变为禁止状态
     *  3. 为禁止状态的时候，调用次 服务 变为正常状态
     * */
    @Transactional
    public Integer changeSupplierStatus(long id, User user){
        Suppliers suppliers = crudSuppliersService.retrieveMaster(id);
        if (ShiroKit.hasPermission(SuppliersPermission.SUPPLIERS_EDIT)){
            if (suppliers.getSupplierStatus().compareTo(SupplierStatus.Forbidden.toString())==0){
                suppliers.setSupplierStatus(SupplierStatus.Normal.toString());
            };
            suppliers.setSupplierStatus(SupplierStatus.Forbidden.toString());
            return crudSuppliersService.updateMaster(suppliers);
        }
        throw new BusinessException(BusinessCode.NoPermission);
    }

    /**
     * 供应商所供应的所有的货物
     * */

    public SuppliersModel allProductsSuppliersProvide(long id){
        return querySuppliersDao.supplierProducts(id);
    }
}
