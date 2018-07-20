package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.module.warehouse.services.persistence.model.Procurement;
import com.jfeat.am.module.warehouse.services.persistence.dao.ProcurementMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDProcurementService;
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
 *CRUDProcurementService
 * @author Code Generator
 * @since 2018-06-22
 */

@Service
public class CRUDProcurementServiceImpl  extends CRUDServiceOnlyImpl<Procurement> implements CRUDProcurementService {





        @Resource
        private ProcurementMapper procurementMapper;

        @Override
        protected BaseMapper<Procurement> getMasterMapper() {
                return procurementMapper;
        }







}


