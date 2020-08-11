package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDProcurementService;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementModel;
import com.jfeat.am.module.warehouse.services.domain.model.StorageInModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface ProcurementService extends CRUDProcurementService{


    @Transactional
    Integer addProcurement(String username,Long userId, ProcurementModel model);

    @Transactional
    Integer updateProcurement(String username,Long userId, Long procurementId,ProcurementModel model);


    ProcurementModel procurementDetails(Long procurementId);

    @Transactional
    Integer executionStorageIn(String username,Long userId, Long procurementId, ProcurementModel model);

    @Transactional
    Integer deleteProcurement(Long id);

    /**
     *  closed procurement
     * */
    Integer closedProcurement(Long id, ProcurementModel model);

    /**
     *  提交并审核
     * */
    @Transactional
    public Integer updateAndAuditProcurement(Long userId, Long procurementId, ProcurementModel model);


    /**
     *  wait to audit this procurement
     * */
    @Transactional
    Integer auditProcurement(Long id);

    /**
     *  wait to audit passed this procurement
     * */
    @Transactional
    Integer passedProcurement(Long id, ProcurementModel model);

    /*
     * 审核采购的执行入库
     * */
    Integer auditPass(Long id, StorageInModel model);

    Integer executionProcurementStorageIn(Long id);

    /*
     * 审核拒绝采购的执行入库---直接将入库单设置为 关闭状态
     * */
    Integer auditReject(Long id);
}