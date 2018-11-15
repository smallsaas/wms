package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDSalesService;
import com.jfeat.am.common.constant.tips.Ids;

/**
 * Created by vincent on 2017/10/19.
 */
public interface SalesService extends CRUDSalesService {
    Integer bulkDelete(Ids ids);
}