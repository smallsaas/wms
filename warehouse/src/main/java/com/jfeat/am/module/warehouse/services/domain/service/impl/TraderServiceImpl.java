package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.jfeat.am.module.warehouse.services.domain.service.TraderService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDTraderServiceImpl;
import org.springframework.stereotype.Service;
import com.jfeat.am.common.constant.tips.Ids;

import javax.annotation.Resource;

import com.jfeat.am.module.warehouse.services.persistence.dao.TraderMapper;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class TraderServiceImpl extends CRUDTraderServiceImpl implements TraderService {
    @Resource
    private TraderMapper traderMapper;

    @Override
    public Integer bulkDelete(Ids ids) {
        Integer success = 0;
        for (Long id : ids.getIds()) {
            success += traderMapper.deleteById(id);
        }
        return success;
    }
}
