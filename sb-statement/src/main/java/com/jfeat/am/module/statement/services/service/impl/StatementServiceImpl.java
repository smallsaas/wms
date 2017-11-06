package com.jfeat.am.module.statement.services.service.impl;

import com.jfeat.am.module.equipment.services.domain.dao.EquipmentDao;
import com.jfeat.am.module.statement.services.service.StatementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Silent-Y on 2017/11/6.
 */
@Service
public class StatementServiceImpl implements StatementService {

    @Resource
    private EquipmentDao equipmentDao;

    @Override
    public int queryEquipmentCountByStatus(String status) {
        return equipmentDao.queryEquipmentCountByStatus(status);
    }
}
