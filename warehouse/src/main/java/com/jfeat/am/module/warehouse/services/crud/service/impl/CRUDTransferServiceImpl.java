package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.common.crud.FIELD;
import com.jfeat.am.module.warehouse.services.persistence.model.Transfer;
import com.jfeat.am.module.warehouse.services.persistence.dao.TransferMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDTransferService;
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
 *CRUDTransferService
 * @author Code Generator
 * @since 2018-06-22
 */

@Service
public class CRUDTransferServiceImpl  extends CRUDServiceOnlyImpl<Transfer> implements CRUDTransferService {





        @Resource
        private TransferMapper transferMapper;

        @Override
        protected BaseMapper<Transfer> getMasterMapper() {
                return transferMapper;
        }







}


