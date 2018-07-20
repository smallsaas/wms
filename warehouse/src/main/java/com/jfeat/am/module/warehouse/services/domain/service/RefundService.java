package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDRefundService;
import com.jfeat.am.module.warehouse.services.domain.model.RefundModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RefundService extends CRUDRefundService{

    @Transactional
    Integer createRefund(long userId, RefundModel model);

    RefundModel refundDetails(long id);

    @Transactional
    Integer updateRefund(long userId, RefundModel model);

    @Transactional
    Integer deleteRefund(long id);
    
}