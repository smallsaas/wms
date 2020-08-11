package com.jfeat.am.module.warehouse.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.warehouse.services.persistence.model.Transfer;
import com.jfeat.am.module.warehouse.services.persistence.dao.TransferMapper;


import com.jfeat.am.module.warehouse.services.crud.service.CRUDTransferService;
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


