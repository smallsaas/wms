package com.jfeat.am.module.organization.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.organization.services.persistence.model.Department;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface QueryDepartmentService {
    List<Department> findDepartmentPage(Department department);
}