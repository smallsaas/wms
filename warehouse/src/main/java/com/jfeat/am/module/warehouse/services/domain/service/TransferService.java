package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDTransferService;
import com.jfeat.am.module.warehouse.services.domain.model.TransferModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface TransferService extends CRUDTransferService{

    @Transactional
    Integer deleteTransfer(long id);

    TransferModel transferDetails(long id);

    @Transactional
    Integer updateTransfer(TransferModel model, long userId);

    @Transactional
    Integer createTransfer(TransferModel model, long userId);
    
}