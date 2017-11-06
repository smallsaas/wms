package com.jfeat.am.module.statement.services.patch.impl;


import com.jfeat.am.module.equipment.services.domain.dao.EquipmentDao;
import com.jfeat.am.module.equipment.services.persistence.model.Equipment;
import com.jfeat.am.module.equipment.services.service.EquipmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jfeat.am.module.statement.services.patch.PatchService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Code Generator on 2017-11-06
 */
@Service
public class PatchServiceImpl implements PatchService {

    @Resource
    private EquipmentDao equipmentDao;

    @Override
    public List<Map<String, Object>> queryEquipmentStatement(String name,String spec,String code,String departmentName,String status) {
        return equipmentDao.queryEquipmentStatement(name,spec,code,departmentName,status);
    }
}
