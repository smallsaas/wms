package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDRefundService;
import com.jfeat.am.module.warehouse.services.domain.model.RefundModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RefundService extends CRUDRefundService{

    @Transactional
    Integer executionRefund(String username,Long userId, Long refundId);

    /**
     * 重构 Refund 问题
     */
    @Transactional
    Integer createRefund(Long userId, RefundModel model);

    RefundModel refundDetails(Long id);

    @Transactional
    Integer updateRefund(Long refundId, RefundModel model);

    @Transactional
    Integer updateAndCommitRefund(Long refundId, RefundModel model);

    @Transactional
    Integer auditPassed(Long id,String username,Long userId);

    @Transactional
    Integer deleteRefund(Long id);
    
}