package com.jfeat.am.module.warehouse.services.domain.service;

import com.jfeat.am.module.warehouse.services.crud.service.CRUDTraderService;
import com.jfeat.am.common.constant.tips.Ids;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vincent on 2017/10/19.
 */
public interface TraderService extends CRUDTraderService {
    Integer bulkDelete(Ids ids);


    /**
     *  1. 修改 供应商 状态
     *  2. 为正常状态的时候，调用次 服务 变为禁止状态
     *  3. 为禁止状态的时候，调用次 服务 变为正常状态
     * */
    Integer changeTraderStatus(Long id);
}