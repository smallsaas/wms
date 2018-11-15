package com.jfeat.am.module.warehouse.services.domain.service.impl;

import com.jfeat.am.module.warehouse.services.domain.service.SalesService;

import com.jfeat.am.module.warehouse.services.crud.service.impl.CRUDSalesServiceImpl;
import org.springframework.stereotype.Service;
import com.jfeat.am.common.constant.tips.Ids;

import javax.annotation.Resource;

import com.jfeat.am.module.warehouse.services.persistence.dao.SalesMapper;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class SalesServiceImpl extends CRUDSalesServiceImpl implements SalesService {
    @Resource
    private SalesMapper salesMapper;

    @Override
    public Integer bulkDelete(Ids ids) {
        Integer success = 0;
        for (Long id : ids.getIds()) {
            success += salesMapper.deleteById(id);
        }
        return success;
    }
}
