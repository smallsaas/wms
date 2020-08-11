package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Suppliers;
import com.jfeat.am.module.warehouse.services.persistence.dao.SuppliersMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDSuppliersService;
import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.crud.plus.QueryMasterDao;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import com.jfeat.crud.plus.model.IdVersions;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDSuppliersService
 * @author Code Generator
 * @since 2018-06-22
 */

@Service
public class CRUDSuppliersServiceImpl  extends CRUDServiceOnlyImpl<Suppliers> implements CRUDSuppliersService {

        @Resource
        private SuppliersMapper suppliersMapper;

        @Override
        protected BaseMapper<Suppliers> getMasterMapper() {
                return suppliersMapper;
        }


}


