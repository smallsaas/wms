package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDTraderService;
import com.jfeat.am.common.constant.tips.Ids;

/**
 * Created by vincent on 2017/10/19.
 */
public interface TraderService extends CRUDTraderService {
    Integer bulkDelete(Ids ids);
}