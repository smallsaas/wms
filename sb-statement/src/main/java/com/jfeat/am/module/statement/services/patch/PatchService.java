package com.jfeat.am.module.statement.services.patch;

import java.util.List;
import java.util.Map;

/**
 * Created by Code Generator on 2017-11-06
 */
public interface PatchService {

    public List<Map<String,Object>> queryEquipmentStatement(String name,String spec,String code,String departmentName,String status);

}

