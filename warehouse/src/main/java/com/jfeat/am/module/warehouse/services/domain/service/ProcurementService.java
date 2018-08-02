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
    Integer createProcurement(Long userId, ProcurementModel model);

    ProcurementModel procurementDetails(long id);

    @Transactional
    Integer updateProcurement(long userId, ProcurementModel model);

    @Transactional
    Integer deleteProcurement(long id);
}