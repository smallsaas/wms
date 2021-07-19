package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDSalesService;
import com.jfeat.am.module.warehouse.services.domain.model.SalesDetails;
import com.jfeat.am.module.warehouse.services.domain.model.SalesModel;
import com.jfeat.crud.base.request.Ids;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface SalesService extends CRUDSalesService {
    Integer bulkDelete(Ids ids);



    Integer createSales(Long userId, SalesModel model);

    Integer updateSales(Long userId, Long salesId, SalesModel model);

    Integer executionStorageOut(Long userId, Long salesId, SalesModel model);

    SalesDetails salesDetails(Long salesId);

    Integer auditPass(Long salesId, SalesModel model);

    Integer deleteSales(Long id);

    @Transactional
    Integer updateAndCommitSales(Long userId, Long salesId, SalesModel model);


}