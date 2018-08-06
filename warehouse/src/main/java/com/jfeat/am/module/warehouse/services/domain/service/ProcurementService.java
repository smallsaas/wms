package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.common.persistence.model.User;
import com.jfeat.am.module.warehouse.services.crud.service.CRUDProcurementService;
import com.jfeat.am.module.warehouse.services.domain.model.ProcurementModel;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface ProcurementService extends CRUDProcurementService{


    @Transactional
    Integer addProcurement(Long userId, ProcurementModel model);

    @Transactional
    Integer updateProcurement(Long userId, Long procurementId,ProcurementModel model);


    ProcurementModel procurementDetails(Long id);

    @Transactional
    Integer executionStorageIn(Long userId, Long procurementId, ProcurementModel model);

    @Transactional
    Integer deleteProcurement(Long id);
}