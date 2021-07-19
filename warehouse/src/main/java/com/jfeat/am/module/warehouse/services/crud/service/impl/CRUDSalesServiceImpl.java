package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Sales;
import com.jfeat.am.module.warehouse.services.persistence.dao.SalesMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDSalesService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDSalesService
 * @author Code Generator
 * @since 2018-11-14
 */

@Service
public class CRUDSalesServiceImpl  extends CRUDServiceOnlyImpl<Sales> implements CRUDSalesService {





        @Resource
        private SalesMapper salesMapper;

        @Override
        protected BaseMapper<Sales> getMasterMapper() {
                return salesMapper;
        }







}


