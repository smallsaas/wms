package com.jfeat.am.module.organization.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.domain.model.DepartmentItem;
import com.jfeat.am.module.organization.services.persistence.model.Department;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryDepartmentService {

    List<DepartmentItem> findDepartmentPage(Department department);

    List<Map<String, Object>> showDepartmentDetail(Page<Map<String, Object>> page, Long id, String isManager);

    List<Department> queryDepartmentByCode(String code);

}